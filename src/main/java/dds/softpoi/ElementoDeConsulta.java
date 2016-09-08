package dds.softpoi;

import java.util.Date;
import java.text.SimpleDateFormat;

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
	
	

	@SuppressWarnings("null")
	public String fechaFormateada(String formatoElegido){
		if(formatoElegido != null || formatoElegido.isEmpty()){
		String DATE_FORMAT = formatoElegido;
		SimpleDateFormat fechaConformato = new SimpleDateFormat(DATE_FORMAT);
		return fechaConformato.format(fechaConsulta);
	}
		else{
		String DATE_FORMAT = "yyyy/MM/dd";
		SimpleDateFormat fechaConformato = new SimpleDateFormat(DATE_FORMAT);
		return fechaConformato.format(fechaConsulta);
	}
		
		}//fin metodo fechaFormateada
	
	
}