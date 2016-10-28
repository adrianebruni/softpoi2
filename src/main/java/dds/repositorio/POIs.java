package dds.repositorio;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.EntityManager;

import dds.softpoi.POI;
import modelo.Comuna;
import modelo.Poi;

public class POIs extends Repositorio{
	POIs(EntityManager em){
		super(em);		
	}
	
	public void persistir(POI unPoi){
		em.getTransaction().begin();
		em.persist(unPoi);
		em.getTransaction().commit();
	}
	public POI buscarPOIPorId(int id){
		return em.find(POI.class, id);
	}
	
	public List<POI> buscarPOIPorNombre(String nombre){
		List<POI> pois = null;
		pois = em.createNamedQuery("buscarPOIPorNombre").setParameter("pnombre", "%" + nombre + "%").getResultList();
		return pois;
	}
	
	public void actualizarPOI(POI unPoi){
		
		POI poiAActualizar = em.find(POI.class, unPoi.getIdpoi());
		em.getTransaction().begin();
		try {
		Field[] fields = poiAActualizar.getClass().getDeclaredFields();
			for(Field f : fields){
				f.setAccessible(true);
				f.get(unPoi);
				f.set(poiAActualizar, f.get(unPoi));
			}
			Field[] fields2 = POI.class.getDeclaredFields();
			for(Field f : fields2){
				f.setAccessible(true);
				f.get(unPoi);
				f.set(poiAActualizar, f.get(unPoi));
			}
		 	em.getTransaction().commit();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	

}
