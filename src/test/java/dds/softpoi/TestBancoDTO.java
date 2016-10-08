package dds.softpoi;

import java.util.ArrayList;
import org.junit.Test;
import dds.json.BancoDTO;

public class TestBancoDTO {

	@Test
	public void test() throws Exception {
		
		BancoDTO jsonCentro = new BancoDTO();
		
		ArrayList<POI> POIs = jsonCentro.dameDatosExternos("http://trimatek.org/Consultas/banco");
		//ArrayList<POI> POIs = jsonCentro.dameDatosExternos("http://localhost:8080/Consultas/banco");	
		
		System.out.println("CANT. POIs: " + POIs.size());
		System.out.println("************************************************************************************************");

		for (int i = 0; i <= POIs.size() - 1; i++){
			
			System.out.println("NOMBRE BCO ITEM " + i + ": " + ((Banco) POIs.get(i)).getNombre());
			System.out.println("LONGITUD ITEM " + i + ": " + ((Banco) POIs.get(i)).getLongitud());
			System.out.println("LATITUD ITEM " + i + ": " + ((Banco) POIs.get(i)).getLatitud());
			System.out.println("SUCURSAL ITEM " + i + ": " + ((Banco) POIs.get(i)).getSucursal());
			
			for (int j = 0; j <= ((Banco) POIs.get(i)).getServicios().size() - 1; j++){
				System.out.println("SERVICIOS ITEM " + i + ": " + ((Banco) POIs.get(i)).getServicios().get(j).getServicio());
			}
			
			System.out.println("------------------------------------------------------------------------------------------------");
			
		} // FIN FOR i
		
	}
}
