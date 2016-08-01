package json.centro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class CentroDTO {
	
	private int comuna;
	private String zonas;
	private String director;
	private String domicilio;
	private String telefono;		
	private ArrayList<ServicioCentro> servicioDTO = new ArrayList<ServicioCentro>();
	
	private JsonArray arrayCentro;
	
	
	// ***************************************************************************
	// Constructor
	// ***************************************************************************
	
	public CentroDTO(){}
	
	public CentroDTO(int comuna, String zonas, String director, String domicilio, String telefono,
			ArrayList<ServicioCentro> servicioDTO) {
		super();
		this.comuna = comuna;
		this.zonas = zonas;
		this.director = director;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.servicioDTO = servicioDTO;
	}
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public int getComuna() {
		return comuna;
	}

	public String getZonas() {
		return zonas;
	}

	public String getDirector() {
		return director;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public ArrayList<ServicioCentro> getServicioDTO() {
		return servicioDTO;
	}
	
	public JsonArray getArrayCentro() {
		return arrayCentro;
	}
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	public void setComuna(int comuna) {
		this.comuna = comuna;
	}

	public void setZonas(String zonas) {
		this.zonas = zonas;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setServicioDTO(ArrayList<ServicioCentro> servicioDTO) {
		this.servicioDTO = servicioDTO;
	}
	
	public void setArrayCentro(JsonArray arrayCentro) {
		this.arrayCentro = arrayCentro;
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
	
	// 
	public JsonArray consultarJson(String urlString) throws Exception {
		
		String json = this.readUrl(urlString);
		arrayCentro = (JsonArray) new JsonParser().parse(json);
		
		if (this.arrayCentro.size() == 0){
			// No hay elementos
			return null;
		}else{
			return this.arrayCentro;
			//JsonElement comuna = ((JsonObject)arrayCentro.get(0)).get("comuna");
		}
		
	}


	
	
	
	/*
	public static class CentroDeserializer implements JsonDeserializer<CentroDTO>{

		@Override
		public CentroDTO deserialize(final JsonElement jsonCentro,final Type t, JsonDeserializationContext context) throws JsonParseException {
			
			// Creamos un objeto del tipo Json
			final JsonObject jsonObject = jsonCentro.getAsJsonObject();
			
			// Creamos un elemento del tipo Json (indicado en el enunciado del TP)
			final JsonElement jsonComuna = jsonObject.get("comuna");
			
			
			JsonElement comuna = ((JsonObject)arrayCentro.get(0)).get("comuna");
			
			
			// Creamos una variable del mismo tipo y asignamos su valor
			final int comuna = jsonComuna.getAsInt();
			
			// Creamos un objeto del tipo CentroDTO y le asignamos los valores obtenidos de Json.
			final CentroDTO unCentroDTO = new CentroDTO();			
			unCentroDTO.setComuna(comuna);

			return unCentroDTO;
		}
	}
	*/

	
}
