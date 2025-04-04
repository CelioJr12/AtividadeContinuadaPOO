package br.edu.cs.poo.ac.seguro.entidades;
import java.time.LocalDateTime;
import java.math.BigDecimal;

import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Setter 
@Getter
@AllArgsConstructor

public class Sinistro {
	private String numero;
	private Veiculo veiculo;
	private LocalDateTime dataHoraSinistro;
	private LocalDateTime dataHoraRegistro;
	private String usuarioRegistro;
	private BigDecimal valorSinistro;
	private TipoSinistro tipo;
}
