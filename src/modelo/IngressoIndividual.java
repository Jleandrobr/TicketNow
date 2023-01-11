package modelo;

public class IngressoIndividual extends Ingresso {
	private Jogo jogo;

	
	public IngressoIndividual(int codigo) {
		super(codigo);
		//this.jogo = jogo;
	}
	
	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public double calcularValor() {
		double total = 0;
		total = 1.2 * jogo.getPreco();
		return total;
		//O valor arrecadado de um jogo é a soma dos valores de
		//seus ingressos e o valor arrecadado de um time é a soma dos valores de seus jogos.
	}

	public Jogo getJogo() {
		return jogo;
	}

}
