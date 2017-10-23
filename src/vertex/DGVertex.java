package vertex;

import java.util.HashSet;

public class DGVertex extends Vertex {

	private int indegree; 	/* Grau de recepção */
	private int outdegree; 	/* Grau de emissão */
	private HashSet<Vertex> predecessors;
	private HashSet<Vertex> successors;
	
	public DGVertex(Object data) {
		super(data);
		
		indegree = 0;
		outdegree = 0;
		
		predecessors = new HashSet<Vertex>();
		successors = new HashSet<Vertex>();
	}
	
	public HashSet<Vertex> getPredecessors() {
		return predecessors;
	}
	
	public boolean addPredecessor(Vertex v) {
		return predecessors.add(v);
	}
	
	public boolean removePredecessor(Vertex v) {
		return predecessors.remove(v);
	}
	
	public HashSet<Vertex> getSuccessors() {
		return successors;
	}
	
	public boolean addSuccessor(Vertex v) {
		return successors.add(v);
	}
	
	public boolean removeSuccessor(Vertex v) {
		return successors.remove(v);
	}
	
	public int getIndegree() {
		return indegree;
	}
	
	public void incIndegree() {
		indegree++;
	}
	
	public void decIndegree() {
		indegree--;
	}
	
	public int getOutdegree() {
		return outdegree;
	}
	
	public void incOutdegree() {
		outdegree++;
	}
	
	public void decOutdegree() {
		outdegree--;
	}
	

}
