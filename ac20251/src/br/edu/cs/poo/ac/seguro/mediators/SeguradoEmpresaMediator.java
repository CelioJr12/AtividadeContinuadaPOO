package br.edu.cs.poo.ac.seguro.mediators;

import br.edu.cs.poo.ac.seguro.daos.SeguradoEmpresaDAO;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoEmpresa;

public class SeguradoEmpresaMediator {
	private SeguradoMediator seguradoMediator = SeguradoMediator.getInstancia();
	private SeguradoEmpresaDAO seguradoEmpresaDAO = new SeguradoEmpresaDAO();

	private static final SeguradoEmpresaMediator instancia = new SeguradoEmpresaMediator();

	public static SeguradoEmpresaMediator getInstancia() {
		return instancia;
	}

	public String validarCnpj(String cnpj) {
		if (StringUtils.ehNuloOuBranco(cnpj)) {
			return "CNPJ não pode ser vazio.";
		}
		if (!StringUtils.temSomenteNumeros(cnpj)) {
			return "CNPJ deve conter apenas números.";
		}
		if (cnpj.length() != 14) {
			return "CNPJ deve conter exatamente 14 dígitos.";
		}
		if (!ValidadorCpfCnpj.ehCnpjValido(cnpj)) {
			return "CNPJ inválido.";
		}
		return null;
	}

	public String validarFaturamento(double faturamento) {
		if (faturamento < 0) {
			return "Faturamento não pode ser negativo.";
		}
		return null;
	}

	public String incluirSeguradoEmpresa(SeguradoEmpresa seg) {
		String msg = validarSeguradoEmpresa(seg);
		if (msg != null) {
			return msg;
		}
		if (seguradoEmpresaDAO.incluir(seg)) {
			return null;
		} else {
			return "Segurado com este CNPJ já existe.";
		}
	}

	public String alterarSeguradoEmpresa(SeguradoEmpresa seg) {
		String msg = validarSeguradoEmpresa(seg);
		if (msg != null) {
			return msg;
		}
		if (seguradoEmpresaDAO.alterar(seg)) {
			return null;
		} else {
			return "Segurado não encontrado para alteração.";
		}
	}

	public String excluirSeguradoEmpresa(String cnpj) {
		String msg = validarCnpj(cnpj);
		if (msg != null) {
			return msg;
		}
		if (!seguradoEmpresaDAO.excluir(cnpj)) {
			return "Segurado não encontrado para exclusão.";
		}
		return null;
	}

	public SeguradoEmpresa buscarSeguradoEmpresa(String cnpj) {
		String msg = validarCnpj(cnpj);
		if (msg != null) {
			return null;
		}
		return seguradoEmpresaDAO.buscar(cnpj);
	}

	public String validarSeguradoEmpresa(SeguradoEmpresa seg) {
		if (seg == null) {
			return "Segurado não pode ser nulo.";
		}

		String msg = seguradoMediator.validarNome(seg.getNome());
		if (msg != null) return msg;

		msg = seguradoMediator.validarEndereco(seg.getEndereco());
		if (msg != null) return msg;

		msg = seguradoMediator.validarDataCriacao(seg.getDataAbertura());
		if (msg != null) return msg;

		msg = validarCnpj(seg.getCnpj());
		if (msg != null) return msg;

		msg = validarFaturamento(seg.getFaturamento());
		if (msg != null) return msg;

		return null;
	}
}
