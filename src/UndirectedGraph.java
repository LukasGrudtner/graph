import java.util.HashSet;
import java.util.Iterator;

import vertex.UGVertex;
import vertex.Vertex;

public class UndirectedGraph extends Graph {

	public UndirectedGraph() {
		super();
	}
	
	public boolean connect(Vertex v1, Vertex v2) {
		Edge edge1 = new Edge(v1, v2);
		Edge edge2 = new Edge(v2, v1);
		
		/* Incrementa grau de v1 e adiciona v2 como adjacente. */
		((UGVertex) v1).incDegree();
		((UGVertex) v1).addNeighbor(v2);
		
		/* Incrementa grau de v2 e adiciona v1 como adjacente. */
		((UGVertex) v2).incDegree();
		((UGVertex) v2).addNeighbor(v1);
		
		return edges.add(edge1) && edges.add(edge2);
	}
	
	public boolean desconnect(Vertex v1, Vertex v2) {
		Edge edge1 = new Edge(v1, v2);
		Edge edge2 = new Edge(v2, v1);
		
		/* Decrementa grau de v1 e remove v2 como adjacente. */
		((UGVertex) v1).decDegree();
		((UGVertex) v1).removeNeighbor(v2);
		
		/* Decrementa grau de v2 e remove v1 como adjacente. */
		((UGVertex) v2).decDegree();
		((UGVertex) v2).removeNeighbor(v1);
		
		return edges.remove(edge1) && edges.remove(edge2);
	}
	
	public HashSet<Vertex> neighborhood(Vertex v) {
		return ((UGVertex) v).getNeighborhood();
	}
	
	public boolean isRegular() {
		Iterator<Vertex> iterator = vertices.iterator();
		
		UGVertex vertex;
		int degree = ((UGVertex) this.oneVertex()).getDegree();
		
		while (iterator.hasNext()) {
			vertex = (UGVertex) iterator.next();
			
			if (vertex.getDegree() != degree)
				return false;
		}
		
		return true;
	}
	
	public boolean isComplete() {
		Iterator<Vertex> iterator = vertices.iterator();
		
		UGVertex vertex;
		
		while (iterator.hasNext()) {
			vertex = (UGVertex) iterator.next();
			
			if (vertex.getDegree() != (vertices.size()-1))
				return false;
		}
		
		return true;
	}
	
	public boolean isTree() {
		
		// TODO Implementar
		return true;
	}
	
}
