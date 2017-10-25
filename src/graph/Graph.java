package graph;
import java.util.HashSet;
import java.util.Iterator;

import vertex.Vertex;

/*
 * Implementa��o de um Grafo gen�rico.
 */
public abstract class Graph {
	
	/* Conjunto de v�rtices do grafo. */
	protected HashSet<Vertex> vertices;
	
	/* Inicializa��o do conjunto de v�rtices. */
	public Graph() {
		vertices = new HashSet<Vertex>();
	}
	
	/* Adiciona o v�rtice v ao grafo. */
	public void addVertex(Vertex v) {
		vertices.add(v);
	}
	
	/* Remove o v�rtice v do grafo. */
	public void removeVertex(Vertex v) {
		vertices.remove(v);
	}
	
	/* Conecta o v�rtice v1 ao v�rtice v2. Sua implementa��o difere para grafos orientados e n�o-orientados. */
	public abstract void connect(Vertex v1, Vertex v2);
	
	/* Desconecta o v�rtice v1 do v�rtice v2. Sua implementa��o difere para grafos orientados e n�o-orientados. */
	public abstract void disconnect(Vertex v1, Vertex v2);
	
	/* Retorna a ordem do grafo. */
	public int order() {
		return vertices.size();
	}
	
	/* Retorna o conjunto de v�rtices do grafo. */
	public HashSet<Vertex> vertices() {
		return vertices;
	}
	
	/* Retorna um v�rtice qualquer do grafo. */
	public Vertex oneVertex() {
		Iterator<Vertex> iterator = vertices.iterator();
		return iterator.next();
	}
	
	/* Retorna o valor booleano true se o grafo for regular. Caso contr�rio, retorna false. */
	public abstract boolean isRegular();
	
	/* Retorna o valor booleano true se o grafo for completo. Caso contr�rio, retorna false. */
	public abstract boolean isComplete();
	
	/* Retorna o valor booleano true se o grafo for conexo. Caso contr�rio, retorna false. */
	public abstract boolean isConnected();

	
	
	
	

}
