package dds.softpoi;

public class PermisoDisponibilidad extends PermisosTerminal{

	
	private static PermisoDisponibilidad permisoDisponibilidadSingleton= new PermisoDisponibilidad();
	
	//GETTER
	public static PermisoDisponibilidad getpermisoDisponibilidadSingleton() {
		 return permisoDisponibilidadSingleton;
		}
	
	
}
