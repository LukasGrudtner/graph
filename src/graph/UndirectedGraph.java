package graph;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import vertex.UndirectedGraphVertex;
import vertex.Vertex;

/*
 * Implementa��o de um Grafo N�o-orientado. Herda caracter�sticas da classe Grafo, por�m possui fun��es caracter�sticas pr�prias.
 */

public class UndirectedGraph extends Graph {

	public UndirectedGraph() {
		super();
	}
	
	@Override
	public void removeVertex(Vertex v) {
		/* Remove as arestas que ligam o v�rtice v � todos os seus adjacentes. */
		ArrayList<UndirectedGraphVertex> list = new ArrayList<UndirectedGraphVertex>();
		
		/* 
		 * O Iterator gera uma exce��o quando se tenta remover objetos do conjunto enquanto ele est� iterando
		 * sobre eles. Logo, a solu��o foi pass�-los todos para uma lista e a� ent�o fazer sua remo��o chamando
		 * a fun��o "disconnect" para desconectar os v�rtices do v�rtice removido.
		 */
		Iterator<Vertex> iterator = ((UndirectedGraphVertex) v).neighborhood().iterator();
		while (iterator.hasNext()) {
			list.add((UndirectedGraphVertex) iterator.next());
		}
		
		for (int i = 0; i < list.size(); i++) {
			disconnect(v, list.get(i));
		}
		
		vertices.remove(v);
	}
	
	public void connect(Vertex v1, Vertex v2) {
	
		if (vertices.contains(v1) && vertices.contains(v2)) {
			((UndirectedGraphVertex) v1).addNeighbor(v2);
			((UndirectedGraphVertex) v2).addNeighbor(v1);
		}
	}
	
	public void disconnect(Vertex v1, Vertex v2) {

		if (((UndirectedGraphVertex) v1).neighborhood().contains(v2) && ((UndirectedGraphVertex) v2).neighborhood().contains(v1)) {
			((UndirectedGraphVertex) v1).removeNeighbor(v2);
			((UndirectedGraphVertex) v2).removeNeighbor(v1);
		}
	}
	
	/* Retorna o conjunto dos v�rtices adjacentes ao v�rtice v. */
	public HashSet<Vertex> neighborhood(Vertex v) {
		return ((UndirectedGraphVertex) v).neighborhood();
	}
	
	/* Retorna o grau do v�rtice v. */
	public int degree(Vertex v) {
		return ((UndirectedGraphVertex) v).degree();
	}
	
	@Override
	public boolean isRegular() {
		Iterator<Vertex> iterator = vertices.iterator();
		UndirectedGraphVertex vertex;
		int degree = ((UndirectedGraphVertex) this.oneVertex()).degree();
		
		while (iterator.hasNext()) {
			vertex = (UndirectedGraphVertex) iterator.next();
			
			if (vertex.degree() != degree)
				return false;
		}
		return true;
	}
	
	@Override
	public boolean isComplete() {
		Iterator<Vertex> iterator = vertices.iterator();
		UndirectedGraphVertex vertex;
		
		while (iterator.hasNext()) {
			vertex = (UndirectedGraphVertex) iterator.next();
			
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
		Iterator<Vertex> iterator = ((UndirectedGraphVertex) v).neighborhood().iterator();
		UndirectedGraphVertex vertex;
		
		markedVertices.add(v);
		
		while (iterator.hasNext()) {
			vertex = (UndirectedGraphVertex) iterator.next();
			
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
		Iterator<Vertex> iterator = ((UndirectedGraphVertex) v).neighborhood().iterator();
		Vertex vertex;
		
		if (markedVertices.contains(v))
			return true;
		
		markedVertices.add(v);
		
		while (iterator.hasNext()) {
			vertex = (UndirectedGraphVertex) iterator.next();
			
			if (!vertex.equals(previous))
				if (isThereCycle(vertex, v, markedVertices))
					return true;
		}
		return false;
	}

	
}
