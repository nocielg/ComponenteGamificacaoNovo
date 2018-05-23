import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Usuario {
	private String nomeUsuario;
	private Map<String, Integer> tipo = new HashMap<String, Integer>();
	
	public Usuario(String nomeUsuario) {
		this.nomeUsuario=nomeUsuario;
	}
	
	public int getPontos(String tipo){
		if(this.tipo.containsKey(tipo)){
			return this.tipo.get(tipo);
		}
		return 0;
	}
	
	
	public void adicionaTipoDePontos(String tipoDePonto, int pontuacao){
		tipo.put(tipoDePonto, getPontos(tipoDePonto)+pontuacao);
		
	}
	
	public String getNomeUsuario(){
		return nomeUsuario;
	}
	
	public boolean verificaSeTipoTaVazio(){
		if(this.tipo.isEmpty()){
			return false;
		}
		return true;
	}
	
	public String getTipos(){
		String string = "";
		Set<String> chave = tipo.keySet();
		for (String i:chave){
			if(i!=null){
				string=string+i+ ";";
			}
		}
		return string;
	} 
	
	
}