package dds.softpoi;

public abstract class Usuario {
	
	protected String nombre;
	protected String email;
	protected String clave;
	protected String token;
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getClave() {
		return this.clave;
	}
	
	public String getToken() {
		return this.token;
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

	public void setPass(String unaClave) {
		clave = unaClave;
	}
	
	public void setToken (String unToken) {
		token = unToken;
	}
	
}
