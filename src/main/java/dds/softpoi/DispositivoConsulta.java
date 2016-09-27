package dds.softpoi;

import java.util.HashSet;
import java.util.Set;

public class DispositivoConsulta extends Usuario{

	private double longitud;
	private double latitud;
	private String zona;
	private Set<PermisosTerminal> permisosPrevios = new HashSet<PermisosTerminal>();
	private Set<PermisosTerminal> permisosActuales = new HashSet<PermisosTerminal>();

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

	public void setPermisosPrevios(Set<PermisosTerminal> permisos){
		this.permisosPrevios= permisos;
	}
	
	public void setPermisosActuales(Set<PermisosTerminal> permisos){
		this.permisosActuales= permisos;
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
	
	public Set<PermisosTerminal> getPermisosPrevios(){
		return permisosPrevios;
	}
	
	public Set<PermisosTerminal> getPermisosActuales(){
		return permisosActuales;
	}

	
	// ***************************************************************************
	// Methods
	// ***************************************************************************
	
	public boolean estaCercaMio(POI unPoi){
		return unPoi.estaCercaDe(this);
	}
	
}