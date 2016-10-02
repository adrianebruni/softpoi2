package dds.softpoi;


import org.junit.Test;

public class TestAgregarAccionesAusuarios {

	@Test
	public void testAgregarAccionesAusuarios() {
	
		
		//crear Servidor
		Servidor servidorPpal = new Servidor();

		DispositivoConsulta dispositivo2 = new DispositivoConsulta("disp Flores2", 0.350219708, 0.715584992, "Flores Norte");
		dispositivo2.setFlagAuditoriaBusqueda(true);
		dispositivo2.setFlagNotificaciones(true);
		dispositivo2.setServidor(servidorPpal);
				
		//crear administrador
		Administrador unAdministrador = new Administrador();
		unAdministrador.setPass("passPrueba");
		unAdministrador.setServidor(servidorPpal);
		servidorPpal.addAdmin(unAdministrador);
		
		
		//TESTEO agregado de permisos
		unAdministrador.agregarPermiso(PermisoCercania.getpermisoCercaniaSingleton());
		unAdministrador.commitPermisos(dispositivo2);
		
		//imprimo permisos de dispositivo2
		System.out.println("Agrego permisos:");
		dispositivo2.imprimirPermisos();
		
		
		//TESTEO modificado de permisos
		unAdministrador.agregarPermiso(PermisoCercania.getpermisoCercaniaSingleton());
		unAdministrador.agregarPermiso(PermisoDisponibilidad.getpermisoDisponibilidadSingleton());
		unAdministrador.commitPermisos(dispositivo2);
		
		//imprimo NUEVOS permisos de dispositivo2
		System.out.println("\nModificacion de permisos: ");
		dispositivo2.imprimirPermisos();
	
	}
}
