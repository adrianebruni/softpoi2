package dds.softpoi;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rubro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	private String nombre;
	private double radioCercania;
	
	// ***************************************************************************
	// Constructor
	// ***************************************************************************
	public Rubro(){}
	
	public Rubro(String unNombre, double unRadioCercania){
		this.nombre = unNombre;
		this.radioCercania = unRadioCercania;
	}
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************

	public void setRubro(String unNombre){
		this.nombre = unNombre;
	}
		
	public void setRadioCercania(double unRadioCercania){
		this.radioCercania= unRadioCercania;
	}
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public String getRubro(){
		return nombre;
	}
	
	public double getRadioCercania() {
		return radioCercania;
	}

}
