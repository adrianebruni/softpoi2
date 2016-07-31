package json.centro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonCentro {

	public String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}	
	
	
	public void dameAlgo(String urlString) throws Exception {
		
		String json = this.readUrl(urlString);
		Gson gson = new Gson();
		CentroDTO[] gsonCentro = gson.fromJson(json, CentroDTO[].class);	
		JsonArray arrayCentro = (JsonArray) new JsonParser().parse(json);
	
		//JsonElement comuna = ((JsonObject)arrayCentro.get(0)).get("comuna");
		
	}
	
	
	
	
	
	
	
	
	
}
