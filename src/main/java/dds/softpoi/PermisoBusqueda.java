package dds.softpoi;

public class PermisoBusqueda extends PermisosTerminal{

	
	private static PermisoBusqueda permisoBusquedaSingleton= new PermisoBusqueda();
	
	//GETTER
	public static PermisoBusqueda getpermisoBusquedaSingleton() {
		 return permisoBusquedaSingleton;
		}
	
}
