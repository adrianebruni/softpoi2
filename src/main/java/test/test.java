package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class test {

	public static void main(String[] args) {
		
		// PUNTO 3:
		// Los costos de acceso a los servicios externos de POIs se están volviendo muy onorosos,
		// por lo cual es necesario minimizar el acceso a ellos.
		
		
		// RTA: Se opto por desarrollar un demonio, el cual se ejecuta automaticamente todos los dias
		// 		El mismo se conecta y consume los servicios externos d ePOIs y los guarda en una base no relacional
		//		De este modo, las terminales consultan al servidor propio, disminuyendo el costo del servicio del proveedor.
		//		Desventaja: aumento de consumo de banda ancha en nuestro servidor.
		
		// 1) Solicitar todos los datos del servicio externo
		
		// 2) Recorrer el JSon y
		
		
		JsonArray arrayConsulta = null;
		DBCollection mongoTablaPOI = null;
		
		

		
		
		
		
		
		
		// Intentamos conectarnos con la fuente externa para obtener todos los POIs
		try {
			String jsonString = readUrl("http://trimatek.org/Consultas/centro");
			arrayConsulta = (JsonArray) new JsonParser().parse(jsonString);
		
		} catch (Exception e) {
			System.out.println("Se produjo un error al intentar conectar con la fuente externa de datos.");
			e.printStackTrace();
			return;
		}	
		
		
		// Intentamos abrir una conexion mongoDB
		try {
			
			/****************************************************************** 
			 * Connectamos con MongoDB  
			 ******************************************************************/
			
			// tanto "servidorMongoDB" como el "puertoMongoDB", deben ir en la clase PARAMETROS
			String servidorMongoDB = "localhost";
			int puertoMongoDB = 27017;
					
			@SuppressWarnings("resource")
			MongoClient mongoConect = new MongoClient(servidorMongoDB, puertoMongoDB);
			
			
			/****************************************************************** 
			 * Indicamos la base de datos de mongo
			 * 
			 * Si la base no existe, MongoDB la crea automaticamente
			 ******************************************************************/
			@SuppressWarnings("deprecation")
			DB mongoMyDB = mongoConect.getDB("db_pois_externos");
			
			
			/****************************************************************** 
			 * Indicamos el nombre de la tabla (coleccion de almacenamiento)
			 * 
			 * Si la coleccion no existe, MongoDB la crea automaticamente
			 ******************************************************************/
			mongoTablaPOI = mongoMyDB.getCollection("poi");
			
			
			
			/**** Find and display ****/
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("comuna", 1);

			DBCursor cursor = mongoTablaPOI.find(searchQuery);

			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
			
			
		}catch (Exception e) {
			System.out.println("Se produjo un error al intentar conectar con MongoDB.");
			e.printStackTrace();
			return;
		}
		
		// Intentamos insertar los datos de JSON a MongoDB
		try {
			
			// Recorremos los datos externos obtenidos y los insertamos en la base
			for(JsonElement unDatoExterno : arrayConsulta){
				
				System.out.println(unDatoExterno.toString());
								
				DBObject dbObjMongo = null;
				
				dbObjMongo = (DBObject) JSON.parse(unDatoExterno.toString());
				
				System.out.println(dbObjMongo.toMap());
				
				mongoTablaPOI.insert(dbObjMongo);
				
			}
			
		}catch (Exception e) {
			System.out.println("Se produjo un error al intentar insertar los datos en MongoDB.");
			e.printStackTrace();
			return;
		}
				
		
	}
	
	// ------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------------
	
	// Funcion que obtiene todo los datos del parametro enviado por URL a string
	public static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}
	
}