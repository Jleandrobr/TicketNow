package modelo;

import java.util.ArrayList;

public class Time { 
	private String nome;
	private String origem;
	private ArrayList<Jogo> jogos = new ArrayList<Jogo>();
	

	public Time(String nome, String origem) {
		super();
		this.nome = nome;
		this.origem = origem;
		//this.jogos = jogos;
	}

	public String getOrigem() {
		return origem;
	}

	public double obterValorArrecadado() {
		double total = 0;
		for(Jogo j : jogos) {
			total += j.obterValorArrecadado();
		}
		return total;
		//O valor arrecadado de um jogo é a soma dos valores de
		//seus ingressos e o valor arrecadado de um time é a soma dos valores de seus jogos.
	}
	
	public void adicionar(Jogo j) {
		jogos.add(j);
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return "Time [nome=" + nome + ", origem=" + origem + "]";
	}

	
	

}
