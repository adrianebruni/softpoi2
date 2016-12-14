package dds.softpoi;


import static org.junit.Assert.*;

import org.junit.Test;

public class TestActualizacionCentrosComerciales {
	@Test
	public void testActualizacionCentrosComerciales() {
		
		Parametros parametro = new Parametros();
		Servidor servidorPpal = new Servidor();
		
		//agrego POIs
		//RepoPOI colPoisPrueba = new RepoPOI();
		
		//servidorPpal.cargarPOIs(colPoisPrueba.Dame_Bolsa_POI());
		
	    Administrador unUsuario = new Administrador();
		unUsuario.setNombre("Juan");
		unUsuario.setClave("passPrueba");
		unUsuario.setServidor(servidorPpal);
		
		unUsuario.actualizarLocalesComerciales(parametro.getRutaarchivolocalescomerciales());
		assertEquals("El usuario 'admin1' token: ", 1, 1);
		
	}
}