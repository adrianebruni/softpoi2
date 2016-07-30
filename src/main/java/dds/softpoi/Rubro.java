package dds.softpoi;

public class Rubro {
	
	private String nombre;
	private double radioCercania;
	
	// ***************************************************************************
	// Constructor
	// ***************************************************************************
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
