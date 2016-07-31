package json.centro;

import java.util.ArrayList;

public class CentroDTO {
	
/*
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
*/	
	
	public CentroDTO parent;
	
	private int comuna;
	private String zonas;
	private String director;
	private String domicilio;
	private String telefono;		
	private ArrayList<ServicioCentro> servicioDTO = new ArrayList<ServicioCentro>();
	
	// ***************************************************************************
	// Constructor
	// ***************************************************************************
	
	/*
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
	 */
	
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
	
	public CentroDTO getParent() {
		return parent;
	}
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	public void setParent(final CentroDTO parent) {
		this.parent = parent;
	}
	
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
	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************
	
	/*
	public static class CentroDeserializer implements JsonDeserializer<CentroDTO>{

		@Override
		public CentroDTO deserialize(final JsonElement jsonCentro,final Type t, JsonDeserializationContext context) throws JsonParseException {
			
			// Creamos un objeto del tipo Json
			final JsonObject jsonObject = jsonCentro.getAsJsonObject();
			
			// Creamos un elemento del tipo Json (indicado en el enunciado del TP)
			final JsonElement jsonComuna = jsonObject.get("comuna");
			
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
