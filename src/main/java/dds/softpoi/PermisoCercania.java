package dds.softpoi;

import javax.persistence.Entity;

@Entity
public class PermisoCercania extends PermisosTerminal{


	private static PermisoCercania permisoCercaniaSingleton= new PermisoCercania();
	
	// ***************************************************************************
	// Constructor
	// ***************************************************************************
	
	public PermisoCercania(){}
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public static PermisoCercania getpermisoCercaniaSingleton() {
		return permisoCercaniaSingleton;
	}	

	
}
