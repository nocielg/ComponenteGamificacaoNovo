import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;


public class Armazenamento {
	
	ArrayList <Usuario> usuario = new ArrayList<Usuario>(); 
	
	private String lerArquivo(String arquivo) throws IOException{
		FileReader fileReader = new FileReader("/home/gleisson/workspace/ComponenteGameficacao/arquivo.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String textoLido;
		textoLido = bufferedReader.readLine();
		bufferedReader.close();
		
		return textoLido;
		
	}
	
	public void adicionaUsuario(Usuario usuario){
		this.usuario.add(usuario);
	}
	
	public void adicionandoPonto(String usuario, int ponto, String tipoDePonto){
		Usuario user = retornaUsuario(usuario);
		user.adicionaTipoDePontos(tipoDePonto, ponto);
	}
	
	private Usuario retornaUsuario(String nomeUsuario){
		for (Usuario i: this.usuario){
			if (i.getNomeUsuario()==nomeUsuario)
				return i;
		}
		return null;
	}
	
	public int retornaPontosDosTipos(String usuario, String tipoDePonto){
		Usuario user = retornaUsuario(usuario);
		return user.getPontos(tipoDePonto);
		
	}
	
	public String retornaUsuariosQueReceberamPontos(){
		String nome = "";
		for(Usuario i:this.usuario){
			if (i.verificaSeTipoTaVazio()==true){
				nome = nome + i.getNomeUsuario() + ";";
			}
		}
		return nome;
	}
	
	public String tiposDePontosRegistradosPorUsuario(String nomeUsuario){
		Usuario u = retornaUsuario(nomeUsuario);
			return u.getTipos();
		
	}
	
	private void imprimeUsuariosSeparadamente(PrintWriter pw){
		String string = retornaUsuariosQueReceberamPontos();
		String[] nomes = string.split(";");
		for (String n:nomes){
			pw.println("-" + n);
		}
	}
	
	public void retornaUsuarioPorTipoDePontoRecebido(PrintWriter pw, Usuario usuario){
		String string = usuario.getTipos();
		String[] nomes = string.split(";");
		for (String n:nomes){
			pw.printf(usuario.getNomeUsuario(),n,usuario.getPontos(n));
			
		}
		
	}
	
	public void escrevendoNoArquivo() throws IOException{
		PrintWriter printWriter = new PrintWriter("/home/gleisson/workspace/ComponenteGameficacao/arquivo.txt");
		for(Usuario u: this.usuario){
			retornaUsuarioPorTipoDePontoRecebido(printWriter, u);
		}
		printWriter.close();
		
	}
}