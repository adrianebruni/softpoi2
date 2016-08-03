package dds.softpoi;

import static org.junit.Assert.*;
import org.junit.Test;
import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import dds.json.BancoDTO;

public class TestBancoDTO {

	@Test
	public void test() throws Exception {
		
		JsonArray jsaBanco = new JsonArray();
		BancoDTO bcoDto = new BancoDTO();
		
		try {
			jsaBanco = bcoDto.consultarJson("http://trimatek.org/Consultas/banco");
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		System.out.println("Cantidad de ocurrencias en json: " + jsaBanco.size());
		for (int i = 0; i <= jsaBanco.size() - 1; i++){
			
			// Creamos un objeto del tipo Banco
			Banco unBanco = new Banco();
			
			// Seteamos en nombre del banco
			String jsonNombreBanco = ((JsonObject)jsaBanco.get(i)).get("banco").getAsString();
			unBanco.setNombre(jsonNombreBanco);
			
			// Seteamos Latitud
			Double jsonX = ((JsonObject)jsaBanco.get(i)).get("x").getAsDouble();
			unBanco.setLatitud(jsonX);
			
			// Seteamos Longitud
			Double jsonY = ((JsonObject)jsaBanco.get(i)).get("y").getAsDouble();
			unBanco.setLongitud(jsonY);
			
			// Seteamos la sucursal
			String jsonSucursal = ((JsonObject)jsaBanco.get(i)).get("sucursal").getAsString();
			unBanco.setSucursal(jsonSucursal);
			
			// Seteamos el gerente
			String jsonGerente = ((JsonObject)jsaBanco.get(i)).get("gerente").getAsString();
			unBanco.setGerente(jsonGerente);
			
			// Obtenemos el array de servicios
			JsonArray jsonServicios = (JsonArray) ((JsonObject)jsaBanco.get(i)).get("servicios");
						
			// Seteamos los servicios
			for (int j = 0; j <= jsonServicios.size() - 1; j++){
				
				// Creamos un objeto del tipo Servicio
				Servicio unServicio = new Servicio();
				
				// Nombre del Servicio
				String jsonServicioNombre = jsonServicios.get(j).getAsString();
					
				unServicio.setServicio(jsonServicioNombre);
				unBanco.setServicios(unServicio);
				
			} // FIN FOR J
					
			System.out.println("------------------------------------------------------------");
			
		} // FIN FOR i
		
	}
}
