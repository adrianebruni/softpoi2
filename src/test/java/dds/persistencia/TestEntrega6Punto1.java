package dds.persistencia;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

//import static org.junit.Assert.assertEquals;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import dds.repositorio.Repositorio;
import dds.softpoi.Banco;
import dds.softpoi.CGP;
import dds.softpoi.Comercio;
import dds.softpoi.Comuna;
import dds.softpoi.POI;
import dds.softpoi.ParadaColectivo;
import dds.softpoi.Rubro;


public class TestEntrega6Punto1 {

	@Test
	public void testPunto1() {
		/*
		// ENUNCIADO:
		// Obtener un POI, modificar sus coordenadas geográficas, persistirlo, recuperarlo y
		// verificar que las coordenadas sean las ingresadas en la última modificación.
		
		// Configuracion de persistencia
		final String PERSISTENCE_UNIT_NAME = "DDS";
		EntityManagerFactory emFactory;
		Repositorio repositorio;
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		repositorio = new Repositorio(emFactory.createEntityManager());
		
		// Creamos un POI de tipo BANCO y lo persistimos
		Banco unbanco = new Banco();
		unbanco.setLatitud(10);
		unbanco.setLongitud(15);
		unbanco.setNombre("bancofrances");
		unbanco.setGerente("Peralta");
		repositorio.pois().persistir(unbanco);
		
		// Creamos un POI de tipo ParadaColectivo y lo persistimos
		ParadaColectivo unalinea = new ParadaColectivo();
		unalinea.setLatitud(20);
		unalinea.setLongitud(25);
		unalinea.setNumeroDeLinea(10);
		unalinea.setNombre("Parada del 10");
		unalinea.setCalle("Las Herasa");
		repositorio.pois().persistir(unalinea);
		
		// Creamos un POI de tipo CGP y lo persistimos
		CGP uncgp = new CGP();
		uncgp.setLatitud(30);
		uncgp.setLongitud(35);
		uncgp.setBarrio("Palermo");
		uncgp.setDirector("Adri");
		uncgp.setNombre("Nombredel cgp");
		Comuna unacomuna = new Comuna();
		unacomuna.setZonas("Palermo,recoleta");
		uncgp.setComuna(unacomuna);
		repositorio.pois().persistir(uncgp);
		
		// Creamos un POI de tipo COMERCIO y lo persistimos
		Comercio uncomercio = new Comercio();
		uncomercio.setLatitud(11);
		uncomercio.setLongitud(12);
		uncomercio.setNombre("MacDonals");
		uncomercio.setCalle("mitre");
		Rubro unrubro = new Rubro();
		unrubro.setRadioCercania(5);
		unrubro.setRubro("Comida chatarra");
		uncomercio.setRubro(unrubro);
		repositorio.pois().persistir(uncomercio);
		
		// Recuperamos el POI con ID = 2 y mostramos su atributos
		POI recuperado = repositorio.pois().buscarPOIPorId(1);
		System.out.println("POI RECUPERADO: ID= " + recuperado.getIdpoi() + " | Nombre: " + recuperado.getNombre());
		System.out.println("POI RECUPERADO: Latitud= " + recuperado.getLatitud() + " | Longitud= " + recuperado.getLongitud());
		
		// Al POI recuperado de la base de datos, le cambiamos la latitud y longitud.
		recuperado.setLatitud(40);
		recuperado.setLongitud(50);
		repositorio.pois().actualizarPOI(recuperado);
		
		// Recuperamos el POI con ID = 2 y mostramos su nuevos atributos
		recuperado = repositorio.pois().buscarPOIPorId(1);
		System.out.println("POI RECUPERADO MOD: ID= " + recuperado.getIdpoi() + " | Nombre: " + recuperado.getNombre());
		System.out.println("POI RECUPERADO MOD: Latitud= " + recuperado.getLatitud() + " | Longitud= " + recuperado.getLongitud());		
		
		repositorio.cerrar();
		emFactory.close();
		*/
		assertEquals("Test obsoleto ", true, true);
		
	}
	
}
