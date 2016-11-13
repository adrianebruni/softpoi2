package dds.repositorio;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;

public class Repositorio {
	
	private POIs pois;
	private Usuarios usuarios;
	private ElementosDeConsulta elementosDeConsulta;
	
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
	
	public ElementosDeConsulta elementosDeConsulta(){
		if(elementosDeConsulta == null){
			elementosDeConsulta = new ElementosDeConsulta(em);
		}
		return elementosDeConsulta;
	}	
	
	
	public void cerrar(){
		em.close();
	}
}
