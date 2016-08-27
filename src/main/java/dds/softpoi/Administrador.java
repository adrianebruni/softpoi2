package dds.softpoi;


public class Administrador extends Usuario{

	private String pass;
	private Servidor serv;
	
// ***************************************************************************
// Setters
// ***************************************************************************
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public void setServidor (Servidor elServidor) {
		this.serv = elServidor;
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
	
// ***************************************************************************
// Metodos
// ***************************************************************************
						
	public void cargarPOI(POI unPOI) {
		serv.cargarPOI(unPOI);
	}
	
	public boolean modificarPOI(POI unPOI) throws IllegalArgumentException, IllegalAccessException{ 
	    serv.modificarPOI(unPOI);
		return true;
	}
	
	public boolean eliminarPOI(POI unPOI){
		serv.eliminarPOI(unPOI);
		return true;
	}
	
	public boolean obtenerEstadisticas(){
	// Pendiente: Hay que ver la logica
		return true;
	}
	
	public boolean loguin(){
		serv.loguin(serv.getcolAdmins(), this);
		return true;
	}	
	
	
}
