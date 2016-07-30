package dds.softpoi;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestDistanciaParadaCol {

@Test
	
	public void methDistanciaParada() {
		System.out.println("Iniciando testDistancia Dispositivo - POI");
	
		//creo DispositivoConsulta
		DispositivoConsulta dispositivo1 = new DispositivoConsulta("disp Palermo1", 0.873823331, 0.87408513, "Palermo soho");
		System.out.println("dispositivo: " + dispositivo1.getNombre() + ", latitud: " + dispositivo1.getLatitud() + ", longitud: " + dispositivo1.getLongitud());
		
		//creo un POI Parada de Colectivo de prueba
		ParadaColectivo callaoCordoba132 = new ParadaColectivo("132 Callao Cordoba",0.873818483, 0.874080282);
		callaoCordoba132.setEmpresa("Nuevos Rumbos S.A.");
		callaoCordoba132.setNumeroDeLinea(132);
		//System.out.println("tipo de POI:" + callaoCordoba132.tipoPOI());
		System.out.println(callaoCordoba132.tipoPOI() + ": " + callaoCordoba132.getNombre() + ", latitud: " + callaoCordoba132.getLatitud() + ", longitud: " + callaoCordoba132.getLongitud());		

		assertEquals("Verificamos que la parada este cerca del dispositivo", true, dispositivo1.estaCercaMio(callaoCordoba132));
		
	}
	
}
