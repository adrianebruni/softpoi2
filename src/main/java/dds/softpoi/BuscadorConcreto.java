package dds.softpoi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import com.mongodb.DBCursor;

import dds.bbdd.DBMySQL;
import dds.mongodb.MongoDB;

public class BuscadorConcreto implements BuscadorAbstracto {
	
	private static Parametros objParam = new Parametros();
	
	public ArrayList<POI> consultar(String query, Servidor unServidor){
		
		Set<POI> auxPOIs = new HashSet<POI>();
		ArrayList<POI> colAux = new ArrayList<POI>();
	
		// Buscamos en MySQL
		DBMySQL objBBDD = new DBMySQL();
		objBBDD.getConexion();
		auxPOIs.addAll(objBBDD.buscarPOIs("nombre", query, false));	
		
		// Buscamos en MongoDB
		MongoDB objMongo = new MongoDB();
		DBCursor cursor;
		objMongo.crearConexion(objParam.getBaseMongoDB(), objParam.getTablaMongoPOIsExternos());
		cursor = objMongo.buscarDato("nombre", query, false);
		
		System.out.println("Hay que ver como pasar de objMongo a POI (con morphia)");			
		while(cursor.hasNext()){
			System.out.println(cursor.next());			
		}
		
		colAux.addAll(auxPOIs);
		
		return colAux;
	}
	
	
	public ArrayList<POI> consultar2(String query, Servidor unServidor){
		ArrayList<POI> poiEncontrados = new ArrayList<POI>();
		ArrayList<POI> todosLosPoi = new ArrayList<POI>();
		todosLosPoi.addAll(unServidor.getColPOIs());
		
		// agregamos los pois externos de mongoDB
		//todosLosPoi.addAll(unServidor.getcolPOIsExternos());
		
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
			}
			/*else{
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
					
			}*/
				
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