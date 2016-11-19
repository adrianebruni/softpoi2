package dds.softpoi;

import java.util.ArrayList;

public class BuscadorConcreto implements BuscadorAbstracto {

	
	public ArrayList<POI> consultar(String query, Servidor unServidor){
		ArrayList<POI> poiEncontrados = new ArrayList<POI>();
		ArrayList<POI> todosLosPoi = new ArrayList<POI>();
		todosLosPoi.addAll(unServidor.getColPOIs());
		
		// agregamos los pois externos de mongoDB
		todosLosPoi.addAll(unServidor.getcolPOIsExternos());
		
		/*
		 * Las siguiente lineas las comento porque tira error cuando se quiere mostrar por primefaces los datos POI
		 * 
		unServidor.actualizoDesdeDatosExternos(query);
		try{
			todosLosPoi.addAll(unServidor.getcolPOIsExternos());
		}catch (Exception e) {
			System.out.println("no encuentro errores");
		}
		*/
		
		for(POI unPoi : todosLosPoi){
			if (unPoi.getNombre().toUpperCase().indexOf(query.toUpperCase()) > -1){
				poiEncontrados.add(unPoi);
			}else{
				for(Servicio unservicio : unPoi.servicios){
					if (unservicio.getServicio().toUpperCase().indexOf(query.toUpperCase()) > -1){
						poiEncontrados.add(unPoi);
						break;
					}
				}
				if (unPoi.tipoPOI().equalsIgnoreCase("Comercio"))
					if (((Comercio)(unPoi)).getRubro().getRubro().toUpperCase().indexOf(query.toUpperCase()) > -1)
						poiEncontrados.add(unPoi);
				if (unPoi.tipoPOI().equalsIgnoreCase("softpoi.CGP")){
					if (!(((CGP)unPoi).getComuna() == null)){
						if(((CGP)unPoi).getComuna().getZonas().toUpperCase().indexOf(query.toUpperCase()) > -1){
							poiEncontrados.add(unPoi);
						}
					}
					
				}
					
			}
				
		}
		return poiEncontrados;
	}	
	


	public ArrayList<POI> consultarPOIporNombreExacto(String query, Servidor unServidor){
		ArrayList<POI> poiEncontrados = new ArrayList<POI>();
		ArrayList<POI> todosLosPoi = new ArrayList<POI>();
		todosLosPoi.addAll(unServidor.getColPOIs());
		unServidor.actualizoDesdeDatosExternos(query);
		
		try{
			todosLosPoi.addAll(unServidor.getcolPOIsExternos());
		}catch (Exception e) {
			System.out.println("no encuentro errores");
		}
		
		for(POI unPoi : todosLosPoi){
			if (unPoi.getNombre().equals(query)){
			poiEncontrados.add(unPoi);
		}
			
	}
	return poiEncontrados;
}	

}