package br.edu.cs.poo.ac.seguro.testes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.cs.poo.ac.seguro.daos.ApoliceDAO;
import br.edu.cs.poo.ac.seguro.daos.SeguradoEmpresaDAO;
import br.edu.cs.poo.ac.seguro.entidades.Apolice;

public class TesteApoliceDAO extends TesteDAO{
	private ApoliceDAO dao = new ApoliceDAO();
	protected Class getClasse() {
		return Apolice.class;
	}
	
	
}
