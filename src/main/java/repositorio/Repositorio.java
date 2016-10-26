package repositorio;

import javax.persistence.EntityManager;

public class Repositorio {
	private Pois pois;
	private Comunas comunas;
	protected EntityManager em;

	public Repositorio(EntityManager em) {
		System.out.println("Repositorio");
		this.em = em;
	}

	public Pois pois() {
		System.out.println("pois");
		if (pois == null) {
			pois = new Pois(em);
		}
		return pois;
	}

	public Comunas comunas() {
		System.out.println("comunas");
		if (comunas == null) {
			comunas = new Comunas(em);
		}
		return comunas;
	}

	public void cerrar() {
		System.out.println("cerrar");
		em.close();
	}
}