package br.edu.cs.poo.ac.seguro.mediators;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.edu.cs.poo.ac.seguro.entidades.Endereco;

public class SeguradoMediator {

    private static final SeguradoMediator instancia = new SeguradoMediator();

    private SeguradoMediator() {}

    public static SeguradoMediator getInstancia() {
        return instancia;
    }

    public String validarNome(String nome) {
        if (StringUtils.ehNuloOuBranco(nome)) {
            return "Nome não pode ser vazio.";
        }
        if (nome.length() < 2 || nome.length() > 100) {
            return "Nome deve ter entre 2 e 100 caracteres.";
        }
        if (!nome.matches("[A-Za-zÀ-ÖØ-öø-ÿ\\s]+")) {
            return "Nome contém caracteres inválidos.";
        }
        if(StringUtils.temSomenteNumeros(nome)) {
        	return "Nome não pode ter somente números no nome";
        }
        if (nome.matches(".*\\d.*")) {
            return "Nome não pode conter números.";
        }
        return null;
    }

    public String validarEndereco(Endereco endereco) {
        if (endereco == null) {
            return "Endereço não pode ser nulo.";
        }
        
        if(StringUtils.ehNuloOuBranco(endereco.getCep())) {
        	return "Cep não pode ser vazio";
        }
        if (endereco.getCep().length() != 8) {
            return "CEP deve conter exatamente 8 dígitos numéricos.";
        }
        
        if (!StringUtils.temSomenteNumeros(endereco.getCep())) {
            return "Tem que ser somente dígitos númericos";
        }
        
        if (!StringUtils.temSomenteNumeros(endereco.getNumero())) {
            return "Tem que ser somente dígitos númericos";
        }
        if (StringUtils.ehNuloOuBranco(endereco.getPais())) {
            return "País não pode ser vazio.";
        }
        if(StringUtils.temSomenteNumeros(endereco.getPais())) {
        	return "País não pode ter somente números";
        }
        
        if (StringUtils.ehNuloOuBranco(endereco.getLogradouro())) {
            return "Rua não pode ser vazia.";
        }
        if(StringUtils.temSomenteNumeros(endereco.getLogradouro())) {
        	return "Rua não pode ter somente números";
        }
        
        if (StringUtils.ehNuloOuBranco(endereco.getCidade())) {
            return "Cidade não pode ser vazia.";
        }
        if(StringUtils.temSomenteNumeros(endereco.getCidade())) {
        	return "Cidade não pode conter somente números";
        }
        
        if (StringUtils.ehNuloOuBranco(endereco.getEstado())) {
            return "Estado não pode ser vazio.";
        }
        if(StringUtils.temSomenteNumeros(endereco.getEstado())) {
        	return "Estado não possui somente números";
        }
        if (endereco.getEstado().matches(".*\\d.*")) {
            return "Nome não pode conter números.";
        }
        return null;
    }

    public String validarDataCriacao(LocalDate dataCriacao) {
        if (dataCriacao == null) {
            return "Data de criação não pode ser nula.";
        }

        if (dataCriacao.isAfter(LocalDate.now())) {
            return "Data de criação não pode ser no futuro.";
        }

        return null;
    }

    public BigDecimal ajustarDebitoBonus(BigDecimal bonus, BigDecimal valorDebito) {
        if (bonus == null || valorDebito == null) {
            return BigDecimal.ZERO;
        }

        BigDecimal resultado = valorDebito.subtract(bonus);

        if (resultado.compareTo(BigDecimal.ZERO) < 0) {
            return BigDecimal.ZERO;
        }
        return resultado;
    }

}
