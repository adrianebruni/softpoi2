package dds.softpoi;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
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
		
		//agrego dos busquedas en otro dia forzando la fecha
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 8, 25, 0, 0, 0);
        Date otroDia = cal.getTime();

        ElementoDeConsulta elemA = new ElementoDeConsulta(otroDia, "consultaUsuario1", 0.011, "terminalAbasto", 20);
        servidorPpal.getHistoricoConsulta().setelementosDeConsulta(elemA);

        ElementoDeConsulta elemB = new ElementoDeConsulta(otroDia, "consultaUsuario2", 0.088, "terminalLanus", 30);
        servidorPpal.getHistoricoConsulta().setelementosDeConsulta(elemB);
		
		//consultamos historico por "BANCO"		
		servidorPpal.getHistoricoConsulta().cantidadBusquedasPorFecha(unAdministrador);
		
		
		fail("Not yet implemented");
	}

}
