package dds.softpoi;

import java.lang.reflect.Field;
import java.util.ArrayList;
import dds.json.BancoDTO;
import dds.json.CentroDTO;
import java.security.SecureRandom;
import java.math.BigInteger;

public class Servidor {
	// Constructor
	public ArrayList<POI> colPOIs = new ArrayList<POI>();
	public ArrayList<Administrador> colAdmins = new ArrayList<Administrador>();
	//Esta coleccion es para origenes de datos externos
	public ArrayList<POI> colPOIsExternos = new ArrayList<POI>();
	
	HistoricoConsulta histconsulta = new HistoricoConsulta();
	SecureRandom random = new SecureRandom();
	Parametros parametros = new Parametros();
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
		public void addAdmin(Administrador unAdmin) {
			unAdmin.setToken(generarToken());
			this.colAdmins.add(unAdmin);
		}
		
		public void cargarPOI(POI unPOI) {
			unPOI.setIdpoi(this.proximoIdPOI());
			this.colPOIs.add(unPOI);
		}
		
		public void cargarPOIs(ArrayList<POI> listaPOIs) {
			this.colPOIs.addAll(listaPOIs);
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
		
		public Parametros getParametros(){
			return parametros;
		}
		
	// Metodos
	public boolean obtenerEstadisticas(){
		return true;
	}
	
	/*
	public boolean loguin(ArrayList<Administrador> colAdmins, Administrador adminBuscado){
		for (Administrador s: colAdmins)
		{
			if(s.equals(adminBuscado))
				return true;
		}
		return false;
	}
	*/
	
	public String login(Administrador unAdmin){	
		 if (colAdmins.contains(unAdmin)){
			 return unAdmin.getToken();
		 }else{
			 return null;
		 }
			 
	}
	
	public String generarToken(){
		return new BigInteger(130,random).toString(32);
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
	
	public void modificarPOI(POI poimodificado) throws IllegalArgumentException, IllegalAccessException {
	
		for(POI unpoi : this.colPOIs){

			if(unpoi.getIdpoi() == poimodificado.getIdpoi()){
				// copia valores de clase hija
				Field[] fields = unpoi.getClass().getDeclaredFields();
				for(Field f : fields){
					f.setAccessible(true);
					f.get(poimodificado);
					f.set(unpoi, f.get(poimodificado));
				}
	
				// copia valores de clase padre
				Field[] fields2 = POI.class.getDeclaredFields();
				for(Field f : fields2){
					f.setAccessible(true);
					f.get(poimodificado);
					f.set(unpoi, f.get(poimodificado));
				}
			}	
		}
	
			
		
	
		
	}
	
	public ArrayList<POI> buscaPOI(String cadenadebusqueda, Usuario unUsuario){
		return histconsulta.consultar(cadenadebusqueda, this,unUsuario);
	}
	
	
/*	public ArrayList<POI> buscaPOI(String cadenadebusqueda){
		ArrayList<POI> poiencontrados = new ArrayList<POI>();

		//Esta es la logica que le agrego para considerar los pois de origen de datos externos
		ArrayList<POI> todoslospoi = new ArrayList<POI>();
		todoslospoi.addAll(getcolPOIs());
		//Aca busco en los datos externosa ver que pois hay cargados
		actualizoDesdeDatosExternos(cadenadebusqueda);
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
	}*/
	
	//
	public void actualizoDesdeDatosExternos(String cadena) {
		//boleteo todos los pois de la coleccion de externos...
		colPOIsExternos.removeAll(colPOIsExternos);
		//agrego los pois del origen de bancos
		//colPOIsExternos.addAll(obtenerBancosDeOrigenExterno("http://trimatek.org/Consultas/banco"));
		//cargarPOIExterno(poiexterno);
		//colPOIsExternos.addAll(bancosExternos.dameDatosExternos("http://trimatek.org/Consultas/banco?banco="+cadena));
		
		//BancoDTO bancosExternos1 = new BancoDTO();
		//colPOIsExternos.addAll(bancosExternos1.dameDatosExternos("http://trimatek.org/Consultas/banco?servidor="+cadena));
		
		BancoDTO bancosExternos = new BancoDTO();
		//												     	          http://trimatek.org/Consultas/banco
		ArrayList<POI> cadenabusqueda = bancosExternos.dameDatosExternos("http://trimatek.org/Consultas/banco?banco="+cadena);
		
		if (cadenabusqueda == null) {
			colPOIsExternos.addAll(bancosExternos.dameDatosExternos("http://trimatek.org/Consultas/banco?servidor="+cadena));
		}else {
			colPOIsExternos.addAll(cadenabusqueda);
		}
		
		CentroDTO centrosExternos = new CentroDTO();
		//														  http://trimatek.org/Consultas/centro
		colPOIsExternos.addAll(centrosExternos.dameDatosExternos("http://trimatek.org/Consultas/centro?zona="+cadena));	
		
	}
	

	
	//ver de optimizar con la funcion sort de la coleccion
	// despues de ordenarlo, obtenemos el maximo idpoi+1  *ver
    public int proximoIdPOI(){
		int idaux = 1;
		for(POI unpoi : colPOIs){
			if( unpoi.getIdpoi() >= idaux ){
				idaux = unpoi.getIdpoi() + 1;
			}
		}
		return idaux;	
	}
    
	
}
