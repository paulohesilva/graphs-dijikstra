package test.graphs.dijikstra;


import static org.junit.Assert.assertEquals;
import graphs.dijikstra.exception.IllegalConfigurationGraphException;
import graphs.dijikstra.exception.IllegalFileLinesException;
import graphs.dijikstra.helper.FileReader;
import graphs.dijikstra.model.Graph;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class DijikstraTest {

	private List<String> lineList = null;
	
	public DijikstraTest() {
		this.lineList = FileReader.getLines("C:\\Users\\Paulo\\Downloads\\inputGrafos.txt");
	}
	
	@Test
	public void quantidadeDeLinhasDoArquivo(){
		assertEquals(12,lineList.size());
	}
	
	@Test
	public void validaConfiguracaoInicial() throws IllegalFileLinesException, IllegalConfigurationGraphException{
		Graph graph = new Graph(this.lineList);
		assertEquals(4,graph.getVertexLength());
		assertEquals(5,graph.getEdgeLength());
		assertEquals(4,graph.getVertexMap().size());
	}
	
	@Test
	public void validaQauntidadeDaSaidaComEntradaDeExemploInicial() throws IllegalFileLinesException, IllegalConfigurationGraphException{
		Graph graph = new Graph(this.lineList);
		assertEquals(5,graph.getDijikstraResult().size());
	}
	
	@Test
	public void verificaSePrimeiraASaidaEhComoOProfessorPediu5() throws IllegalFileLinesException, IllegalConfigurationGraphException{
		String [] saidaEsperada = {"5","11","19","7","Nao e possivel entregar a carta"};
		Graph graph = new Graph(this.lineList);
		assertEquals(Arrays.asList(saidaEsperada),graph.getDijikstraResult());
	}
	
	@Test(expected=IllegalConfigurationGraphException.class)
	public void ct01_501Cidades() throws IllegalFileLinesException, IllegalConfigurationGraphException{
		List<String> lineList = FileReader.getLines("C:\\Users\\Paulo\\Downloads\\ct01.txt");
		Graph graph = new Graph(lineList);
	}
		
	@Test(expected=IllegalConfigurationGraphException.class)
	public void ct02_quantidadeDeArcos2VezesMaiorQueAQuantidadeDeCidades() throws IllegalFileLinesException, IllegalConfigurationGraphException{
		List<String> lineList = FileReader.getLines("C:\\Users\\Paulo\\Downloads\\ct02.txt");
		Graph graph = new Graph(lineList);
	}
	
	@Test(expected=IllegalConfigurationGraphException.class)
	public void ct03_maximoDeHorasPossiveisNoEnvio() throws IllegalFileLinesException, IllegalConfigurationGraphException{
		List<String> lineList = FileReader.getLines("C:\\Users\\Paulo\\Downloads\\ct03.txt");
		Graph graph = new Graph(lineList);
	}
	
}
