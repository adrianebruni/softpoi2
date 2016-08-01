package dds.softpoi;

import json.centro.*;

//import static org.junit.Assert.*;
import org.junit.Test;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class TestCentroDTO {

	@Test
	public void test() throws Exception {
		
		
/*		
		String json = this.readUrl("http://trimatek.org/Consultas/centro?zona=Retiro");
		JsonArray arrayCentro = (JsonArray) new JsonParser().parse(json);
		JsonElement comuna = ((JsonObject)arrayCentro.get(0)).get("zonas");
		System.out.println(comuna.toString() + " algo");
*/
		
		CentroDTO jsonCentro = new CentroDTO();
		JsonArray jsaCentro = new JsonArray();
		jsaCentro = jsonCentro.consultarJson("http://trimatek.org/Consultas/centro");	
		//int jsonComuna = ((JsonObject)jsaCentro.get(0)).get("comuna").getAsInt();
		//System.out.println(jsonComuna);
				
		//System.out.println(jsaCentro.size());
		for (int i = 0; i <= jsaCentro.size() - 1; i++){
			
			// Creamos un objeto del tipo CGP
			CGP unCGP = new CGP();
			
			// Seteamos la comuna (ID, Zonas)
			int jsonComuna = ((JsonObject)jsaCentro.get(i)).get("comuna").getAsInt();
			String jsonZonas = ((JsonObject)jsaCentro.get(i)).get("zonas").getAsString();
			Comuna unaComuna = new Comuna();
			unaComuna.setID(jsonComuna);
			unaComuna.setZonas(jsonZonas);
			unCGP.setComuna(unaComuna);
			
			// Seteamos el director
			String jsonDirector = ((JsonObject)jsaCentro.get(i)).get("director").getAsString();
			unCGP.setDirector(jsonDirector);
			
			// Seteamos el domicilio
			String jsonDomicilio = ((JsonObject)jsaCentro.get(i)).get("domicilio").getAsString();
			unCGP.setCalle(jsonDomicilio);
			
			// Seteamos el telefono
			String jsonTelefono = ((JsonObject)jsaCentro.get(i)).get("telefono").getAsString();
			unCGP.setTelefono(jsonTelefono);
			
			// Seteamos los servicios
			JsonArray jsonServicios = (JsonArray) ((JsonObject)jsaCentro.get(i)).get("servicios");
			//JsonElement jsonServicios = ((JsonObject)jsaCentro.get(0)).get("servicios");
			//System.out.println(jsonServicios.toString());	
				
			for (int j = 0; j <= jsonServicios.size() - 1; j++){
				
				// Creamos un objeto del tipo Servicio
				Servicio unServicio = new Servicio();
				
				// nombre del servicio
				String jsonServicioNombre = ((JsonObject)jsonServicios.get(j)).get("nombre").getAsString();
				unServicio.setServicio(jsonServicioNombre);
				
				// Dias y Horarios disponibles
				JsonArray jsonDisponibilidad = (JsonArray) ((JsonObject)jsonServicios.get(j)).get("horarios");
				
				for (int k = 0; k <= jsonDisponibilidad.size() - 1; k++){
					
					// Hora Desde
					JsonElement jsonHoraDesde = ((JsonObject)jsonDisponibilidad.get(k)).get("horaDesde");			
					// Minutos Desde
					JsonElement jsonMinutoDesde = ((JsonObject)jsonDisponibilidad.get(k)).get("minutosDesde");
					
					// Hora Hasta
					JsonElement jsonHoraHasta = ((JsonObject)jsonDisponibilidad.get(k)).get("horaHasta");
					// Minutos Hasta
					JsonElement jsonMinutoHasta = ((JsonObject)jsonDisponibilidad.get(k)).get("minutosHasta");
					
					String apertura = jsonHoraDesde.toString() + ":" + jsonMinutoDesde.toString();
					String cierre = jsonHoraHasta.toString() + ":" + jsonMinutoHasta.toString();
					
					RangoHorario unRangoHorario = new RangoHorario(apertura, cierre);
					
					// Dia disponible
					int jsonDia = ((JsonObject)jsonDisponibilidad.get(k)).get("diaSemana").getAsInt();
					Disponibilidad unaDisponibilidad = new Disponibilidad();
					unaDisponibilidad.setDia(jsonDia);
					unaDisponibilidad.setRangoHorario(unRangoHorario);
					unServicio.setDisponibilidad(unaDisponibilidad);
					
				} // FIN FOR K
				
				unCGP.setServicios(unServicio);
			} // FIN FOR J
			
			
			// Aca hay que darle la logica de que queremos hacer con cada CGP
			System.out.println("COMUNA: " + unCGP.getComuna().getID());
			System.out.println("ZONAS: " + unCGP.getComuna().getZonas());
			System.out.println("DIRECTOR: " + unCGP.getDirector());
			System.out.println("DOMICILIO: " + unCGP.getCalle());
			System.out.println("TELEFONO: " + unCGP.getTelefono());
			System.out.println("SERVICIO(CANT.): " + unCGP.getServicios().size());
			System.out.println("------------------------------------------------------------");
			
		} // FIN FOR i
		
	}

}