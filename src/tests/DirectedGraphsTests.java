package tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;

import graph.DirectedGraph;
import graph.Graph;
import graph.UndirectedGraph;
import vertex.DGVertex;
import vertex.UGVertex;
import vertex.Vertex;

public class DirectedGraphsTests {
	
	
	
	@org.junit.Test
	public void addVertex() {
		Graph graph = new DirectedGraph();
		graph.addVertex(new Vertex("Example"));
		
		assertEquals(1, graph.order());
	}
	
	@org.junit.Test
	public void removeVertex() {
		Graph graph = new DirectedGraph();
		Vertex v1 = new UGVertex("Vertex One");
		
		graph.addVertex(v1);
		graph.removeVertex(v1);
		
		assertEquals(0, graph.order());
	}
	
	@org.junit.Test
	public void connectTwoVertices() {
		
		Graph graph = new DirectedGraph();
		Vertex v1 = new DGVertex("Vertex One");
		Vertex v2 = new DGVertex("Vertex Two");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		
		graph.connect(v1, v2);
		
		assertEquals(1, ((DirectedGraph) graph).outdegree(v1));
		assertEquals(0, ((DirectedGraph) graph).indegree(v1));
		
		assertEquals(1, ((DirectedGraph) graph).indegree(v2));
		assertEquals(0, ((DirectedGraph) graph).outdegree(v2));
	}
	
	@org.junit.Test
	public void disconnectTwoVertices() {
		
		Graph graph = new DirectedGraph();
		Vertex v1 = new DGVertex("Vertex One");
		Vertex v2 = new DGVertex("Vertex Two");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		
		graph.connect(v1, v2);
		graph.disconnect(v1, v2);
		
		assertEquals(0, ((DirectedGraph) graph).outdegree(v1));
		assertEquals(0, ((DirectedGraph) graph).indegree(v2));

	}
	
	@org.junit.Test
	public void predecessorsAndSuccessors() {
		
		Graph graph = new DirectedGraph();
		Vertex v1 = new DGVertex("Vertex One");
		Vertex v2 = new DGVertex("Vertex Two");
		Vertex v3 = new DGVertex("Vertex Three");
		Vertex v4 = new DGVertex("Vertex Four");
		
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
	
	@org.junit.Test
	public void regularity() {
		
		Graph graph = new DirectedGraph();
		Vertex v1 = new DGVertex("Vertex One");
		Vertex v2 = new DGVertex("Vertex Two");
		Vertex v3 = new DGVertex("Vertex Three");
		Vertex v4 = new DGVertex("Vertex Four");
		
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
	
	@org.junit.Test
	public void completeness() {
		
		Graph graph = new DirectedGraph();
		Vertex v1 = new DGVertex("Vertex One");
		Vertex v2 = new DGVertex("Vertex Two");
		Vertex v3 = new DGVertex("Vertex Three");
		
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
	
	@org.junit.Test
	public void degreeCycles() {
		
		Graph graph = new DirectedGraph();
		Vertex v1 = new DGVertex("Vertex One");
		
		graph.addVertex(v1);
		graph.connect(v1, v1);
		
		assertEquals(1, ((DirectedGraph) graph).indegree(v1));
		assertEquals(1, ((DirectedGraph) graph).outdegree(v1));

	}
}
