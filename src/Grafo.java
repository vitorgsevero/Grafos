import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;

public class Grafo {

	private final int nroVertices;
	private int nroArestas; // representadas por listas de adjac�ncia

	private HashMap<Integer, ArrayDeque<Integer>> listaAdjacencia;

	// construtores

	public Grafo(int n) { // cria grafo sem arestas
		if (n < 0) { // verifica o par�metro informado
			throw new IllegalArgumentException("O n�mero de v�rtices de um grafo deve ser maior do que zero!");
		}
		this.nroVertices = n;
		this.nroArestas = 0;
		this.listaAdjacencia = new HashMap<>(); // cria a lista de adj vazia

		for (int i = 0; i < n; i++) {
			listaAdjacencia.put(i, new ArrayDeque<Integer>()); // cria a lista para cada v�rtice
		}
	}

	public Grafo(String arquivo) throws FileNotFoundException, Exception { // cria grafo a partir do nome do arquivo txt
																			// que contem o caminho
		// do arquivo
		this(new Scanner(new File(arquivo)));
	}

	public Grafo(Scanner in) throws Exception {
		if (null == in) {
			throw new IllegalArgumentException("Scanner deve ser inicializado!");
		}
		// v�rtices
		if (!in.hasNextInt()) {
			throw new Exception("Erro encontrado: Primeiro par�metro deve ser o n�mero de v�rtices!");
		}

		nroVertices = in.nextInt();

		// verifica��o do nro de v�rtices informado
		if (0 > nroVertices) {
			throw new IllegalArgumentException("O n�mero de v�rtices de um grafo deve ser maior do que zero!");
		}

		in.nextLine(); // pula para a pr�xima linha, para poder ler o nro de arestas

		// arestas
		if (!in.hasNextInt()) {
			throw new Exception("Erro encontrado: Segundo par�metro deve ser o n�mero de arestas!");
		}

		int auxNroArestas = in.nextInt();

		// verifica��o do nro de v�rtices informado
		if (0 > auxNroArestas) {
			throw new IllegalArgumentException("O n�mero de arestas de um grafo deve ser maior do que zero!");
		}

		in.nextLine(); // pula para a pr�xima linha

		// iniciando adj
		listaAdjacencia = new HashMap<>();
		for (int i = 0; i < nroVertices; i++) {
			this.listaAdjacencia.put(i, new ArrayDeque<Integer>()); // cria a lista para cada v�rtice
		}

		for (int i = 0; i < auxNroArestas; i++) { // ler os pares de vertices para cada aresta
			int u = in.nextInt();
			int v = in.nextInt();
			// int custo = in.nextInt();
			// Aresta custo_ar = new CustoAresta(u,v,custo);
			// ArrayList al = new ArrayList();
			// al.add(custo_ar);
			this.insereAresta(u, v);
			if (in.hasNextLine())
				in.nextLine();
		}

	}

	public Grafo(Grafo g) { // cria��o de um Grafo a partir de outro existente
		if (g == null) {
			throw new IllegalArgumentException("Grafo n�o existe!");
		}
		this.nroVertices = g.getNroVertices();
		this.nroArestas = g.getNroArestas();

		for (int i = 0; i < this.nroVertices; i++) {
			listaAdjacencia.put(i, g.listaAdjacencia.get(i).clone());
		}

	}

	private boolean verificaVertice(int u) {
		return ((u >= 0) && (u < nroVertices));
	}

	public final void insereAresta(int u, int v) { // insere uma aresta n�o direcionada onde U = V�rtice de Origem (1) e
													// V = V�rtice de Destino (2)
		if (!this.verificaVertice(u)) {
			throw new IllegalArgumentException("V�rtice de origem fora da faixa!");
		}
		if (!this.verificaVertice(v)) {
			throw new IllegalArgumentException("V�rtice de origem fora da faixa!");
		}
		nroArestas++;
		listaAdjacencia.get(u).add(v); // acrescenta a vizinhan�a
		listaAdjacencia.get(v).add(u); // grafo n�o direcionado
	}

	public final void insereArestaDirecionada(int u, int v) { // insere uma aresta direcionada
		if (!this.verificaVertice(u)) {
			throw new IllegalArgumentException("V�rtice de origem fora da faixa!");
		}
		if (!this.verificaVertice(v)) {
			throw new IllegalArgumentException("V�rtice de origem fora da faixa!");
		}
		nroArestas++;
		listaAdjacencia.get(u).add(v); // acrescenta a vizinhan�a //grafo direcionado
	}

	public Iterable<Integer> listaAdjacencia(int v) { // retorna a adjac�ncia (vizinhan�a) de um v�rtice
		if (!this.verificaVertice(v)) {
			throw new IllegalArgumentException("V�rtice de origem fora da faixa!");
		}
		return listaAdjacencia.get(v); // retorna o grafo como uma String
	}

	@Override
	public String toString() { // mostra o grafo

		StringBuilder sb = new StringBuilder();
		String separador = System.getProperty("line.separator");

		sb.append(nroVertices).append(" V�rtices || ").append(nroArestas).append(" Arestas ").append(separador);

		for (int i = 0; i < nroVertices; i++) { // loop para gerar cada linha da vizinhan�a
			sb.append(i).append(" : ");
			for (int u : listaAdjacencia(i)) {
				sb.append(u).append(" ");
			}
			sb.append(separador);
		}

		return sb.toString();

	}

	// get e set

	public int getNroVertices() {
		return nroVertices;
	}

	public int getNroArestas() {
		return nroArestas;
	}

}
