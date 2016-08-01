package dds.softpoi;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Disponibilidad {

	private String dia; // "DOMINGO", "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO"
	private ArrayList<RangoHorario> horarios = new ArrayList<RangoHorario>();
	
	private String[] dias = {"DOMINGO", "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO"};
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	public void setDia(String unDia) {
		this.dia = unDia;
	}
	
	public void setDia(int unDia) {
		if (unDia > 0 && unDia < 6){
			this.dia = dias[unDia];
		}
	}
	
	public void setHorarios(ArrayList<RangoHorario> horarios) {
		this.horarios = horarios;
	}

	public void setRangoHorario(RangoHorario objRangoHorario){
		this.horarios.add(objRangoHorario);
	}

	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	public String getDia() {
		return dia;
	}

	public ArrayList<RangoHorario> getHorarios() {
		return horarios;
	}
	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************
	
	private String diaDeLaSemana(Date UnDia){
		int numeroDia = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(UnDia);
		numeroDia = cal.get(Calendar.DAY_OF_WEEK);
		return dias[numeroDia - 1];
	}
	
	public boolean estaDisponible(Date unDia, String horaActual){
		boolean existe = false;
		String diaSemana = diaDeLaSemana(unDia);
		
		if(this.dia.equals(diaSemana)){		
			for(RangoHorario unRangoHorario : horarios)
			{				
				if(unRangoHorario.estaDisponible(horaActual)){
			    	existe = true;
			    	break;
			    }
			}
		}
		return existe;
	}
	
}
