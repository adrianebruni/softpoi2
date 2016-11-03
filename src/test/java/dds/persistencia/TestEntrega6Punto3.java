package dds.persistencia;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

//import static org.junit.Assert.assertEquals;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import dds.repositorio.Repositorio;
import dds.softpoi.*;

public class TestEntrega6Punto3 {


	@Test
	public void testPunto3() {
		
		// ENUNCIADO:
		// Realizar una busqueda, persistirla, recuperarla y verificar que corresponda al objeto de esa busqueda
		// e incluya referencias a los POI
		
		// Configuracion de persistencia
		final String PERSISTENCE_UNIT_NAME = "DDS";
		EntityManagerFactory emFactory;
		Repositorio repositorio;
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		repositorio = new Repositorio(emFactory.createEntityManager());
		
		
		//
		
		Servidor servidorPpal = new Servidor();
		Administrador juanAdmin = new Administrador();
		juanAdmin.setNombre("Juan");
		juanAdmin.setClave("passPrueba");
		juanAdmin.setServidor(servidorPpal);
		juanAdmin.setFlagAuditoriaBusqueda(true);
		servidorPpal.addAdmin(juanAdmin);	
		servidorPpal.setRepositorio(repositorio);
		
		// 1) Crear un par de POIs y persistirlos.
		Banco unbanco = new Banco();
		unbanco.setLatitud(10);
		unbanco.setLongitud(15);
		unbanco.setNombre("bancofrances");
		unbanco.setGerente("Peralta");
		//repositorio.pois().persistir(unbanco);
		juanAdmin.cargarPOI(unbanco);
		
		Banco otrobanco = new Banco();
		otrobanco.setLatitud(12);
		otrobanco.setLongitud(13);
		otrobanco.setNombre("bancogalicia");
		otrobanco.setGerente("Ale");
		//repositorio.pois().persistir(otrobanco);
		juanAdmin.cargarPOI(otrobanco);
		
		ParadaColectivo unalinea = new ParadaColectivo();
		unalinea.setLatitud(20);
		unalinea.setLongitud(25);
		unalinea.setNumeroDeLinea(10);
		unalinea.setNombre("Parada del 10");
		unalinea.setCalle("Las Herasa");
		juanAdmin.cargarPOI(unalinea);
		
		// Buscamos un poi para que genere historial de busqueda
		juanAdmin.buscaPOI("bancogal");
		
		// 3) Realizar una busqueda desde la terminal de consulta y persistirla
		
		
		// 4) Recuperar la busqueda realizada
		ArrayList<ElementoDeConsulta> resultadosDeBusquedas = juanAdmin.historialBusquedaPantalla("Juan", null, null);
		System.out.println("El usuario es: " + resultadosDeBusquedas.get(0).getTipoUsuario());
		
		
		repositorio.cerrar();
		emFactory.close();
	}
	
}
