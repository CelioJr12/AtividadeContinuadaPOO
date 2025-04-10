package br.edu.cs.poo.ac.seguro.testes;

import static org.junit.Assert.*;
import org.junit.Test;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import br.edu.cs.poo.ac.seguro.daos.SinistroDAO;
import br.edu.cs.poo.ac.seguro.entidades.Sinistro;
import br.edu.cs.poo.ac.seguro.entidades.Veiculo;
import br.edu.cs.poo.ac.seguro.entidades.TipoSinistro;
import br.edu.cs.poo.ac.seguro.entidades.CategoriaVeiculo;

public class TesteSinistroDAO {

    private SinistroDAO dao = new SinistroDAO();
    private Veiculo veiculoExemplo = new Veiculo("ABC1234", 2020, null, null, CategoriaVeiculo.BASICO);
    private Sinistro sinistroExemplo = new Sinistro(veiculoExemplo, LocalDateTime.now(), LocalDateTime.now(), "usuarioTeste", new BigDecimal("1000.00"), TipoSinistro.COLISAO);

    @Test
    public void teste01() {
        dao.excluir(sinistroExemplo.getNumero());
        boolean resultado = dao.incluir(sinistroExemplo);
        assertTrue(resultado);
        Sinistro buscado = dao.buscar(sinistroExemplo.getNumero());
        assertNotNull(buscado);
    }

    @Test
    public void teste02() {
        dao.excluir(sinistroExemplo.getNumero());
        dao.incluir(sinistroExemplo);
        boolean resultado = dao.incluir(sinistroExemplo);
        assertFalse(resultado);
    }

    @Test
    public void teste03() {
        dao.excluir(sinistroExemplo.getNumero());
        dao.incluir(sinistroExemplo);
        sinistroExemplo.setValorSinistro(new BigDecimal("2000.00"));
        boolean resultado = dao.alterar(sinistroExemplo);
        assertTrue(resultado);
        Sinistro buscado = dao.buscar(sinistroExemplo.getNumero());
        assertEquals(new BigDecimal("2000.00"), buscado.getValorSinistro());
    }

    @Test
    public void teste04() {
        dao.excluir(sinistroExemplo.getNumero());
        dao.incluir(sinistroExemplo);
        boolean resultado = dao.excluir(sinistroExemplo.getNumero());
        assertTrue(resultado);
        Sinistro buscado = dao.buscar(sinistroExemplo.getNumero());
        assertNull(buscado);
    }
}
