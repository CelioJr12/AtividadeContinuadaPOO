package br.edu.cs.poo.ac.seguro.mediators;

import br.edu.cs.poo.ac.seguro.daos.SeguradoEmpresaDAO;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoEmpresa;

public class SeguradoEmpresaMediator {
	SeguradoMediator seguradoMediator;
	SeguradoEmpresaDAO seguradoEmpresaDAO;
	
	private static final SeguradoEmpresaMediator instancia = new SeguradoEmpresaMediator();
	
	public static SeguradoEmpresaMediator getInstancia() {
		return instancia;
	}
	public String validarCnpj(String cnpj) {
		
		if (StringUtils.ehNuloOuBranco(cnpj)) {
	        return "cpnj não pode ser vazio.";
	    }
	    if (!StringUtils.temSomenteNumeros(cnpj)) {
	        return "cnpj deve conter apenas números.";
	    }
	    
		return null;
	}
	public String validarFaturamento(double faturamento) {
		return null;
	}
	public String incluirSeguradoEmpresa(SeguradoEmpresa seg) {
		return null;
	}
	public String alterarSeguradoEmpresa(SeguradoEmpresa seg) {
		return null;
	}
	public String excluirSeguradoEmpresa(String cnpj) {
		return null;
	}
	public SeguradoEmpresa buscarSeguradoEmpresa(String cnpj) {
		return null;
	}
	public String validarSeguradoEmpresa(SeguradoEmpresa seg) {
		return null;
	}
}