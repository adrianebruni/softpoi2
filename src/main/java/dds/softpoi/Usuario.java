package dds.softpoi;

public abstract class Usuario {
	
	String nombre;
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String unNombre){
		nombre = unNombre;
	}
}
