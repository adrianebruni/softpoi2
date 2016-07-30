package dds.softpoi;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestParadaColectivo {

	@Test
	public void testPOIParadaColectivo() {

		System.out.println("Iniciando testPOIParadaColectivo");
		System.out.println("--------------------------------");
				
		// *********************************************************************************************
		// Prueba de clase ParadaColectivo 
		// *********************************************************************************************
		
		ParadaColectivo parada1 = new ParadaColectivo("Parada 59 Las Heras y Billingurst", -34.5832488, -58.4042595);
		ParadaColectivo parada2 = new ParadaColectivo("Parada 10 Las Heras y Ocampo", -34.5831289, -58.40384);
		ParadaColectivo parada3 = new ParadaColectivo("Parada 118 Las Heras y Bustamante", -34.5836402, -58.4033537);
		ParadaColectivo parada4 = new ParadaColectivo("    ", -34.5836402, -58.4033537);
		
		System.out.println("parada1.esValido()" + parada1.esValido());
		System.out.println("parada2.esValido()" + parada2.esValido());
		System.out.println("parada3.esValido()" + parada3.esValido());
		System.out.println("parada4.esValido()" + parada4.esValido());
		
		
		System.out.println("parada1.estaDisponible(...)" + parada1.estaDisponible("", FechayHora.fechaHoy() , FechayHora.horaHoy()));
		System.out.println("parada2.estaDisponible(...)" + parada2.estaDisponible("", FechayHora.fechaHoy() , FechayHora.horaHoy()));
		System.out.println("parada3.estaDisponible(...)" + parada3.estaDisponible("", FechayHora.fechaHoy() , FechayHora.horaHoy()));
		System.out.println("parada4.estaDisponible(...)" + parada4.estaDisponible("", FechayHora.fechaHoy() , FechayHora.horaHoy()));
	
		assertEquals("parada1.esValido(): ", true, parada1.esValido());
		assertEquals("parada2.esValido(): ", true, parada2.esValido());
		assertEquals("parada3.esValido(): ", true, parada3.esValido());
		assertEquals("parada4.esValido(): ", false,parada4.esValido());
		
		assertEquals("parada1.estaDisponible(...): ", true, parada1.estaDisponible("", FechayHora.fechaHoy() , FechayHora.horaHoy()));
		assertEquals("parada2.estaDisponible(...): ", true, parada2.estaDisponible("", FechayHora.fechaHoy() , FechayHora.horaHoy()));
		assertEquals("parada3.estaDisponible(...): ", true, parada3.estaDisponible("", FechayHora.fechaHoy() , FechayHora.horaHoy()));
		assertEquals("parada4.estaDisponible(...): ", true, parada4.estaDisponible("", FechayHora.fechaHoy() , FechayHora.horaHoy()));
		
		System.out.println("Test Finalizado !");
	}

}
