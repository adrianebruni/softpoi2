package dds.softpoi;

import static org.junit.Assert.*;
import org.junit.Test;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import dds.json.BancoDTO;

public class TestBancoDTO {

	@Test
	public void test() throws Exception {
		
		BancoDTO jsonBanco = new BancoDTO();
		JsonArray jsaBanco = new JsonArray();
		jsaBanco = jsonBanco.consultarJson("http://trimatek.org/Consultas/banco");	
		//int jsonComuna = ((JsonObject)jsaCentro.get(0)).get("comuna").getAsInt();		
		System.out.println("Cantidad de ocurrencias en json: " + jsaBanco.size());
		for (int i = 0; i <= jsaBanco.size() - 1; i++){
			
			// Creamos un objeto del tipo Banco
			Banco unBanco = new Banco();
			
			// Seteamos la comuna (ID, Zonas)
			String jsonNombreBanco = ((JsonObject)jsaBanco.get(i)).get("banco").getAsString();
			//System.out.println(jsonNombreBanco);
			Double jsonX = ((JsonObject)jsaBanco.get(i)).get("x").getAsDouble();
			//System.out.println(jsonX);
			Double jsonY = ((JsonObject)jsaBanco.get(i)).get("y").getAsDouble();
			//String jsonSucursal = ((JsonObject)jsaBanco.get(i)).get("sucursal").getAsString();
			//String jsonGerente = ((JsonObject)jsaBanco.get(i)).get("gerente").getAsString();
			
			// Seteamos los servicios
			JsonArray jsonServicios = (JsonArray) ((JsonObject)jsaBanco.get(i)).get("servicios");
			//JsonElement jsonServicios = ((JsonObject)jsaCentro.get(0)).get("servicios");
			//System.out.println(jsonServicios.toString());	
			//System.out.println(jsonGerente);
			for (int j = 0; j <= jsonServicios.size() - 1; j++){
				// Creamos un objeto del tipo Servicio
				Servicio unServicio = new Servicio();
				// nombre del servicio
				//System.out.println(jsonServicios.get(j).getAsString());
				String jsonServicioNombre = jsonServicios.get(j).getAsString();
				//System.out.println(jsonServicioNombre);
				unServicio.setServicio(jsonServicioNombre);
				unBanco.setServicios(unServicio);
			} // FIN FOR J
			
			unBanco.setNombre(jsonNombreBanco);
			unBanco.setLatitud(jsonX);
			unBanco.setLongitud(jsonY);
			
			// Aca hay que darle la logica de que queremos hacer con cada CGP
			System.out.println("NOMBRE: " + unBanco.getNombre());
			System.out.println("LATI: " + unBanco.getLatitud());
			System.out.println("LONGI: " + unBanco.getLongitud());
			
			System.out.println("------------------------------------------------------------");
			
		} // FIN FOR i
		
	}
}
