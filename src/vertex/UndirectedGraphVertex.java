package vertex;

import java.util.HashSet;

/*
 * Implementação de um Vértice para Grafos Não-Orientados. Herda funções da classe Vértice, porém possui funções características próprias
 * para grafo não-orientados.
 */
public class UndirectedGraphVertex extends Vertex {
	
	private int degree;						/* Grau do vértice.						*/
	private HashSet<Vertex> neighborhood;	/* Conjunto dos vértices adjacentes.	*/
	
	public UndirectedGraphVertex(Object data) {
		super(data);
		degree = 0;
		neighborhood = new HashSet<Vertex>();
	}

	/* Retorna o conjunto dos vértices adjacentes. */
	public HashSet<Vertex> neighborhood() {
		return neighborhood;
	}
	
	/* Adiciona o vértice v ao conjunto dos vértices adjacentes. */
	public boolean addNeighbor(Vertex v) {
		if (neighborhood.add(v)) {
			if (v.equals(this))
				degree += 2;
			else
				degree++;
			return true;
		}
		return false;
	}
	
	/* Remove o vértice v do conjunto dos vértices adjacentes. */
	public boolean removeNeighbor(Vertex v) {
		if (neighborhood.remove(v)) {
			degree--;
			return true;
		}
		return false;
	}
	
	/* Retorna o grau. */
	public int degree() {
		return degree;
	}
}
