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
	 * Ações básicas
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
	 * Ações derivadas
	 */
	
	/* Verifica se todos os vértices tem o mesmo grau. */
	/* Complexidade: n */
	public abstract boolean isRegular();
	
	/* Verifica se cada vértice de G está conectados a todos os outros vértices. */
	/* Complexidade: n */
	public abstract boolean isComplete();
	
	public abstract boolean isConnected();

	
	
	
	

}
