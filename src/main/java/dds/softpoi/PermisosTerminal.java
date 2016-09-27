package dds.softpoi;

abstract class PermisosTerminal {

	public String tipoPermiso(){
		return this.getClass().getName().substring(4);
	}
	
}
