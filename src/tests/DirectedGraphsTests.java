package tests;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

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
	
	@org.junit.Test
	public void directTransitiveClosure() {
		Graph graph = new DirectedGraph();
		Vertex v0 = new DGVertex("Vertex Zero");
		Vertex v1 = new DGVertex("Vertex One");
		Vertex v2 = new DGVertex("Vertex Two");
		Vertex v3 = new DGVertex("Vertex Three");
		Vertex v4 = new DGVertex("Vertex Four");
		Vertex v5 = new DGVertex("Vertex Five");
		Vertex v6 = new DGVertex("Vertex Six");
		Vertex v7 = new DGVertex("Vertex Seven");
		
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
		
		HashSet<Vertex> DTC_v0 = ((DirectedGraph) graph).directTrasitiveClosure(v0);
		HashSet<Vertex> DTC_v1 = ((DirectedGraph) graph).directTrasitiveClosure(v1);
		HashSet<Vertex> DTC_v2 = ((DirectedGraph) graph).directTrasitiveClosure(v2);
		HashSet<Vertex> DTC_v3 = ((DirectedGraph) graph).directTrasitiveClosure(v3);
		HashSet<Vertex> DTC_v7 = ((DirectedGraph) graph).directTrasitiveClosure(v7);
		
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
	
	@org.junit.Test
	public void indirectTransitiveClosure() {
		Graph graph = new DirectedGraph();
		Vertex v0 = new DGVertex("Vertex Zero");
		Vertex v1 = new DGVertex("Vertex One");
		Vertex v2 = new DGVertex("Vertex Two");
		Vertex v3 = new DGVertex("Vertex Three");
		Vertex v4 = new DGVertex("Vertex Four");
		Vertex v5 = new DGVertex("Vertex Five");
		Vertex v6 = new DGVertex("Vertex Six");
		Vertex v7 = new DGVertex("Vertex Seven");
		
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
		
		HashSet<Vertex> DTC_v0 = ((DirectedGraph) graph).directTrasitiveClosure(v0);
		HashSet<Vertex> DTC_v1 = ((DirectedGraph) graph).directTrasitiveClosure(v1);
		HashSet<Vertex> DTC_v2 = ((DirectedGraph) graph).directTrasitiveClosure(v2);
		HashSet<Vertex> DTC_v4 = ((DirectedGraph) graph).directTrasitiveClosure(v4);
		HashSet<Vertex> DTC_v7 = ((DirectedGraph) graph).directTrasitiveClosure(v7);
		
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
}
