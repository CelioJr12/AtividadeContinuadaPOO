package br.edu.cs.poo.ac.seguro.testes;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.cs.poo.ac.seguro.daos.ApoliceDAO;
import br.edu.cs.poo.ac.seguro.entidades.Apolice;

public class TesteApoliceDAO extends TesteDAO{
	private ApoliceDAO dao = new ApoliceDAO();
	@SuppressWarnings({ "rawtypes" })
	protected Class getClasse() {
		return Apolice.class;
	}
	
	@Test
	public void teste01() {
		String numero="00000000";
		cadastro.incluir(new Apolice(numero, null, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, LocalDate.now()), numero);
		Apolice ap = dao.buscar(numero);
		Assertions.assertNotNull(ap);
	}
	@Test
	public void teste02() {
		String numero="10000000";
		cadastro.incluir(new Apolice(numero, null, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, LocalDate.now()), numero);
		Apolice ap = dao.buscar("20000000");
		Assertions.assertNotNull(ap);
	}
	@Test
	public void test03() {
		String numero="2000000";
		cadastro.alterar(new Apolice(numero, null, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, LocalDate.now()), numero);
		boolean ret =dao.excluir(numero);
		Assertions.assertTrue(ret);
	}
	@Test
	public void test04() {
		String numero="3000000";
		cadastro.alterar(new Apolice(numero, null, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, LocalDate.now()), numero);
		boolean ret =dao.excluir("310000000");
		Assertions.assertTrue(ret);
	}
	@Test
	public void test05() {
		String numero = "40000000";		
		boolean ret = dao.incluir(new Apolice(numero, null, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, LocalDate.now()));		
		Assertions.assertTrue(ret);
		Apolice ap = dao.buscar(numero);
		Assertions.assertNotNull(ap);	
	}
	@Test
	public void test06() {
		String numero = "50000000";		
		Apolice ap = new Apolice(numero, null, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, LocalDate.now());
		cadastro.incluir(ap, numero);
		boolean ret = dao.incluir(ap);
		Assertions.assertFalse(ret);
	}
	@Test
	public void test07() {
		String numero = "60000000";			
		boolean ret = dao.alterar(new Apolice(numero, null, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, LocalDate.now()));		
		Assertions.assertFalse(ret);
		Apolice ve = dao.buscar(numero);
		Assertions.assertNull(ve);
	}
	@Test
	public void test08() {
		String numero = "70000000";			
		Apolice ap = new Apolice(numero, null, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, LocalDate.now());
		cadastro.incluir(ap, numero);
		ap = new Apolice(numero, null, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, LocalDate.now());
		boolean ret = dao.alterar(ap);
		Assertions.assertTrue(ret);
	}
	
}
