package dds.softpoi;

import org.junit.Test;

public class TestBusqHistPantalla {

	@Test
	public void testBusqHistPantalla() {

		// Generar la instancia de servidor
		//crear Servidor
		Servidor servidorPpal = new Servidor();
				
		//crear administrador
		Administrador juanAdmin = new Administrador();
		juanAdmin.setNombre("Juan");
		juanAdmin.setClave("passPrueba");
		juanAdmin.setServidor(servidorPpal);
		juanAdmin.setFlagAuditoriaBusqueda(true);
		servidorPpal.addAdmin(juanAdmin);

		//agrego POIs
		RepoPOI colPoisPrueba = new RepoPOI();
		servidorPpal.cargarPOIs(colPoisPrueba.Dame_Bolsa_POI());
		
		//buscamos coincidencias para COMUNA
		juanAdmin.buscaPOI("banco");

		//2da busqueda
		//juanAdmin.buscaPOI("FRA");
		
		//juanAdmin.historialBusquedaPantalla("Juan",null,null);
		
		//print
		for(ElementoDeConsulta unitem : juanAdmin.historialBusquedaPantalla(null,"2016/10/24",null)){
			System.out.println(
					unitem.getFechaConsulta() + " " + unitem.getConsultaUsuario() + " " +
					unitem.getTiempoRespuesta() + " " + unitem.getTipoUsuario() + " " +
					unitem.getTotalResultados());
		}
		
	}
	
	
}
