import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Test;


public class testeArmazenamento {

	private PrintWriter printWriter;
	private FileWriter fileWriter;
	private FileReader fileReader;
	private BufferedReader bufferedReader; 
	private Armazenamento armazenamento;
	
	@Before
	public void comecandoTeste(){
		armazenamento = new Armazenamento();
		Usuario usuario1 = new Usuario("Joao");
		armazenamento.adicionaUsuario(usuario1);
		Usuario usuario2 = new Usuario("Maria");
		armazenamento.adicionaUsuario(usuario2);
	}

	@Test
	public void testaAdcionarDoisPontosTipoEstrela(){
		armazenamento.adicionandoPonto("Joao", 10, "Estrela");
		armazenamento.adicionandoPonto("Joao", 20, "Estrela");
		assertEquals(armazenamento.retornaPontosDosTipos("Joao", "Estrela"),30);
	}
	
	@Test
	public void testaAdcionarUmPontoTipoMoeda(){
		armazenamento.adicionandoPonto("Joao", 20, "Estrela");
		assertEquals(armazenamento.retornaPontosDosTipos("Joao", "Estrela"),20);
	}
	
	@Test
	public void testaAdcionarDoisTiposPontoSDiferentesComDoisUsuarios(){
		armazenamento.adicionandoPonto("Joao", 20, "Estrela");
		armazenamento.adicionandoPonto("Joao", 30, "Curtida");
		armazenamento.adicionandoPonto("Joao", 40, "Curtida");
		armazenamento.adicionandoPonto("Maria", 5, "Estrela");
		assertEquals(armazenamento.retornaPontosDosTipos("Joao", "Estrela"),20);
		assertEquals(armazenamento.retornaPontosDosTipos("Joao", "Curtida"),70);
		assertEquals(armazenamento.retornaPontosDosTipos("Maria","Estrela"),5);
	}
	
	@Test
	public void testaMetodoQueRetornaUsuariosComPontos(){
		armazenamento.adicionandoPonto("Joao", 20, "Estrela");
		armazenamento.adicionandoPonto("Maria", 5, "Estrela");
		assertEquals(armazenamento.retornaUsuariosQueReceberamPontos(),"Joao;Maria;");
	}
	
	@Test
	public void testaRetornoTipoDePontosRegistrados(){
		armazenamento.adicionandoPonto("Joao", 20, "Estrela");
		armazenamento.adicionandoPonto("Joao", 5, "Estrela");
		armazenamento.adicionandoPonto("Joao", 30, "Comentario");
		armazenamento.adicionandoPonto("Joao", 50, "Topico");
		assertEquals(armazenamento.tiposDePontosRegistradosPorUsuario("Joao"),"Estrela;Comentario;Topico;");
	}
	
	@Test
	public void testandoGerarArquivo() throws IOException{
		armazenamento.adicionandoPonto("Joao", 20, "Estrela");
		armazenamento.adicionandoPonto("Maria", 5, "Estrela");
		armazenamento.escrevendoNoArquivo();
	}
	
}