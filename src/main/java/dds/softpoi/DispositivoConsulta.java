package dds.softpoi;
public class DispositivoConsulta {

	private double longitud;
	private double latitud;
	private String zona;
	private String nombre;

	// ***************************************************************************
	// Constructor
	// ***************************************************************************
	public DispositivoConsulta(String nombre, double latitud, double longitud, String zona) {
		this.nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;
		this.zona = zona;
	}
		
	// ***************************************************************************
	// Setters
	// ***************************************************************************
		
		public void setNombre(String unNombre){
			this.nombre= unNombre;
		}
	
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
		
		public String getNombre(){
			return nombre;
		}
		
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