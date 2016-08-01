package dds.softpoi;
import java.util.ArrayList;
import java.util.Date;

public class CGP extends POI{
	private int altura;
	private int piso;
	private String departamento;
	private String unidad;
	private String codigoPostal;
	private String director;
	private String telefono;
	private Comuna comuna;
	//private ArrayList<Servicio> servicios = new ArrayList<Servicio>();
	
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
		if (latitud  <= unaComuna.getLimNorte() && 
			latitud  >= unaComuna.getLimSur()   && 
			longitud >= unaComuna.getLimEste()  && 
			longitud <= unaComuna.getLimOeste()){
		this.comuna = unaComuna;
		}
	}
	
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
	
	public ArrayList<Servicio> getServicios() {
		return servicios;
	}
	
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
 	
		for(Servicio unServicioDisponible : servicios)
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
	
}
