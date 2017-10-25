package vertex;

/*
 * Implementa��o de um V�rtice gen�rico.
 */
public class Vertex {
	
	private Object data;	/* Um objeto qualquer que pode ser armazenado no v�rtice. */
	
	public Vertex(Object data) {
		this.data = data;
	}
	
	/* Retorna o objeto armazenado no v�rtice. */
	public Object getData() {
		return data;
	}
	
	/* Insere o objeto armazenado no v�rtice. */
	public void setData(Object data) {
		this.data = data;
	}
}
