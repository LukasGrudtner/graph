package graph;
import java.util.HashSet;
import java.util.Iterator;

import vertex.Vertex;

/*
 * Implementação de um Grafo genérico.
 */
public abstract class Graph {
	
	/* Conjunto de vértices do grafo. */
	protected HashSet<Vertex> vertices;
	
	/* Inicialização do conjunto de vértices. */
	public Graph() {
		vertices = new HashSet<Vertex>();
	}
	
	/* Adiciona o vértice v ao grafo. */
	public void addVertex(Vertex v) {
		vertices.add(v);
	}
	
	/* Remove o vértice v do grafo. */
	public void removeVertex(Vertex v) {
		vertices.remove(v);
	}
	
	/* Conecta o vértice v1 ao vértice v2. Sua implementação difere para grafos orientados e não-orientados. */
	public abstract void connect(Vertex v1, Vertex v2);
	
	/* Desconecta o vértice v1 do vértice v2. Sua implementação difere para grafos orientados e não-orientados. */
	public abstract void disconnect(Vertex v1, Vertex v2);
	
	/* Retorna a ordem do grafo. */
	public int order() {
		return vertices.size();
	}
	
	/* Retorna o conjunto de vértices do grafo. */
	public HashSet<Vertex> vertices() {
		return vertices;
	}
	
	/* Retorna um vértice qualquer do grafo. */
	public Vertex oneVertex() {
		Iterator<Vertex> iterator = vertices.iterator();
		return iterator.next();
	}
	
	/* Retorna o valor booleano true se o grafo for regular. Caso contrário, retorna false. */
	public abstract boolean isRegular();
	
	/* Retorna o valor booleano true se o grafo for completo. Caso contrário, retorna false. */
	public abstract boolean isComplete();
	
	/* Retorna o valor booleano true se o grafo for conexo. Caso contrário, retorna false. */
	public abstract boolean isConnected();

	
	
	
	

}
