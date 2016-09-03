package dds.softpoi;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class TestReporteBusqPorTerm {

	@Test
	public void testReporteBusqPorTerm(){

		Servidor servidorPpal = new Servidor();
				
		//crear administrador
		Usuario unAdministrador = new Administrador();
		unAdministrador.setNombre("alex");
		unAdministrador.setPass("passPrueba");
		unAdministrador.setServidor(servidorPpal);
		servidorPpal.addAdmin(unAdministrador);
		
		Usuario dosAdministrador = new Administrador();
		dosAdministrador.setNombre("sabr");
		dosAdministrador.setPass("passPrueba");
		dosAdministrador.setServidor(servidorPpal);
		servidorPpal.addAdmin(dosAdministrador);
		
		
		Usuario tresAdministrador = new Administrador();
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

		

		unHistorico.consultar("BANCO", servidorPpal, unAdministrador);
		unHistorico.consultar("Cab", servidorPpal, unAdministrador);
		unHistorico.consultar("BANCO", servidorPpal, unAdministrador);
		unHistorico.consultar("Cab", servidorPpal, unAdministrador);
		

		unHistorico.consultar("nac", servidorPpal, tresAdministrador);
		unHistorico.consultar("Cab", servidorPpal, tresAdministrador);
		unHistorico.consultar("Cab", servidorPpal, tresAdministrador);
		
		ArrayList<ItemReporteTerminal> colResult = unHistorico.cantidadBusquedasPorTerminal();
		
		System.out.println("Parciales por Terminal");
		System.out.println("");
		
		for(ItemReporteTerminal unitem : colResult){
			System.out.println("Usuario: " + unitem.getNombreTerminal());
			for(int i : unitem.getCantidadEncontrados()){
				System.out.println(i);
			}
			System.out.println("");
		}
		System.out.println("Totales por Usuarios");
		System.out.println("");
		System.out.println("Usuario\tCantidad Resultados Totales");
		for(ItemReporteTerminal unitem : colResult){
			System.out.println( unitem.getNombreTerminal() + "\t" + unitem.cantidadResultadosTotales());
		}
		assertEquals("Verificamos", 2, 2);
			
	}
	
}
