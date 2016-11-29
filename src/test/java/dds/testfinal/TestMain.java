package dds.testfinal;

import dds.softpoi.Administrador;
import dds.softpoi.Servidor;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Arranco el test");
		Servidor server = AltasIniciales.generarServidor();
		
		/* Cargo inicialmente el servidor con POIs */
		//RepoPOI colPoisPrueba = new RepoPOI();
		//server.cargarPOIs(colPoisPrueba.Dame_Bolsa_POI());
		
		Administrador unAdministrador = (Administrador) server.getColAdmins().get(0);
		
		System.out.println("Cant pois bco : " + server.getColPOIs().size());
		
		System.out.println("Cant pois bco : " + unAdministrador.buscaPOI("Banco").size());
		
		
		System.out.println("Termino el test");
	}

}
