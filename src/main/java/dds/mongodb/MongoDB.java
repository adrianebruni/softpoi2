package dds.mongodb;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

import dds.softpoi.CGP;
import dds.softpoi.Parametros;

public class MongoDB {

	static Parametros parametros = new Parametros();
	static DBCollection mongoCollection = null;
	static DB mongoMyDB = null;
	
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
		
		mongoCollection = mongoMyDB.getCollection(nombreTabla);
		
	}
	
	public void eliminarTabla(String nombreTabla){
		try {
			mongoMyDB.getCollection(nombreTabla).drop();
		} catch (Exception e) {
			System.out.println("No se pudo eliminar la tabla : " + nombreTabla);
		}
	}
	
	public void insertarDato(Object unObjeto){
		// Intentamos insertar los datos de JSON a MongoDB
		try {
			Gson gson = new Gson();
			BasicDBObject objMongo = (BasicDBObject) JSON.parse(gson.toJson(unObjeto));
			mongoCollection.insert(objMongo);			
		}catch (Exception e) {
			System.out.println("Se produjo un error al intentar insertar los datos en MongoDB.");
			e.printStackTrace();
		}
	}
	
	public void buscarDato(String key, String valor){
		try {
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put(key, valor);
			DBCursor cursor = mongoCollection.find(searchQuery);
			while (cursor.hasNext()) {
				System.out.println("Valor esperado: " + valor);
				System.out.println("Valor encontrado: " + cursor.next().get(key).toString());
			}
		}catch (Exception e) {
			System.out.println("Se produjo un error al intentar buscar los datos en MongoDB.");
			e.printStackTrace();
		}
	}
	
	public DBCursor buscarDato(){
		try {
			return mongoCollection.find();			
		}catch (Exception e) {
			System.out.println("Se produjo un error al intentar buscar todos los datos en MongoDB.");
			e.printStackTrace();
			return null;
		}
	}
	
	public void insertarDato(JsonArray arrayConsulta){
		// Intentamos insertar los datos de JSON a MongoDB
		try {
			
			// Recorremos los datos externos obtenidos y los insertamos en la base
			for(JsonElement unDatoExterno : arrayConsulta){
				DBObject dbObjMongo = null;
				dbObjMongo = (DBObject) JSON.parse(unDatoExterno.toString());				
				mongoCollection.insert(dbObjMongo);				
			}
			
		}catch (Exception e) {
			System.out.println("Se produjo un error al intentar insertar los datos en MongoDB.");
			e.printStackTrace();
			return;
		}
	}
	
}
