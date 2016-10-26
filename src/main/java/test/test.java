package test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.Comuna;
import modelo.Georef;
import modelo.Poi;
import repositorio.Repositorio;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String PERSISTENCE_UNIT_NAME = "DDS";
		EntityManagerFactory emFactory;
		Repositorio repositorio;
		
		//emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		repositorio = new Repositorio(emFactory.createEntityManager());
		
		
		Comuna c = new Comuna();
		c.setNombre("12");
		Poi p = new Poi();
		p.setNombre("Almacén Don Manolo");
		p.setComuna(c);
		Georef g = new Georef();
		g.setLatitud(222.111F);
		g.setLongitud(-444.111F);
		g.setPoi(p);
		p.setGeoref(g);
		c.addPoi(p);
		repositorio.comunas().persistir(c);
		
		System.out.println("buscarComunaPorId");
		Comuna comuna = repositorio.comunas().buscarPorId(1L);
		System.out.println("Comuna encontrada por ID: " + comuna.getNombre());
		
	}

}