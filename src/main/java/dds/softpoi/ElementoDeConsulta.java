package dds.softpoi;

import java.util.Date;
import java.util.Calendar;
import java.util.Comparator;

public class ElementoDeConsulta {

	private Date fechaConsulta;
	private String consultaUsuario;
	private double tiempoRespuesta;
	private String tipoUsuario;
	private int totalResultados;
    
    // ***************************************************************************
 	// Constructor
 	// ***************************************************************************
    
    public ElementoDeConsulta(Date fechaConsulta,String consultaUsuario,double tiempoRespuesta,String tipoUsuario,int totalResultados) {
    	this.fechaConsulta = fechaConsulta;
    	this.consultaUsuario = consultaUsuario;
    	this.tiempoRespuesta = tiempoRespuesta;
    	this.tipoUsuario = tipoUsuario;
    	this.totalResultados = totalResultados;
    }
    
    // ***************************************************************************
 	// Setters
 	// ***************************************************************************
  
    public void setFechaConsulta(Date fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}
    public void setConsultaUsuario(String consultaUsuario) {
		this.consultaUsuario = consultaUsuario;
	}
    public void setTiempoRespuesta(float tiempoRespuesta) {
		this.tiempoRespuesta = tiempoRespuesta;
    }    
        
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	public void setTotalResultados(int totalResultados) {
		this.totalResultados = totalResultados;
	}


	// ***************************************************************************
	// Getters
	// ***************************************************************************

	public Date getFechaConsulta() {
		return fechaConsulta;
	}
	public String getConsultaUsuario() {
		return consultaUsuario;
	}
	public Double getTiempoRespuesta() {
		return tiempoRespuesta;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public int getTotalResultados() {
		return totalResultados;
	}
	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************
	
	public static Comparator <ElementoDeConsulta> Comparar_Por_Fecha = new Comparator <ElementoDeConsulta> () {
		public int compare(ElementoDeConsulta fecha1, ElementoDeConsulta fecha2) {
			return fecha1.getFechaConsulta().compareTo(fecha2.getFechaConsulta());
		}
	};
	
	public static Comparator <ElementoDeConsulta> Comparar_Por_Usuario = new Comparator <ElementoDeConsulta> () {
		public int compare(ElementoDeConsulta usuario1, ElementoDeConsulta usuario2) {
			return usuario1.getTipoUsuario().compareTo(usuario2.getTipoUsuario());
		}
	};
	
	public String fechaFormateada(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.fechaConsulta);
		return cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR) ;
	}
	
}