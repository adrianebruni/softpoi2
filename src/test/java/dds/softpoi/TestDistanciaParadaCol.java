package dds.softpoi;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestDistanciaParadaCol {

@Test
	
	public void methDistanciaParada() {
		System.out.println("Iniciando testDistancia Dispositivo - POI");
	
	
		//Creamos Servidor
		Servidor servidorPpal = new Servidor();
		//Agregamos Terminal Consulta
		DispositivoConsulta dispositivoConsulta = new DispositivoConsulta("Cabildo y Juramento", -34.562073, -58.456817, "Flores Norte");
		dispositivoConsulta.setFlagAuditoriaBusqueda(true);
		dispositivoConsulta.setFlagNotificaciones(true);
		dispositivoConsulta.setServidor(servidorPpal);
		//Agregamos Administrador
		Administrador unAdministrador = new Administrador();
		unAdministrador.setPass("passPrueba");
		unAdministrador.setServidor(servidorPpal);
		servidorPpal.addAdmin(unAdministrador);
		//Asignamos permiso a Terminal
		unAdministrador.agregarPermiso(PermisoCercania.getpermisoCercaniaSingleton());
		unAdministrador.commitPermisos(dispositivoConsulta);
		
		System.out.println("dispositivo: " + dispositivoConsulta.getNombre() + ", latitud: " + dispositivoConsulta.getLatitud() + ", longitud: " + dispositivoConsulta.getLongitud());

		//creo un POI Parada de Colectivo de prueba
		ParadaColectivo callaoCordoba132 = new ParadaColectivo("64 Cabildo y Juramento",-34.562471,-58.4566566);
		callaoCordoba132.setEmpresa("Nuevos Rumbos S.A.");
		callaoCordoba132.setNumeroDeLinea(132);
		//System.out.println("tipo de POI:" + callaoCordoba132.tipoPOI());
		System.out.println(callaoCordoba132.tipoPOI() + ": " + callaoCordoba132.getNombre() + ", latitud: " + callaoCordoba132.getLatitud() + ", longitud: " + callaoCordoba132.getLongitud());		

		assertEquals("Verificamos que la parada este cerca del dispositivo", true, dispositivoConsulta.estaCercaMio(callaoCordoba132));
		
	}
	
}
