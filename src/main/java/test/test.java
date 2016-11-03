package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dds.repositorio.Repositorio;
import dds.softpoi.Administrador;
import dds.softpoi.Banco;
import dds.softpoi.POI;
import dds.softpoi.Servidor;
import dds.softpoi.ElementoDeConsulta;




public class test {

	public static void main(String[] args) {
		
		// ENUNCIADO:
		// Realizar una busqueda, persistirla, recuperarla y verificar que corresponda al objeto de esa busqueda
		// e incluya referencias a los POI
		
		// Configuracion de persistencia
		final String PERSISTENCE_UNIT_NAME = "DDS";
		EntityManagerFactory emFactory;
		Repositorio repositorio;
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		repositorio = new Repositorio(emFactory.createEntityManager());
		
		
		//
		
		Servidor servidorPpal = new Servidor();
		
		//crear administrador
		Administrador juanAdmin = new Administrador();
		juanAdmin.setNombre("Juan");
		juanAdmin.setClave("passPrueba");
		juanAdmin.setServidor(servidorPpal);
		juanAdmin.setFlagAuditoriaBusqueda(true);
		servidorPpal.addAdmin(juanAdmin);
		
		servidorPpal.setRepositorio(repositorio);
		
		// 1) Crear un par de POIs y persistirlos.
		Banco unbanco = new Banco();
		unbanco.setLatitud(10);
		unbanco.setLongitud(15);
		unbanco.setNombre("bancofrances");
		unbanco.setGerente("Peralta");
		//repositorio.pois().persistir(unbanco);
		juanAdmin.cargarPOI(unbanco);
		
		Banco otrobanco = new Banco();
		otrobanco.setLatitud(12);
		otrobanco.setLongitud(13);
		otrobanco.setNombre("bancogalicia");
		otrobanco.setGerente("Ale");
		//repositorio.pois().persistir(otrobanco);
		juanAdmin.cargarPOI(otrobanco);
		
		// 2) Crear una terminal de consulta.
		
		juanAdmin.buscaPOI("bancogal");
		
		// 3) Realizar una busqueda desde la terminal de consulta y persistirla
		
		/*
		ElementoDeConsulta unElemento = new ElementoDeConsulta();
		unElemento.setConsultaUsuario("cad de busq");
		unElemento.setFechaConsulta(new Date(116, 10, 05));
		unElemento.setTiempoRespuesta(5F);
		unElemento.setTipoUsuario("Admin");
		unElemento.setTotalResultados(0);
		List<POI> unaColeccionDePois = new ArrayList<POI>();
		unaColeccionDePois.add(unbanco);
		unaColeccionDePois.add(otrobanco);
		unElemento.setColPOIs(unaColeccionDePois);
		*/
		
		//repositorio.elementosDeConsulta().persistir(unElemento);
		// 4) Recuperar la busqueda realizada
		
		
		repositorio.cerrar();
		emFactory.close();
		
	}

}