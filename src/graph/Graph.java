package graph;
import java.util.HashSet;
import java.util.Iterator;

import vertex.Vertex;

public abstract class Graph {
	
	protected HashSet<Vertex> vertices;
	
	public Graph() {
		vertices = new HashSet<Vertex>();
	}
	
	/*
	 * A��es b�sicas
	 */
	
	public void addVertex(Vertex v) {
		vertices.add(v);
	}
	
	public void removeVertex(Vertex v) {
		vertices.remove(v);
	}
	
	public abstract void connect(Vertex v1, Vertex v2);
	
	public abstract void disconnect(Vertex v1, Vertex v2);
	
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
	
	/*
	 * A��es derivadas
	 */
	
	/* Verifica se todos os v�rtices tem o mesmo grau. */
	/* Complexidade: n */
	public abstract boolean isRegular();
	
	/* Verifica se cada v�rtice de G est� conectados a todos os outros v�rtices. */
	/* Complexidade: n */
	public abstract boolean isComplete();
	
	public abstract boolean isConnected();

	
	
	
	

}
