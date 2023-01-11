package modelo;


public abstract class Ingresso {
	private int codigo;



	public Ingresso(int codigo) {
		super();
		this.codigo = codigo;
	}
	
	
	public double calcularValor() {
		//abstrato
//		double total = 0;
//		for(Jogo j : jogos) {
//			total+= 0.9 * j.getPreco();
//		}
//		return total;
	}

	public int getCodigo() {
		return codigo;
	}


	@Override
	public String toString() {
		return "Ingresso [codigo=" + codigo + "]";
	}
	
	

}
