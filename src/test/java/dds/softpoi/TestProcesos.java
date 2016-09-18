package dds.softpoi;

import java.util.ArrayList;

import org.junit.Test;

public class TestProcesos {

@Test	

	public void cargarXml(){

		Servidor unServidor = new Servidor();
		RepoPOI colPoisPrueba = new RepoPOI();
		unServidor.cargarPOIs(colPoisPrueba.Dame_Bolsa_POI());
		
		Administrador unAdministrador = new Administrador();
		unAdministrador.setNombre("Alexis");
		unAdministrador.setPass("passPrueba");
		unAdministrador.setServidor(unServidor);
		unServidor.addAdmin(unAdministrador);
		
		ArrayList<POI> pois =unAdministrador.buscaPOI("campo");
		System.out.println("Tamaño de la lista dew pois :" + pois.size());
		
		Procesos proceso = new Procesos();
		proceso.procesoBajaDePOI(unServidor,unAdministrador,"/home/alexis/work/softpoi2/src/test/java/dds/softpoi/archivobajas.xml");
    
		pois =unAdministrador.buscaPOI("campo");
		System.out.println("Tamaño de la lista dew pois :" + pois.size());
	}

}

      
	
	

