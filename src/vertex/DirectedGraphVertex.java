package vertex;

import java.util.HashSet;

/*
 * Implementação de um Vértice para Grafos Orientados. Herda funções da classe Vértice, porém possui funções características próprias
 * para grafo orientados.
 */
public class DirectedGraphVertex extends Vertex {

	private int indegree; 					/* Grau de recepção do vértice. 		*/
	private int outdegree; 					/* Grau de emissão do vértice. 			*/
	private HashSet<Vertex> predecessors;	/* Conjunto de vértices predecessores. 	*/
	private HashSet<Vertex> successors;		/* Conjunto de vértices sucessores. 	*/
	
	public DirectedGraphVertex(Object data) {
		super(data);
		
		indegree = 0;
		outdegree = 0;
		
		predecessors = new HashSet<Vertex>();
		successors = new HashSet<Vertex>();
	}
	
	/* Retorna o conjunto dos vértices predecessores. */
	public HashSet<Vertex> predecessors() {
		return predecessors;
	}
	
	/* Adiciona o vértice v ao conjunto dos vértices predecessores. */
	public boolean addPredecessor(Vertex v) {
		if (predecessors.add(v)) {
			indegree++;
			return true;
		}
		return false;
	}
	
	/* Remove o vértice v do conjunto dos vértices predecessores. */
	public boolean removePredecessor(Vertex v) {
		if (predecessors.remove(v)) {
			indegree--;
			return true;
		}
		return false;
	}
	
	/* Retorna o conjunto dos vértices sucessores. */
	public HashSet<Vertex> successors() {
		return successors;
	}
	
	/* Adiciona o vértice v ao conjunto dos vértices sucessores. */
	public boolean addSuccessor(Vertex v) {
		if (successors.add(v)) {
			outdegree++;
			return true;
		}
		return false;
	}
	
	/* Remove o vértice v do conjunto dos vértices sucessores. */
	public boolean removeSuccessor(Vertex v) {
		if (successors.remove(v)) {
			outdegree--;
			return true;
		}
		return false;
	}
	
	/* Retorna o grau de rececepção. */
	public int indegree() {
		return indegree;
	}
	
	/* Retorna o grau de emissão. */
	public int outdegree() {
		return outdegree;
	}
}
