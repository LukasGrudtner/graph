import java.util.HashSet;
import java.util.Iterator;

public abstract class Graph {
	
	protected HashSet<Vertex> vertices;
	protected HashSet<Edge> edges;
	
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
	
	public abstract boolean connect(Vertex v1, Vertex v2);
	
	public abstract boolean desconnect(Vertex v1, Vertex v2);
	
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
	
	public int degree(Vertex v) {
		return v.getDegree();
	}
	
	

}
