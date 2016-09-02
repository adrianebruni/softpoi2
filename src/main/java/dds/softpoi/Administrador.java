package dds.softpoi;

public class Administrador extends Usuario{

	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************
						
	public void cargarPOI(POI unPOI) {
		// Falta validar con clase seguridad
		System.out.println("Falta validar con clase seguridad");
		super.getServidor().cargarPOI(unPOI);
	}
	
	public void modificarPOI(POI unPOI) throws IllegalArgumentException, IllegalAccessException{ 
	    serv.modificarPOI(unPOI);
		
	}
	
	public void eliminarPOI(POI unPOI){
		serv.eliminarPOI(unPOI);
		
	}	
	
}
