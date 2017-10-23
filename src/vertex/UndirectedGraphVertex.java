package vertex;

import java.util.HashSet;

public class UndirectedGraphVertex extends Vertex {
	
	private HashSet<Vertex> neighborhood;
	
	public UndirectedGraphVertex(Object data) {
		super(data);
		neighborhood = new HashSet<Vertex>();
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
