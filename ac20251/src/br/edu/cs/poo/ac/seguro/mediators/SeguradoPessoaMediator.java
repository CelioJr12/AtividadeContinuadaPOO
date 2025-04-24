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
	        return "cpf não pode ser vazio.";
	    }
	    if (!StringUtils.temSomenteNumeros(cpf)) {
	        return "cpf deve conter apenas números.";
	    }
		
		return null;
	}
	public String validarRenda(double renda) {
		return null;
	}
	public String incluirSeguradoPessoa(SeguradoPessoa seg) {
		return msg;
	}
	public String alterarSeguradoPessoa(SeguradoPessoa seg) {
		return null;
	}
	public String excluirSeguradoPessoa(String cpf) {
		return null;
	}
	public SeguradoPessoa buscarSeguradoPessoa(String cpf) {
		return null;
	}
	public String validarSeguradoPessoa(SeguradoPessoa seg) {
		return null;
	}
}