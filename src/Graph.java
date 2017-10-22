import java.util.HashSet;
import java.util.Iterator;

public class Graph {
	
	private HashSet<Vertex> vertices;
	private HashSet<Edge> edges;
	
	public Graph() {
		vertices = new HashSet<Vertex>();
		edges = new HashSet<Edge>();
	}
	
	public boolean addVertex(Vertex v) {
		return vertices.add(v);
	}
	
	public boolean removeVertex(Vertex v) {
		return vertices.remove(v);
	}
	
	public boolean conect(Vertex v1, Vertex v2) {
		Edge edge = new Edge(v1, v2);
		return edges.add(edge);
	}
	
	public boolean desconect(Vertex v1, Vertex v2) {
		Edge edge = new Edge(v1, v2);
		return edges.remove(edge);
	}
	
	public int order() {
		return vertices.size();
	}
	
	public HashSet<Vertex> vertices() {
		return vertices;
	}
	
	public Vertex oneVertex() {
		Iterator<Vertex> iterator = vertices.iterator();
		return iterator.next();
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
	
	public int degree(Vertex v) {
		int degree = 0;
		Iterator<Edge> iterator = edges.iterator();
		
		Edge edge;
		while (iterator.hasNext()) {
			edge = iterator.next();
			if (edge.getVertices()[0].equals(v) || edge.getVertices()[1].equals(v)) {
				degree++;
			}
		}
		
		return degree;
	}
	
	

}
