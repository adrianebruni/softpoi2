package dds.softpoi;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
abstract class PermisosTerminal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_permiso;
	
	// ***************************************************************************
	// CONTRUCTOR
	// ***************************************************************************

	public PermisosTerminal(){}
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	public void setId_permiso(int id_permiso) {
		this.id_permiso = id_permiso;
	}
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public int getId_permiso() {
		return id_permiso;
	}
	
	// ***************************************************************************
	// METODOS
	// ***************************************************************************
	
	public String tipoPermiso(){
		return this.getClass().getName().substring(12);
	}
	
}
