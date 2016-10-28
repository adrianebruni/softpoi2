package dds.repositorio;

import javax.persistence.EntityManager;

public class Repositorio {
	private POIs pois;
	protected EntityManager em;
	
	public Repositorio(EntityManager em){
		this.em = em;
	}

	public POIs pois(){
		if(pois==null){
			pois = new POIs(em);
		}
		return pois;
	}
	
	public void cerrar(){
		em.close();
	}
}
