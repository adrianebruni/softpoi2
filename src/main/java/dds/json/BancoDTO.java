package dds.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dds.softpoi.Banco;
import dds.softpoi.Disponibilidad;
import dds.softpoi.POI;
import dds.softpoi.RangoHorario;
import dds.softpoi.Servicio;

import java.util.ArrayList;

public class BancoDTO extends OrigenJSON{

	public ArrayList<POI> dameDatosExternos(String sURL) {
	
		JsonArray jsaBanco = new JsonArray();
		this.POIs = new ArrayList<POI>();
				
		try {
			jsaBanco = this.consultarJson(sURL);
		} catch (Exception e) {
			System.out.println("ERROR: BancoDTO.java - dameDatosExternos --> No se pudo conectar con la fuente externa");
			e.printStackTrace();
		}	
		
		for (int i = 0; i <= jsaBanco.size() - 1; i++){
			
			// Creamos un objeto del tipo Banco
			Banco unBanco = new Banco();
			
			// Seteamos en nombre del banco
			String jsonNombreBanco = ((JsonObject)jsaBanco.get(i)).get("banco").getAsString();
			unBanco.setNombre(jsonNombreBanco.toUpperCase());
			
			// Seteamos Latitud
			Double jsonX = ((JsonObject)jsaBanco.get(i)).get("x").getAsDouble();
			unBanco.setLatitud(jsonX);
			
			// Seteamos Longitud
			Double jsonY = ((JsonObject)jsaBanco.get(i)).get("y").getAsDouble();
			unBanco.setLongitud(jsonY);
			
			// Seteamos la sucursal
			String jsonSucursal = ((JsonObject)jsaBanco.get(i)).get("sucursal").getAsString();
			unBanco.setSucursal(jsonSucursal.toUpperCase());
			
			// Seteamos el gerente
			String jsonGerente = ((JsonObject)jsaBanco.get(i)).get("gerente").getAsString();
			unBanco.setGerente(jsonGerente.toUpperCase());
			
			// Obtenemos el array de servicios
			JsonArray jsonServicios = (JsonArray) ((JsonObject)jsaBanco.get(i)).get("servicios");
						
			// Seteamos los servicios
			for (int j = 0; j <= jsonServicios.size() - 1; j++){
				
				// Creamos un objeto del tipo Servicio
				Servicio unServicio = new Servicio();
				
				// Seteamos el Nombre del Servicio
				String jsonServicioNombre = jsonServicios.get(j).getAsString();
				unServicio.setServicio(jsonServicioNombre.toUpperCase());
				
				// Seteamos un rango horario y dia disponible por defecto.
				RangoHorario objRangoHorario = new RangoHorario("10:00:00", "15:00:00");
				for (int k = 0; k <= 6; k++){
					Disponibilidad objDisponibilidad = new Disponibilidad();
					objDisponibilidad.setDia(k);
					objDisponibilidad.setRangoHorario(objRangoHorario);
					unServicio.setDisponibilidad(objDisponibilidad);
				}	
				
				unBanco.setServicios(unServicio);
				
			} // FIN FOR J
			
			POIs.add(unBanco);
			
		} // FIN FOR i
		
		return POIs;
	}

}
