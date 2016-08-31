package dds.softpoi;

public class Seguridad {
	
	protected Servidor unServidor;
	
	// ***************************************************************************
	// Constructor
	// ***************************************************************************
	
	public Seguridad(Servidor unServidor){
		this.unServidor = unServidor;
	}
	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************	
	
	public Boolean login(Usuario unUsuario) {
		if (unServidor.login(unUsuario) != null){
			return true;
		}else{
			return false;
		}
	}
	
	public Boolean validarUsuario(Usuario unUsuario){
		//Usuario
		return true;
	}
	
}