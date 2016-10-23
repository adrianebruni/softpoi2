package dds.softpoi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Administrador extends Usuario{

	private Set<PermisosTerminal> permisosAsetear = new HashSet<PermisosTerminal>();
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************
						
	public void cargarPOI(POI unPOI) {
		// Falta validar con clase seguridad
		System.out.println("Falta validar con clase seguridad");
		super.getServidor().cargarPOI(unPOI);
	}
	
	public void modificarPOI(POI unPOI) throws IllegalArgumentException, IllegalAccessException{ 
	    serv.modificarPOI(unPOI);
		
	}
	
	public void eliminarPOI(POI unPOI){
		serv.eliminarPOI(unPOI);
		
	}	
	
	public void actualizarLocalesComerciales(String rutaArchivo){
		try {
			serv.actualizarLocalesComerciales(rutaArchivo);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void agregarPermiso(PermisosTerminal unPermiso){
		permisosAsetear.add(unPermiso);
	}
	
	public void commitPermisos(DispositivoConsulta unDispositivo){
		//piso los permisos viejos con los actuales, haciendo un backup
		unDispositivo.setPermisosPrevios(unDispositivo.getPermisosActuales());
		unDispositivo.setPermisosActuales(permisosAsetear);
	}
	
	public void undoCommitPermisos(DispositivoConsulta unDispositivo){
		//piso los permisos actuales con los viejos, revirtiendo el ultimo cambio
		unDispositivo.setPermisosActuales(unDispositivo.getPermisosPrevios());
	}
	
	public ArrayList<ElementoDeConsulta> historialBusquedaPantalla(String unUsuario, String fechaInicial, String fechaFinal){

		ArrayList<ElementoDeConsulta> poiEncontradosHist = new ArrayList<ElementoDeConsulta>();
		
		poiEncontradosHist.addAll(this.getServidor().historialBusquedaPantalla(unUsuario,fechaInicial,fechaFinal));
		return poiEncontradosHist;
	}
	
}