package dds.softpoi;

import org.junit.Test;

import dds.schedule.Job;
import dds.schedule.JobBajaPOIs;
import dds.softpoi.RepoPOI;

public class TestJobBajaPOIs {
	@Test
	public void testJobBajaPois(){
		//crear Servidor
		Servidor servidorPpal = new Servidor();

		//agrego POIs
		RepoPOI colPoisPrueba = new RepoPOI();
		servidorPpal.cargarPOIs(colPoisPrueba.Dame_Bolsa_POI());
		String rutaBajas = servidorPpal.getParametros().getRutaXMLBajas();
		int cantidadPois = servidorPpal.getColPOIs().size();
		int cantidadBorrados = servidorPpal.colPOIsBorrados.size();		
		System.out.println("Antes de JobBajaPOIs, el servidor tiene en su coleccion: " + cantidadPois + " y borrados: " + cantidadBorrados);
		
		Job jobBajaPois = new JobBajaPOIs(null, "baja de pois", 1, servidorPpal, rutaBajas);
		
		jobBajaPois.ejecutar();
		
		cantidadPois = servidorPpal.getColPOIs().size();
		cantidadBorrados = servidorPpal.colPOIsBorrados.size();
		System.out.println("Luego de JobBajaPOIs, el servidor tiene en su coleccion: " + cantidadPois + " y borrados: " + cantidadBorrados);

		
	}
}
