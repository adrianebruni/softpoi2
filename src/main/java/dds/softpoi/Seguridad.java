package dds.softpoi;

import dds.bbdd.DBMySQL;

public class Seguridad {
	
	private Servidor unServidor;
	private DBMySQL objMyDB;
	
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
		Usuario userAdmin = new Administrador();
		Usuario userTerm = new DispositivoConsulta();
		
		objMyDB = new DBMySQL();
		objMyDB.getConexion();
		userAdmin = objMyDB.buscarAdministrador(nombreUsuario, claveUsuario);
		userTerm = objMyDB.buscarTerminal(nombreUsuario, claveUsuario);
		objMyDB.cerrarConexion();
		
		try {
			if (userAdmin != null){
				return userAdmin;
			}
		} catch (Exception e) {	}
		
		try {
			if (userTerm != null){
				return userTerm;
			}
		} catch (Exception e) {	}
		
		return null;
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