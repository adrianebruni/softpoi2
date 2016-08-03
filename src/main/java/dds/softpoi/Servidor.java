package dds.softpoi;

import java.util.ArrayList;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dds.json.BancoDTO;
import dds.json.CentroDTO;

public class Servidor {
	// Constructor
	public ArrayList<POI> colPOIs = new ArrayList<POI>();
	public ArrayList<Administrador> colAdmins = new ArrayList<Administrador>();
	//Esta coleccion es para origenes de datos externos
	public ArrayList<POI> colPOIsExternos = new ArrayList<POI>();
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
		public void addAdmin(Administrador unAdmin) {
			this.colAdmins.add(unAdmin);
		}
		
		public void cargarPOI(POI unPOI) {
			unPOI.setIdpoi(this.proximoIdPOI());
			this.colPOIs.add(unPOI);
		}
	
		public void cargarPOIExterno(POI unPOI) {
			this.colPOIsExternos.add(unPOI);
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
		
		public ArrayList<POI> getcolPOIsExternos() {
			return colPOIsExternos;
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
	
	public void eliminarPOI(int idpoi){
		for(POI unpoi : this.colPOIs){
			if(unpoi.getIdpoi() == idpoi ){
				colPOIs.remove(unpoi);
				break;
			}
		}		
	}	

    //Punto punto=new Punto(20, 30);
    //Punto pCopia=(Punto)punto.clone();
	
	
	public void modificarPOI(POI poimodificado){
		for(POI unpoi : this.colPOIs){
			if(unpoi.getIdpoi() == poimodificado.getIdpoi() ){
				//aca copio tdos los atributos	
				//mirar el metodo clone() (hay que implementar implements Cloneable
				unpoi = poimodificado; 
				break;
			}
		}		
		
	}
	
	public ArrayList<POI> buscaPOI(String cadenadebusqueda){
		ArrayList<POI> poiencontrados = new ArrayList<POI>();

		//Esta es la logica que le agrego para considerar los pois de origen de datos externos
		ArrayList<POI> todoslospoi = new ArrayList<POI>();
		todoslospoi.addAll(getcolPOIs());
		//Aca busco en los datos externosa ver que pois hay cargados
		actualizoDesdeDatosExternos();
		todoslospoi.addAll(getcolPOIsExternos());
		//luego hago el for sobre la conjuncion de los pois, los del sistema y los externos		
		for(POI unpoi : todoslospoi){
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
	
	//
	public void actualizoDesdeDatosExternos() {
		//boleteo todos los pois de la coleccion de externos...
		colPOIsExternos.removeAll(colPOIsExternos);
		//agrego los pois del origen de bancos
		//colPOIsExternos.addAll(obtenerBancosDeOrigenExterno("http://trimatek.org/Consultas/banco"));
		//cargarPOIExterno(poiexterno);
		
		BancoDTO bancosExternos = new BancoDTO();
		colPOIsExternos.addAll(bancosExternos.dameDatosExternos("http://trimatek.org/Consultas/banco"));
		
		CentroDTO centrosExternos = new CentroDTO();
		colPOIsExternos.addAll(centrosExternos.dameDatosExternos("http://trimatek.org/Consultas/centro"));
		
	}
	

	
	//ver de optimizar con la funcion sort de la coleccion
	// despues de ordenarlo, obtenemos el maximo idpoi+1  *ver
    public int proximoIdPOI(){
		int idaux = 1;
		for(POI unpoi : colPOIs){
			if( unpoi.idpoi >= idaux ){
				idaux = unpoi.idpoi + 1;
			}
		}
		return idaux;	
	}
    
	
}
