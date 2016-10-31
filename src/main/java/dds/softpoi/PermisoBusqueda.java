package dds.softpoi;

import javax.persistence.Entity;

@Entity
public class PermisoBusqueda extends PermisosTerminal{

	private static PermisoBusqueda permisoBusquedaSingleton = new PermisoBusqueda();
	
	// ***************************************************************************
	// Constructor
	// ***************************************************************************
	
	public PermisoBusqueda(){}
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************

	public static PermisoBusqueda getpermisoBusquedaSingleton() {
		return permisoBusquedaSingleton;
	}
	
}
