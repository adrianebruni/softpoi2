package dds.softpoi;

//import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Date;

import org.junit.Test;

public class TestReporteBusqPorTerm {

	@Test
	public void testReporteBusqPorTerm(){

		Servidor servidorPpal = new Servidor();
				
		//agrego POIs
		RepoPOI colPoisPrueba = new RepoPOI();
		
		servidorPpal.cargarPOIs(colPoisPrueba.Dame_Bolsa_POI());
	
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 7, 25, 0, 0, 0);
        //Date unDia = cal.getTime();
		
        /*
         * 
         *   FALTA AGREGAR COLECCION DE POIS
         * 
         * 
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
	    */
	    
		ArrayList<ItemReporteTerminal> colResult = new ArrayList<ItemReporteTerminal>();
			
	    Administrador unUsuario = new Administrador();
		unUsuario.setNombre("Juan");
		unUsuario.setClave("passPrueba");
		unUsuario.setServidor(servidorPpal);
		
		// Lo agrego o no, depende si queremos que tenga permisos o no para generar reportes
		servidorPpal.addAdmin(unUsuario);
        
		//genero reporte por terminal
		colResult = unUsuario.reportePorTerminal();
		
		//le indico el display a utilizar para el reporte
		ReporteHistorico unReportePorTerminal = new ReporteHistorico();
		unReportePorTerminal.displayReportePorTerminal(colResult);
		
	}
	
}
