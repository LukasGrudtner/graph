package tests;

import static org.junit.Assert.assertEquals;

import graph.Graph;
import graph.UndirectedGraph;
import vertex.UndirectedGraphVertex;
import vertex.Vertex;

public class UndirectedGraphsTests {
	
	/*
	 * Conjunto de testes para a classe de Grafos N�o Orientados.
	 */
	
	/* Verifica a adi��o de um v�rtice ao grafo. */
	@org.junit.Test
	public void addVertex() {
		Graph graph = new UndirectedGraph();
		graph.addVertex(new Vertex("Example"));
		
		assertEquals(1, graph.order());
	}
	
	/* Verifica a remo��o de um v�rtice do grafo. */
	@org.junit.Test
	public void removeVertex() {
		Graph graph = new UndirectedGraph();
		Vertex v1 = new UndirectedGraphVertex("Vertex One");
		
		graph.addVertex(v1);
		graph.removeVertex(v1);
		
		assertEquals(0, graph.order());
	}
	
	/* Verifica a conex�o de dois v�rtices. */
	@org.junit.Test
	public void connectTwoVertices() {
		
		Graph graph = new UndirectedGraph();
		Vertex v1 = new UndirectedGraphVertex("Vertex One");
		Vertex v2 = new UndirectedGraphVertex("Vertex Two");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		
		graph.connect(v1, v2);
		
		assertEquals(1, ((UndirectedGraph) graph).degree(v1));
		assertEquals(1, ((UndirectedGraph) graph).degree(v2));
	}
	
	/* Verifica a desconex�o de dois v�rtices. */
	@org.junit.Test
	public void disconnectTwoVertices() {
		
		Graph graph = new UndirectedGraph();
		Vertex v1 = new UndirectedGraphVertex("Vertex One");
		Vertex v2 = new UndirectedGraphVertex("Vertex Two");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		
		graph.connect(v1, v2);
		graph.disconnect(v2, v1);
		
		assertEquals(0, ((UndirectedGraph) graph).degree(v1));
		assertEquals(0, ((UndirectedGraph) graph).degree(v2));

	}
	
	/* Verifica a adjac�ncia dos v�rtices. */
	@org.junit.Test
	public void neighborhood() {
		
		Graph graph = new UndirectedGraph();
		Vertex v1 = new UndirectedGraphVertex("Vertex One");
		Vertex v2 = new UndirectedGraphVertex("Vertex Two");
		Vertex v3 = new UndirectedGraphVertex("Vertex Three");
		Vertex v4 = new UndirectedGraphVertex("Vertex Four");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		
		graph.connect(v1, v2);
		graph.connect(v3, v4);
		graph.connect(v4, v2);
		
		int neighborhoodSize = ((UndirectedGraph) graph).neighborhood(v4).size();
		assertEquals(2, neighborhoodSize);
				
		boolean containsV2 = ((UndirectedGraph) graph).neighborhood(v4).contains(v2);
		boolean containsV3 = ((UndirectedGraph) graph).neighborhood(v4).contains(v3);
		assertEquals(true, containsV2 && containsV3);
	}
	
	/* Verifica a regularidade do grafo. */
	@org.junit.Test
	public void regularity() {
		
		Graph graph = new UndirectedGraph();
		Vertex v1 = new UndirectedGraphVertex("Vertex One");
		Vertex v2 = new UndirectedGraphVertex("Vertex Two");
		Vertex v3 = new UndirectedGraphVertex("Vertex Three");
		Vertex v4 = new UndirectedGraphVertex("Vertex Four");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		
		graph.connect(v1, v2);
		graph.connect(v2, v3);
		graph.connect(v3, v4);
		graph.connect(v4, v1);
		
		assertEquals(2, ((UndirectedGraph) graph).degree(v3));
		assertEquals(true, graph.isRegular());
	}
	
