package dds.softpoi;

import json.centro.*;
//import static org.junit.Assert.*;
import org.junit.Test;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



public class TestCentroDTO {

	@SuppressWarnings("unused")
	@Test
	public void test() throws Exception {
		
		
		
		
		JsonCentro jsonCentro = new JsonCentro();
		
		
		algunlado = jsonCentro.dameAlgo("http://trimatek.org/Consultas/centro?zona=Retiro");
		
		
		
		
		//final String json = "{\"id\":46,\"nombre\":\"Miguel\",\"empresa\":\"Autentia\"}";
		String json = JsonCentro.readUrl("http://trimatek.org/Consultas/centro?zona=Retiro");
		//String json = "[{\"comuna\":1,\"zonas\":\"Retiro, San Telmo, San Nicolás, Puerto Madero, Monserrat, Constitución\",\"director\":\"Roberto Rodriguez\",\"domicilio\":\"\",\"telefono\":\"\",\"servicios\":[{\"nombre\":\"Atención ciudadana\",\"horarios\":[{\"diaSemana\":1,\"horaDesde\":9,\"minutosDesde\":0,\"horaHasta\":15,\"minutosHasta\":0}]}]}]";	
		//System.out.println(json);

		Gson gson = new Gson();
		//final Empleado empleado = gson.fromJson(json, Empleado.class);
				
		//final CentroDTO unCentro = gson.fromJson(json, CentroDTO.class);
		
		CentroDTO[] gsonCentro = gson.fromJson(json, CentroDTO[].class);
		
		//System.out.println(gsonCentro);
		
		//System.out.println(gson.toJson(gsonCentro));
		
		
		JsonArray entries = (JsonArray) new JsonParser().parse(json);
		JsonElement comuna = ((JsonObject)entries.get(0)).get("comuna");
		
		System.out.println(comuna.toString());
		
		
		
		
		
		
		
		//System.out.println(empleado.getNombre());
		
		//System.out.println(gsonCentro..getComuna());
		
		//System.out.println(lstCentro.get(1).getComuna());
		
	    //System.out.println(lstCentro.get(0).getDirector());
	    //System.out.println(lstCentro.get(1).getDirector());
	    
	    //assertEquals(1, unCentro.getComuna());
	}


	
	
	
	
}