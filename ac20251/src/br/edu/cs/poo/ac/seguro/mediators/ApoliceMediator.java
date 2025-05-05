package br.edu.cs.poo.ac.seguro.mediators;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.edu.cs.poo.ac.seguro.daos.ApoliceDAO;
import br.edu.cs.poo.ac.seguro.daos.SeguradoEmpresaDAO;
import br.edu.cs.poo.ac.seguro.daos.SeguradoPessoaDAO;
import br.edu.cs.poo.ac.seguro.daos.SinistroDAO;
import br.edu.cs.poo.ac.seguro.daos.VeiculoDAO;
import br.edu.cs.poo.ac.seguro.entidades.Apolice;
import br.edu.cs.poo.ac.seguro.entidades.CategoriaVeiculo;
import br.edu.cs.poo.ac.seguro.entidades.PrecoAno;
import br.edu.cs.poo.ac.seguro.entidades.Segurado;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoEmpresa;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoPessoa;
import br.edu.cs.poo.ac.seguro.entidades.Sinistro;
import br.edu.cs.poo.ac.seguro.entidades.Veiculo;

public class ApoliceMediator {
	private SeguradoPessoaDAO daoSegPes;
	private SeguradoEmpresaDAO daoSegEmp;
	private VeiculoDAO daoVel;
	private ApoliceDAO daoApo;
	private SinistroDAO daoSin;

	private ApoliceMediator() {}
	
	public RetornoInclusaoApolice incluirApolice(DadosVeiculo dados) {

	    String mensagemErro = validarTodosDadosVeiculo(dados);
	    if (mensagemErro != null) {
	        return new RetornoInclusaoApolice(null, mensagemErro);
	    }


	    Veiculo veiculo = daoVel.buscar(dados.getPlaca());
	    if (veiculo == null) {
	        veiculo = new Veiculo(dados.getPlaca(), dados.getAno(), null, null, null); 
	        daoVel.incluir(veiculo);
	    }

	    Segurado segurado = null;
	    if (dados.getCpfOuCnpj().length() == 11) { 
	        segurado = daoSegPes.buscar(dados.getCpfOuCnpj());
	    } else if (dados.getCpfOuCnpj().length() == 14) { 
	        segurado = daoSegEmp.buscar(dados.getCpfOuCnpj());
	    }

	    if (segurado == null) {
	        return new RetornoInclusaoApolice(null, "Segurado não encontrado.");
	    }

	    
	    if (segurado instanceof SeguradoPessoa) {
	        veiculo.setProprietarioPessoa((SeguradoPessoa) segurado);
	    } else if (segurado instanceof SeguradoEmpresa) {
	        veiculo.setProprietarioEmpresa((SeguradoEmpresa) segurado);
	    }

	    
	    String numeroApolice = gerarNumeroApolice(dados.getCpfOuCnpj(), dados.getPlaca());

	    
	    BigDecimal valorPremio = calcularPremio(dados, segurado);
	    BigDecimal valorFranquia = calcularFranquia(valorPremio);

	    
	    Apolice apolice = new Apolice(veiculo, valorFranquia, valorPremio, dados.getValorMaximoSegurado(), LocalDate.now());

	    
	    boolean sucesso = daoApo.incluir(apolice);
	    if (!sucesso) {
	        return new RetornoInclusaoApolice(null, "Erro ao incluir apólice.");
	    }

	    
	    bonificarSegurado(segurado, apolice);

	    return new RetornoInclusaoApolice(apolice.getNumero(), null);
	}

	private String gerarNumeroApolice(String cpfOuCnpj, String placa) {
	    int anoAtual = LocalDate.now().getYear();
	    if (cpfOuCnpj.length() == 11) {
	        return anoAtual + "000" + cpfOuCnpj + placa;
	    } else {
	        return anoAtual + cpfOuCnpj + placa;
	    }
	}

	private BigDecimal calcularPremio(DadosVeiculo dados, Segurado segurado) {
	    BigDecimal vpa = dados.getValorMaximoSegurado().multiply(new BigDecimal("0.03"));
	    BigDecimal vpb = (segurado instanceof SeguradoEmpresa && ((SeguradoEmpresa) segurado).isEhLocadoraDeVeiculos())
	                    ? vpa.multiply(new BigDecimal("1.2"))
	                    : vpa;
	    BigDecimal vpc = vpb.subtract(segurado.getBonus().divide(new BigDecimal("10")));
	    return vpc.compareTo(BigDecimal.ZERO) > 0 ? vpc : BigDecimal.ZERO;
	}

	private BigDecimal calcularFranquia(BigDecimal valorPremio) {
	    return valorPremio.multiply(new BigDecimal("1.3"));
	}

	private void bonificarSegurado(Segurado segurado, Apolice apolice) {
	    Sinistro[] sinistros = daoSin.buscarTodos();
	    for (Sinistro sinistro : sinistros) {
	        if (sinistro.getVeiculo().equals(apolice.getVeiculo()) && sinistro.getDataHoraSinistro().getYear() == apolice.getDataInicioVigencia().getYear() - 1) {
	            return;
	        }
	    }

	
	    BigDecimal bonus = segurado.getBonus().add(apolice.getValorPremio().multiply(new BigDecimal("0.30")));
	    segurado.creditarBonus(bonus);
	    if (segurado instanceof SeguradoPessoa) {
	        daoSegPes.alterar((SeguradoPessoa) segurado);
	    } else if (segurado instanceof SeguradoEmpresa) {
	        daoSegEmp.alterar((SeguradoEmpresa) segurado);
	    }
	}

	private String validarTodosDadosVeiculo(DadosVeiculo dados) {
	    if (dados == null) {
	        return "Dados do veículo não podem ser nulos.";
	    }
	    if (dados.getPlaca() == null || dados.getPlaca().isEmpty()) {
	        return "Placa do veículo não pode ser vazia.";
	    }
	    if (dados.getValorMaximoSegurado() == null || dados.getValorMaximoSegurado().compareTo(BigDecimal.ZERO) <= 0) {
	        return "Valor máximo segurado inválido.";
	    }
	    return null;
	}

	
	
	public Apolice buscarApolice(String numero) {
		if (numero == null || numero.trim().isEmpty()) {
			return null;
		}
		return daoApo.buscar(numero);
	}
	public String excluirApolice(String numero) {
	    if (StringUtils.ehNuloOuBranco(numero)) {
	        return "Exclusão não permitida";
	    }

	    Apolice apolice = buscarApolice(numero);
	    if (apolice == null) {
	        return "Não existe Apólice";
	    }

	    Veiculo veiculoApolice = apolice.getVeiculo();
	    int anoVigencia = apolice.getDataInicioVigencia().getYear();

	    Sinistro[] todosSinistros = daoSin.buscarTodos();
	    if (todosSinistros != null) {
	        for (Sinistro sinistro : todosSinistros) {
	            int anoSinistro = sinistro.getDataHoraSinistro().getYear();
	            if (anoSinistro == anoVigencia && sinistro.getVeiculo().equals(veiculoApolice)) {
	                return "Exclusão não permitida";
	            }
	        }
	    }

	    boolean excluido = daoApo.excluir(numero);
	    return excluido ? null : "Exclusão não permitida";
	}

}