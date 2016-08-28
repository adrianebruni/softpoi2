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
		servidorPpal.cargarPOIs(RepoPOI.Dame_Bolsa_POI());
		
		Timer unTimer = new Timer();
		unTimer.consultar("BANCO", servidorPpal, unAdministrador);
		HistoricoConsulta unHistorico = new HistoricoConsulta();
		unHistorico.consultar("BANCO", servidorPpal, unAdministrador);
		
		unHistorico.cantidadBusquedasPorFecha(unAdministrador);
		
		
		//para correr el test con algo
		assertEquals("Verificamos", 3, 3);
		
		
		
		
		
		// Generar los POIs
		
		// Realizar una consulta
		
		// 
		
		
		
		
		
		fail("Not yet implemented");
	}

}
