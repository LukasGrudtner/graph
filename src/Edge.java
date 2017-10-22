
public class Edge {
	
	private Vertex[] conection;
	
	public Edge(Vertex v, Vertex w) {
		this.conection = new Vertex[2];
		this.conection[0] = v;
		this.conection[1] = w;
	}
	
	public Vertex[] getVertices() {
		return conection;
	}
	
	public void setVertices(Vertex[] conection) {
		this.conection = conection;
	}

}
