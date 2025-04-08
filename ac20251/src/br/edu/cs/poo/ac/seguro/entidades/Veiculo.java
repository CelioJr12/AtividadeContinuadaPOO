package br.edu.cs.poo.ac.seguro.entidades;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;

import java.io.Serializable;
@Setter 
@Getter
@AllArgsConstructor

public class Veiculo implements Serializable{
    private static final long serialVersionUID = 1L;
	private String placa;
	private int ano;
	private SeguradoEmpresa proprietarioEmpresa;
	private SeguradoPessoa proprietarioPessoa;
	private CategoriaVeiculo categoria;
}
