import java.util.HashSet;

public class DirectedGraph extends Graph {
	
	public DirectedGraph() {
		super();
	}

	@Override
	public boolean connect(Vertex v1, Vertex v2) {
		Edge edge = new Edge(v1, v2);
		
		v1.incDegree();
		v2.incDegree();
		
		return edges.add(edge);
	}

	@Override
	public boolean desconnect(Vertex v1, Vertex v2) {
		Edge edge = new Edge(v1, v2);
		
		v1.decDegree();
		v2.decDegree();
		
		return edges.remove(edge);
	}
	
	/* Antecessores */
	public HashSet<Vertex> indegree(Vertex v) {
		HashSet<Vertex> indegree = new HashSet<Vertex>();
		
		/* TODO: Implementar o método. */
		
		return indegree;
	}
	
	/* Sucessores */
	public HashSet<Vertex> outdegree(Vertex v) {
		HashSet<Vertex> outdegree = new HashSet<Vertex>();
		
		/* TODO: Implementar o método. */
		
		return outdegree;
	}

}
