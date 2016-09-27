package dds.softpoi;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestAgregarAccionesAusuarios {

	@Test
	public void testBusquedaConAuditoria() {
	
		
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
		
		//TESTEO CERCANIA
		
		unAdministrador.agregarPermiso(PermisoCercania.getpermisoCercaniaSingleton());
		unAdministrador.commitPermisos(dispositivo2);
		
		//imprimo permisos
		if(dispositivo2.getPermisosActuales() == null){
	    	System.out.println("Terminal Sin Permisos\n\n");
	    }else{
	    	System.out.println("Terminal con permisos...\n\n");
	    	for(PermisosTerminal unPermiso: dispositivo2.getPermisosActuales()) {	
				System.out.println(unPermiso.tipoPermiso());
			}
	    }
		
		//creo DispositivoConsulta
System.out.println("dispositivo: " + dispositivo2.getNombre() + ", latitud: " + dispositivo2.getLatitud() + ", longitud: " + dispositivo2.getLongitud());

		//creo una Comuna para el CGP
		Comuna comunaFlores = new Comuna (1, 0.350219707, 0.52475748, 0.698131701, 0.715584993, "lanus, lomas de zamora");
		System.out.println("Comuna, limite Sur: " + comunaFlores.getLimSur() + ", Limite Norte: " + comunaFlores.getLimNorte() + ", Limite Este: " + comunaFlores.getLimEste() + ", Limite Oeste: " + comunaFlores.getLimOeste());		
				
		//creo un POI CGP de prueba
		CGP cgpFlores = new CGP("CGP Flores",0.36,0.70);
		cgpFlores.setComuna(comunaFlores);
		System.out.println(cgpFlores.tipoPOI()+ ": " + cgpFlores.getNombre() + ", latitud: " + cgpFlores.getLatitud() + ", longitud: " + cgpFlores.getLongitud());		
		
		assertEquals("Verificamos que el CGP este cerca del dispositivo", true, dispositivo2.estaCercaMio(cgpFlores));
	
	}
}
