package dds.softpoi;

import static org.junit.Assert.*;
import org.junit.Test;


public class TestComercio {

	@Test
	public void testPOIParadaColectivo() {

		System.out.println("Iniciando testPOIComercio");
		System.out.println("-------------------------");
				
		// *********************************************************************************************
		// Prueba de clase Comercio 
		// *********************************************************************************************
		Rubro restaurante = new Rubro("Restaurante", 15);
		RangoHorario rango1 = new RangoHorario("00:00:00", "20:01:08");
		Disponibilidad unDia = new Disponibilidad();
		unDia.setDia("LUNES");
		unDia.agregarRangoHorario(rango1);
		Servicio unServicio = new Servicio();
		unServicio.setServicio("apertura de caja de ahorro");
		unServicio.agregarDisponibilidad(unDia);

		
		
		Comercio comercio1 = new Comercio("Mc Donalls", -34.5836168,-58.4060661, restaurante);
		Comercio comercio2 = new Comercio("Caballito", -34.5836212,-58.4058086, restaurante);
		Comercio comercio3 = new Comercio("   ", -34.5836212,-58.4058086, restaurante);
		
		comercio1.setServicios(unServicio);

		System.out.println("comercio1.esValido()" + comercio1.esValido());
		System.out.println("comercio2.esValido()" + comercio2.esValido());
		System.out.println("comercio3.esValido()" + comercio3.esValido());
		System.out.println(comercio1.estaDisponible("", FechayHora.fechaHoy(), FechayHora.horaHoy()) );
		
		assertEquals("comercio1.esValido(): ", true, comercio1.esValido());
		assertEquals("comercio2.esValido(): ", true, comercio2.esValido());
		assertEquals("comercio3.esValido(): ", false, comercio3.esValido());
		
		System.out.println("Test Finalizado !");
	}

}
