package dds.softpoi;

import java.util.ArrayList;

//import static org.junit.Assert.*;
import org.junit.Test;
import dds.json.CentroDTO;

public class TestCentroDTO {

	@Test
	public void test() throws Exception {
		

		CentroDTO jsonCentro = new CentroDTO();
		
		ArrayList<POI> POIs = jsonCentro.dameDatosExternos("http://trimatek.org/Consultas/centro");
		
		System.out.println("CANT. POIs: " + POIs.size());
		System.out.println("************************************************************************************************");
	
		for (int i = 0; i <= POIs.size() - 1; i++){
			System.out.println("COMUNA ID ITEM " + i + ": " + ((CGP) POIs.get(i)).getComuna().getID());
			System.out.println("ZONAS ITEM " + i + ": " + ((CGP) POIs.get(i)).getComuna().getZonas());
			System.out.println("DIRECTOR ITEM " + i + ": " + ((CGP) POIs.get(i)).getDirector());
			System.out.println("CALLE ITEM " + i + ": " + ((CGP) POIs.get(i)).getCalle());
			System.out.println("TELEFONO ITEM " + i + ": " + ((CGP) POIs.get(i)).getTelefono());
			
			for (int j = 0; j <= ((CGP) POIs.get(i)).getServicios().size() - 1; j++){
				System.out.println("|---> SERVICIO ITEM " + i + ": " + ((CGP) POIs.get(i)).getServicios().get(j).getServicio());
			}
			
		System.out.println("------------------------------------------------------------------------------------------------");
		
		}
				
	}

}