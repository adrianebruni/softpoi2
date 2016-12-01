package dds.softpoi;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestDistanciaBanco {

	@Test
	
	public void methDistanciaBanco() {
	
		System.out.println("Iniciando testDistancia Dispositivo - POI tipo Banco");
		
		//Creamos Servidor
		Servidor servidorPpal = new Servidor();
		//Agregamos Terminal Consulta
		DispositivoConsulta dispositivoConsulta = new DispositivoConsulta("disp Flores2", 0.350219708, 0.715584992, "Flores Norte");
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
		
		
		
		
		//creo DispositivoConsulta
//		DispositivoConsulta dispositivoConsulta = new DispositivoConsulta("disp Flores2", 0.350219708, 0.715584992, "Flores Norte");

		System.out.println("dispositivoConsulta: " + dispositivoConsulta.getNombre() + ", latitud: " + dispositivoConsulta.getLatitud() + ", longitud: " + dispositivoConsulta.getLongitud());
		

		
		//creo un POI Banco de prueba
		Banco bancoNacion1 = new Banco("Banco Nacion microcentro",0.350219708,0.715484992);
		System.out.println(bancoNacion1.getTipoPOI() + ": " + bancoNacion1.getNombre() + ", latitud: " + bancoNacion1.getLatitud() + ", longitud: " + bancoNacion1.getLongitud());		
		
		assertEquals("Verificamos que el Banco este cerca del dispositivo", true, dispositivoConsulta.estaCercaMio(bancoNacion1));
		
		
	}
}
