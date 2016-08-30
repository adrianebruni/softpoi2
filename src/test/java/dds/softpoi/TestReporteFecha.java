package dds.softpoi;

import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TestReporteFecha {

	@Test
	public void testReporteFecha() {
		
		// Generar la instancia de servidor
		//crear Servidor
		Servidor servidorPpal = new Servidor();
				
		//crear administrador
		Administrador unAdministrador = new Administrador();
		unAdministrador.setNombre("Juan");
		unAdministrador.setPass("passPrueba");
		unAdministrador.setServidor(servidorPpal);
		servidorPpal.addAdmin(unAdministrador);
		
		//agrego POIs
		RepoPOI colPoisPrueba = new RepoPOI();
		servidorPpal.cargarPOIs(RepoPOI.Dame_Bolsa_POI());
	
		//buscamos coincidencias para COMUNA
		servidorPpal.buscaPOI("COMUNA",unAdministrador);

		//demoro la ejecucion de la segunda consulta a proposito 5 SEG
		try {Thread.sleep(5000);}
		catch (InterruptedException e)
		{e.printStackTrace();}

		//2da busqueda
		servidorPpal.buscaPOI("FRA",unAdministrador);
		
		//consultamos historico por "BANCO"		
		servidorPpal.cantidadBusquedasPorFecha(unAdministrador);
		
		
		fail("Not yet implemented");
	}

}
