package regras_negocio;
/**********************************
 * Projeto2 de POO (2022.2)
 * 
 * José Leandro Brasileiro 
 * 
 * 
 **********************************/

//import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

import modelo.Ingresso;
import modelo.IngressoGrupo;
import modelo.IngressoIndividual;
import modelo.Jogo;
import modelo.Time;
import repositorio.Repositorio;

public class Fachada {
	private static Repositorio repositorio = new Repositorio();	
	private Fachada() {}	

	
	public static ArrayList<Time> listarTimes() {
		//retorna todos os times do reposit�rio
		return repositorio.getTimes();
	}
	public static ArrayList<Jogo> listarJogos() {
		return repositorio.getJogos();
		//retorna todos os jogos do reposit�rio
	}
	
	public static ArrayList<Ingresso> listarIngressos() {
		return repositorio.getIngressos();
		//retorna todos os ingressos do reposit�rio 
	}
	public static ArrayList<Jogo> listarJogos(String data) {
		ArrayList<Jogo> jogosPorData = new ArrayList<>();
		for (Jogo j: repositorio.getJogos()){
			if(j.getData().equals(data)){
				jogosPorData.add(j);
			}
		}
		return jogosPorData;
		//retorna os jogos do reposit�rio na data fornecida
	}
		

	
	public static Ingresso localizarIngresso(int codigo) {
		return repositorio.localizarIngresso(codigo);
		//retorna o ingresso do reposit�rio com o c�digo fornecido
		//...
	}
	
	public static Jogo	localizarJogo(int id) {
		return repositorio.localizarJogo(id);
		//retorna o jogo do reposit�rio com o id fornecido
		//...
	}

	public static Time 	criarTime(String nome, String origem) throws Exception {
		nome = nome.trim();
		origem = origem.trim();
		
		//Exce��o: nome existente no repositorio
		Time t = repositorio.localizarTime(nome);
		if(t!= null)
			throw new Exception("Time" + nome + "já está cadastrado(a)");
		
		//criar o time
		t = new Time(nome, origem);
		
		//adicionar este time no reposit�rio
		repositorio.adicionar(t);
		
		//salvar o repositorio em arquivo
		repositorio.salvar();
		return t;
	
	}

	public static Jogo 	criarJogo(String data, String local, int estoque, double preco, String nometime1, String nometime2)  throws Exception {
		data = data.trim();
		local = local.trim();
		nometime1 = nometime1.trim();
		nometime2 = nometime2.trim();
		
		//Exce��o: nometime1 ou nometime2 inexistente no repositorio
		Time t = repositorio.localizarTime(nometime1);
		if (t == null)
			throw new Exception("Time"+ ""+ nometime1 +""+ "não está cadastrado(a)");
		
		Time t2 = repositorio.localizarTime(nometime2);
		if (t2 == null)
			throw new Exception("Time" + ""+ nometime2 +""+ "não está cadastrado(a)");
		
		
		//  local ou data vazios, estoque ou pre�o menor ou igual a zero
//		Jogo j1 = repositorio.localizarJogo(data);
		
		//gerar id sequencial do jogo 
		int id = repositorio.gerarId();

		
		//criar o jogo, 
		Jogo j1 = new Jogo(id, data, local, estoque, preco, t, t2);
		
		
		if(j1.getData() == null)
			throw new Exception("Data não está cadastrada");
		
		if(j1.getLocal() == " ")
			throw new Exception("Local não está cadastrado");
		
		if(j1.getEstoque() <= 0)
			throw new Exception("Estoque vazio");
		
		if(j1.getPreco() <= 0)
			throw new Exception("Preco inexistente");	
			
		//relacionar o jogo com os dois times 
		j1.setTime1(t);
		j1.setTime2(t2);
		
		//adicionar este jogo no reposit�rio
		repositorio.adicionar(j1);
		
		//salvar o repositorio em arquivo
		repositorio.salvar();
		
		return j1;
	}
	
	public static void 	apagarJogo(int id) throws Exception{
		//Exce��o: id inexistente no repositorio
		Jogo j = repositorio.localizarJogo(id);
		if(j == null)
			throw new Exception("Não deletou evento: " + id + " inexistente");
		
		//remover o jogo do reposit�rio
		repositorio.remover(j);
		//salvar o repositorio em arquivo
		repositorio.salvar();
	}

	public static IngressoIndividual criarIngressoIndividual(int id) throws Exception{
		//Exce��o: id inexistente no repositorio
		Jogo j = repositorio.localizarJogo(id);
		if(j == null) {
			throw new Exception("Id inexistente");
		}
			
		
		//gerar codigo aleat�rio e verificar unicididade do codigo no jogo indicado
		//criar o ingresso individual
		Random sorteio = new Random();
		IngressoIndividual i = new IngressoIndividual(sorteio.nextInt(1000000));
		i.setJogo(j);
		
		//relacionar este ingresso com o jogo indicado
		j.adicionar(i);
		j.setEstoque(j.getEstoque()-1);
		
		//adicionar este ingresso no reposit�rio
		repositorio.adicionar(i);
		//salvar o repositorio em arquivo
		repositorio.salvar();
		
		return i;
	}
	
	public static IngressoGrupo	criarIngressoGrupo(int[] id) throws Exception{
		//Exce��o: id inexistente no repositorio
		ArrayList<Jogo> jogosIndicados = new ArrayList<>(); //arraylist para os jogos indicados pelo usuario
//		ArrayList<Integer> idIngressosJogos = new ArrayList<>();
		ArrayList<Integer> codJogos = new ArrayList<>(); // arraylist para os codigos de cada jogo
		
		//gerar codigo aleat�rio e verificar unicididade do codigo nos jogos indicados
		//criar o ingresso de grupo,
		Random sorteio = new Random();
		IngressoGrupo ingresso = new IngressoGrupo(sorteio.nextInt(1000000));
		
		//criacao codigos de jogos que estão no repositorio
		for(Jogo j: listarJogos()) {
			codJogos.add(j.getId());
		}
		
		// verifica se o codigo digitado pelo usuario existe no repositorio
		//Exce��o: id inexistente no repositorio
		// adiciona a lista jogos indicados pelo usuario.
	
		for(Integer i: id) {
			if(!codJogos.contains(i)) {
				throw new Exception("Jogo no id"+ i + "não existe");
			}
			jogosIndicados.add(repositorio.localizarJogo(i));
		}
		
		//adiciona o ingresso a cada jogo existente no jogosindicados.
		for(Jogo j: jogosIndicados) {
			j.adicionar(ingresso);
			j.setEstoque(j.getEstoque()-1);
			ingresso.adicionar(j);
		}
		
		//adicionar este ingresso no reposit�rio
		repositorio.adicionar(ingresso);
		//salvar o repositorio em arquivo
		repositorio.salvar();
		
		return ingresso;
	}
	
	public static void	cancelarIngresso(int codigo) throws Exception {
		//Exce��o: codigo inexistente no repositorio
		Ingresso i = repositorio.localizarIngresso(codigo);
		if(i == null)
			throw new Exception("Codigo" + codigo + "inexistente!");
		
		Jogo j = repositorio.localizarJogo(codigo);
		if(j == null) {
			throw new Exception("Codigo" + codigo + "inexistente!");
		}
		
		//remover o relacionamento entre ele e o(s) jogo(s) ligados a ele
		j.remover(i);
		//remover ingresso do reposit�rio 
		repositorio.remover(i);
		//salvar o repositorio em arquivo
		repositorio.salvar();
	}


}
