package dds.persistencia;


import org.junit.Test;
//import static org.junit.Assert.assertEquals;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import dds.repositorio.Repositorio;
import dds.softpoi.*;

public class TestEntrega6Punto2 {


	@Test
	public void testPunto2() {
		
		// ENUNCIADO:
		// Crear un nuevo PoI, persistirlo, recuperarlo, eliminarlo y al solicitar nuevamente su
		// recuperación, la respuesta deberá ser que no existe (null).
		
		// Configuracion de persistencia
		final String PERSISTENCE_UNIT_NAME = "DDS";
		EntityManagerFactory emFactory;
		Repositorio repositorio;
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		repositorio = new Repositorio(emFactory.createEntityManager());
		
		// Creamos un POI de tipo BANCO y lo persistimos
		Banco unBanco = new Banco();
		unBanco.setLatitud(33);
		unBanco.setLongitud(44);
		unBanco.setNombre("Banco Nacion de la Republica Argentina");
		unBanco.setBarrio("Recoleta");
		unBanco.setPais("Argentina");
		unBanco.setGerente("Robledo");

		// Persistimos el POI de tipo BANCO
		repositorio.pois().persistir(unBanco);
		
		// Recuperamos el POI
		System.out.println("Recuperamos el POI");
		
		POI unPOIRecuperado = repositorio.pois().buscarPOIPorId(unBanco.getIdpoi());
		System.out.println("POI RECUPERADO: ID= " + unPOIRecuperado.getIdpoi() + " | Nombre: " + unPOIRecuperado.getNombre());
		
		// Eliminamos el POI recien insertado
		if (repositorio.pois().eliminarPOI(unPOIRecuperado)){
			System.out.println("Se eliminó el POI RECUPERADO: ID= " + unBanco.getIdpoi());
		}else{
			System.out.println("Se produjo un error al eliminar el POI RECUPERADO: ID= " + unBanco.getIdpoi());
		}
		
		// Recuperamos nuevamente el POI eliminado y verificamos que ya no existe
		unPOIRecuperado = repositorio.pois().buscarPOIPorId(unBanco.getIdpoi());
		if (unPOIRecuperado == null){
			System.out.println("No se encuentra el POI: ID=" + unBanco.getIdpoi());
		}else{
			System.out.println("Algo fallo porque el POI existe: ID=" + unBanco.getIdpoi());
		}
		
		repositorio.cerrar();
		emFactory.close();
		
	}
	
}
