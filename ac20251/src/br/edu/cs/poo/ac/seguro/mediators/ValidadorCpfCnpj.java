package br.edu.cs.poo.ac.seguro.mediators;

public class ValidadorCpfCnpj {
	public static boolean ehCnpjValido(String cnpj) {
		int cont = 0;
		int resultado13 = 0;
		int resultado14 = 0;
		int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

		if (cnpj.length() == 14) {
			for (int i = 0; i < 14; i++) {
				char n = cnpj.charAt(i);
				if (i < 12) {
					int numero13 = Character.getNumericValue(n) * pesos1[i];
					int numero14 = Character.getNumericValue(n) * pesos2[i];
					resultado13 = resultado13 + numero13;
					resultado14 = resultado14 + numero14;
				} else if (i == 12) {
					int numero13 = Character.getNumericValue(n);
					int validacao = resultado13 % 11;
					if (validacao < 2) {
						if (numero13 == 0) {
							cont++;
						} else {
							return false;
						}
					} else {
						if (numero13 == 11 - validacao) {
							cont++;
						} else {
							return false;
						}
					}
					int numero14 = Character.getNumericValue(n) * pesos2[i];
					resultado14 = resultado14 + numero14;
				} else {
					int numero14 = Character.getNumericValue(n);
					int validacao2 = resultado14 % 11;
					if (cont > 0) {
						if (validacao2 < 2) {
							if (numero14 == 0) {
								return true;
							} else {
								return false;
							}
						} else {
							if (numero14 == 11 - validacao2) {
								return true;
							} else {
								return false;
							}
						}
					}
				}
			}
		} else {
			return false;
		}

		return false;
	}

	public static boolean ehCpfValido(String cpf) {
		int contador=1;
		int resultado11 = 0;
		int resultado12 = 0;
		int[] pesos1 = {10,9,8,7,6,5,4,3,2};
		int[] pesos2 = {11,10,9,8,7,6,5,4,3,2};
		for (int i = 0; i < 12; i++) {
			char n = cpf.charAt(i);
			if(i<9) {
				int numero11 = Character.getNumericValue(n) * pesos1[i];
				int numero12 = Character.getNumericValue(n) * pesos2[i];
				resultado11= resultado11 + numero11;
				resultado12= resultado12 + numero12;
			}else if(i==10) {
				int numero12 = Character.getNumericValue(n) * pesos2[i];
				resultado12= resultado12 + numero12;
				int validacao2 = (resultado11*10)%11;
				if(validacao2==10 || validacao2==11) {
					if(numero12==0) {
						contador++;
					}else {
						return false;
					}
				}else {
					return false;
				}
			}else {
				int validacao3 =11-((resultado11*10)%11);
				int numero12 = Character.getNumericValue(n);
				if(validacao3==numero12) {
					if(contador>1) {
						return true;
					}else {
						return false;
					}
				}else {
					return false;
				}
			}
		}
		return false; 
	}
}
