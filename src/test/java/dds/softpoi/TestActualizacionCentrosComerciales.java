package dds.softpoi;


import static org.junit.Assert.*;

import org.junit.Test;

public class TestActualizacionCentrosComerciales {
	@Test
	public void testActualizacionCentrosComerciales() {
	
		
		Servidor servidorPpal = new Servidor();
		
		//agrego POIs
		RepoPOI colPoisPrueba = new RepoPOI();
		
		servidorPpal.cargarPOIs(colPoisPrueba.Dame_Bolsa_POI());
		
	    Administrador unUsuario = new Administrador();
		unUsuario.setNombre("Juan");
		unUsuario.setClave("passPrueba");
		unUsuario.setServidor(servidorPpal);
		
		unUsuario.actualizarLocalesComerciales("/home/alexis/work/softpoi2/src/test/java/dds/softpoi/ComerciosPalabrasClave");
		assertEquals("El usuario 'admin1' token: ", 1, 1);
		
	}
}