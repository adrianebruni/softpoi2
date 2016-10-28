package test;

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

public class otrotest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String PERSISTENCE_UNIT_NAME = "DDS";
		EntityManagerFactory emFactory;
		Repositorio repositorio;
		
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		repositorio = new Repositorio(emFactory.createEntityManager());
		
		//POI unpoi = new POI();
		//unpoi.setNombre("Poi de Alex");
		
		Banco unbanco = new Banco();
		unbanco.setLatitud(10);
		unbanco.setLongitud(15);
		unbanco.setNombre("bancofrances");
		unbanco.setGerente("Rodo");
		repositorio.pois().persistir(unbanco);
		

		ParadaColectivo unalinea = new ParadaColectivo();
		unalinea.setLatitud(20);
		unalinea.setLongitud(25);
		unalinea.setNumeroDeLinea(10);
		unalinea.setNombre("Parada del 10");
		unalinea.setCalle("Las Herasa");
		repositorio.pois().persistir(unalinea);
		
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
		
		POI recuperado = repositorio.pois().buscarPOIPorId(2);
		System.out.println("El poi es: " + recuperado.getIdpoi() + " " + recuperado.getNombre());
		System.out.println("Latitud : " + recuperado.getLatitud() + " - Longitud: " + recuperado.getLongitud());
		
		recuperado.setLatitud(99);
		recuperado.setNombre("lo cambio");
		repositorio.pois().actualizarPOI(recuperado);
		
		POI recuperado2 = repositorio.pois().buscarPOIPorId(2);
		System.out.println("El poi es: " + recuperado2.getIdpoi() + " " + recuperado2.getNombre());
		System.out.println("Latitud : " + recuperado2.getLatitud() + " - Longitud: " + recuperado2.getLongitud());

	}

}
