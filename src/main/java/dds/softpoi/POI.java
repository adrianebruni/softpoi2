package dds.softpoi;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "POI")
/*
@NamedQueries(
{
	@NamedQuery(name = "buscarPOIPorNombre", query = "SELECT p FROM POI p WHERE p.nombre LIKE :pnombre"),
	@NamedQuery(name = "buscarPOIPorNombreExacto", query = "SELECT p FROM POI p WHERE p.nombre LIKE :pnombre")	
})
*/
@NamedQuery(name = "buscarPOIPorNombre", query = "SELECT p FROM POI p WHERE p.nombre LIKE :pnombre")
public abstract class POI {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int idpoi;
	
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
	protected Date fecha_baja;
	protected String palabras_clave;
	
	
	public abstract List<String> getInfo();
	
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************

	public void setIdpoi(int idpoi){
		this.idpoi = idpoi;
	}
	
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
	
	public void setPalabrasClave(String palabras){
		this.palabras_clave = palabras;
	}
	
	public void setFechaBaja(Date fechabaja){
		this.fecha_baja = fechabaja;
	}
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public int getIdpoi(){
		return idpoi;
	}
	
	
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
	
	public String getPalabrasClave(){
		return this.palabras_clave;
	}
	
	public Date getFechaBaja(){
		return this.fecha_baja;
	}
	
	public String getDireccion(){
		return this.getCalle();
	}
	
	public String getImagenIcon(String tipoPOI){	
		Parametros objParametro = new Parametros();
		
		String unArchivo = objParametro.getRutaImagenesIconosAbsoluta()+ idpoi + ".gif";
		
		File arch = new File(unArchivo);
		
		if (arch.exists()){
			return objParametro.getRutaImagenesIconos() + idpoi + ".gif";
		}else{
			return objParametro.getRutaImagenesIconos() + tipoPOI + ".png";
		}
		
		
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
