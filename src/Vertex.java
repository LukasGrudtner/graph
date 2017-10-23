
public class Vertex {
	
	private Object data;
	private int degree;
	
	public Vertex(Object data) {
		this.data = data;
		this.degree = 0;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public int getDegree() {
		return degree;
	}
	
	public void incDegree() {
		degree++;
	}
	
	public void decDegree() {
		degree--;
	}

}
