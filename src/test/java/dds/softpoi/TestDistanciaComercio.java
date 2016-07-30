package dds.softpoi;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestDistanciaComercio {

@Test
	
	public void methDistanciaComercio() {
	
		System.out.println("Iniciando testDistancia Dispositivo - POI tipo Comercio");
	
		//creo DispositivoConsulta
		DispositivoConsulta dispositivo3 = new DispositivoConsulta("disp Caballito1", 0.350224555, 0.699552205, "Caballito Norte");
		System.out.println("dispositivo: " + dispositivo3.getNombre() + ", latitud: " + dispositivo3.getLatitud() + ", longitud: " + dispositivo3.getLongitud());
			
		//creo un Rubro para el Comercio
		Rubro rubroMerceria = new Rubro ("Merceria", 500.0);
		System.out.println("Rubro: " + rubroMerceria.getRubro() + ", radio de cercania: " + rubroMerceria.getRadioCercania());		
		
		//creo un POI Comercio de prueba
		Comercio merceriaJose = new Comercio("Merceria Jose",0.350219707,0.699629775,rubroMerceria);
		System.out.println(merceriaJose.tipoPOI() + ": " + merceriaJose.getNombre() + ", latitud: " + merceriaJose.getLatitud() + ", longitud: " + merceriaJose.getLongitud());		
			
	
		assertEquals("Verificamos que el Comercio este cerca del dispositivo", true, dispositivo3.estaCercaMio(merceriaJose));
		//System.out.println(super.rubroMerceria.distancia(dispositivo3.getLatitud(),dispositivo3.getLongitud(),rubroMerceria.latitud,rubroMerceria.longitud));
}
	
}
