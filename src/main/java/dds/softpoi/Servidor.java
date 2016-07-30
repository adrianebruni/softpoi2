package dds.softpoi;
import java.util.ArrayList;


public class Servidor {
	// Constructor
	public ArrayList<POI> colPOIs = new ArrayList<POI>();
	public ArrayList<Administrador> colAdmins = new ArrayList<Administrador>();

	// ***************************************************************************
	// Setters
	// ***************************************************************************
		public void addAdmin(Administrador unAdmin) {
			this.colAdmins.add(unAdmin);
		}
		
		public void cargarPOI(POI unPOI) {
			this.colPOIs.add(unPOI);
		}
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
		public ArrayList<POI> getcolPOIs() {
			return colPOIs;
		}
		
		public ArrayList<Administrador> getcolAdmins() {
			return colAdmins;
		}
	
	// Metodos
	public boolean obtenerEstadisticas(){
		return true;
	}
	
	public boolean loguin(ArrayList<Administrador> colAdmins, Administrador adminBuscado){
		for (Administrador s: colAdmins)
		{
			if(s.equals(adminBuscado))
				return true;
		}
		return false;
	}
	
	
	public void eliminarAdmin(Administrador unAdmin){
		if(colAdmins.contains(unAdmin) == true){
			colAdmins.remove(unAdmin);
		}
	}	
	
	public void eliminarPOI(POI unPOI){
		if(colPOIs.contains(unPOI) == true){
			colPOIs.remove(unPOI);
		}
	}	
	

	public ArrayList<POI> buscaPOI(String cadenadebusqueda){
		ArrayList<POI> poiencontrados = new ArrayList<POI>();
		for(POI unpoi : this.colPOIs){
			if (unpoi.getNombre().toUpperCase().indexOf(cadenadebusqueda.toUpperCase()) > -1){
				poiencontrados.add(unpoi);
			}else{
				for(Servicio unservicio : unpoi.servicios){
					if (unservicio.getServicio().toUpperCase().indexOf(cadenadebusqueda.toUpperCase()) > -1){
						poiencontrados.add(unpoi);
						break;
					}
				}
				if (unpoi.tipoPOI().equalsIgnoreCase("Comercio"))
					if (((Comercio)(unpoi)).getRubro().getRubro().toUpperCase().indexOf(cadenadebusqueda.toUpperCase()) > -1)
						poiencontrados.add(unpoi);
			}
				
		}
		return poiencontrados;
	}
}
