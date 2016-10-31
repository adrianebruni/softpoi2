package dds.repositorio;

import javax.persistence.EntityManager;
import dds.softpoi.Usuario;

public class Usuarios extends Repositorio{

	Usuarios(EntityManager em){
		super(em);		
	}
	
	public void persistir(Usuario unUsuario){
		em.getTransaction().begin();
		em.persist(unUsuario);
		em.getTransaction().commit();
	}
	
	public Usuario buscarUsuarioPorId(int id){
		Usuario unUsuario = null;
		try {
			unUsuario = em.find(Usuario.class, id);
		} catch (Exception e) {
			System.out.println("FALLO: buscarUsuarioPorId - clase Usuarios.java");
		}
		return unUsuario;
	}
}
