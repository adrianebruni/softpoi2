package dds.softpoi;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class CGP extends POI{
	private int altura;
	private int piso;
	private String departamento;
	private String unidad;
	private String codigoPostal;
	private String director;
	private String telefono;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "comuna_id", referencedColumnName = "id")
	private Comuna comuna;
	
//	private ArrayList<Servicio> servicios = new ArrayList<Servicio>();
	
	
	
	// ***************************************************************************
	// Constructor
	// ***************************************************************************
	
	public CGP(){};
	
	public CGP(String nombre, double latitud, double longitud) {
		super.nombre = nombre;
		super.latitud = latitud;
		super.longitud = longitud;		
	}
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	public void setAltura(int unaAltura){
		this.altura = unaAltura;
	}
	
	public void setPiso(int unPiso){
		this.piso = unPiso;
	}
	
	public void setDepartamento(String unDepartamento){
		this.departamento = unDepartamento;
	}
	
	public void setUnidad(String unaUnidad){
		this.unidad = unaUnidad;
	}
	
	public void setCodigoPostal(String unCodigoPostal){
		this.codigoPostal= unCodigoPostal;
	}
	
	public void setDirector(String unDirector) {
		this.director = unDirector;
	}
	
	public void setTelefono(String unTelefono) {
		this.telefono = unTelefono;
	}
	
	public void setComuna(Comuna unaComuna){
	//	if (latitud  <= unaComuna.getLimNorte() && 
	//		latitud  >= unaComuna.getLimSur()   && 
	//		longitud >= unaComuna.getLimEste()  && 
	//		longitud <= unaComuna.getLimOeste()){
		this.comuna = unaComuna;
	//	}
	}
	
	/*public void setServicios(Servicio unServicio) {
		this.servicios.add(unServicio);
	}*/
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public int getAltura(){
		return altura;
	}
	
	public int getPiso(){
		return piso;
	}
	
	public String getDepartamento(){
		return departamento;	
	}
	
	public String getUnidad(){
		return unidad;
	}
	
	public String getCodigoPostal(){
		return codigoPostal;
	}
	
	/*public ArrayList<Servicio> getServicios() {
		return servicios;
	}*/
	
	public Comuna getComuna(){
		return comuna;
	}
	
	public String getDirector() {
		return director;
	}

	public String getTelefono() {
		return telefono;
	}
	
	// ***************************************************************************
	// Methods
	// ***************************************************************************
	
	public boolean estaDisponible(String unServicio, Date unDia, String unaHora){		
		boolean existe = false;
 	
		for(Servicio unServicioDisponible : this.getServicios())
		{
		    if(unServicioDisponible.getServicio().equals(unServicio)){
		    	if (unServicioDisponible.estaDisponible(unDia, unaHora)){
		    		existe = true;
		    		break;
		    	}
		    }
		}
		
		return existe;
	}
	
	
	public boolean estaCercaDe(DispositivoConsulta unDispositivo){
		
		if (unDispositivo.getLatitud()  <= this.comuna.getLimNorte() && 
			unDispositivo.getLatitud()  >= this.comuna.getLimSur()   && 
			unDispositivo.getLongitud() >= this.comuna.getLimEste()  && 
			unDispositivo.getLongitud() <= this.comuna.getLimOeste()) {
			return true;
		}else{
			return false;
		}
	}
	
	public String getDireccion(){
		String direccion;
		if (this.getCalle() == null){
			return "<< SIN DIRECCION >>";
		}else{
			if(this.getPiso() == 0){
			direccion= this.getCalle() +" "+ this.getAltura();
			return direccion;
			}else{
				direccion= this.getCalle() +" "+ this.getAltura() +" "+ this.getPiso() +"° "+ this.getDepartamento() +" "+ this.getUnidad();
				return direccion;
				}
			}
	}
	
	public String getZona(){
		if (this.getComuna() == null){
			return "<< SIN ZONA >>";
		}else{
			return this.getComuna().getZonas();
		}
		
	}
	
	public String getListaServiciosyHorarios(){
		String cadenaServicioDisponibilidad="";
		for(Servicio unServicio : this.getServicios())
		{				
			if(cadenaServicioDisponibilidad.equals("")){
				cadenaServicioDisponibilidad = unServicio.getCadenaDisponibilidad();
		    }
			else{
				cadenaServicioDisponibilidad = 
						cadenaServicioDisponibilidad + "\n" + unServicio.getCadenaDisponibilidad();
			}
		}
		return cadenaServicioDisponibilidad;
	}
	
	public List<String> getInfo(){	
		List<String> listaInfo = new ArrayList<String>();
		listaInfo.add("Direccion: " + this.getDireccion());
		listaInfo.add("Zona: " + this.getZona());
		
		if (servicios.size() == 0 ){
			listaInfo.add("Servicio: << SIN SERVICIOS >>");
		}
		
		for(Servicio unServicioDisponible : servicios){
			
			/*for(Servicio unServicioDisponible : servicios){
				
			}*/
			
			listaInfo.add("Servicio: " + unServicioDisponible.getServicio() + "<< FALTA IMPLEMENTAR HORARIO >>");
			
		}	
		return listaInfo;
	}
	
	public String getImagenIcon(){
		return super.getImagenIcon("cgp");
	}
	
}
