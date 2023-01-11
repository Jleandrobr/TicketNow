package modelo;

import java.util.ArrayList;

public class IngressoGrupo extends Ingresso {
	ArrayList<Jogo> jogos = new ArrayList<Jogo>();
	

	public IngressoGrupo(int codigo) {
		super(codigo);
		//this.jogos = jogos;
	}

	public void adicionar(Jogo j) {
		jogos.add(j);
	}
	
	public void remover(Jogo j) {
		jogos.remove(j);
		}
	
	public double calcularValor() {
		double total = 0;
		for(Jogo j : jogos) {
			total+= 0.9 * j.getPreco();
		}
		return total;
		//O valor arrecadado de um jogo é a soma dos valores de
		//seus ingressos e o valor arrecadado de um time é a soma dos valores de seus jogos.
	}

	
	public ArrayList<Jogo> getJogos() {
		return jogos;
	}
	

}
