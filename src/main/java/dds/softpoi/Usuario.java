package dds.softpoi;

public abstract class Usuario {
	
	private String nombre;
	private String email;
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getEmail() {

		return this.email;
	}
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	public void setNombre(String unNombre){
		nombre = unNombre;
	}

	public void setEmail(String unEmail){
		email = unEmail;
	}

}
