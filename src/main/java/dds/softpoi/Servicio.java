package dds.softpoi;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Servicio {
	private int idservicio;
	private String servicio;
	private ArrayList<Disponibilidad> disponibilidad = new ArrayList<Disponibilidad>();
	
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	
	public void setDisponibilidades(ArrayList<Disponibilidad> disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	
	public void setDisponibilidad(Disponibilidad objDisponibilidad){
		this.disponibilidad.add(objDisponibilidad);
	}
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	public String getServicio() {
		return servicio;
	}
	
	public ArrayList<Disponibilidad> getDisponibilidades() {
		return disponibilidad;
	}
	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************

	
	// Esta disponible el servicio si: atiende el dia "unDia" y en el horario "horaActual"
	public boolean estaDisponible(Date unDia, String horaActual){

		boolean existe = false;
		
		for(Disponibilidad unaDisponibilidad : disponibilidad)
		{
	    	if (unaDisponibilidad.estaDisponible(unDia, horaActual) ){
	    		existe = true;
	    		break;	
	    	}
		}
		return existe;
	}
	
	public String getCadenaDisponibilidad(){
		String cadenaDisponibilidad="";
		for(Disponibilidad unaDisponibilidad : disponibilidad)
		{				
			if(cadenaDisponibilidad.equals("")){
				cadenaDisponibilidad = this.getServicio() + "-> " + unaDisponibilidad.getCadenaDiasHorarios();
		    }
			else{
				cadenaDisponibilidad = cadenaDisponibilidad + "; " + unaDisponibilidad.getCadenaDiasHorarios();
			}
		}
		return cadenaDisponibilidad;
	}
	
}
