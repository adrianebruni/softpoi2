package dds.json;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import dds.softpoi.CGP;
import dds.softpoi.Comuna;
import dds.softpoi.Disponibilidad;
import dds.softpoi.POI;
import dds.softpoi.RangoHorario;
import dds.softpoi.Servicio;

public class CentroDTO extends OrigenJSON {
	
	public ArrayList<POI> dameDatosExternos(String sURL) {
		
		JsonArray jsaCentro = new JsonArray();
		this.POIs = new ArrayList<POI>();
		
		try {
			jsaCentro = this.consultarJson(sURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i <= jsaCentro.size() - 1; i++){
			
			// Creamos un objeto del tipo CGP
			CGP unCGP = new CGP();
			
			// Seteamos la comuna (ID, Zonas)
			Comuna unaComuna = new Comuna();
			
			int jsonComuna = ((JsonObject)jsaCentro.get(i)).get("comuna").getAsInt();
			unaComuna.setID(jsonComuna);
			String jsonZonas = ((JsonObject)jsaCentro.get(i)).get("zonas").getAsString();
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
			
			POIs.add(unCGP);
			
		} // FIN FOR i
		
		return POIs;
		
	}
	
}
