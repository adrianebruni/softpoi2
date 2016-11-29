package dds.mongodb;

import org.junit.Test;

import dds.json.BancoDTO;
import dds.json.CentroDTO;
import dds.softpoi.POI;
import dds.softpoi.Parametros;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//import static org.junit.Assert.assertEquals;

public class TestEntrega7Punto3 {

	public ArrayList<POI> colPOIsExternos = new ArrayList<POI>();
	Parametros parametros = new Parametros();
	
	@Test
	public void testPunto3() {
		
		
		
		// ENUNCIADO:
		// Los costos de acceso a los servicios externos de POIs se están volviendo muy onorosos,
		// por lo cual es necesario minimizar el acceso a ellos.
		
		// SOLUCION:
		// Se opto por desarrollar un demonio, el cual se ejecuta automaticamente todos los dias
		// El mismo se conecta y consume los servicios externos d ePOIs y los guarda en una base no relacional
		// De este modo, las terminales consultan al servidor propio, disminuyendo el costo del servicio del proveedor.
		
		try {
			// Connectamos con MongoDB  
			MongoDB myMongo = new MongoDB();
			myMongo.crearConexion("db_pois_externos", "poi");

			actualizoDesdeDatosExternos();
			myMongo.eliminarTabla("poi");
						
			for(POI unPOI : colPOIsExternos){
				try {	
					myMongo.insertarDato(unPOI);
				}catch (Exception e) {
					System.out.println("Se produjo un error al intentar insertar los datos en MongoDB.");
					e.printStackTrace();
				}
			}
			
			myMongo.cerrarConexion();
			
			System.out.println("Conexion cerrada");
			
		}catch (Exception e) {
			System.out.println("Se produjo un error al intentar conectar con MongoDB.");
			e.printStackTrace();
			return;
		}	

			/*
		
		// Buscamos un valor insertado
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("comuna", 1);
		DBCursor cursor = mongoTablaPOI.find(searchQuery);
		while (cursor.hasNext()) {
			System.out.println("Valor esperado: 1");
			System.out.println("Valor encontrado: " + cursor.next().get("comuna").toString());
			assertEquals(1, cursor.next().get("comuna"));
		}
*/		
		
	}
	
	// ------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------------

	
	public void actualizoDesdeDatosExternos() {
		
		colPOIsExternos.removeAll(colPOIsExternos);
		ArrayList<POI> colAUX = new ArrayList<POI>();

		
		BancoDTO bancosExternos = new BancoDTO();
		try{
			colAUX.addAll(bancosExternos.dameDatosExternos(parametros.getUrlJsonBanco()));
		}catch (Exception e) {
			//System.out.println("No se encontraron banco.nombre externos");
		}
		
		CentroDTO centrosExternos = new CentroDTO();
		
		try{
			colAUX.addAll(centrosExternos.dameDatosExternos(parametros.getUrlJsonCentro()));
		}catch (Exception e) {
			//System.out.println("No se encontraron centros externos");
		}
		Set<POI> datosExternosSinRepetir = new HashSet<POI>();
		datosExternosSinRepetir.addAll(colAUX);
		colPOIsExternos.addAll(datosExternosSinRepetir);
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
