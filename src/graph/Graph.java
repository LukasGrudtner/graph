package graph;
import java.util.HashSet;
import java.util.Iterator;

import vertex.Vertex;

public abstract class Graph {
	
	protected HashSet<Vertex> vertices;
	protected HashSet<Edge> edges;
	
	public Graph() {
		vertices = new HashSet<Vertex>();
		edges = new HashSet<Edge>();
	}
	
	/*
	 * A��es b�sicas
	 */
	
	public boolean addVertex(Vertex v) {
		return vertices.add(v);
	}
	
	public boolean removeVertex(Vertex v) {
		return vertices.remove(v);
	}
	
	public abstract boolean connect(Vertex v1, Vertex v2);
	
	public abstract boolean disconnect(Vertex v1, Vertex v2);
	
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
	
	public boolean isConnected() {
		
		/* TODO Implementar */
		return true;
	}

	
	
	
	

}
