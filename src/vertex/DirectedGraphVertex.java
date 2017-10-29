package vertex;

import java.util.HashSet;

/*
 * Implementa��o de um V�rtice para Grafos Orientados. Herda fun��es da classe V�rtice, por�m possui fun��es caracter�sticas pr�prias
 * para grafo orientados.
 */
public class DirectedGraphVertex extends Vertex {

	private int indegree; 					/* Grau de recep��o do v�rtice. 		*/
	private int outdegree; 					/* Grau de emiss�o do v�rtice. 			*/
	private HashSet<Vertex> predecessors;	/* Conjunto de v�rtices predecessores. 	*/
	private HashSet<Vertex> successors;		/* Conjunto de v�rtices sucessores. 	*/
	
	public DirectedGraphVertex(Object data) {
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
		if (predecessors.add(v)) {
			indegree++;
			return true;
		}
		return false;
	}
	
	/* Remove o v�rtice v do conjunto dos v�rtices predecessores. */
	public boolean removePredecessor(Vertex v) {
		if (predecessors.remove(v)) {
			indegree--;
			return true;
		}
		return false;
	}
	
	/* Retorna o conjunto dos v�rtices sucessores. */
	public HashSet<Vertex> successors() {
		return successors;
	}
	
	/* Adiciona o v�rtice v ao conjunto dos v�rtices sucessores. */
	public boolean addSuccessor(Vertex v) {
		if (successors.add(v)) {
			outdegree++;
			return true;
		}
		return false;
	}
	
	/* Remove o v�rtice v do conjunto dos v�rtices sucessores. */
	public boolean removeSuccessor(Vertex v) {
		if (successors.remove(v)) {
			outdegree--;
			return true;
		}
		return false;
	}
	
	/* Retorna o grau de rececep��o. */
	public int indegree() {
		return indegree;
	}
	
	/* Retorna o grau de emiss�o. */
	public int outdegree() {
		return outdegree;
	}
}
