package dds.mongodb;

import org.junit.Test;

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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import static org.junit.Assert.assertEquals;

public class TestEntrega7Punto3 {

	@Test
	public void testPunto3() {
		
		// ENUNCIADO:
		// Los costos de acceso a los servicios externos de POIs se están volviendo muy onorosos,
		// por lo cual es necesario minimizar el acceso a ellos.
		
		// SOLUCION:
		// Se opto por desarrollar un demonio, el cual se ejecuta automaticamente todos los dias
		// El mismo se conecta y consume los servicios externos d ePOIs y los guarda en una base no relacional
		// De este modo, las terminales consultan al servidor propio, disminuyendo el costo del servicio del proveedor.
		
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
			mongoMyDB.getCollection("poi").drop();
			mongoTablaPOI = mongoMyDB.getCollection("poi");
			
			
		}catch (Exception e) {
			System.out.println("Se produjo un error al intentar conectar con MongoDB.");
			e.printStackTrace();
			return;
		}
		
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
				
		
		/****************************************************************** 
		 * Buscamos un valor insertado
		 ******************************************************************/
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("comuna", 1);
		DBCursor cursor = mongoTablaPOI.find(searchQuery);
		while (cursor.hasNext()) {
			System.out.println("Valor esperado: 1");
			System.out.println("Valor encontrado: " + cursor.next().get("comuna").toString());
			assertEquals(1, cursor.next().get("comuna"));
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
