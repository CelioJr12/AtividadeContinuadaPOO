package br.edu.cs.poo.ac.seguro.entidades;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Setter 
@Getter
@AllArgsConstructor

public class Veiculo {
	private String placa;
	private int ano;
	private SeguradoEmpresa proprietarioEmpresa;
	private SeguradoPessoa proprietarioPessoa;
	private CategoriaVeiculo categoria;
}
