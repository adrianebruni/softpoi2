package dds.softpoi;

import java.util.ArrayList;

public class Estadistica {
	private ArrayList<Consulta> colConsultas = new ArrayList<Consulta>();
	
	public void agregarConsulta(String termusuario, String strdeconsulta, float tiempoderespuesta, ArrayList<POI> colResultado){	
		this.colConsultas.add(new Consulta(termusuario, strdeconsulta, tiempoderespuesta, colResultado.size()));
	}
	
}
