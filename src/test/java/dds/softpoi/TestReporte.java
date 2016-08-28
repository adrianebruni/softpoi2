package dds.softpoi;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestReporte {

	@Test
	public void testReporte() {
		
		// Generar la instancia de servidor
		//crear Servidor
		Servidor servidorPpal = new Servidor();
				
		//crear administrador
		Administrador unAdministrador = new Administrador();
		unAdministrador.setPass("passPrueba");
		unAdministrador.setServidor(servidorPpal);
		servidorPpal.addAdmin(unAdministrador);
		
		RepoPOI colPoisPrueba = new RepoPOI();
//		servidorPpal.colPOIs = colPoisPrueba.Dame_Bolsa_POI();
		
		
		
		
		// Generar los POIs
		
		// Realizar una consulta
		
		// 
		
		
		
		
		
		fail("Not yet implemented");
	}

}
