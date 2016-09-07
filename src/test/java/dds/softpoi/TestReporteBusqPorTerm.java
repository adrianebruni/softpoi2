package dds.softpoi;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
		
		//agrego POIs
		RepoPOI colPoisPrueba = new RepoPOI();
		
		servidorPpal.cargarPOIs(colPoisPrueba.Dame_Bolsa_POI());
	
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 7, 25, 0, 0, 0);
        Date unDia = cal.getTime();
		
		ElementoDeConsulta elemA = new ElementoDeConsulta(unDia, "Banco Galicia", 0.91, "terminalAbasto", 20);
	    servidorPpal.getHistoricoConsulta().setelementosDeConsulta(elemA);
		ElementoDeConsulta elemB = new ElementoDeConsulta(unDia, "Banco Galicia", 0.90, "terminalAbasto", 20);
	    servidorPpal.getHistoricoConsulta().setelementosDeConsulta(elemB);
		ElementoDeConsulta elemC = new ElementoDeConsulta(unDia, "CGP tanto", 0.01, "terminalUTN", 1);
	    servidorPpal.getHistoricoConsulta().setelementosDeConsulta(elemC);
		ElementoDeConsulta elemD = new ElementoDeConsulta(unDia, "Bondi 101", 0.99, "terminalUTN", 3);
	    servidorPpal.getHistoricoConsulta().setelementosDeConsulta(elemD);
		ElementoDeConsulta elemE = new ElementoDeConsulta(unDia, "Torre Pizza", 0.011, "terminalCentro", 0);
	    servidorPpal.getHistoricoConsulta().setelementosDeConsulta(elemE);
		ElementoDeConsulta elemF = new ElementoDeConsulta(unDia, "Pool", 0.01, "terminalAbasto", 100);
	    servidorPpal.getHistoricoConsulta().setelementosDeConsulta(elemF);
	    
	    
		ArrayList<ItemReporteTerminal> colResult = new ArrayList<ItemReporteTerminal>();
		
		//ArrayList<ItemReporteTerminal> colResult = servidorPpal.getHistoricoConsulta() unHistorico.cantidadBusquedasPorTerminal();
		
	    Administrador unUsuario = new Administrador();
		unUsuario.setNombre("Juan");
		unUsuario.setPass("passPrueba");
		unUsuario.setServidor(servidorPpal);
		servidorPpal.addAdmin(unUsuario);
        
		
		colResult = unUsuario.reportePorTerminal();
	    if(colResult == null){
	    	System.out.println("Reporte generado por un usuario sin permisos...\n\n");
	    	assertEquals("Verificamos", 2, 1);
	    }else{
	    	System.out.println("Reporte generado por un usuario con permisos...\n\n");
	        
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
	
}
