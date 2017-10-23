package vertex;

import java.util.HashSet;

public class UGVertex extends Vertex {
	
	private int degree;
	private HashSet<Vertex> neighborhood;
	
	public UGVertex(Object data) {
		super(data);
		degree = 0;
		neighborhood = new HashSet<Vertex>();
	}

	public HashSet<Vertex> neighborhood() {
		return neighborhood;
	}
	
	public boolean addNeighbor(Vertex v) {
		return neighborhood.add(v);
	}
	
	public boolean removeNeighbor(Vertex v) {
		return neighborhood.remove(v);
	}
	
	public int degree() {
		return degree;
	}
	
	public void incDegree() {
		degree++;
	}
	
	public void decDegree() {
		degree--;
	}
}
