package vertex;

import java.util.HashSet;

/*
 * Implementa��o de um V�rtice para Grafos N�o-Orientados. Herda fun��es da classe V�rtice, por�m possui fun��es caracter�sticas pr�prias
 * para grafo n�o-orientados.
 */
public class UndirectedGraphVertex extends Vertex {
	
	private int degree;						/* Grau do v�rtice.						*/
	private HashSet<Vertex> neighborhood;	/* Conjunto dos v�rtices adjacentes.	*/
	
	public UndirectedGraphVertex(Object data) {
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
		if (neighborhood.add(v)) {
			if (v.equals(this))
				degree += 2;
			else
				degree++;
			return true;
		}
		return false;
	}
	
	/* Remove o v�rtice v do conjunto dos v�rtices adjacentes. */
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
