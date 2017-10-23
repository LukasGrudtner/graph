package vertex;
import java.util.HashSet;

public class Vertex {
	
	private Object data;
	private int degree;
	
	private HashSet<Vertex> indegree;
	private HashSet<Vertex> outdegree;
	private HashSet<Vertex> neighborhood;
	
	public Vertex(Object data) {
		this.data = data;
		this.degree = 0;
		
		this.indegree = new HashSet<Vertex>();
		this.outdegree = new HashSet<Vertex>();
		
		this.neighborhood = new HashSet<Vertex>();
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public int getDegree() {
		return degree;
	}
	
	public void incDegree() {
		degree++;
	}
	
	public void decDegree() {
		degree--;
	}
	
	public HashSet<Vertex> getIndegree() {
		return indegree;
	}
	
	public boolean addIndegreeVertex(Vertex v) {
		return indegree.add(v);
	}
	
	public boolean removeIndegreeVertex(Vertex v) {
		return indegree.remove(v);
	}
	
	public HashSet<Vertex> getOutdegree() {
		return outdegree;
	}
	
	public boolean addOutdegreeVertex(Vertex v) {
		return outdegree.add(v);
	}
	
	public boolean removeOutdegreeVertex(Vertex v) {
		return outdegree.remove(v);
	}
	
	public HashSet<Vertex> getNeighborhood() {
		return neighborhood;
	}
	
	public boolean addNeighborhoodVertex(Vertex v) {
		return neighborhood.add(v);
	}
	
	public boolean removeNeighborhoodVertex(Vertex v) {
		return neighborhood.remove(v);
	}

}
