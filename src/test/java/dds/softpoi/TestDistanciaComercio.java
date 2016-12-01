package dds.softpoi;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestDistanciaComercio {

@Test
	
	public void methDistanciaComercio() {
	
		System.out.println("Iniciando testDistancia Dispositivo - POI tipo Comercio");
	
		//Creamos Servidor
		Servidor servidorPpal = new Servidor();
		//Agregamos Terminal Consulta
		DispositivoConsulta dispositivoConsulta = new DispositivoConsulta("Cabildo y Juramento", -34.562073, -58.456817, "Flores Norte");
		dispositivoConsulta.setFlagAuditoriaBusqueda(true);
		dispositivoConsulta.setFlagNotificaciones(true);
		dispositivoConsulta.setServidor(servidorPpal);
		//Agregamos Administrador
		Administrador unAdministrador = new Administrador();
		unAdministrador.setClave("passPrueba");
		unAdministrador.setServidor(servidorPpal);
		servidorPpal.addAdmin(unAdministrador);
		//Asignamos permiso a Terminal
		unAdministrador.agregarPermiso(PermisoCercania.getpermisoCercaniaSingleton());
		unAdministrador.commitPermisos(dispositivoConsulta);
		
		System.out.println("dispositivo: " + dispositivoConsulta.getNombre() + ", latitud: " + dispositivoConsulta.getLatitud() + ", longitud: " + dispositivoConsulta.getLongitud());
			
		//creo un Rubro para el Comercio
		Rubro rubroMerceria = new Rubro ("Merceria", 500.0);
		System.out.println("Rubro: " + rubroMerceria.getRubro() + ", radio de cercania: " + rubroMerceria.getRadioCercania());		
		
		//creo un POI Comercio de prueba
		Comercio merceriaJose = new Comercio("Merceria Jose",-34.5617237,-58.4586025,rubroMerceria);
		System.out.println(merceriaJose.getTipoPOI() + ": " + merceriaJose.getNombre() + ", latitud: " + merceriaJose.getLatitud() + ", longitud: " + merceriaJose.getLongitud());		
			
	
		assertEquals("Verificamos que el Comercio este cerca del dispositivo", true, dispositivoConsulta.estaCercaMio(merceriaJose));
		//System.out.println(super.rubroMerceria.distancia(dispositivo3.getLatitud(),dispositivo3.getLongitud(),rubroMerceria.latitud,rubroMerceria.longitud));
}
	
}
