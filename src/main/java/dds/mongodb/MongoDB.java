package dds.mongodb;

import com.google.gson.JsonElement;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

import dds.softpoi.Parametros;

public class MongoDB {

	static Parametros parametros = new Parametros();
	DBCollection mongoTablaPOI = null;
	
	public MongoDB() {}
	
	//nombreDB = "db_pois_externos"
	//nombreTabla = "poi"
	public void crearConexion(String nombreDB, String nombreTabla){
		
		/****************************************************************** 
		 * Connectamos con MongoDB  
		 ******************************************************************/
		@SuppressWarnings("resource")
		MongoClient mongoConect = new MongoClient(parametros.getServidorMongoDB(), parametros.getPuertoMongoDB());	
		
		/****************************************************************** 
		 * Indicamos la base de datos de mongo
		 * Si la base no existe, MongoDB la crea automaticamente
		 ******************************************************************/
		@SuppressWarnings("deprecation")
		DB mongoMyDB = mongoConect.getDB(nombreDB);
		
		/****************************************************************** 
		 * Indicamos el nombre de la tabla (coleccion de almacenamiento)
		 * Si la coleccion no existe, MongoDB la crea automaticamente
		 ******************************************************************/
		mongoMyDB.getCollection(nombreTabla).drop();
		mongoTablaPOI = mongoMyDB.getCollection(nombreTabla);
		
	}
	
	
	public void insertarDato(Object unObjeto){
		// Intentamos insertar los datos de JSON a MongoDB
		try {
			DBObject dbObjMongo = null;
			dbObjMongo = (DBObject) unObjeto;
			mongoTablaPOI.insert(dbObjMongo);				
		}catch (Exception e) {
			System.out.println("Se produjo un error al intentar insertar los datos en MongoDB.");
			e.printStackTrace();
		}
	}
	
	public void buscarDato(String key, String valor){
		try {
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put(key, valor);
			DBCursor cursor = mongoTablaPOI.find(searchQuery);
			while (cursor.hasNext()) {
				System.out.println("Valor esperado: " + valor);
				System.out.println("Valor encontrado: " + cursor.next().get(key).toString());
			}
		}catch (Exception e) {
			System.out.println("Se produjo un error al intentar buscar los datos en MongoDB.");
			e.printStackTrace();
		}
	}
	
	
	
	
	/*
	public insertarJSON(){
		// Intentamos insertar los datos de JSON a MongoDB
		try {
			
			// Recorremos los datos externos obtenidos y los insertamos en la base
			for(JsonElement unDatoExterno : arrayConsulta){
				DBObject dbObjMongo = null;
				dbObjMongo = (DBObject) JSON.parse(unDatoExterno.toString());				
				mongoTablaPOI.insert(dbObjMongo);				
			}
			
		}catch (Exception e) {
			System.out.println("Se produjo un error al intentar insertar los datos en MongoDB.");
			e.printStackTrace();
			return;
		}
	}
	*/
}
