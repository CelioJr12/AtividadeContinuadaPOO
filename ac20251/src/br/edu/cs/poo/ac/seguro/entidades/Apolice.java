package br.edu.cs.poo.ac.seguro.entidades;
import java.math.BigDecimal;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Setter 
@Getter
@AllArgsConstructor

public class Apolice {
	private Veiculo veiculo;
	private BigDecimal valorFranquia;
	private BigDecimal valorPremio;
	private BigDecimal valorMaximoSegurado;
}
