package dds.json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public abstract class OrigenJSON {

	protected JsonArray arrayConsulta;

	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public JsonArray arrayConsulta() {
		return arrayConsulta;
	}
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	public void setArrayCentro(JsonArray arrayConsulta) {
		this.arrayConsulta = arrayConsulta;
	}
	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************
	
	// Funcion que obtiene todo los datos del parametro enviado por URL a string
	private String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}
	
	public JsonArray consultarJson(String urlString) throws Exception {
		
		String json = this.readUrl(urlString);
		arrayConsulta = (JsonArray) new JsonParser().parse(json);
		
		if (this.arrayConsulta.size() != 0){
			return this.arrayConsulta;
		}else{
			// No hay elementos
			return null;
		}
		
	}

}
