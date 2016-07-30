package dds.softpoi;
import java.util.ArrayList;
import java.util.Date;

//import maps.java.*; //propiedades-java build path-libraries-add external jars-MapsJavaJar
	
public abstract class POI {
	protected String nombre;
	protected double latitud;              
	protected double longitud;              
	protected String pais;
	protected String provincia;			
	protected String barrio;
	protected String localidad;
	protected String calle;
	protected String calle_interseccion;
	protected ArrayList<Servicio> servicios = new ArrayList<Servicio>();
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	public void setCalle_interseccion(String calle_interseccion) {
		this.calle_interseccion = calle_interseccion;
	}
	
	public void setServicios(Servicio unServicio) {
		this.servicios.add(unServicio);
	}
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public String getNombre() {
		return nombre;
	}

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}
	
	public String getPais() {
		return pais;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getBarrio() {
		return barrio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public String getCalle() {
		return calle;
	}

	public String getCalle_interseccion() {
		return calle_interseccion;
	}
	
	public ArrayList<Servicio> getServicios() {
		return servicios;
	}
	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************
	
	public boolean estaCercaDe(DispositivoConsulta unDispositivo){	
		if (distancia(unDispositivo.getLatitud(),unDispositivo.getLongitud(),this.latitud,this.longitud) < 500){ 
			return true;
		}else{
			return false;
		}
	}
	
	
	public boolean esValido(){
		return !(this.nombre == null || this.nombre.trim().isEmpty() ||
				 this.latitud > 90 || this.latitud < -90 ||
				 this.longitud > 180 || this.longitud < -180);
	}
	
	
	public String tipoPOI(){
		return this.getClass().getName().substring(4);
	}
	
	/*
	// Funcion que calcula la distancia entre 2 coordenadas (con el uso de internet)
	public double distancia(double lat1, double lon1, double lat2, double lon2){
			double distancia = 0;
			//System.out.println("Arranca");
			Geocoding ObjGeocod=new Geocoding();
		    Route ObjRout=new Route();
			try {
				ArrayList<String> puntogeografico1=ObjGeocod.getAddress(lat1,lon1);
				ArrayList<String> puntogeografico2=ObjGeocod.getAddress(lat2,lon2);
				//System.out.println(puntogeografico1.get(0));
				//System.out.println(puntogeografico2.get(0));
				@SuppressWarnings("unused")
				String[][] resultado=ObjRout.getRoute(puntogeografico1.get(0), puntogeografico2.get(0), null, Boolean.TRUE, Route.mode.walking, Route.avoids.nothing);
				ArrayList<Integer> distancias = ObjRout.getTotalDistance();
				//System.out.println("La distancia es " + distancias.get(0));
				distancia = distancias.get(0);
			} catch (Exception e) {
				System.out.println("Se pudrio todo obteniendo la distancia...necesita internet!");
			}
			//System.out.println("Termina");
			System.out.println("Distancia: " + distancia);
			return distancia;
	}
	*/
	
	// Funcion que calcula la distancia entre 2 coordenadas (la unidad de medida es opcional, por defecto calcula en metros)
	public static double distancia(double lat1, double lon1, double lat2, double lon2, String unidadMedida){
		
		double radioTierra;
		
		switch (unidadMedida){		
			case "millas":
				radioTierra = 3958.75;
				break;
			
			case "kilometros":
				radioTierra = 6370.99;
				break;
				
			case "metros":
				radioTierra = 6370990.56;
				break;
				
			default:
				radioTierra = 6370990.56;
		}
			
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		
		double senoLat = Math.sin(dLat / 2);
		double senoLon = Math.sin(dLon / 2);
		
		double aux1 = Math.pow(senoLat, 2) + Math.pow(senoLon, 2) * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
		double aux2 = 2 * Math.atan2(Math.sqrt(aux1), Math.sqrt(1 - aux1));
		
		double distancia = radioTierra * aux2;
		
		return distancia;
		
	}
	
	// Funcion que calcula la distancia entre 2 coordenadas
	public static double distancia(double lat1, double lon1, double lat2, double lon2){
		
		double radioTierra = 6370990.56;

		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		
		double senoLat = Math.sin(dLat / 2);
		double senoLon = Math.sin(dLon / 2);
		
		double aux1 = Math.pow(senoLat, 2) + Math.pow(senoLon, 2) * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
		double aux2 = 2 * Math.atan2(Math.sqrt(aux1), Math.sqrt(1 - aux1));
		
		double distancia = radioTierra * aux2;
		
		return distancia;		
	}
	
	
    // estaDisponible("nombre del servicio",new Date(),"HH:MM:SS")
	public boolean estaDisponible(String unServicio, Date unDia, String unaHora){

		boolean existe = false;
		
		// Si no se envia un servicio por parametro, entonces buscamos un servicio cualquiera que cumpla con la fecha y horario
		if (unServicio.trim().isEmpty()){
					
			for(Servicio unServicioDisponible : servicios)
			{
			   	if (unServicioDisponible.estaDisponible(unDia, unaHora)){
			    		existe = true;
			    		break;
			   	}
			}
		}else{
		// Si se paso un servicio como parametro, entonces buscamos que exista tal servicio para la fecha y hora enviada por parametro.
			for(Servicio unServicioDisponible : servicios)
			{
			    if(unServicioDisponible.getServicio().equals(unServicio)){
			    	if (unServicioDisponible.estaDisponible(unDia, unaHora)){
			    		existe = true;
			    		break;
			    	}
			    }
			}
			
		}

		return existe;		
	}
	
}
