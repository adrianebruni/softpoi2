package dds.softpoi;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestAltaPOI {
	
	@Test
	public void testAltaPOI() throws Exception {
		
		//crear Servidor
		Servidor servidorPpal = new Servidor();
		
		//crear administrador
		Administrador unAdministrador = new Administrador();
		unAdministrador.setClave("passPrueba");
		unAdministrador.setServidor(servidorPpal);
		servidorPpal.addAdmin(unAdministrador);
		
		//crear POI
		
		// Creamos el objeto rango1 de la clase RangoHorario.
		RangoHorario rango1 = new RangoHorario("09:00:00", "13:00:00");
		// Creamos el objeto unDia de la clase Disponibilidad y le asignamos valores
		Disponibilidad unDia = new Disponibilidad();
		unDia.setDia("VIERNES");
		unDia.setRangoHorario(rango1);
		// Creamos un servicio y le asignamos valores.
		Servicio unServicio = new Servicio();
		unServicio.setServicio("apertura de cuenta corriente");
		unServicio.setDisponibilidad(unDia);
		// Creamos el objeto bancoFrances de la clase Banco.
		Banco bancoFrances = new Banco("BANCO FRANCES", -34.603075, -58.381653);
		bancoFrances.setServicios(unServicio);
		
		//Creamos un objeto parada1 de ParadaColectivo
		ParadaColectivo parada1 = new ParadaColectivo("Parada 59 Las Heras y Billingurst", -34.5832488, -58.4042595);
		
		//Creamos un objeto unCGP de CGP
		//creo una Comuna para el CGP
		Comuna comunaFlores = new Comuna (1, 0.350219707, 0.52475748, 0.698131701, 0.715584993, "lanus, lomas de zamora");
		//creo un POI CGP de prueba
		CGP cgpFlores = new CGP("CGP Flores",0.36,0.70);
		cgpFlores.setComuna(comunaFlores);
		
		int cantidadAnterior = servidorPpal.colPOIs.size();
		//ejecutar cargarPOI(unPOI)
		unAdministrador.cargarPOI(bancoFrances);
		unAdministrador.cargarPOI(parada1);
		unAdministrador.cargarPOI(cgpFlores);
		
		//System.out.println("CANT. POIs repositorio: " + servidorPpal.colPOIs.size());
		int cantidadPosterior = servidorPpal.colPOIs.size() - 3 ;
		//pruebo que este asignando ids incrementales a los POIs agregados al repositorio
		System.out.println("IDs y nombres de los POIs que fueron agregados al repositorio");
				for(POI unpoi : servidorPpal.getColPOIs()){
			System.out.println(unpoi.getIdpoi() + ", " + unpoi.getNombre());
		};
		
		//Verificamos que realmente se agregaron 3 POIs al repositorio
		assertEquals("Verificamos que realmente se agregaron 3 POIs al repositorio", cantidadAnterior, cantidadPosterior);
		
	}

}