	/* Verifica a completude do grafo. */
	@org.junit.Test
	public void completeness() {
		
		Graph graph = new UndirectedGraph();
		Vertex v1 = new UndirectedGraphVertex("Vertex One");
		Vertex v2 = new UndirectedGraphVertex("Vertex Two");
		Vertex v3 = new UndirectedGraphVertex("Vertex Three");
		Vertex v4 = new UndirectedGraphVertex("Vertex Four");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		
		graph.connect(v1, v2);
		graph.connect(v1, v3);
		graph.connect(v1, v4);
		graph.connect(v2, v3);
		graph.connect(v2, v4);
		assertEquals(false, graph.isComplete());
		
		graph.connect(v3, v4);
		assertEquals(true, graph.isComplete());
	}
	
	/* Verifica o grau de um v�rtice com la�o. */
	@org.junit.Test
	public void degreeLoop() {
		
		Graph graph = new UndirectedGraph();
		Vertex v1 = new UndirectedGraphVertex("Vertex One");
		
		graph.addVertex(v1);

		graph.connect(v1, v1);
		
		assertEquals(2, ((UndirectedGraph) graph).degree(v1));
	}
	
	/* Verifica a adjac�ncia de um v�rtice com la�o. */
	@org.junit.Test
	public void neighborhoodLoop() {
		
		Graph graph = new UndirectedGraph();
		Vertex v1 = new UndirectedGraphVertex("Vertex One");
		Vertex v2 = new UndirectedGraphVertex("Vertex Two");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		
		graph.connect(v1, v1);
		graph.connect(v1, v2);
		
		assertEquals(2, ((UndirectedGraphVertex) v1).neighborhood().size());
		assertEquals(true, ((UndirectedGraphVertex) v1).neighborhood().contains(v1));
	}
	
	/* Verifica a conectividade do grafo. */
	@org.junit.Test
	public void connectivity() {
		Graph graph = new UndirectedGraph();
		
		Vertex v1 = new UndirectedGraphVertex("Vertex One");
		Vertex v2 = new UndirectedGraphVertex("Vertex Two");
		Vertex v3 = new UndirectedGraphVertex("Vertex Three");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		
		graph.connect(v1, v2);
		assertEquals(false, graph.isConnected());
		
		graph.connect(v2, v3);
		assertEquals(true, graph.isConnected());
		
		graph.removeVertex(v2);
		assertEquals(false, graph.isConnected());
	}
	
	/* Verifica a conectividade de um grafo com um �nico v�rtice. */
	@org.junit.Test
	public void connectivitySingleVertex() {
		Graph graph = new UndirectedGraph();
		Vertex v1 = new UndirectedGraphVertex("Vertex One");
		graph.addVertex(v1);
		
		assertEquals(true, graph.isConnected());
	}
	
	/* Verifica a completude de um grafo com um �nico v�rtice. */
	@org.junit.Test
	public void completenessSingleVertex() {
		Graph graph = new UndirectedGraph();
		Vertex v1 = new UndirectedGraphVertex("Vertex One");
		graph.addVertex(v1);
		
		assertEquals(true, graph.isComplete());
	}
	
	/* Verifica a condi��o para que o grafo seja uma �rvore. */
	@org.junit.Test
	public void isTree() {
		Graph graph = new UndirectedGraph();
		
		Vertex v1 = new UndirectedGraphVertex("Vertex One");
		Vertex v2 = new UndirectedGraphVertex("Vertex Two");
		Vertex v3 = new UndirectedGraphVertex("Vertex Three");
		Vertex v4 = new UndirectedGraphVertex("Vertex Four");
		Vertex v5 = new UndirectedGraphVertex("Vertex Five");
		Vertex v6 = new UndirectedGraphVertex("Vertex Six");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		graph.addVertex(v5);
		graph.addVertex(v6);
		
		graph.connect(v1, v2);
		graph.connect(v1, v3);
		graph.connect(v1, v4);
		graph.connect(v2, v5);
		graph.connect(v3, v6);
		assertEquals(true, ((UndirectedGraph) graph).isTree());
		
		graph.connect(v4, v6);
		assertEquals(false, ((UndirectedGraph) graph).isTree());
		
		graph.disconnect(v6, v3);
		assertEquals(true, ((UndirectedGraph) graph).isTree());
	}
	
