package br.edu.cs.poo.ac.seguro.entidades;

import java.io.Serializable;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;

@Getter
@Setter
@RequiredArgsConstructor


public class Apolice implements Serializable {
	private static final long serialVersionUID = 1L;
	@NonNull
    private String numero;
    @NonNull
    private Veiculo veiculo;
    @NonNull
    private BigDecimal valorFranquia;
    @NonNull
    private BigDecimal valorPremio;
    @NonNull
    private BigDecimal valorMaximoSegurado;
    @NonNull
    private LocalDate dataInicioVigencia;

}
