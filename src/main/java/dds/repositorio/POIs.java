package dds.repositorio;

import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.EntityManager;
import dds.softpoi.POI;

public class POIs extends Repositorio{
	POIs(EntityManager em){
		super(em);		
	}
	
	public void persistir(POI unPoi){
		em.getTransaction().begin();
		em.persist(unPoi);
		//System.out.println("ID:"+ unPoi.getIdpoi());
		em.getTransaction().commit();
	}
	
	public POI buscarPOIPorId(int id){
		POI unPOI = null;
		
		try {
			//System.out.println("ENTRO AL TRY");
			unPOI = em.find(POI.class, id);
		} catch (Exception e) {
			System.out.println("FALLO: busquedarPOIPorID - clase POIs.java");
		}
		//System.out.println("VALOR UN POI: " + unPOI);
		return unPOI;
	}
	
	public List<POI> buscarPOIPorNombre(String nombre){
		List<POI> lstPois = null;
		lstPois = em.createNamedQuery("buscarPOIPorNombre").setParameter("pnombre", "%" + nombre + "%").getResultList();
		return lstPois;
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
	 
	public Boolean eliminarPOI(POI unPoi){

		em.getTransaction().begin();
		
		try {
			em.remove(unPoi);
			em.getTransaction().commit();
			System.out.println("POI eliminado");
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("POI NO eliminado");
			return false;
		}
		
	}

}
