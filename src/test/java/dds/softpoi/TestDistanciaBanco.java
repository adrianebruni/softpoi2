package dds.softpoi;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestDistanciaBanco {

	@Test
	
	public void methDistanciaBanco() {
	
		System.out.println("Iniciando testDistancia Dispositivo - POI tipo Banco");
		
		//creo DispositivoConsulta
		DispositivoConsulta dispositivo4 = new DispositivoConsulta("disp Flores2", 0.350219708, 0.715584992, "Flores Norte");
		System.out.println("dispositivo: " + dispositivo4.getNombre() + ", latitud: " + dispositivo4.getLatitud() + ", longitud: " + dispositivo4.getLongitud());
		
		//creo un POI Banco de prueba
		Banco bancoNacion1 = new Banco("Banco Nacion microcentro",0.350219708,0.715484992);
		System.out.println(bancoNacion1.tipoPOI() + ": " + bancoNacion1.getNombre() + ", latitud: " + bancoNacion1.getLatitud() + ", longitud: " + bancoNacion1.getLongitud());		
		
		assertEquals("Verificamos que el Banco este cerca del dispositivo", true, dispositivo4.estaCercaMio(bancoNacion1));
		
		
	}
}
