package vertex;

import java.util.HashSet;

public class DirectedGraphVertex extends Vertex {

	private HashSet<Vertex> indegree;
	private HashSet<Vertex> outdegree;
	
	public DirectedGraphVertex(Object data) {
		super(data);
		indegree = new HashSet<Vertex>();
		outdegree = new HashSet<Vertex>();
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
	

}
