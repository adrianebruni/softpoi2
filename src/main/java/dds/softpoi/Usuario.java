package dds.softpoi;

public class Usuario {
	String nombreUsuario;
	String clave;
	String token;

	public String getNombreUsuario(){
		return this.nombreUsuario;
	}
	
	public void setNombreUsuario(String unNombre){
		nombreUsuario = unNombre;
	}
}
