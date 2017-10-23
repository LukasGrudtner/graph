import java.util.HashSet;
import java.util.Iterator;

public class UndirectedGraph extends Graph {

	public UndirectedGraph() {
		super();
	}
	
	public boolean connect(Vertex v1, Vertex v2) {
		Edge edge1 = new Edge(v1, v2);
		Edge edge2 = new Edge(v2, v1);
		
		v1.incDegree();
		v2.incDegree();
		
		return edges.add(edge1) && edges.add(edge2);
	}
	
	public boolean desconnect(Vertex v1, Vertex v2) {
		Edge edge1 = new Edge(v1, v2);
		Edge edge2 = new Edge(v2, v1);
		
		v1.decDegree();
		v2.decDegree();
		
		return edges.remove(edge1) && edges.remove(edge2);
	}
	
	public HashSet<Vertex> neighborhood(Vertex v) {
		HashSet<Vertex> neighborhood = new HashSet<Vertex>();
		Iterator<Edge> iterator = edges.iterator();
		Edge edge;
		while (iterator.hasNext()) {
			edge = iterator.next();
			if (edge.getVertices()[0].equals(v))
				neighborhood.add(edge.getVertices()[1]);
		}
		return neighborhood;
	}
	
}
