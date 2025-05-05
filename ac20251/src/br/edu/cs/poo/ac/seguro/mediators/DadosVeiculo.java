package br.edu.cs.poo.ac.seguro.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.edu.cs.poo.ac.seguro.entidades.CategoriaVeiculo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosVeiculo {
    private String placa;
    private CategoriaVeiculo categoria;
    private int anoFabricacao;
    private BigDecimal valor;
    private String cpfOuCnpj;
    private boolean indicadorLocadora;
    private LocalDate inicioVigencia;
}
