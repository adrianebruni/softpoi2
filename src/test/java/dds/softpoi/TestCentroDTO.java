package dds.softpoi;

import json.centro.*;
import static org.junit.Assert.*;
import org.junit.Test;
import com.google.gson.Gson;


public class TestCentroDTO {

	@Test
	public void test() throws Exception {
		
		JsonCentro jsonCentro = new JsonCentro();
		
		//final String json = "{\"id\":46,\"nombre\":\"Miguel\",\"empresa\":\"Autentia\"}";
		String json = jsonCentro.readUrl("http://trimatek.org/Consultas/centro?comuna=1");		
		System.out.println(json);

		final Gson gson = new Gson();
		final CentroDTO unCentro = gson.fromJson(json, CentroDTO.class);
		
	    System.out.println(unCentro.getDirector());
	    assertEquals(1, unCentro.getComuna());
	}


	
	
	
	
}