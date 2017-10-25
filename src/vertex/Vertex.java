package vertex;

/*
 * Implementação de um Vértice genérico.
 */
public class Vertex {
	
	private Object data;	/* Um objeto qualquer que pode ser armazenado no vértice. */
	
	public Vertex(Object data) {
		this.data = data;
	}
	
	/* Retorna o objeto armazenado no vértice. */
	public Object getData() {
		return data;
	}
	
	/* Insere o objeto armazenado no vértice. */
	public void setData(Object data) {
		this.data = data;
	}
}
