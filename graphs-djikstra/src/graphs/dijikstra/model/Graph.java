package graphs.dijikstra.model;

import graphs.dijikstra.exception.IllegalConfigurationGraphException;
import graphs.dijikstra.exception.IllegalFileLinesException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {

	private static final String NAO_E_POSSIVEL_ENTREGAR_A_CARTA = "Nao e possivel entregar a carta";
	private static final int INITIAL_ROOT_COST = 0;
	private static final int INFINITY = 1001;
	private static final int MAXCOSTBYSEND = 1000;
	private Map<String,Vertex> vertexMap;
	private int vertexLength;
	private int edgeLength;	
	private List<String> dijikstraResult;
	
	public Graph(List<String> fileLines) throws IllegalFileLinesException, IllegalConfigurationGraphException {
		if(fileLines == null || fileLines.isEmpty()){
			throw new IllegalFileLinesException();
		}
		this.vertexMap = new HashMap<String,Vertex>();
		this.initialConfiguration(fileLines);
	}

	private void initialConfiguration(List<String> fileLines) throws IllegalConfigurationGraphException{
		String line = fileLines.get(0);
		
		if(line != null){
			String [] lineArr = line.split(" ");
			this.vertexLength = Integer.parseInt(lineArr[0]);
			if(vertexLength > 500 || vertexLength < 1) throw new IllegalConfigurationGraphException();
			this.edgeLength = Integer.parseInt(lineArr[1]);
			if((this.edgeLength > (this.vertexLength*2)) || this.edgeLength < 0) throw new IllegalConfigurationGraphException();
		}
        
		for (int i = 1; i < this.edgeLength + 1; i++) {
        	createEdge(fileLines.get(i));
        }
        
		int start = 2 + edgeLength;
		
		this.dijikstraResult = new ArrayList<String>();		
		for(int i = start ; i < fileLines.size();i++){
			this.dijikstraResult.add(executeDijkstra(fileLines.get(i).split(" ")));
		}
		
	}
	
	private void createEdge(String line) throws IllegalConfigurationGraphException {
		String [] col =  line.split(" ");
		Vertex vertex = getVertex(col[0]);
		int costBySend = Integer.parseInt(col[2]);
		if(costBySend > MAXCOSTBYSEND || costBySend < 1) throw new IllegalConfigurationGraphException();
		Neighbor neighbor = new Neighbor(getVertex(col[1]),costBySend);
		vertex.addNeighbor(neighbor);
		this.vertexMap.put(vertex.getLabel(),vertex);
	}

	private Vertex getVertex(String label) {
		Vertex vertex = this.vertexMap.get(label);
		if(vertex == null) {
			vertex = new Vertex(label);
			this.vertexMap.put(label,vertex);
		}
		return vertex;
	}
	
	   public String executeDijkstra(String [] edge) {
		   
		    String source = edge[0]; 
		    String target = edge[1];
		    String labelLowCost;
		    
		    List<Vertex> vertexList = new ArrayList<Vertex>(this.vertexMap.values());
		    Map<String,Vertex> tempVertexMap = initializeTemporaryVertexList(source);
		    
	        while(!vertexList.isEmpty()){
	        	labelLowCost = getLowCost(vertexList);
	        	List<Neighbor> neighbors = tempVertexMap.get(labelLowCost).getNeighborList(); 
	            neighborWithLowerCost(labelLowCost, tempVertexMap, neighbors);
	            vertexList.remove(tempVertexMap.get(labelLowCost));
	        }
	        
	        if(tempVertexMap.get(target).getCost() >= INFINITY )
	            return NAO_E_POSSIVEL_ENTREGAR_A_CARTA;
	        else
	            return tempVertexMap.get(target).getCost()+"";

	    }

	private void neighborWithLowerCost(String labelLowCost, Map<String, Vertex> tempVertexMap, List<Neighbor> neighbors) {
		for (Neighbor neighbor : neighbors) {
		    if(neighbor.getVertex().getCost() >= INFINITY || neighbor.getVertex().getCost() > neighbor.getCost() + tempVertexMap.get(labelLowCost).getCost()){
		        tempVertexMap.get(neighbor.getVertex().getLabel()).setCost(neighbor.getCost() + tempVertexMap.get(labelLowCost).getCost());
		    }
		}
	}
	    
	    private Map<String,Vertex> initializeTemporaryVertexList(String label) {
	    	Map<String,Vertex> result = new HashMap<String, Vertex>();
	    	Set<String> keys =  this.vertexMap.keySet();
	    	for(String key : keys){
	    		Vertex currentVertex = this.vertexMap.get(key);
	    		
	    		if(!currentVertex.getLabel().equals(label)){
	    			currentVertex.setCost(INFINITY);
	    		}else{
	    			currentVertex.setCost(INITIAL_ROOT_COST);
	    		}
	    		result.put(key, currentVertex);
	    	}
	        return result;
	}

		public String getLowCost(List<Vertex> vertexList){
	        Integer rootCost = vertexList.get(0).getCost();
	        String rootLabelLowCost = vertexList.get(0).getLabel();
	        for(Vertex currentVertex: vertexList){
	            if(currentVertex.getCost() < rootCost){
	                rootCost = currentVertex.getCost();
	                rootLabelLowCost = currentVertex.getLabel();
	            }
	        }
	        return rootLabelLowCost;
	    }
	
	public int getVertexLength() {
		return vertexLength;
	}

	public int getEdgeLength() {
		return edgeLength;
	}

	public Map<String, Vertex> getVertexMap() {
		return vertexMap;
	}

	public void setVertexMap(Map<String, Vertex> vertexMap) {
		this.vertexMap = vertexMap;
	}

	public List<String> getDijikstraResult() {
		return dijikstraResult;
	}

	public void setDijikstraResult(List<String> dijikstraResult) {
		this.dijikstraResult = dijikstraResult;
	}
	
}

