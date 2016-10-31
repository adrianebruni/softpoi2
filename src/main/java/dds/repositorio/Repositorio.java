package dds.repositorio;

import javax.persistence.EntityManager;

public class Repositorio {
	
	private POIs pois;
	private Usuarios usuarios;
	
	protected EntityManager em;
	
	public Repositorio(EntityManager em){
		this.em = em;
	}

	public POIs pois(){
		if(pois == null){
			pois = new POIs(em);
		}
		return pois;
	}
	
	public Usuarios usuarios(){
		if(usuarios == null){
			usuarios = new Usuarios(em);
		}
		return usuarios;
	}	
	
	
	public void cerrar(){
		em.close();
	}
}
