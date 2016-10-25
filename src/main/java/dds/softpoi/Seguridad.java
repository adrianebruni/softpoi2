package dds.softpoi;

public class Seguridad {
	
	protected Servidor unServidor;
	
	// ***************************************************************************
	// Constructor
	// ***************************************************************************
	
	public Seguridad(){};
	
	public Seguridad(Servidor unServidor){
		this.unServidor = unServidor;
	}
	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************	
	
	public Usuario login(String nombreUsuario, String claveUsuario) {
		return unServidor.login(nombreUsuario, claveUsuario);
	}
	
	public Boolean validarUsuarioAdmin(Usuario unUsuario){
		
		// FALTA DIFERENCIAR SI ES "ADMINISTRADOR" o "TERMINAL"
		/*
		for(Usuario unTipoDeUsuario : unServidor.colAdmins){
			unTipoDeUsuario.getClass().getName().substring(4);
			
			
		}
		*/
		
		return this.unServidor.colAdmins.contains(unUsuario);
	}
	
}