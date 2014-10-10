package graphs.dijikstra.control;

import graphs.dijikstra.exception.IllegalConfigurationGraphException;
import graphs.dijikstra.exception.IllegalFileLinesException;
import graphs.dijikstra.helper.FileReader;
import graphs.dijikstra.model.Graph;

import java.util.List;

import javax.swing.JOptionPane;

public class Dijkstra {
	
	private static List<String> lineList = null;
	
	public static void main(String[] args) throws IllegalFileLinesException, IllegalConfigurationGraphException {
		String result = null;
		String address = JOptionPane.showInputDialog("Insira o Endereço do Arquivo","C:\\Users\\Paulo\\Downloads\\inputGrafos.txt");
		try {
			lineList = FileReader.getLines(address);
			Graph graph = new Graph(lineList);
			result = graph.getDijikstraResult().toString();
		} catch (Exception e) {
			// TODO: handle exception
			result = "Ocorreu um erro! Reveja os parâmetros e tente novamente!";
		}
		JOptionPane.showMessageDialog(null,result);
	}
	
}
