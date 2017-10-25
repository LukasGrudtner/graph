package vertex;

import java.util.HashSet;

/*
 * Implementação de um Vértice para um Grafo Não-Orientado. Herda funções da classe Vértice, porém possui funções características próprias
 * para grafo não-orientados.
 */
public class UGVertex extends Vertex {
	
	private int degree;						/* Grau do vértice.						*/
	private HashSet<Vertex> neighborhood;	/* Conjunto dos vértices adjacentes.	*/
	
	public UGVertex(Object data) {
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
		return neighborhood.add(v);
	}
	
	/* Remove o vértice v do conjunto dos vértices adjacentes. */
	public boolean removeNeighbor(Vertex v) {
		return neighborhood.remove(v);
	}
	
	/* Retorna o grau. */
	public int degree() {
		return degree;
	}
	
	/* Incrementa em 1 unidade o grau. */
	public void incDegree() {
		degree++;
	}
	
	/* Decrementa em 1 unidade o grau. */
	public void decDegree() {
		degree--;
	}
}
