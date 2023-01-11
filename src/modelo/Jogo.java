package modelo;

import java.util.ArrayList;

public class Jogo {
	private int id;
	private String data;
	private String local;
	private int estoque;
	private double preco;
	private Time time1;
	private Time time2;
	ArrayList<Ingresso> ingressos = new ArrayList<Ingresso>();
	
	
	
	public Jogo(int id, String data, String local, int estoque, double preco, Time time1, Time time2) {
		super();
		this.id = id;
		this.data = data;
		this.local = local;
		this.estoque = estoque;
		this.preco = preco;
		this.time1 = time1;
		this.time2 = time2;
		//this.ingressos = ingressos;
	}


	public String getData() {
		return data;
	}

	public int getEstoque() {
		return estoque;
	}

	public String getLocal() {
		return local;
	}

	public double getPreco() {
		return preco;
	}

	public Time getTime1() {
		return time1;
	}

	public Time getTime2() {
		return time2;
	}

	public double obterValorArrecadado() {
		double total = 0;
		for(Ingresso i : ingressos) {
			total += i.calcularValor();
		}
		return total;
		
		//O valor arrecadado de um jogo é a soma dos valores de
		//seus ingressos e o valor arrecadado de um time é a soma dos valores de seus jogos.
	}
	
	public void adicionar(Ingresso i){
		ingressos.add(i);
	}
	
	public void remover(Ingresso i) {
		ingressos.remove(i);
		}

	public void setTime1(Time time1) {
		this.time1 = time1;
	}

	public void setTime2(Time time2) {
		this.time2 = time2;
	}

	public int getId() {
		return id;
	}


	@Override
	public String toString() {
		return "Jogo [id=" + id + ", data=" + data + ", local=" + local + ", estoque=" + estoque + ", preco=" + preco
				+ ", time1=" + time1 + ", time2=" + time2 + ", ingressos=" + ingressos + "]";
	}




	public ArrayList<Ingresso> getIngressos() {
		return ingressos;
	}



	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}



	
}
