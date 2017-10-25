package graph;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.plaf.synth.SynthSeparatorUI;

import vertex.DGVertex;
import vertex.Vertex;

/*
 * Implementa��o de um Grafo Orientado, o qual herda caracter�sticas da classe Grafo, por�m possui caracter�sticas pr�prias.
 */
 
public class DirectedGraph extends Graph {
	
	public DirectedGraph() {
		super();
	}

	@Override
	public void connect(Vertex v1, Vertex v2) {
		
		/* Incrementa grau de emiss�o de v1 e adiciona v2 como sucessor. */
		((DGVertex) v1).incOutdegree();
		((DGVertex) v1).addSuccessor(v2);
		
		/* Incrementa grau de recep��o de v2 e adiciona v1 como antecessor. */
		((DGVertex) v2).incIndegree();
		((DGVertex) v2).addPredecessor(v1);
	}

	@Override
	public void disconnect(Vertex v1, Vertex v2) {
		
		/* Decrementa grau de emiss�o de v1 e remove v2 como sucessor. */
		((DGVertex) v1).decOutdegree();
		((DGVertex) v1).removeSuccessor(v2);
		
		/* Decrementa grau de recep��o de v2 e remove v1 como antecessor. */
		((DGVertex) v2).decIndegree();
		((DGVertex) v2).removePredecessor(v1);
	}
	
	/* Retorna o conjunto de v�rtices predecessores do v�rtice v. */
	public HashSet<Vertex> predecessors(Vertex v) {
		return ((DGVertex) v).predecessors();
	}
	
	/* Retorna o conjunto de v�rtices sucessores do v�rtice v. */
	public HashSet<Vertex> successors(Vertex v) {
		return ((DGVertex) v).successors();
	}
	
	/* Retorna o grau de recep��o do v�rtice v. */
	public int indegree(Vertex v) {
		return ((DGVertex) v).indegree();
	}
	
	/* Retorna o grau de emiss�o do v�rtice v. */
	public int outdegree(Vertex v) {
		return ((DGVertex) v).outdegree();
	}

	@Override
	public boolean isRegular() {
		Iterator<Vertex> iterator = vertices.iterator();
		
		DGVertex vertex = (DGVertex) this.oneVertex();
		int indegree = vertex.indegree();
		int outdegree = vertex.outdegree();
		
		while (iterator.hasNext()) {
			vertex = (DGVertex) iterator.next();
			
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
		
		DGVertex vertex;
		
		while (iterator.hasNext()) {
			vertex = (DGVertex) iterator.next();
			
			if ((vertex.indegree() != (vertices.size()-1)) ||
					(vertex.outdegree() != (vertices.size()-1))) 
				return false;
		}
		return true;
	}
	
	/* Retorna um conjunto com todos os v�rtices que s�o fecho transitivo direto de um dado v�rtice v. */
	public HashSet<Vertex> directTrasitiveClosure(Vertex v) {
		HashSet<Vertex> markedVertices = new HashSet<Vertex>();
		return searchDirectTransitiveClosure(v, markedVertices);
	}
	
	/* Fun��o auxiliar recursiva para encontrar o fecho transitivo direto do v�rtice v. */
	private HashSet<Vertex> searchDirectTransitiveClosure(Vertex v, HashSet<Vertex> markedVertices) {
		Iterator<Vertex> iterator = ((DGVertex) v).successors().iterator();
		Vertex vertex;

		markedVertices.add(v);		
		
		while (iterator.hasNext()) {
			vertex = (DGVertex) iterator.next();
			
			if (!markedVertices.contains(vertex))
				searchDirectTransitiveClosure(vertex, markedVertices);
		}
		return markedVertices;
	}
	
	/* Retorna um conjunto com todos os v�rtices que s�o fecho transitivo indireto de um dado v�rtice v. */
	public HashSet<Vertex> indirectTransitiveClosure(Vertex v) {
		HashSet<Vertex> markedVertices = new HashSet<Vertex>();
		return searchIndirectTransitiveClosure(v, markedVertices);
	}
	
	/* Fun��o auxiliar recursiva para encontrar o fecho transitivo indireto de um dado v�rtice v. */
	private HashSet<Vertex> searchIndirectTransitiveClosure(Vertex v, HashSet<Vertex> markedVertices) {
		Iterator<Vertex> iterator = ((DGVertex) v).predecessors().iterator();
		Vertex vertex;
		
		markedVertices.add(v);
		
		while (iterator.hasNext()) {
			vertex = (DGVertex) iterator.next();
			
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
	
	/* Retorna uma lista com a ordem topol�gica do grafo. */
	public ArrayList<Vertex> topologicalSort() {
		ArrayList<Vertex> list = new ArrayList<Vertex>();
		Vertex v;
		Iterator<Vertex> iterator = vertices.iterator();
		
		while (iterator.hasNext()) {
			v = iterator.next();
			/* Somente v�rtices fonte */
			if (((DGVertex) v).predecessors().size() == 0) {
				list = searchTopologicalSort(v, list);
			}
		}
		
		Collections.reverse(list);
		
		return list;
	}
	
	/* Fun��o auxiliar recursiva para encontrar a ordena��o topol�gica do grafo. */
	private ArrayList<Vertex> searchTopologicalSort(Vertex v, ArrayList<Vertex> list) {
		Iterator<Vertex> iterator = ((DGVertex) v).successors().iterator();
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
