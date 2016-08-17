package dds.softpoi;

import java.util.ArrayList;

public class Estadistica {
	public ArrayList<Consulta> colConsultas = new ArrayList<Consulta>();
	
	public void agregoConsulta(String termusuario, String strdeconsulta, float tiempoderespuesta, ArrayList<POI> colResultado){
		int cantderesultados = 0;
		for(POI unpoi : colResultado){
			cantderesultados++;
		}
		this.colConsultas.add(new Consulta(termusuario, strdeconsulta, tiempoderespuesta, cantderesultados));
	}
	
}