	/* Verifica a condi��o para que o grafo de um �nico v�rtice seja uma �rvore. */
	@org.junit.Test
	public void isTreeSingleVertice() {
		Graph graph = new UndirectedGraph();
		
		Vertex v1 = new UndirectedGraphVertex("Vertex One");
		graph.addVertex(v1);
		
		assertEquals(true, ((UndirectedGraph) graph).isTree());
	}
	
	/* Verifica se, ao remover um v�rtice conectado com os outros, suas liga��es s�o rompidas. */
	@org.junit.Test
	public void removeAVertexConnectedWithOtherVertices() {
		Graph graph = new UndirectedGraph();
		Vertex v1 = new UndirectedGraphVertex("Vertex One");
		Vertex v2 = new UndirectedGraphVertex("Vertex Two");
		Vertex v3 = new UndirectedGraphVertex("Vertex Three");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		
		graph.connect(v1, v1);
		graph.connect(v1, v2);
		graph.connect(v1, v3);
		graph.connect(v2, v3);
		
		assertEquals(4, ((UndirectedGraphVertex) v1).degree());
		assertEquals(2, ((UndirectedGraphVertex) v3).degree());
		
		graph.removeVertex(v2);
		
		assertEquals(3, ((UndirectedGraphVertex) v1).degree());
		assertEquals(1, ((UndirectedGraphVertex) v3).degree());
		
		assertEquals(2, ((UndirectedGraphVertex) v1).neighborhood().size());
		assertEquals(false, ((UndirectedGraphVertex) v1).neighborhood().contains(v2));
		assertEquals(1, ((UndirectedGraphVertex) v3).neighborhood().size());
		assertEquals(false, ((UndirectedGraphVertex) v3).neighborhood().contains(v2));
	}
	
	/* Verificar quando se testa conectar e desconectar um v�rtice que n�o faz parte do grafo. */
	@org.junit.Test
	public void connectAndDisconnectAVertexThatIsNotPartOfTheGraph() {
		Graph graph = new UndirectedGraph();
		Vertex v1 = new UndirectedGraphVertex("Vertex One");
		Vertex v2 = new UndirectedGraphVertex("Vertex Two");
		
		graph.addVertex(v1);
		
		assertEquals(0, ((UndirectedGraphVertex) v1).degree());
		
		graph.connect(v1, v2);
		
		assertEquals(0, ((UndirectedGraphVertex) v1).degree());
		
		graph.disconnect(v1, v2);
		
		assertEquals(0, ((UndirectedGraphVertex) v1).degree());
	}
	
	/* Verificar o grau dos v�rtices quando se tenta conectar dois deles j� conectados. */
	@org.junit.Test
	public void connectTwoVerticesAlreadyConnected() {
		Graph graph = new UndirectedGraph();
		Vertex v1 = new UndirectedGraphVertex("Vertex One");
		Vertex v2 = new UndirectedGraphVertex("Vertex Two");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		
		graph.connect(v1, v2);
		graph.connect(v1, v2);
		graph.connect(v2, v1);
		
		assertEquals(1, ((UndirectedGraphVertex) v1).degree());
		assertEquals(1, ((UndirectedGraphVertex) v2).degree());
	}
	
	/* Verificar se um v�rtice faz parte de seu fecho transitivo. */
	@org.junit.Test
	public void isAVertexPartOfYourTransitiveClosure() {
		Graph graph = new UndirectedGraph();
		Vertex v1 = new UndirectedGraphVertex("Vertex One");
		Vertex v2 = new UndirectedGraphVertex("Vertex Two");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		
		graph.connect(v1, v2);
		
		assertEquals(true, ((UndirectedGraph) graph).transitiveClosure(v1).contains(v1));
	}
	
	
	
}
