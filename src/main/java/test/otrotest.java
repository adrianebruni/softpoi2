package test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import dds.repositorio.Repositorio;
import dds.softpoi.Banco;
import dds.softpoi.POI;
import dds.softpoi.ParadaColectivo;

public class otrotest {

	public static void main(String[] args) {
		
		// Configuracion de persistencia
		final String PERSISTENCE_UNIT_NAME = "DDS";
		EntityManagerFactory emFactory;
		Repositorio repositorio;
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		repositorio = new Repositorio(emFactory.createEntityManager());
		
		System.out.println("Creamo un POI BANCO");
		
		// Creamos un POI de tipo BANCO y lo persistimos
		Banco unbanco = new Banco();
		unbanco.setLatitud(10);
		unbanco.setLongitud(15);
		unbanco.setNombre("bancofrances");
		unbanco.setGerente("Rodo");
		repositorio.pois().persistir(unbanco);
		
		// Creamos un POI de tipo ParadaColectivo y lo persistimos
		ParadaColectivo unalinea = new ParadaColectivo();
		unalinea.setLatitud(20);
		unalinea.setLongitud(25);
		unalinea.setNumeroDeLinea(10);
		unalinea.setNombre("Parada del 10");
		unalinea.setCalle("Las Herasa");
		repositorio.pois().persistir(unalinea);
		/*
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
		uncomercio.setLatitud(40);
		uncomercio.setLongitud(45);
		uncomercio.setNombre("MacDonals");
		uncomercio.setCalle("mitre");
		Rubro unrubro = new Rubro();
		unrubro.setRadioCercania(5);
		unrubro.setRubro("Comida chatarra");
		uncomercio.setRubro(unrubro);
		repositorio.pois().persistir(uncomercio);
		*/
		// Recuperamos el POI con ID = 2 y mostramos su atributos
		POI recuperado = repositorio.pois().buscarPOIPorId(2);
		System.out.println("POI RECUPERADO: ID= " + recuperado.getIdpoi() + " | Nombre: " + recuperado.getNombre());
		System.out.println("POI RECUPERADO: Latitud= " + recuperado.getLatitud() + " | Longitud= " + recuperado.getLongitud());
		
		// Al POI recuperado de la base de datos, le cambiamos la latitud y longitud.
		recuperado.setLatitud(40);
		recuperado.setLongitud(50);
		repositorio.pois().actualizarPOI(recuperado);
		
		// Recuperamos el POI con ID = 2 y mostramos su nuevos atributos
		POI recuperado2 = repositorio.pois().buscarPOIPorId(2);
		System.out.println("POI RECUPERADO MOD: ID= " + recuperado2.getIdpoi() + " | Nombre: " + recuperado2.getNombre());
		System.out.println("POI RECUPERADO MOD: Latitud= " + recuperado2.getLatitud() + " | Longitud= " + recuperado2.getLongitud());

	}

}
