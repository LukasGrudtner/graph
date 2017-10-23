import java.util.HashSet;

import vertex.Vertex;

public class DirectedGraph extends Graph {
	
	public DirectedGraph() {
		super();
	}

	@Override
	public boolean connect(Vertex v1, Vertex v2) {
		Edge edge = new Edge(v1, v2);
		
		/* Incrementa grau de v1 e adiciona v2 como sucessor. */
		v1.incDegree();
		v1.addOutdegreeVertex(v2);
		
		/* Incrementa grau de v2 e adiciona v1 como antecessor. */
		v2.incDegree();
		v2.addIndegreeVertex(v1);
		
		return edges.add(edge);
	}

	@Override
	public boolean desconnect(Vertex v1, Vertex v2) {
		Edge edge = new Edge(v1, v2);
		
		/* Decrementa grau de v1 e remove v2 como sucessor. */
		v1.decDegree();
		v1.removeOutdegreeVertex(v2);
		
		/* Decrementa grau de v2 e remove v1 como antecessor. */
		v2.decDegree();
		v2.removeOutdegreeVertex(v1);
		
		return edges.remove(edge);
	}
	
	/* Antecessores */
	public HashSet<Vertex> indegree(Vertex v) {
		return v.getIndegree();
	}
	
	/* Sucessores */
	public HashSet<Vertex> outdegree(Vertex v) {
		return v.getOutdegree();
	}

	@Override
	public boolean isRegular() {
		// TODO Implementar
		return false;
	}

	@Override
	public boolean isComplete() {
		// TODO Implementar
		return false;
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
