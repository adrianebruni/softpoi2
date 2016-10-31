package dds.persistencia;

import org.junit.Test;
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
		
		// 1) Crear un par de POIs y persistirlos.
		
		// 2) Crear una terminal de consulta.
		
		// 3) Realizar una busqueda desde la terminal de consulta y persistirla
		
		// 4) Recuperar la busqueda realizada
		
		
		repositorio.cerrar();
		emFactory.close();
		
	}
	
}
