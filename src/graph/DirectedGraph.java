package graph;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import vertex.DirectedGraphVertex;
import vertex.Vertex;

/*
 * Implementação de um Grafo Orientado, o qual herda características da classe Grafo, porém possui características próprias.
 */
 
public class DirectedGraph extends Graph {
	
	public DirectedGraph() {
		super();
	}
	
	@Override
	public void removeVertex(Vertex v) {
		/* Remove as arestas que ligam os vértices predecessores de v ao o próprio vértice v. */
		Iterator<Vertex> iteratorPredecessors = ((DirectedGraphVertex) v).predecessors().iterator();
		while (iteratorPredecessors.hasNext())
			disconnect(iteratorPredecessors.next(), v);
		
		/* Remove as arestas que ligam o vértice v com os seus sucessores. */
		Iterator<Vertex> iteratorSuccessors = ((DirectedGraphVertex) v).successors().iterator();
		while (iteratorSuccessors.hasNext())
			disconnect(v, iteratorSuccessors.next());
		
		vertices.remove(v);
	}

	@Override
	public void connect(Vertex v1, Vertex v2) {
		
		if (vertices.contains(v1) && vertices.contains(v2)) {
			((DirectedGraphVertex) v1).addSuccessor(v2);
			((DirectedGraphVertex) v2).addPredecessor(v1);
		}
	}

	@Override
	public void disconnect(Vertex v1, Vertex v2) {
		
		if (((DirectedGraphVertex) v1).successors().contains(v2) && ((DirectedGraphVertex) v2).predecessors().contains(v1)) {
			((DirectedGraphVertex) v1).removeSuccessor(v2);
			((DirectedGraphVertex) v2).removePredecessor(v1);
		}
	}
	
	/* Retorna o conjunto de vértices predecessores do vértice v. */
	public HashSet<Vertex> predecessors(Vertex v) {
		return ((DirectedGraphVertex) v).predecessors();
	}
	
	/* Retorna o conjunto de vértices sucessores do vértice v. */
	public HashSet<Vertex> successors(Vertex v) {
		return ((DirectedGraphVertex) v).successors();
	}
	
	/* Retorna o grau de recepção do vértice v. */
	public int indegree(Vertex v) {
		return ((DirectedGraphVertex) v).indegree();
	}
	
	/* Retorna o grau de emissão do vértice v. */
	public int outdegree(Vertex v) {
		return ((DirectedGraphVertex) v).outdegree();
	}

	@Override
	public boolean isRegular() {
		Iterator<Vertex> iterator = vertices.iterator();
		
		DirectedGraphVertex vertex = (DirectedGraphVertex) this.oneVertex();
		int indegree = vertex.indegree();
		int outdegree = vertex.outdegree();
		
		while (iterator.hasNext()) {
			vertex = (DirectedGraphVertex) iterator.next();
			
			if ((vertex.indegree() != indegree) ||
					(vertex.outdegree() != outdegree)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isComplete() {
		Iterator<Vertex> iterator = vertices.iterator();
		
		DirectedGraphVertex vertex;
		
		while (iterator.hasNext()) {
			vertex = (DirectedGraphVertex) iterator.next();
			
			if ((vertex.indegree() != (vertices.size()-1)) ||
					(vertex.outdegree() != (vertices.size()-1))) 
				return false;
		}
		return true;
	}
	
	/* Retorna um conjunto com todos os vértices que são fecho transitivo direto de um dado vértice v. */
	public HashSet<Vertex> directTransitiveClosure(Vertex v) {
		HashSet<Vertex> markedVertices = new HashSet<Vertex>();
		return searchDirectTransitiveClosure(v, markedVertices);
	}
	
	/* Função auxiliar recursiva para encontrar o fecho transitivo direto do vértice v. */
	private HashSet<Vertex> searchDirectTransitiveClosure(Vertex v, HashSet<Vertex> markedVertices) {
		Iterator<Vertex> iterator = ((DirectedGraphVertex) v).successors().iterator();
		Vertex vertex;

		markedVertices.add(v);		
		
		while (iterator.hasNext()) {
			vertex = (DirectedGraphVertex) iterator.next();
			
			if (!markedVertices.contains(vertex))
				searchDirectTransitiveClosure(vertex, markedVertices);
		}
		return markedVertices;
	}
	
	/* Retorna um conjunto com todos os vértices que são fecho transitivo indireto de um dado vértice v. */
	public HashSet<Vertex> indirectTransitiveClosure(Vertex v) {
		HashSet<Vertex> markedVertices = new HashSet<Vertex>();
		return searchIndirectTransitiveClosure(v, markedVertices);
	}
	
	/* Função auxiliar recursiva para encontrar o fecho transitivo indireto de um dado vértice v. */
	private HashSet<Vertex> searchIndirectTransitiveClosure(Vertex v, HashSet<Vertex> markedVertices) {
		Iterator<Vertex> iterator = ((DirectedGraphVertex) v).predecessors().iterator();
		Vertex vertex;
		
		markedVertices.add(v);
		
		while (iterator.hasNext()) {
			vertex = (DirectedGraphVertex) iterator.next();
			
			if (!markedVertices.contains(vertex))
				searchIndirectTransitiveClosure(vertex, markedVertices);
		}
		return markedVertices;
	}

	@Override
	public boolean isConnected() {
		HashSet<Vertex> allVerticesSet = new HashSet<Vertex>();
		Vertex vertex = oneVertex();
		
		searchDirectTransitiveClosure(vertex, allVerticesSet);
		searchIndirectTransitiveClosure(vertex, allVerticesSet);
		
		return (allVerticesSet.size() == vertices.size());
	}
	
	/* Retorna uma lista com a ordem topológica do grafo. */
	public ArrayList<Vertex> topologicalSort() {
		ArrayList<Vertex> list = new ArrayList<Vertex>();
		Vertex v;
		Iterator<Vertex> iterator = vertices.iterator();
		
		while (iterator.hasNext()) {
			v = iterator.next();
			/* Somente vértices fonte */
			if (((DirectedGraphVertex) v).predecessors().size() == 0) {
				list = searchTopologicalSort(v, list);
			}
		}
		
		Collections.reverse(list);
		
		return list;
	}
	
	/* Função auxiliar recursiva para encontrar a ordenação topológica do grafo. */
	private ArrayList<Vertex> searchTopologicalSort(Vertex v, ArrayList<Vertex> list) {
		Iterator<Vertex> iterator = ((DirectedGraphVertex) v).successors().iterator();
		Vertex vertex;
		
		while (iterator.hasNext()) {
			vertex = iterator.next();
			
			if (!list.contains(vertex)) {
				searchTopologicalSort(vertex, list);
			}
		}
		
		list.add(v);
		return list;
	}
}
