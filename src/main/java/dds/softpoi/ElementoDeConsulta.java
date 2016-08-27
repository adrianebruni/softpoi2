package dds.softpoi;

import java.util.Date;

public class ElementoDeConsulta {

	Date fechaConsulta;
    String consultaUsuario;
    float tiempoRespuesta;
    String tipoUsuario;
    int totalResultados;
    
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


	//***************************************************************************
	// Getters
	// ***************************************************************************

	public Date getFechaConsulta() {
		return fechaConsulta;
	}
	public String getConsultaUsuario() {
		return consultaUsuario;
	}
	public float getTiempoRespuesta() {
		return tiempoRespuesta;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public int getTotalResultados() {
		return totalResultados;
	}
	
	}