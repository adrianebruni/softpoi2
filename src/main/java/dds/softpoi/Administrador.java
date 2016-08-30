package dds.softpoi;


public class Administrador extends Usuario{

	private String pass;
	private Servidor serv;
	private String token;
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public void setServidor (Servidor elServidor) {
		this.serv = elServidor;
	}
	
	public void setToken (String unToken) {
		this.token = unToken;
	}
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public String getPass() {
		return pass;
	}
	
	public Servidor getServidor() {
		return serv;
	}
	
	public String getToken() {
		return token;
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
