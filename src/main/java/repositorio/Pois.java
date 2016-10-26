package repositorio;

import java.util.List;
import javax.persistence.EntityManager;

import modelo.Poi;


public class Pois extends Repositorio {
	Pois(EntityManager em) {
		super(em);
	}

	public Poi buscarPorId(Long id) {
		return em.find(Poi.class, id);
	}

	public List<Poi> buscarPoiPorNombre(String nombre) {
		List<Poi> pois = null;
		pois = em.createNamedQuery("buscarPoiPorNombre").setParameter("pnombre", "%" + nombre + "%").getResultList();
		return pois;
	}
}