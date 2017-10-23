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
	public boolean connect(Vertex v1, Vertex v2) {
		Edge edge = new Edge(v1, v2);
		
		/* Incrementa grau de emissão de v1 e adiciona v2 como sucessor. */
		((DGVertex) v1).incOutdegree();
		((DGVertex) v1).addSuccessor(v2);
		
		/* Incrementa grau de recepção de v2 e adiciona v1 como antecessor. */
		((DGVertex) v2).incIndegree();
		((DGVertex) v2).addPredecessor(v1);
		
		return edges.add(edge);
	}

	@Override
	public boolean disconnect(Vertex v1, Vertex v2) {
		Edge edge = new Edge(v1, v2);
		
		/* Decrementa grau de emissão de v1 e remove v2 como sucessor. */
		((DGVertex) v1).decOutdegree();
		((DGVertex) v1).removeSuccessor(v2);
		
		/* Decrementa grau de recepção de v2 e remove v1 como antecessor. */
		((DGVertex) v2).decIndegree();
		((DGVertex) v2).removePredecessor(v1);
		
		return edges.remove(edge);
	}
	
	/* Antecessores */
	public HashSet<Vertex> predecessors(Vertex v) {
		return ((DGVertex) v).getPredecessors();
	}
	
	/* Sucessores */
	public HashSet<Vertex> successors(Vertex v) {
		return ((DGVertex) v).getSuccessors();
	}
	
	public int indegree(Vertex v) {
		return ((DGVertex) v).getIndegree();
	}
	
	public int outdegree(Vertex v) {
		return ((DGVertex) v).getOutdegree();
	}

	@Override
	public boolean isRegular() {
		Iterator<Vertex> iterator = vertices.iterator();
		
		DGVertex vertex = (DGVertex) this.oneVertex();
		int indegree = vertex.getIndegree();
		int outdegree = vertex.getOutdegree();
		
		while (iterator.hasNext()) {
			vertex = (DGVertex) iterator.next();
			
			if ((vertex.getIndegree() != indegree) ||
					(vertex.getOutdegree() != outdegree)) {
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
			
			if ((vertex.getIndegree() != (vertices.size()-1)) ||
					(vertex.getOutdegree() != (vertices.size()-1))) 
				return false;
			
		}
		
		return true;
	}
	
	public HashSet<Vertex> getDirectTrasitiveClosure() {
		HashSet<Vertex> directTransitiveClosure = new HashSet<Vertex>();
		
		return directTransitiveClosure;
	}
	
	public HashSet<Vertex> getIndirectTrasitiveClosure() {
		HashSet<Vertex> indirectTransitiveClosure = new HashSet<Vertex>();
		
		return indirectTransitiveClosure;
	}
	

}
