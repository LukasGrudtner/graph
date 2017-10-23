package tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;

import graph.DirectedGraph;
import graph.Graph;
import graph.UndirectedGraph;
import vertex.DGVertex;
import vertex.UGVertex;
import vertex.Vertex;

public class UndirectedGraphsTests {
	
	
	
	@org.junit.Test
	public void addVertex() {
		Graph graph = new UndirectedGraph();
		graph.addVertex(new Vertex("Example"));
		
		assertEquals(1, graph.order());
	}
	
	@org.junit.Test
	public void removeVertex() {
		Graph graph = new UndirectedGraph();
		Vertex v1 = new UGVertex("Vertex One");
		
		graph.addVertex(v1);
		graph.removeVertex(v1);
		
		assertEquals(0, graph.order());
	}
	
	@org.junit.Test
	public void connectTwoVertices() {
		
		Graph graph = new UndirectedGraph();
		Vertex v1 = new UGVertex("Vertex One");
		Vertex v2 = new UGVertex("Vertex Two");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		
		graph.connect(v1, v2);
		
		assertEquals(1, ((UndirectedGraph) graph).degree(v1));
		assertEquals(1, ((UndirectedGraph) graph).degree(v2));
	}
	
	@org.junit.Test
	public void disconnectTwoVertices() {
		
		Graph graph = new UndirectedGraph();
		Vertex v1 = new UGVertex("Vertex One");
		Vertex v2 = new UGVertex("Vertex Two");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		
		graph.connect(v1, v2);
		graph.disconnect(v2, v1);
		
		assertEquals(0, ((UndirectedGraph) graph).degree(v1));
		assertEquals(0, ((UndirectedGraph) graph).degree(v2));

	}
	
	@org.junit.Test
	public void neighborhood() {
		
		Graph graph = new UndirectedGraph();
		Vertex v1 = new UGVertex("Vertex One");
		Vertex v2 = new UGVertex("Vertex Two");
		Vertex v3 = new UGVertex("Vertex Three");
		Vertex v4 = new UGVertex("Vertex Four");
		
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
	
	@org.junit.Test
	public void regularity() {
		
		Graph graph = new UndirectedGraph();
		Vertex v1 = new UGVertex("Vertex One");
		Vertex v2 = new UGVertex("Vertex Two");
		Vertex v3 = new UGVertex("Vertex Three");
		Vertex v4 = new UGVertex("Vertex Four");
		
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
	
	@org.junit.Test
	public void completeness() {
		
		Graph graph = new UndirectedGraph();
		Vertex v1 = new UGVertex("Vertex One");
		Vertex v2 = new UGVertex("Vertex Two");
		Vertex v3 = new UGVertex("Vertex Three");
		Vertex v4 = new UGVertex("Vertex Four");
		
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
	
	@org.junit.Test
	public void degreeCycles() {
		
		Graph graph = new UndirectedGraph();
		Vertex v1 = new UGVertex("Vertex One");
		
		graph.addVertex(v1);
		graph.connect(v1, v1);
		
		assertEquals(2, ((UndirectedGraph) graph).degree(v1));
	}
	
	@org.junit.Test
	public void connectivity() {
		Graph graph = new UndirectedGraph();
		
		Vertex v1 = new UGVertex("Vertex One");
		Vertex v2 = new UGVertex("Vertex Two");
		Vertex v3 = new UGVertex("Vertex Three");
		
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
	
	@org.junit.Test
	public void connectivitySingleVertex() {
		Graph graph = new UndirectedGraph();
		Vertex v1 = new UGVertex("Vertex One");
		graph.addVertex(v1);
		
		assertEquals(true, graph.isConnected());
	}
}
