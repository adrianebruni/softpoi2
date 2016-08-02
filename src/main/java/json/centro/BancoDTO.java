package json.centro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class BancoDTO {
	private String banco;
	private float x;
	private float y;
	private String sucursal;
	private String gerente;		
	private ArrayList<ServicioBanco> servicioDTO = new ArrayList<ServicioBanco>();
	
	private JsonArray arrayBanco;
	
	
	// ***************************************************************************
	// Constructor
	// ***************************************************************************
	
	public BancoDTO(){}
	
	public BancoDTO(String banco, float x, float y, String sucursal, String gerente,
			ArrayList<ServicioBanco> servicioDTO) {
		super();
		this.banco = banco;
		this.x = x;
		this.y = y;
		this.sucursal = sucursal;
		this.gerente = gerente;
		this.servicioDTO = servicioDTO;
	}
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public String getBanco() {
		return banco;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public String getSucursal() {
		return sucursal;
	}

	public String getGerente() {
		return gerente;
	}

	public ArrayList<ServicioBanco> getServicioBanco() {
		return servicioDTO;
	}
	
	public JsonArray getArrayCentro() {
		return arrayBanco;
	}
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	public void setBanco(String banco) {
		this.banco = banco;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public void setGerente(String gerente) {
		this.gerente = gerente;
	}

	public void setServicioDTO(ArrayList<ServicioBanco> servicioDTO) {
		this.servicioDTO = servicioDTO;
	}
	
	public void setArrayCentro(JsonArray arrayBanco) {
		this.arrayBanco = arrayBanco;
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
		arrayBanco = (JsonArray) new JsonParser().parse(json);
		
		if (this.arrayBanco.size() == 0){
			// No hay elementos
			return null;
		}else{
			return this.arrayBanco;
		}
		
	}

}
