package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import graph.DirectedGraph;
import graph.Graph;
import vertex.DirectedGraphVertex;
import vertex.Vertex;

public class DirectedGraphsTests {
	
	/*
	 * Conjunto de testes para a classe de Grafos Orientados.
	 */
	
	/* Verifica a adição de um vértice ao grafo. */
	@org.junit.Test
	public void addVertex() {
		Graph graph = new DirectedGraph();
		graph.addVertex(new Vertex("Example"));
		
		assertEquals(1, graph.order());
	}
	
	/* Verifica a remoção de um vértice do grafo. */
	@org.junit.Test
	public void removeVertex() {
		Graph graph = new DirectedGraph();
		Vertex v1 = new DirectedGraphVertex("Vertex One");
		
		graph.addVertex(v1);
		graph.removeVertex(v1);
		
		assertEquals(0, graph.order());
	}
	
	/* Verifica a conexão de dois vértices. */
	@org.junit.Test
	public void connectTwoVertices() {
		
		Graph graph = new DirectedGraph();
		Vertex v1 = new DirectedGraphVertex("Vertex One");
		Vertex v2 = new DirectedGraphVertex("Vertex Two");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		
		graph.connect(v1, v2);
		
		assertEquals(1, ((DirectedGraph) graph).outdegree(v1));
		assertEquals(0, ((DirectedGraph) graph).indegree(v1));
		
		assertEquals(1, ((DirectedGraph) graph).indegree(v2));
		assertEquals(0, ((DirectedGraph) graph).outdegree(v2));
	}
	
	/* Verifica a desconexão de dois vértices. */
	@org.junit.Test
	public void disconnectTwoVertices() {
		
		Graph graph = new DirectedGraph();
		Vertex v1 = new DirectedGraphVertex("Vertex One");
		Vertex v2 = new DirectedGraphVertex("Vertex Two");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		
		graph.connect(v1, v2);
		graph.disconnect(v1, v2);
		
		assertEquals(0, ((DirectedGraph) graph).outdegree(v1));
		assertEquals(0, ((DirectedGraph) graph).indegree(v2));

	}
	
	/* Verifica predecessores e sucessores dos vértices. */
	@org.junit.Test
	public void predecessorsAndSuccessors() {
		
		Graph graph = new DirectedGraph();
		Vertex v1 = new DirectedGraphVertex("Vertex One");
		Vertex v2 = new DirectedGraphVertex("Vertex Two");
		Vertex v3 = new DirectedGraphVertex("Vertex Three");
		Vertex v4 = new DirectedGraphVertex("Vertex Four");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		
		graph.connect(v1, v2);
		graph.connect(v2, v1);
		graph.connect(v3, v4);
		graph.connect(v4, v2);
		graph.connect(v4, v1);
		graph.connect(v4, v4);
		
		int successorsSizeV4 = ((DirectedGraph) graph).successors(v4).size();
		assertEquals(3, successorsSizeV4);
		
		int predecessorsSizeV4 = ((DirectedGraph) graph).predecessors(v4).size();
		assertEquals(2, predecessorsSizeV4);
		
		boolean IsV4InPredecessors = ((DirectedGraph) graph).predecessors(v4).contains(v4);
		boolean IsV2InPredecessors = ((DirectedGraph) graph).predecessors(v4).contains(v2);
		assertEquals(true, IsV4InPredecessors);
		assertEquals(false, IsV2InPredecessors);
		
		graph.disconnect(v4, v4);
		IsV4InPredecessors = ((DirectedGraph) graph).predecessors(v4).contains(v4);
		assertEquals(false, IsV4InPredecessors);
	}
	
	
	/* Verifica a regularidade do grafo. */
	@org.junit.Test
	public void regularity() {
		
		Graph graph = new DirectedGraph();
		Vertex v1 = new DirectedGraphVertex("Vertex One");
		Vertex v2 = new DirectedGraphVertex("Vertex Two");
		Vertex v3 = new DirectedGraphVertex("Vertex Three");
		Vertex v4 = new DirectedGraphVertex("Vertex Four");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		
		graph.connect(v1, v2);
		graph.connect(v2, v3);
		graph.connect(v3, v4);
		graph.connect(v4, v1);
		
		assertEquals(1, ((DirectedGraph) graph).indegree(v3));
		assertEquals(true, graph.isRegular());
	}
	
