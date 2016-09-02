package dds.softpoi;
public class DispositivoConsulta extends Usuario{

	private double longitud;
	private double latitud;
	private String zona;
	// El nombre por ejemplo: terminalAbasto

	// ***************************************************************************
	// Constructor
	// ***************************************************************************
	
	public DispositivoConsulta(){}
	
	public DispositivoConsulta(String nombre, double latitud, double longitud, String zona) {
		super.nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;
		this.zona = zona;
	}
		
	// ***************************************************************************
	// Setters
	// ***************************************************************************

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	
	public void setLongitud(Double longitud){
		this.longitud= longitud;
	}
	
	public void setZona(String zona){
		this.zona= zona;
	}

	// ***************************************************************************
	// Getters
	// ***************************************************************************
		
	public double getLatitud(){
		return latitud;
	}
	
	public double getLongitud(){
		return longitud;
	}
	
	public String getZona(){
		return zona;
	}
	
	// ***************************************************************************
	// Methods
	// ***************************************************************************
	
	public boolean estaCercaMio(POI unPoi){
		return unPoi.estaCercaDe(this);
	}
	
}