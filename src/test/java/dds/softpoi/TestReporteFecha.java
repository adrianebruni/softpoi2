package dds.softpoi;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class TestReporteFecha {

	@Test
	public void testReporteFecha() {
		
		// Generar la instancia de servidor
		//crear Servidor
		Servidor servidorPpal = new Servidor();
				
		//crear administrador
		Administrador juanAdmin = new Administrador();
		juanAdmin.setNombre("Juan");
		juanAdmin.setClave("passPrueba");
		juanAdmin.setServidor(servidorPpal);
		servidorPpal.addAdmin(juanAdmin);
		
		//agrego POIs
		RepoPOI colPoisPrueba = new RepoPOI();
		servidorPpal.cargarPOIs(colPoisPrueba.Dame_Bolsa_POI());
	
		//buscamos coincidencias para COMUNA
		juanAdmin.buscaPOI("COMUNA");

		//demoro la ejecucion de la segunda consulta a proposito 5 SEG
		//try {Thread.sleep(5000);}
		//catch (InterruptedException e)
		//{e.printStackTrace();}

		//2da busqueda
		juanAdmin.buscaPOI("FRA");
		
		//agrego dos busquedas en otro dia forzando la fecha
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 7, 25, 0, 0, 0);
        Date otroDia = cal.getTime();

        ElementoDeConsulta elemA = new ElementoDeConsulta(otroDia, "consultaUsuario1", 0.011, "terminalAbasto", 20, colPoisPrueba.Dame_Bolsa_POI());
        servidorPpal.getHistoricoConsulta().setelementosDeConsulta(elemA);
/*
        ElementoDeConsulta elemB = new ElementoDeConsulta(otroDia, "consultaUsuario2", 0.088, "terminalLanus", 30);
        servidorPpal.getHistoricoConsulta().setelementosDeConsulta(elemB);
        */
        //pido un reporte de parte de unAdministrador
        ArrayList<ItemReporteFecha> colResult = new ArrayList<ItemReporteFecha>();
        
        
        //creamos un usuario que no tiene autorizacion para pedir un reporte
		Administrador unUsuario = new Administrador();
		unUsuario.setNombre("Juan");
		unUsuario.setClave("passPrueba");
		unUsuario.setServidor(servidorPpal);
        
		// Lo agrego o no, depende si queremos que tenga permisos o no para generar reportes
		servidorPpal.addAdmin(unUsuario);
        
		//armamos el reporte por Fecha
		colResult = unUsuario.reportePorFecha();
		
		//elegimos el display a hacer del reporte -> imprimir por pantalla
		ReporteHistorico unReportePorFecha = new ReporteHistorico();
		unReportePorFecha.displayReportePorFecha(colResult);

	}

}
