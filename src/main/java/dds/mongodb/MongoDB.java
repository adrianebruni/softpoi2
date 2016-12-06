package dds.mongodb;

import java.util.regex.Pattern;

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
import dds.softpoi.Parametros;

public class MongoDB {
	
	private Parametros parametros = new Parametros();
	
	private MongoClient mongoConect = null;
	private DB mongoMyDB = null;
	private DBCollection mongoCollection = null;
	
	
	public MongoDB() {}
	
	@SuppressWarnings("deprecation")
	public void crearConexion(String nombreDB, String nombreTabla){
		
		if ( (nombreDB == null) || (nombreTabla == null) ){
			System.out.println("ERROR: El nombre de la Base y la Tabla son requeridos.");
			return;
		}
				
		// Connectamos con MongoDB
		this.mongoConect = new MongoClient(parametros.getServidorMongoDB(), parametros.getPuertoMongoDB());	
		
		// Indicamos la base de datos de mongo
		// Si la base no existe, MongoDB la crea automaticamente
		this.mongoMyDB = mongoConect.getDB(nombreDB);
 
		// Indicamos el nombre de la tabla (coleccion de almacenamiento)
		// Si la coleccion no existe, MongoDB la crea automaticamente
		mongoCollection = this.mongoMyDB.getCollection(nombreTabla);
		
	}
	
	
	
	public void cerrarConexion(){
		this.mongoCollection = null;
		this.mongoMyDB = null;
		this.mongoConect.close();
		this.mongoConect = null;
	}

	
	
	public void eliminarTabla(String nombreTabla){
		try {
			mongoMyDB.getCollection(nombreTabla).drop();
		} catch (Exception e) {
			System.out.println("No se pudo eliminar la tabla: " + nombreTabla);
			System.out.println("ERROR Stack Trace: ");
			e.printStackTrace();
			System.out.println("ERROR Throwable Message: " + e.getMessage());
		}
	}
	
	
	
	public DBCursor buscarDato(String key, String valorBuscado, boolean busquedaExacta){
		
		DBCursor cursor = null;
		
		try {
			
			BasicDBObject searchQuery = new BasicDBObject();
			
			if (busquedaExacta){
				searchQuery.put(key, valorBuscado.toUpperCase());
			}else{			
				Pattern regex = Pattern.compile(valorBuscado.toUpperCase());
				searchQuery.put(key, regex);
			}
			
			cursor = mongoCollection.find(searchQuery);
			
			System.out.println("cursor.count() = " + cursor.count() + " | cursor.size() = " + cursor.size());
			
			// Debug para ver los registros encontrados
			//while(cursor.hasNext()){
			//	System.out.println(cursor.next());
			//}
			
		}catch (Exception e) {
			System.out.println("Se produjo un error al intentar buscar todos los datos en MongoDB.");
			e.printStackTrace();
		}
		
		//List<DBObject> obj = collection.find(query).toArray();
		return cursor;
	}
	
	public DBCollection dameColeccion(){
		return this.mongoCollection;
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
	
	
	
	public void insertarDato(Object unObjeto){
		// Intentamos insertar los datos de un objeto a MongoDB
		try {
			Gson gson = new Gson();
			BasicDBObject objMongo = (BasicDBObject) JSON.parse(gson.toJson(unObjeto));
			mongoCollection.insert(objMongo);			
		}catch (Exception e) {
			System.out.println("Se produjo un error al intentar insertar los datos en MongoDB.");
			e.printStackTrace();
		}
	}
	
}
