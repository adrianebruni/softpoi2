package dds.softpoi;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestDistanciaCGP {
	
@Test
	
	public void methDistanciaCGP() {
	
		System.out.println("Iniciando testDistancia Dispositivo - POI tipo CGP");
	
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
		

		//creo una Comuna para el CGP
		Comuna comunaFlores = new Comuna (1, 0.350219707, 0.52475748, 0.698131701, 0.715584993, "lanus, lomas de zamora");
		System.out.println("Comuna, limite Sur: " + comunaFlores.getLimSur() + ", Limite Norte: " + comunaFlores.getLimNorte() + ", Limite Este: " + comunaFlores.getLimEste() + ", Limite Oeste: " + comunaFlores.getLimOeste());		
				
		//creo un POI CGP de prueba
		CGP cgpFlores = new CGP("CGP Flores",0.36,0.70);
		cgpFlores.setComuna(comunaFlores);
		System.out.println(cgpFlores.getTipoPOI() + ": " + cgpFlores.getNombre() + ", latitud: " + cgpFlores.getLatitud() + ", longitud: " + cgpFlores.getLongitud());		
				
		assertEquals("Verificamos que el CGP este cerca del dispositivo", true, dispositivoConsulta.estaCercaMio(cgpFlores));

	}
}
