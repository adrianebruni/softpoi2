package dds.softpoi;

import java.util.Date;

public class Consulta {
	Date fechadeconsulta;
	String termusuario;
	String strdeconsulta;
	float tiempoderespuesta;
	int cantderesultados;
	
	public Consulta(String termusuario, String strdeconsulta, float tiempoderespuesta, int cantderesultados){
		setFechadeconsulta(fechadeconsulta);
		setTermusuario(termusuario);
		setStrdeconsulta(strdeconsulta);
		setTiempoderespuesta(tiempoderespuesta);
		setCantderesultados(cantderesultados);		
	}
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	public void setFechadeconsulta(Date fechadeconsulta) {
		this.fechadeconsulta = fechadeconsulta;
	}
	public void setTermusuario(String termusuario) {
		this.termusuario = termusuario;
	}
	public void setStrdeconsulta(String strdeconsulta) {
		this.strdeconsulta = strdeconsulta;
	}
	public void setTiempoderespuesta(float tiempoderespuesta) {
		this.tiempoderespuesta = tiempoderespuesta;
	}
	public void setCantderesultados(int cantderesultados) {
		this.cantderesultados = cantderesultados;
	}
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	public Date getFechadeconsulta() {
		return fechadeconsulta;
	}
	public String getTermusuario() {
		return termusuario;
	}
	public String getStrdeconsulta() {
		return strdeconsulta;
	}
	public float getTiempoderespuesta() {
		return tiempoderespuesta;
	}
	public int getCantderesultados() {
		return cantderesultados;
	}
	
}
