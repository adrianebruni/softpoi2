package dds.softpoi;

import java.util.ArrayList;
import java.util.Date;
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
	
	public boolean verficaExistenciaPermiso(String tipoClase){
		boolean encontrado = false;
		
		if(permisosActuales == null){
	    	System.out.println("Terminal no cuenta con ningun permiso\n\n");
	    	
	    }else{
	    	for(PermisosTerminal unPermiso: permisosActuales) {	
		    	if(tipoClase.equals(unPermiso.tipoPermiso())){
		    		encontrado=true;
		    	}
	    	}
	    }
		return encontrado;
	}
	
	
	public boolean estaCercaMio(POI unPoi){
	
		if(verficaExistenciaPermiso("PermisoCercania")){
			return unPoi.estaCercaDe(this);	
		}else{
			System.out.println("No cuenta con permiso para consultar Cercania");
			return false;
		}
	}
	
	
	public boolean estaDisponible(POI unPOI,String unServicio, Date unDia, String unaHora){
	
		if(verficaExistenciaPermiso("PermisoDisponibilidad")){
			return unPOI.estaDisponible(unServicio,unDia,unaHora);	
		}else{
			System.out.println("No cuenta con permiso para consultar Disponibilidad");
			return false;
		}
	}
	
	public ArrayList<POI> buscaPOI(String cadenadebusqueda){
		
		if(verficaExistenciaPermiso("PermisoBusqueda")){
			return this.buscaPOI (cadenadebusqueda);	
		}else{
			System.out.println("No cuenta con permiso para realizar Busquedas");
			return null;
		}
	}
	
	public void imprimirPermisos(){
		if(this.getPermisosActuales().isEmpty()){
	    	System.out.println("Terminal Sin Permisos\n");
	    }else{
	    	for(PermisosTerminal unPermiso: this.getPermisosActuales()) {	
				System.out.println(unPermiso.tipoPermiso());
			}
	    }
	}
	
	public void imprimirBusqueda(ArrayList<POI> listaPois){
		if(listaPois.isEmpty()){
	    	System.out.println("Sin resultados");
	    }else{
	    	for(POI unPOI: listaPois) {	
				System.out.println(unPOI.getNombre());
			}
	    }
	}
	
}