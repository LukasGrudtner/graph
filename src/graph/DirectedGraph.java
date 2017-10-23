package graph;
import java.util.HashSet;
import java.util.Iterator;

import vertex.DGVertex;
import vertex.Vertex;

public class DirectedGraph extends Graph {
	
	public DirectedGraph() {
		super();
	}

	@Override
	public void connect(Vertex v1, Vertex v2) {
		
		/* Incrementa grau de emissão de v1 e adiciona v2 como sucessor. */
		((DGVertex) v1).incOutdegree();
		((DGVertex) v1).addSuccessor(v2);
		
		/* Incrementa grau de recepção de v2 e adiciona v1 como antecessor. */
		((DGVertex) v2).incIndegree();
		((DGVertex) v2).addPredecessor(v1);
	}

	@Override
	public void disconnect(Vertex v1, Vertex v2) {
		
		/* Decrementa grau de emissão de v1 e remove v2 como sucessor. */
		((DGVertex) v1).decOutdegree();
		((DGVertex) v1).removeSuccessor(v2);
		
		/* Decrementa grau de recepção de v2 e remove v1 como antecessor. */
		((DGVertex) v2).decIndegree();
		((DGVertex) v2).removePredecessor(v1);
	}
	
	public HashSet<Vertex> predecessors(Vertex v) {
		return ((DGVertex) v).predecessors();
	}
	
	public HashSet<Vertex> successors(Vertex v) {
		return ((DGVertex) v).successors();
	}
	
	public int indegree(Vertex v) {
		return ((DGVertex) v).indegree();
	}
	
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
	
	public HashSet<Vertex> directTrasitiveClosure(Vertex v) {
		HashSet<Vertex> markedVertices = new HashSet<Vertex>();
		return DTC(v, markedVertices);
	}
	
	private HashSet<Vertex> DTC(Vertex v, HashSet<Vertex> markedVertices) {
		Iterator<Vertex> iterator = ((DGVertex) v).successors().iterator();
		Vertex vertex;

		markedVertices.add(v);		
		
		while (iterator.hasNext()) {
			vertex = (DGVertex) iterator.next();
			
			if (!markedVertices.contains(vertex))
				DTC(vertex, markedVertices);
		}
		return markedVertices;
	}
	
	public HashSet<Vertex> indirectTransitiveClosure(Vertex v) {
		HashSet<Vertex> markedVertices = new HashSet<Vertex>();
		return ITC(v, markedVertices);
	}
	
	private HashSet<Vertex> ITC(Vertex v, HashSet<Vertex> markedVertices) {
		Iterator<Vertex> iterator = ((DGVertex) v).predecessors().iterator();
		Vertex vertex;
		
		markedVertices.add(v);
		
		while (iterator.hasNext()) {
			vertex = (DGVertex) iterator.next();
			
			if (!markedVertices.contains(vertex))
				ITC(vertex, markedVertices);
		}
		return markedVertices;
	}

	@Override
	public boolean isConnected() {
		HashSet<Vertex> allVerticesSet = new HashSet<Vertex>();
		Vertex vertex = oneVertex();
		
		DTC(vertex, allVerticesSet);
		ITC(vertex, allVerticesSet);
		
		return (allVerticesSet.size() == vertices.size());
	}
}
