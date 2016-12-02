package dds.softpoi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

import dds.bbdd.DBMySQL;
import dds.mongodb.MongoDB;

public class BuscadorConcreto implements BuscadorAbstracto {
	
	private static Parametros objParam = new Parametros();
	
	@SuppressWarnings("deprecation")
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
		
		BasicDBObject unDBObj;
		while(cursor.hasNext()){
			unDBObj = (BasicDBObject) cursor.next();
			
			//System.out.println(((BasicDBObject) cursor.next()).get("nombre"));	
			//System.out.println(cursor.next());
			//Futbolista futbolista = new Futbolista((BasicDBObject) cursor.next());
			
			
			//si tiene gerente, es un Banco
			try {
				if (unDBObj.containsField("gerente")){
					
					Banco unBanco = new Banco();
					unBanco.setNombre(unDBObj.getString("nombre"));
					unBanco.setLatitud(Double.parseDouble(unDBObj.getString("latitud")));
					unBanco.setLongitud(Double.parseDouble(unDBObj.getString("longitud")));
					
					unBanco.setZona(unDBObj.getString("sucursal"));
					unBanco.setGerente(unDBObj.getString("gerente"));
					
					System.out.println("Gerente es : " + unDBObj.getString("gerente"));
					
					auxPOIs.add(unBanco);
					
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("nunca pasa por aca");
			}
			//si tiene director, es un CGP
			try {
				if (unDBObj.containsField("director")){
					CGP unCGP = new CGP();
					unCGP.setNombre(unDBObj.getString("nombre"));
					unCGP.setLatitud(Double.parseDouble(unDBObj.getString("latitud")));
					unCGP.setLongitud(Double.parseDouble(unDBObj.getString("longitud")));
					
					Comuna unaComuna = new Comuna();
					unaComuna.setZonas(((BasicDBObject)unDBObj.get("comuna")).getString("zonas"));
					unCGP.setComuna(unaComuna);
					auxPOIs.add(unCGP);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
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