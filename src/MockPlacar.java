import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class MockPlacar implements Placar{

	ArrayList <Usuario> usuario = new ArrayList<Usuario>();

	Armazenamento armazenamento = new Armazenamento();
		
	public void carregandoUsuariosArmazenados(){
		usuario = (ArrayList) armazenamento.usuario.clone();
	}
	@Override
	public void registraTipoDePonto(String nomeUsuario, int ponto, String tipoDePonto) {
		armazenamento.adicionandoPonto(nomeUsuario, ponto, tipoDePonto);
	
	}

	private Usuario retornaUsuario(String nomeUsuario) {
		for(Usuario u: usuario){
			if(u.getNomeUsuario()==nomeUsuario)
				return u;
		}
		return null;
	}

	@Override
	public String[] retornaTodosPontosDeUmUsuario(String usuarioNome) {
		String tiposDePontos;
		String[] tiposDePontosSplit;
		String[] tipoDePontosUsuarios = null;
		int contador = 0;
		
			for(Usuario u: usuario){
				if ((u.getNomeUsuario()) == usuarioNome){
					tiposDePontos = u.getTipos();
					tiposDePontosSplit = tiposDePontos.split(";");
					tipoDePontosUsuarios = new String[tiposDePontosSplit.length];
					for(String string: tiposDePontosSplit){
						tipoDePontosUsuarios[contador] = u.getNomeUsuario()+";"+string+u.getPontos(string); 
					}
					contador=contador+1;
				}
				
			}
		
		return tipoDePontosUsuarios;
	}

	@Override
	public String[] retornaRankingPorTipoDePonto(String tipoDePonto) {
		
		String tiposDePontos;
		String[] tiposDePontosSplit;
		String[] rankingTipoDePontosUsuarios = null;
		int contador = 0;
				
			for(Usuario u: usuario){
				if ((u.getTipos()) == tipoDePonto){
					tiposDePontos = u.getTipos();
					tiposDePontosSplit = tiposDePontos.split(";");
					rankingTipoDePontosUsuarios = new String[tiposDePontosSplit.length];
					for(String string: tiposDePontosSplit){
						rankingTipoDePontosUsuarios[contador] = u.getNomeUsuario()+";"+u.getPontos(tipoDePonto); 
					}
					contador=contador+1;
				}
				
				
				
				
			}
		Arrays.sort(rankingTipoDePontosUsuarios); 
		
		return rankingTipoDePontosUsuarios;
	}
	
	@Before
	public void inicializanoTeste(){
		armazenamento.adicionaUsuario(new Usuario("Joao"));
		armazenamento.adicionaUsuario(new Usuario("Maria"));
		armazenamento.adicionaUsuario(new Usuario("Carlos"));
		armazenamento.adicionandoPonto("Joao", 5, "Estrela");
		armazenamento.adicionandoPonto("Joao", 10, "Curtida");
		armazenamento.adicionandoPonto("Maria", 20, "Comentario");
		armazenamento.adicionandoPonto("Carlos", 15, "Topico");
		armazenamento.adicionandoPonto("Carlos", 5, "Estrela");
		carregandoUsuariosArmazenados();
			
	}
	
	@Test
	public void testarRegistroDePontos(){
		Usuario usuario_joao = retornaUsuario("Joao");
		Usuario usuario_maria = retornaUsuario("Maria");
		Usuario usuario_carlos = retornaUsuario("Carlos");
		assertEquals(usuario_joao.getPontos("Estrela"),5);
		assertEquals(usuario_maria.getPontos("Comentario"),20);
		assertEquals(usuario_carlos.getPontos("Topico"),15);
	}
	
	@Test
	public void testaRetornaTodosPontosDeUmUsuario(){
		
		assertEquals(Arrays.toString(retornaTodosPontosDeUmUsuario("Maria")),"[Maria;Comentario20]");
		
	}
	
	
	@Test
	public void retornaRankingPorTipoDePonto(){
		
		assertEquals(Arrays.toString(retornaRankingPorTipoDePonto("Topico")),"Joao;Carlos;");
	} 
	
}