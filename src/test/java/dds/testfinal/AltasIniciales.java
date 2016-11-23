package dds.testfinal;

import dds.softpoi.Administrador;
import dds.softpoi.DispositivoConsulta;
import dds.softpoi.RepoPOI;
import dds.softpoi.Servidor;

public class AltasIniciales {

	public static Servidor generarServidor(){
		Servidor unServidor = new Servidor();
		
		/* Genero el administrador  */
		Administrador unAdministrador = new Administrador();
		unAdministrador.setNombre("Alejandro");
		unAdministrador.setClave("passPrueba");
		unAdministrador.setServidor(unServidor);
		unServidor.addAdmin(unAdministrador);
		
		
		/* Genero una terminal */
		DispositivoConsulta unaTerminal = new DispositivoConsulta();
		unaTerminal.setNombre("Terminal Abasto");
		unaTerminal.setLatitud(15.0);
		unaTerminal.setLongitud(10.0);
		unaTerminal.setServidor(unServidor);
		
		/* Cargo inicialmente el servidor con POIs */
		//RepoPOI colPoisPrueba = new RepoPOI();
		//unServidor.cargarPOIs(colPoisPrueba.Dame_Bolsa_POI());
		
		return unServidor;
	}
	
}