package dds.repositorio;

import javax.persistence.EntityManager;

import dds.softpoi.ElementoDeConsulta;


public class ElementosDeConsulta  extends Repositorio{
	
	ElementosDeConsulta(EntityManager em){
		super(em);		
	}
	
	public void persistir(ElementoDeConsulta unElementoDeConsulta){
		em.getTransaction().begin();
		em.persist(unElementoDeConsulta);
		//System.out.println("ID:"+ unPoi.getIdpoi());
		em.getTransaction().commit();
	}
	
}
