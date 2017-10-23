package graph;
import java.util.HashSet;
import java.util.Iterator;

import vertex.UGVertex;
import vertex.Vertex;

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
	
	public HashSet<Vertex> neighborhood(Vertex v) {
		return ((UGVertex) v).neighborhood();
	}
	
	public int degree(Vertex v) {
		return ((UGVertex) v).degree();
	}
	
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
	
	public HashSet<Vertex> transitiveClosure(Vertex v) {
		HashSet<Vertex> markedVertices = new HashSet<Vertex>();
		return searchTransitiveClosure(v, markedVertices);
	}
	
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
		HashSet<Vertex> markedVertices = new HashSet<Vertex>();
		Vertex v = oneVertex();
		
		return vertices.equals(transitiveClosure(v));
	}
	
	private HashSet<Vertex> checkConnectivity(Vertex v, HashSet<Vertex> markedVertices) {
		Iterator<Vertex> iterator = ((UGVertex) v).neighborhood().iterator();
		Vertex vertex;
		
		markedVertices.add(v);
		
		while (iterator.hasNext()) {
			vertex = (UGVertex) iterator.next();
			
			if (!markedVertices.contains(vertex))
				checkConnectivity(vertex, markedVertices);
		}
		return markedVertices;
	}
	
	public boolean isTree() {
		HashSet<Vertex> set = new HashSet<Vertex>();
		Vertex v = oneVertex();
		
		return !isThereCycle(v, null, set) && isConnected();
	}
	
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
