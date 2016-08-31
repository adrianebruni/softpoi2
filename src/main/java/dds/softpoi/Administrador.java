package dds.softpoi;

public class Administrador extends Usuario{

	private Servidor serv;
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	public void setServidor (Servidor elServidor) {
		this.serv = elServidor;
	}
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
		
	public Servidor getServidor() {
		return serv;
	}
	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************
						
	public void cargarPOI(POI unPOI) {
		serv.cargarPOI(unPOI);
	}
	
	
	
	
	
	// Todo lo que esta hardcodeado como return true, hay que cambiarlo
	// indicando si el administrador esta logeado (utilizando el token)
	
	public void modificarPOI(POI unPOI) throws IllegalArgumentException, IllegalAccessException{ 
	    serv.modificarPOI(unPOI);
		
	}
	
	public void eliminarPOI(POI unPOI){
		serv.eliminarPOI(unPOI);
		
	}
	
	public void obtenerEstadisticas(){
	// Pendiente: Hay que ver la logica
		
	}
	
	
}
