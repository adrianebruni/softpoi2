package dds.softpoi;

import org.junit.Test;

import dds.schedule.Job;
import dds.schedule.JobActualizarLocalesComerciales;
import dds.softpoi.RepoPOI;

public class TestJobActualizarLocalesComerciales {
	@Test
	public void testJobActualizarLocalesComerciales(){
		//crear Servidor
		Servidor servidorPpal = new Servidor();

		//agrego POIs
		RepoPOI colPoisPrueba = new RepoPOI();
		servidorPpal.cargarPOIs(colPoisPrueba.Dame_Bolsa_POI());
		String rutaActualizar1 = servidorPpal.getParametros().getRutaComerciosPalabrasClave1();
		
		int cantidadPois = servidorPpal.getColPOIs().size();
		int cantidadBorrados = servidorPpal.colPOIsBorrados.size();		
		System.out.println("Antes del Job, el servidor tiene en su coleccion: " + cantidadPois + " y borrados: " + cantidadBorrados);
		
		Job jobActualizaLocalesComerciales = new JobActualizarLocalesComerciales(null, "ActualizaLocales", 1, servidorPpal, rutaActualizar1);
		
		jobActualizaLocalesComerciales.ejecutar();
		
		cantidadPois = servidorPpal.getColPOIs().size();
		cantidadBorrados = servidorPpal.colPOIsBorrados.size();
		System.out.println("Luego del Job, el servidor tiene en su coleccion: " + cantidadPois + " y borrados: " + cantidadBorrados);

		
	}
}
