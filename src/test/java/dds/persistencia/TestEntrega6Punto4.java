package dds.persistencia;


import org.junit.Test;
//import static org.junit.Assert.assertEquals;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import dds.repositorio.Repositorio;
import dds.softpoi.*;

public class TestEntrega6Punto4 {


	@Test
	public void testPunto4() {
		
		// ENUNCIADO:
		// Dar de alta un usuario, persistirlo, recuperarlo,
		// realizar una modificación en el nombre de usuario,
		// recuperarlo y verificar que el cambio esté presente
		
		// Configuracion de persistencia
		final String PERSISTENCE_UNIT_NAME = "DDS";
		EntityManagerFactory emFactory;
		Repositorio repositorio;
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		repositorio = new Repositorio(emFactory.createEntityManager());
		
		
		System.out.println("INICIO PERSISTENCIA ADMINISTRADOR");
		Usuario unAdmin = new Administrador();
		unAdmin.setNombre("adri");
		unAdmin.setClave("1234");
		repositorio.usuarios().persistir(unAdmin);
		System.out.println("FIN PERSISTENCIA ADMINISTRADOR");
		
		
		Usuario unaTerminal = new DispositivoConsulta("terminalAbasto",10,20,"Abasto");
		unaTerminal.setClave("terminal1");
		unaTerminal.setEmail("terminalAbasto@softpoi.com");
		
		System.out.println("Insertamos el usuario: " + unaTerminal.getNombre());
		repositorio.usuarios().persistir(unaTerminal);
		System.out.println("Usuario: " + unaTerminal.getNombre() + " persistido OK!");
		
		int idTerminal = unaTerminal.getId_usuario();
		System.out.println("ID Usuario: " + idTerminal);
		
		unaTerminal = repositorio.usuarios().buscarUsuarioPorId(idTerminal);
		System.out.println("Usuario Recuperado ID: " + unaTerminal.getId_usuario());
		
		System.out.println("Modificamos el nombre " + unaTerminal.getNombre() + " por terminalZarlanga");
		unaTerminal.setNombre("terminalZarlanga");
		System.out.println("Persistimos el usuario: " + unaTerminal.getNombre());
		repositorio.usuarios().persistir(unaTerminal);
		
		unaTerminal = repositorio.usuarios().buscarUsuarioPorId(idTerminal);
		System.out.println("Usuario Recuperado ID: " + unaTerminal.getId_usuario());
		System.out.println("Usuario Recuperado NOMBRE: " + unaTerminal.getNombre());
		
		repositorio.cerrar();
		emFactory.close();
		
	}
	
}