	/* Verifica a completude do grafo. */
	@org.junit.Test
	public void completeness() {
		
		Graph graph = new DirectedGraph();
		Vertex v1 = new DirectedGraphVertex("Vertex One");
		Vertex v2 = new DirectedGraphVertex("Vertex Two");
		Vertex v3 = new DirectedGraphVertex("Vertex Three");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		
		graph.connect(v1, v2);
		graph.connect(v1, v3);
		graph.connect(v2, v1);
		graph.connect(v2, v3);
		graph.connect(v3, v1);
		assertEquals(false, graph.isComplete());
		
		graph.connect(v3, v2);
		assertEquals(true, graph.isComplete());
	}
	
	/* Verifica o grau de um vértice com laço. */
	@org.junit.Test
	public void indegreeAndOutdegreeLoop() {
		
		Graph graph = new DirectedGraph();
		Vertex v1 = new DirectedGraphVertex("Vertex One");
		
		graph.addVertex(v1);
		graph.connect(v1, v1);
		
		assertEquals(1, ((DirectedGraph) graph).indegree(v1));
		assertEquals(1, ((DirectedGraph) graph).outdegree(v1));
	}
	
	/* Verifica a adjacência de um vértice com laço. */
	@org.junit.Test
	public void predecessorAndSuccessorLoop() {
		
		Graph graph = new DirectedGraph();
		Vertex v1 = new DirectedGraphVertex("Vertex One");
		Vertex v2 = new DirectedGraphVertex("Vertex Two");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		
		graph.connect(v1, v1);
		graph.connect(v1, v2);
		graph.connect(v2, v1);
		
		assertEquals(2, ((DirectedGraphVertex) v1).successors().size());
		assertEquals(true, ((DirectedGraphVertex) v1).successors().contains(v1));
		
		assertEquals(2, ((DirectedGraphVertex) v1).predecessors().size());
		assertEquals(true, ((DirectedGraphVertex) v1).predecessors().contains(v1));
	}
	
	/* Verifica o fecho transitivo direto do grafo. */
	@org.junit.Test
	public void directTransitiveClosure() {
		Graph graph = new DirectedGraph();
		Vertex v0 = new DirectedGraphVertex("Vertex Zero");
		Vertex v1 = new DirectedGraphVertex("Vertex One");
		Vertex v2 = new DirectedGraphVertex("Vertex Two");
		Vertex v3 = new DirectedGraphVertex("Vertex Three");
		Vertex v4 = new DirectedGraphVertex("Vertex Four");
		Vertex v5 = new DirectedGraphVertex("Vertex Five");
		Vertex v6 = new DirectedGraphVertex("Vertex Six");
		Vertex v7 = new DirectedGraphVertex("Vertex Seven");
		
		graph.addVertex(v0);
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		graph.addVertex(v5);
		graph.addVertex(v6);
		graph.addVertex(v7);
		
		graph.connect(v0, v1);
		graph.connect(v1, v2);
		graph.connect(v1, v3);
		graph.connect(v2, v4);
		graph.connect(v2, v5);
		graph.connect(v3, v6);
		graph.connect(v3, v7);
		
		HashSet<Vertex> DTC_v0 = ((DirectedGraph) graph).directTransitiveClosure(v0);
		HashSet<Vertex> DTC_v1 = ((DirectedGraph) graph).directTransitiveClosure(v1);
		HashSet<Vertex> DTC_v2 = ((DirectedGraph) graph).directTransitiveClosure(v2);
		HashSet<Vertex> DTC_v3 = ((DirectedGraph) graph).directTransitiveClosure(v3);
		HashSet<Vertex> DTC_v7 = ((DirectedGraph) graph).directTransitiveClosure(v7);
		
		/* DTC of v0 */
		assertEquals(true, DTC_v0.contains(v0));
		assertEquals(true, DTC_v0.contains(v1));
		assertEquals(8, DTC_v0.size());
		
		/* DTC of v1 */
		assertEquals(true, DTC_v1.contains(v2));
		assertEquals(true, DTC_v1.contains(v3));
		assertEquals(true, DTC_v1.contains(v4));
		assertEquals(true, DTC_v1.contains(v5));
		assertEquals(true, DTC_v1.contains(v6));
		assertEquals(true, DTC_v1.contains(v7));
		assertEquals(7, DTC_v1.size());
		
		/* DTC of v2 */
		assertEquals(false, DTC_v2.contains(v1));
		assertEquals(true, DTC_v2.contains(v4));
		assertEquals(true, DTC_v2.contains(v5));
		assertEquals(3, DTC_v2.size());

		/* DTC of v3 */
		assertEquals(true, DTC_v3.contains(v6));
		assertEquals(true, DTC_v3.contains(v7));
		assertEquals(3, DTC_v3.size());
		
		/* DTC of v7 */
		assertEquals(true, DTC_v7.contains(v7));
		assertEquals(1, DTC_v7.size());
	}
	
