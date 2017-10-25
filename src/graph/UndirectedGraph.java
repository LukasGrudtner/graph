package graph;
import java.util.HashSet;
import java.util.Iterator;

import vertex.UGVertex;
import vertex.Vertex;

/*
 * Implementa��o de um Grafo N�o-orientado. Herda caracter�sticas da classe Grafo, por�m possui fun��es caracter�sticas pr�prias.
 */

public class UndirectedGraph extends Graph {

	public UndirectedGraph() {
		super();
	}
	
	public void connect(Vertex v1, Vertex v2) {
	
		/* Incrementa grau de v1 e adiciona v2 como adjacente. */
		((UGVertex) v1).incDegree();
		((UGVertex) v1).addNeighbor(v2);
		
		/* Incrementa grau de v2 e adiciona v1 como adjacente. */
		((UGVertex) v2).incDegree();
		((UGVertex) v2).addNeighbor(v1);
	}
	
	public void disconnect(Vertex v1, Vertex v2) {
		
		/* Decrementa grau de v1 e remove v2 como adjacente. */
		((UGVertex) v1).decDegree();
		((UGVertex) v1).removeNeighbor(v2);
		
		/* Decrementa grau de v2 e remove v1 como adjacente. */
		((UGVertex) v2).decDegree();
		((UGVertex) v2).removeNeighbor(v1);
	}
	
	/* Retorna o conjunto dos v�rtices adjacentes ao v�rtice v. */
	public HashSet<Vertex> neighborhood(Vertex v) {
		return ((UGVertex) v).neighborhood();
	}
	
	/* Retorna o grau do v�rtice v. */
	public int degree(Vertex v) {
		return ((UGVertex) v).degree();
	}
	
	@Override
	public boolean isRegular() {
		Iterator<Vertex> iterator = vertices.iterator();
		UGVertex vertex;
		int degree = ((UGVertex) this.oneVertex()).degree();
		
		while (iterator.hasNext()) {
			vertex = (UGVertex) iterator.next();
			
			if (vertex.degree() != degree)
				return false;
		}
		return true;
	}
	
	@Override
	public boolean isComplete() {
		Iterator<Vertex> iterator = vertices.iterator();
		UGVertex vertex;
		
		while (iterator.hasNext()) {
			vertex = (UGVertex) iterator.next();
			
			if (vertex.degree() != (vertices.size()-1))
				return false;
		}
		return true;
	}
	
	/* Retorna um conjunto com todos os v�rtices que s�o fecho transitivo de um dado v�rtice v. */
	public HashSet<Vertex> transitiveClosure(Vertex v) {
		HashSet<Vertex> markedVertices = new HashSet<Vertex>();
		return searchTransitiveClosure(v, markedVertices);
	}
	
	/* Fun��o auxiliar recursiva para encontrar o fecho transitivo do v�rtice v. */
	private HashSet<Vertex> searchTransitiveClosure(Vertex v, HashSet<Vertex> markedVertices) {
		Iterator<Vertex> iterator = ((UGVertex) v).neighborhood().iterator();
		UGVertex vertex;
		
		markedVertices.add(v);
		
		while (iterator.hasNext()) {
			vertex = (UGVertex) iterator.next();
			
			if (!markedVertices.contains(vertex))
				searchTransitiveClosure(vertex, markedVertices);
		}
		return markedVertices;
	}
	
	@Override
	public boolean isConnected() {
		Vertex v = oneVertex();
		return vertices.equals(transitiveClosure(v));
	}

	/* Retorna o valor booleano true se o grafo for uma �rvore. Caso contr�rio, retorna false. */
	public boolean isTree() {
		HashSet<Vertex> set = new HashSet<Vertex>();
		Vertex v = oneVertex();
		
		return !isThereCycle(v, null, set) && isConnected();
	}
	
	/* Fun��o auxiliar recuriva para determinar se o grafo possui pelo menos um ciclo. */
	private boolean isThereCycle (Vertex v, Vertex previous, HashSet<Vertex> markedVertices) {
		Iterator<Vertex> iterator = ((UGVertex) v).neighborhood().iterator();
		Vertex vertex;
		
		if (markedVertices.contains(v))
			return true;
		
		markedVertices.add(v);
		
		while (iterator.hasNext()) {
			vertex = (UGVertex) iterator.next();
			
			if (!vertex.equals(previous))
				if (isThereCycle(vertex, v, markedVertices))
					return true;
		}
		return false;
	}
}
