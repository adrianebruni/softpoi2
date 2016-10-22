package dds.softpoi;

import org.junit.Test;

public class TestPermisoCercania {

	@Test
	public void testPermisoCercania() {
	
		//crear Servidor
		Servidor servidorPpal = new Servidor();

		DispositivoConsulta dispositivo2 = new DispositivoConsulta("disp Flores2", 0.350219708, 0.715584992, "Flores Norte");
		dispositivo2.setFlagAuditoriaBusqueda(true);
		dispositivo2.setFlagNotificaciones(true);
		dispositivo2.setServidor(servidorPpal);
		
		//crear administrador
		Administrador unAdministrador = new Administrador();
		unAdministrador.setClave("passPrueba");
		unAdministrador.setServidor(servidorPpal);
		servidorPpal.addAdmin(unAdministrador);
		
		//TAsigno permiso cercania
		unAdministrador.agregarPermiso(PermisoCercania.getpermisoCercaniaSingleton());
		unAdministrador.commitPermisos(dispositivo2);
		dispositivo2.imprimirPermisos();
		
		//imprimo caracteristicas de dispositivo2
		System.out.println("dispositivo: " + dispositivo2.getNombre() + ", latitud: " + dispositivo2.getLatitud() + ", longitud: " + dispositivo2.getLongitud() + "\n");

		//creo una Comuna para el CGP
		Comuna comunaFlores = new Comuna (1, 0.350219707, 0.52475748, 0.698131701, 0.715584993, "lanus, lomas de zamora");
			
		//creo un POI CGP de prueba
		CGP cgpFlores = new CGP("CGP Flores",0.36,0.70);
		cgpFlores.setComuna(comunaFlores);
		System.out.println("dispositivo 2: " + cgpFlores.getNombre() + ", latitud: " + cgpFlores.getLatitud() + ", longitud: " + cgpFlores.getLongitud() + "\n");		
		
		//"Verificamos que el CGP este cerca del dispositivo"
		System.out.println("El disp Flores2 queda cerca de CGP Flores? ->" + dispositivo2.estaCercaMio(cgpFlores));
			
	}
	
}
