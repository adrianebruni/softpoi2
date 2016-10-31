package dds.softpoi;

import javax.persistence.Entity;

@Entity
public class PermisoDisponibilidad extends PermisosTerminal{

	private static PermisoDisponibilidad permisoDisponibilidadSingleton= new PermisoDisponibilidad();
	
	// ***************************************************************************
	// Constructor
	// ***************************************************************************
	
	public PermisoDisponibilidad(){}
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public static PermisoDisponibilidad getpermisoDisponibilidadSingleton() {
		return permisoDisponibilidadSingleton;
	}
	
	
}
