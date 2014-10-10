package graphs.dijikstra.model;

import java.util.ArrayList;
import java.util.List;

public class Vertex{

	private List<Neighbor> neighborList;
	private String label;
	private Integer cost;
	
	public Vertex(String label) {
		this.label = label;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public List<Neighbor> getNeighborList() {
		return neighborList;
	}
	public void setNeighborList(List<Neighbor> neighborList) {
		this.neighborList = neighborList;
	}
	public void addNeighbor(Neighbor neighborList) {
		if(this.neighborList == null) this.neighborList = new ArrayList<Neighbor>();
		this.neighborList.add(neighborList);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}
	
	
}