	/* Verifica o fecho transitivo indireto do grafo. */
	@org.junit.Test
	public void indirectTransitiveClosure() {
		Graph graph = new DirectedGraph();
		Vertex v0 = new DirectedGraphVertex("Vertex Zero");
		Vertex v1 = new DirectedGraphVertex("Vertex One");
		Vertex v2 = new DirectedGraphVertex("Vertex Two");
		Vertex v3 = new DirectedGraphVertex("Vertex Three");
		Vertex v4 = new DirectedGraphVertex("Vertex Four");
		Vertex v5 = new DirectedGraphVertex("Vertex Five");
		Vertex v6 = new DirectedGraphVertex("Vertex Six");
		Vertex v7 = new DirectedGraphVertex("Vertex Seven");
		
		graph.addVertex(v0);
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		graph.addVertex(v5);
		graph.addVertex(v6);
		graph.addVertex(v7);
		
		graph.connect(v1, v0);
		graph.connect(v2, v1);
		graph.connect(v2, v2);
		graph.connect(v3, v1);
		graph.connect(v4, v2);
		graph.connect(v5, v2);
		graph.connect(v6, v3);
		graph.connect(v7, v3);
		graph.connect(v7, v4);
		graph.connect(v7, v5);
		graph.connect(v7, v6);
		graph.connect(v7, v7);
		
		HashSet<Vertex> DTC_v0 = ((DirectedGraph) graph).directTransitiveClosure(v0);
		HashSet<Vertex> DTC_v1 = ((DirectedGraph) graph).directTransitiveClosure(v1);
		HashSet<Vertex> DTC_v2 = ((DirectedGraph) graph).directTransitiveClosure(v2);
		HashSet<Vertex> DTC_v4 = ((DirectedGraph) graph).directTransitiveClosure(v4);
		HashSet<Vertex> DTC_v7 = ((DirectedGraph) graph).directTransitiveClosure(v7);
		
		/* DTC of v0 */
		assertEquals(true, DTC_v0.contains(v0));
		assertEquals(false, DTC_v0.contains(v1));
		assertEquals(1, DTC_v0.size());
		
		/* DTC of v1 */
		assertEquals(true, DTC_v1.contains(v0));
		assertEquals(false, DTC_v1.contains(v3));
		assertEquals(2, DTC_v1.size());
		
		/* DTC of v2 */
		assertEquals(true, DTC_v2.contains(v0));
		assertEquals(true, DTC_v2.contains(v1));
		assertEquals(3, DTC_v2.size());

		/* DTC of v4 */
		assertEquals(true, DTC_v4.contains(v0));
		assertEquals(true, DTC_v4.contains(v1));
		assertEquals(true, DTC_v4.contains(v2));
		assertEquals(true, DTC_v4.contains(v4));
		assertEquals(4, DTC_v4.size());
		
		/* DTC of v7 */
		assertEquals(true, DTC_v7.contains(v0));
		assertEquals(true, DTC_v7.contains(v1));
		assertEquals(true, DTC_v7.contains(v2));
		assertEquals(true, DTC_v7.contains(v3));
		assertEquals(true, DTC_v7.contains(v4));
		assertEquals(true, DTC_v7.contains(v5));
		assertEquals(true, DTC_v7.contains(v6));
		assertEquals(true, DTC_v7.contains(v7));
		assertEquals(8, DTC_v7.size());
	}
	
