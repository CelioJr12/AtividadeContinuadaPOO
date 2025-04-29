package br.edu.cs.poo.ac.seguro.mediators;

import br.edu.cs.poo.ac.seguro.daos.SeguradoPessoaDAO;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoPessoa;

public class SeguradoPessoaMediator {
	SeguradoMediator seguradoMediator;
	SeguradoPessoaDAO seguradoPessoaDAO;
	
	private static final SeguradoPessoaMediator instancia = new SeguradoPessoaMediator();
	
	public static SeguradoPessoaMediator getInstancia() {
        return instancia;
    }
	public String validarCpf(String cpf) {
		if (StringUtils.ehNuloOuBranco(cpf)) {
            return "CPF não pode ser vazio.";
        }
        if (!StringUtils.temSomenteNumeros(cpf)) {
            return "CPF deve conter apenas números.";
        }
        if (cpf.length() != 11) {
            return "CPF deve conter exatamente 11 dígitos.";
        }
        if (!ValidadorCpfCnpj.ehCpfValido(cpf)) {
            return "CPF inválido";
        }
        return null;
        
    }
	public String validarRenda(double renda) {
		if (renda < 0) {
            return "Renda não pode ser negativa.";
        }
        return null;
	}
	public String incluirSeguradoPessoa(SeguradoPessoa seg) {
		String msg = validarSeguradoPessoa(seg);
		if (msg != null) {
		    return msg;
		}
		if (seguradoPessoaDAO.incluir(seg)) {
		    return null;
		} else {
		    return "Segurado com este CPF já existe.";
		}
	}
	public String alterarSeguradoPessoa(SeguradoPessoa seg) {
		String msg = validarSeguradoPessoa(seg);
        if (msg != null) {
            return msg;
        }

        if (seguradoPessoaDAO.alterar(seg)) {
		    return null;
		} else {
		    return "Segurado com este CPF já existe.";
		}
	}
	public String excluirSeguradoPessoa(String cpf) {
		String erro = validarCpf(cpf);
		if (erro != null) {
		    return erro;
		}

		if (!seguradoPessoaDAO.excluir(cpf)) {
		    return "Segurado não encontrado para exclusão.";
		}

		return null;
	}
	public SeguradoPessoa buscarSeguradoPessoa(String cpf) {
		String msg = validarCpf(cpf);
        if (msg != null) {
            return null;
        }
        return seguradoPessoaDAO.buscar(cpf);
	}
	public String validarSeguradoPessoa(SeguradoPessoa seg) {
		if (seg == null) {
            return "Segurado não pode ser nulo.";
        }

        String msg;

        msg = seguradoMediator.validarNome(seg.getNome());
        if (msg != null) {
        	return msg;
        } 

        msg = seguradoMediator.validarEndereco(seg.getEndereco());
        if (msg != null) {
        	return msg;
        }

        msg = seguradoMediator.validarDataCriacao(seg.getDataNascimento());
        if (msg != null) {
        	return msg;
        }

        msg = validarCpf(seg.getCpf());
        if (msg != null) {
        	return msg;
        }

        msg = validarRenda(seg.getRenda());
        if (msg != null) {
        	return msg;
        }
        return null;
	}
}