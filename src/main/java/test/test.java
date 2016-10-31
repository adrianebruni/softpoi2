package test;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dds.repositorio.Repositorio;
import dds.softpoi.Administrador;
import dds.softpoi.DispositivoConsulta;
import dds.softpoi.Usuario;



public class test {

	public static void main(String[] args) {
		
		// Configuracion de persistencia
		final String PERSISTENCE_UNIT_NAME = "DDS";
		EntityManagerFactory emFactory;
		Repositorio repositorio;
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		repositorio = new Repositorio(emFactory.createEntityManager());
		
		/*
		System.out.println("INICIO PERSISTENCIA ADMINISTRADOR");
		Usuario unAdmin = new Administrador();
		unAdmin.setNombre("adri");
		unAdmin.setClave("1234");
		repositorio.usuarios().persistir(unAdmin);
		System.out.println("FIN PERSISTENCIA ADMINISTRADOR");
		*/
		
		System.out.println("INICIO PERSISTENCIA TERMINAL");
		Usuario unaTerminal = new DispositivoConsulta("terminalAbasto",10,20,"Abasto");
		unaTerminal.setClave("terminal1");
		unaTerminal.setEmail("terminalAbasto@softpoi.com");
		repositorio.usuarios().persistir(unaTerminal);
		System.out.println("FIN PERSISTENCIA TERMINAL");
		
		
	}

}