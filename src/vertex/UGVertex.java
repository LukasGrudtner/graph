package vertex;

import java.util.HashSet;

/*
 * Implementa��o de um V�rtice para um Grafo N�o-Orientado. Herda fun��es da classe V�rtice, por�m possui fun��es caracter�sticas pr�prias
 * para grafo n�o-orientados.
 */
public class UGVertex extends Vertex {
	
	private int degree;						/* Grau do v�rtice.						*/
	private HashSet<Vertex> neighborhood;	/* Conjunto dos v�rtices adjacentes.	*/
	
	public UGVertex(Object data) {
		super(data);
		degree = 0;
		neighborhood = new HashSet<Vertex>();
	}

	/* Retorna o conjunto dos v�rtices adjacentes. */
	public HashSet<Vertex> neighborhood() {
		return neighborhood;
	}
	
	/* Adiciona o v�rtice v ao conjunto dos v�rtices adjacentes. */
	public boolean addNeighbor(Vertex v) {
		return neighborhood.add(v);
	}
	
	/* Remove o v�rtice v do conjunto dos v�rtices adjacentes. */
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
