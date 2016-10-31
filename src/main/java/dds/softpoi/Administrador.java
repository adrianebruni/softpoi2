package dds.softpoi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Administrador extends Usuario{

	@OneToMany
	private Set<PermisosTerminal> permisosAsetear = new HashSet<PermisosTerminal>();
	
	// ***************************************************************************
	// Constructor
	// ***************************************************************************
	public Administrador(){};
	
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
	    super.getServidor().modificarPOI(unPOI);
	}
	
	public void eliminarPOI(POI unPOI){
		super.getServidor().eliminarPOI(unPOI);
		
	}	
	
	public void actualizarLocalesComerciales(String rutaArchivo){
		try {
			super.getServidor().actualizarLocalesComerciales(rutaArchivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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