	/* Verifica a conectividade do grafo. */
	@org.junit.Test
	public void connectivity() {
		Graph graph = new DirectedGraph();
		
		Vertex v1 = new DirectedGraphVertex("Vertex One");
		Vertex v2 = new DirectedGraphVertex("Vertex Two");
		Vertex v3 = new DirectedGraphVertex("Vertex Three");
		
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
	
	/* Verifica a conectividade de um grafo com um único vértice. */
	@org.junit.Test
	public void connectivitySingleVertex() {
		Graph graph = new DirectedGraph();
		Vertex v1 = new DirectedGraphVertex("Vertex One");
		graph.addVertex(v1);
		
		assertEquals(true, graph.isConnected());
	}
	
	/* Verifica a completude de um grafo com um único vértice. */
	@org.junit.Test
	public void completenessSingleVertex() {
		Graph graph = new DirectedGraph();
		Vertex v1 = new DirectedGraphVertex("Vertex One");
		graph.addVertex(v1);
		
		assertEquals(true, graph.isComplete());
	}
	
	/* Verifica a ordenação topológica. */
	@org.junit.Test
	public void topologicalSort() {
		Graph graph = new DirectedGraph();
		Vertex v1 = new DirectedGraphVertex("Vertex One");
		Vertex v2 = new DirectedGraphVertex("Vertex Two");
		Vertex v3 = new DirectedGraphVertex("Vertex Three");
		Vertex v4 = new DirectedGraphVertex("Vertex Four");
		Vertex v5 = new DirectedGraphVertex("Vertex Five");
		Vertex v6 = new DirectedGraphVertex("Vertex Six");
		Vertex v7 = new DirectedGraphVertex("Vertex Seven");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		graph.addVertex(v5);
		graph.addVertex(v6);
		graph.addVertex(v7);
		
		graph.connect(v1, v3);
		graph.connect(v1, v4);
		graph.connect(v2, v3);
		graph.connect(v2, v6);
		graph.connect(v3, v5);
		graph.connect(v4, v5);
		graph.connect(v5, v7);
		graph.connect(v6, v7);
		
		ArrayList<Vertex> list = ((DirectedGraph) graph).topologicalSort();
		
		assertEquals(7, list.size());
		assertEquals(true, list.get(0).equals(v1) || list.get(0).equals(v2));
		assertEquals(true, list.get(5).equals(v5) || list.get(5).equals(v6));
		assertEquals(true, list.get(6).equals(v7));
	}
	
	/* Verifica se, ao remover um vértice conectado com os outros, suas ligações são rompidas. */
	@org.junit.Test
	public void removeAVertexConnectedWithOtherVertices() {
		Graph graph = new DirectedGraph();
		Vertex v1 = new DirectedGraphVertex("Vertex One");
		Vertex v2 = new DirectedGraphVertex("Vertex Two");
		Vertex v3 = new DirectedGraphVertex("Vertex Three");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		
		graph.connect(v1, v1);
		graph.connect(v1, v2);
		graph.connect(v1, v3);
		graph.connect(v2, v3);
		
		assertEquals(3, ((DirectedGraphVertex) v1).outdegree());
		assertEquals(2, ((DirectedGraphVertex) v3).indegree());
		
		graph.removeVertex(v2);
		
		assertEquals(2, ((DirectedGraphVertex) v1).outdegree());
		assertEquals(1, ((DirectedGraphVertex) v3).indegree());
		
		assertEquals(2, ((DirectedGraphVertex) v1).successors().size());
		assertEquals(false, ((DirectedGraphVertex) v1).successors().contains(v2));
		assertEquals(1, ((DirectedGraphVertex) v3).predecessors().size());
		assertEquals(false, ((DirectedGraphVertex) v3).predecessors().contains(v2));
	}
	
	/* Verificar quando se testa conectar e desconectar um vértice que não faz parte do grafo. */
	@org.junit.Test
	public void connectAndDisconnectAVertexThatIsNotPartOfTheGraph() {
		Graph graph = new DirectedGraph();
		Vertex v1 = new DirectedGraphVertex("Vertex One");
		Vertex v2 = new DirectedGraphVertex("Vertex Two");
		
		graph.addVertex(v1);
		
		assertEquals(0, ((DirectedGraphVertex) v1).outdegree());
		
		graph.connect(v1, v2);
		
		assertEquals(0, ((DirectedGraphVertex) v1).outdegree());
		
		graph.disconnect(v1, v2);
		
		assertEquals(0, ((DirectedGraphVertex) v1).outdegree());
	}
	
	/* Verificar o grau dos vértices quando se tenta conectar dois deles já conectados. */
	@org.junit.Test
	public void connectTwoVerticesAlreadyConnected() {
		Graph graph = new DirectedGraph();
		Vertex v1 = new DirectedGraphVertex("Vertex One");
		Vertex v2 = new DirectedGraphVertex("Vertex Two");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		
		graph.connect(v1, v2);
		graph.connect(v1, v2);
		
		assertEquals(1, ((DirectedGraphVertex) v1).outdegree());
		assertEquals(1, ((DirectedGraphVertex) v2).indegree());
	}
	
	/* Verificar se um vértice faz parte de seu fecho transitivo. */
	@org.junit.Test
	public void isAVertexPartOfYourTransitiveClosure() {
		Graph graph = new DirectedGraph();
		Vertex v0 = new DirectedGraphVertex("Vertex Zero");
		Vertex v1 = new DirectedGraphVertex("Vertex One");
		Vertex v2 = new DirectedGraphVertex("Vertex Two");
		
		graph.addVertex(v0);
		graph.addVertex(v1);
		graph.addVertex(v2);
		
		graph.connect(v0, v1);
		graph.connect(v1, v2);
		
		assertEquals(false, ((DirectedGraph) graph).directTransitiveClosure(v1).contains(v1));
		assertEquals(false, ((DirectedGraph) graph).indirectTransitiveClosure(v1).contains(v1));
	}
}
