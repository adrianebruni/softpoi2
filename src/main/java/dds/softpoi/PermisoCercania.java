package dds.softpoi;

public class PermisoCercania extends PermisosTerminal{

	private static PermisoCercania permisoCercaniaSingleton= new PermisoCercania();
	
	//GETTER
	public static PermisoCercania getpermisoCercaniaSingleton() {
		 return permisoCercaniaSingleton;
		}	

	
	//METODO
	
	public boolean estaCercaMio(POI unPoi, DispositivoConsulta unDispCons){
		return unPoi.estaCercaDe(unDispCons);
	}
	
}
