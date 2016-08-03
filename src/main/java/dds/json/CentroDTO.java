package dds.json;

import java.util.ArrayList;

public class CentroDTO extends OrigenJSON {
	
	private int comuna;
	private String zonas;
	private String director;
	private String domicilio;
	private String telefono;		
	private ArrayList<ServicioCentro> servicioDTO = new ArrayList<ServicioCentro>();
		
	// ***************************************************************************
	// Constructor
	// ***************************************************************************
	
	public CentroDTO(){}
	
	public CentroDTO(int comuna, String zonas, String director, String domicilio, String telefono,
			ArrayList<ServicioCentro> servicioDTO) {
		super();
		this.comuna = comuna;
		this.zonas = zonas;
		this.director = director;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.servicioDTO = servicioDTO;
	}
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public int getComuna() {
		return comuna;
	}

	public String getZonas() {
		return zonas;
	}

	public String getDirector() {
		return director;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public ArrayList<ServicioCentro> getServicioDTO() {
		return servicioDTO;
	}
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	public void setComuna(int comuna) {
		this.comuna = comuna;
	}

	public void setZonas(String zonas) {
		this.zonas = zonas;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setServicioDTO(ArrayList<ServicioCentro> servicioDTO) {
		this.servicioDTO = servicioDTO;
	}
	
}
