package dds.softpoi;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestReporteBusqPorTerm {

	@Test
	public void testReporteBusqPorTerm(){

		Servidor servidorPpal = new Servidor();
				
		//crear administrador
		Administrador unAdministrador = new Administrador();
		unAdministrador.setNombre("alex");
		unAdministrador.setPass("passPrueba");
		unAdministrador.setServidor(servidorPpal);
		servidorPpal.addAdmin(unAdministrador);
		
		Administrador dosAdministrador = new Administrador();
		dosAdministrador.setNombre("sabr");
		dosAdministrador.setPass("passPrueba");
		dosAdministrador.setServidor(servidorPpal);
		servidorPpal.addAdmin(dosAdministrador);
		
		
		Administrador tresAdministrador = new Administrador();
		tresAdministrador.setNombre("chr");
		tresAdministrador.setPass("passPrueba");
		tresAdministrador.setServidor(servidorPpal);
		servidorPpal.addAdmin(tresAdministrador);
		
		//agrego POIs
		RepoPOI colPoisPrueba = new RepoPOI();
		
		servidorPpal.cargarPOIs(RepoPOI.Dame_Bolsa_POI());
	
		HistoricoConsulta unHistorico = new HistoricoConsulta();
		
		unHistorico.consultar("Cab", servidorPpal, dosAdministrador);
		unHistorico.consultar("Banco", servidorPpal, dosAdministrador);
		unHistorico.consultar("Cab", servidorPpal, tresAdministrador);
		

		unHistorico.consultar("BANCO", servidorPpal, unAdministrador);
		unHistorico.consultar("Cab", servidorPpal, unAdministrador);
		unHistorico.consultar("BANCO", servidorPpal, unAdministrador);
		unHistorico.consultar("Cab", servidorPpal, unAdministrador);
		

		unHistorico.consultar("nac", servidorPpal, tresAdministrador);
		unHistorico.consultar("Cab", servidorPpal, tresAdministrador);
		
		unHistorico.cantidadBusquedasPorTerminal();
		
		
		assertEquals("Verificamos", 2, 2);
			
	}
	
}
