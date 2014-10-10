package graphs.dijikstra.model;

public class Neighbor {

	private Vertex vertex;
	private Integer cost;
	
	public Neighbor(Vertex vertex,Integer cost) {
		this.vertex = vertex;
		this.cost = cost;
	}	
	public Vertex getVertex() {
		return vertex;
	}
	public void setVertex(Vertex vertex) {
		this.vertex = vertex;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	
}
