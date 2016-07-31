package json.centro;

import java.util.ArrayList;

public class ServicioCentro {

	private String servicio;
	private ArrayList<Integer> horarios = new ArrayList<>();
	
	public ServicioCentro(String servicio, ArrayList<Integer> horarios) {
		//super();
		this.servicio = servicio;
		this.horarios = horarios;
	}
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	public String getServicio() {
		return servicio;
	}
	
	public ArrayList<Integer> getHorarios() {
		return horarios;
	}
	
}