package repositorio;

import javax.persistence.EntityManager;

import modelo.Comuna;


public class Comunas extends Repositorio {
	Comunas(EntityManager em) {
		super(em);
	}

	public Comuna buscarPorId(Long id) {
		return em.find(Comuna.class, id);
	}

	public void persistir(Comuna comuna) {
		em.getTransaction().begin();
		em.persist(comuna);
		em.getTransaction().commit();
	}
}