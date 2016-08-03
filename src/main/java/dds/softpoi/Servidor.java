package dds.softpoi;

import java.util.ArrayList;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dds.json.BancoDTO;

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
//			System.out.println(unpoi.nombre);
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
		colPOIsExternos.addAll(obtenerBancosDeOrigenExterno("http://trimatek.org/Consultas/banco"));
		//cargarPOIExterno(poiexterno);
		
	}
	
	public ArrayList<POI> obtenerBancosDeOrigenExterno(String origenexternobanco){
		BancoDTO jsonBanco = new BancoDTO();
		JsonArray jsaBanco = new JsonArray();
		ArrayList<POI> coleccion = new ArrayList<POI>();
		try {
			jsaBanco = jsonBanco.consultarJson(origenexternobanco);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ocurrio error al obtener origen externo");
			return null;
		}	
		
		//aca declaro disponibilidades, al no tenerla en origen de datos, asumo que todos
		//los servicios estan disponibles de lunes a viernes de 10 a 15hs por ser Banco
		RangoHorario rango = new RangoHorario("10:00:00", "15:00:00");
		Disponibilidad lunes = new Disponibilidad();
		Disponibilidad martes = new Disponibilidad();
		Disponibilidad miercoles = new Disponibilidad();
		Disponibilidad jueves = new Disponibilidad();
		Disponibilidad viernes = new Disponibilidad();
		lunes.setDia("LUNES");
		lunes.setRangoHorario(rango);
		martes.setDia("MARTES");
		martes.setRangoHorario(rango);
		miercoles.setDia("MIERCOLES");
		miercoles.setRangoHorario(rango);
		jueves.setDia("JUEVES");
		jueves.setRangoHorario(rango);
		viernes.setDia("VIERNES");
		viernes.setRangoHorario(rango);
		
		for (int i = 0; i <= jsaBanco.size() - 1; i++){
			
			// Creamos un objeto del tipo Banco
			Banco unBanco = new Banco();
			
			// Seteamos la comuna (ID, Zonas)
			String jsonNombreBanco = ((JsonObject)jsaBanco.get(i)).get("banco").getAsString();
			//System.out.println(jsonNombreBanco);
			Double jsonX = ((JsonObject)jsaBanco.get(i)).get("x").getAsDouble();
			//System.out.println(jsonX);
			Double jsonY = ((JsonObject)jsaBanco.get(i)).get("y").getAsDouble();
			//String jsonSucursal = ((JsonObject)jsaBanco.get(i)).get("sucursal").getAsString();
			//String jsonGerente = ((JsonObject)jsaBanco.get(i)).get("gerente").getAsString();
			
			// Seteamos los servicios
			JsonArray jsonServicios = (JsonArray) ((JsonObject)jsaBanco.get(i)).get("servicios");
			//JsonElement jsonServicios = ((JsonObject)jsaCentro.get(0)).get("servicios");
			//System.out.println(jsonServicios.toString());	
			//System.out.println(jsonGerente);
			for (int j = 0; j <= jsonServicios.size() - 1; j++){
				// Creamos un objeto del tipo Servicio
				Servicio unServicio = new Servicio();
				// nombre del servicio
				//System.out.println(jsonServicios.get(j).getAsString());
				String jsonServicioNombre = jsonServicios.get(j).getAsString();
				//System.out.println(jsonServicioNombre);
				unServicio.setServicio(jsonServicioNombre);
				unServicio.setDisponibilidad(lunes);
				unServicio.setDisponibilidad(martes);
				unServicio.setDisponibilidad(miercoles);
				unServicio.setDisponibilidad(jueves);
				unServicio.setDisponibilidad(viernes);
				unBanco.setServicios(unServicio);
		
			} // FIN FOR J
			
			unBanco.setNombre(jsonNombreBanco);
			unBanco.setLatitud(jsonX);
			unBanco.setLongitud(jsonY);
			
			
			coleccion.add(unBanco);
			// Aca hay que darle la logica de que queremos hacer con cada CGP
			//System.out.println("NOMBRE: " + unBanco.getNombre());
			//System.out.println("LATI: " + unBanco.getLatitud());
			//System.out.println("LONGI: " + unBanco.getLongitud());
			
			//System.out.println("------------------------------------------------------------");
		}
		return coleccion;
	
	}
	
	//esta funcion es para obtener el proximo id de poi libre para asignar
	//se podria pasar en poner en el metodo cargarPOI algo asi como unPOI.setID = proximoIdPOI();
	//pero hay que ponerlopublico el ID en el poi... pensarlo entre todos
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
