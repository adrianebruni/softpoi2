package dds.softpoi;

import org.junit.Test;

public class TestPermisoBusqueda {

	@Test
	public void testPermisoBusqueda() {
	
		//crear Servidor
		Servidor servidorPpal = new Servidor();
		
		//crear administrador
		Administrador unAdministrador = new Administrador();
		unAdministrador.setClave("passPrueba");
		unAdministrador.setServidor(servidorPpal);
		servidorPpal.addAdmin(unAdministrador);
		
		//agrego POIs
		RepoPOI colPoisPrueba = new RepoPOI();
		servidorPpal.cargarPOIs(colPoisPrueba.Dame_Bolsa_POI());

		//creo un dispositivo de Consulta
		DispositivoConsulta dispositivo2 = new DispositivoConsulta("disp Flores2", 0.350219708, 0.715584992, "Flores Norte");
		dispositivo2.setFlagAuditoriaBusqueda(true);
		dispositivo2.setFlagNotificaciones(true);
		dispositivo2.setServidor(servidorPpal);
		
		//le doy permiso para que consulte Disponibilidad
		unAdministrador.agregarPermiso(PermisoBusqueda.getpermisoBusquedaSingleton());
		unAdministrador.commitPermisos(dispositivo2);
		System.out.println("Permisos Asignados: ");
		dispositivo2.imprimirPermisos();
		
		//imprimo la busqueda
		System.out.println("\nResultados de la busqueda: ");
		dispositivo2.imprimirBusqueda(unAdministrador.buscaPOI("Banco"));
		

		
	}
}
