package vertex;

import java.util.HashSet;

/*
 * Implementa��o de um V�rtice para um Grafo Orientado. Herda fun��es da classe V�rtice, por�m possui fun��es caracter�sticas pr�prias
 * para grafo orientados.
 */
public class DGVertex extends Vertex {

	private int indegree; 					/* Grau de recep��o do v�rtice. 		*/
	private int outdegree; 					/* Grau de emiss�o do v�rtice. 			*/
	private HashSet<Vertex> predecessors;	/* Conjunto de v�rtices predecessores. 	*/
	private HashSet<Vertex> successors;		/* Conjunto de v�rtices sucessores. 	*/
	
	public DGVertex(Object data) {
		super(data);
		
		indegree = 0;
		outdegree = 0;
		
		predecessors = new HashSet<Vertex>();
		successors = new HashSet<Vertex>();
	}
	
	/* Retorna o conjunto dos v�rtices predecessores. */
	public HashSet<Vertex> predecessors() {
		return predecessors;
	}
	
	/* Adiciona o v�rtice v ao conjunto dos v�rtices predecessores. */
	public boolean addPredecessor(Vertex v) {
		return predecessors.add(v);
	}
	
	/* Remove o v�rtice v do conjunto dos v�rtices predecessores. */
	public boolean removePredecessor(Vertex v) {
		return predecessors.remove(v);
	}
	
	/* Retorna o conjunto dos v�rtices sucessores. */
	public HashSet<Vertex> successors() {
		return successors;
	}
	
	/* Adiciona o v�rtice v ao conjunto dos v�rtices sucessores. */
	public boolean addSuccessor(Vertex v) {
		return successors.add(v);
	}
	
	/* Remove o v�rtice v do conjunto dos v�rtices sucessores. */
	public boolean removeSuccessor(Vertex v) {
		return successors.remove(v);
	}
	
	/* Retorna o grau de rececep��o. */
	public int indegree() {
		return indegree;
	}
	
	/* Incrementa em 1 unidade o grau de recep��o. */
	public void incIndegree() {
		indegree++;
	}
	
	/* Decrementa em 1 unidade o grau de recep��o. */
	public void decIndegree() {
		indegree--;
	}
	
	/* Retorna o grau de emiss�o. */
	public int outdegree() {
		return outdegree;
	}
	
	/* Incrementa em 1 unidade o grau de emiss�o. */
	public void incOutdegree() {
		outdegree++;
	}
	
	/* Decrementa em 1 unidade o grau de emiss�o. */
	public void decOutdegree() {
		outdegree--;
	}
}
