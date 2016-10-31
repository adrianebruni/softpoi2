package dds.softpoi;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;

@Entity
//@Table(name = "T_BANCO")
public class Banco extends POI {
	
	private int altura;
	private int piso;
	private String departamento;
	private String unidad;
	private String codigoPostal;
	private String sucursal;
	private String gerente;
	private String zona;
	
	// ***************************************************************************
	// Constructor
	// ***************************************************************************
	
	public Banco(){}
	
	public Banco(String nombre, double latitud, double longitud) {
		super.nombre = nombre;
		super.latitud = latitud;
		super.longitud = longitud;		
	}

	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	public void setAltura(int altura) {
		this.altura = altura;
	}
	
	public void setPiso(int piso) {
		this.piso = piso;
	}
	
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public void setGerente(String gerente) {
		this.gerente = gerente;
	}
	
	public void setZona(String zona) {
		this.zona = zona;
	}
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public int getAltura() {
		return altura;
	}
	
	public int getPiso() {
		return piso;
	}
	
	public String getDepartamento() {
		return departamento;
	}
	
	public String getUnidad() {
		return unidad;
	}
	
	public String getCodigoPostal() {
		return codigoPostal;
	}
	
	public String getSucursal() {
		return sucursal;
	}
	
	public String getGerente() {
		return gerente;
	}
	
	public String getZona() {
		if (zona == null){
			return "<< SIN ZONA >>";
		}else{
			return zona;
		}
	}
	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************
	
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

	public String getListaServicios(){
		String cadenaServicio="";
		for(Servicio unServicio : this.getServicios())
		{				
			if(cadenaServicio.equals("")){
				cadenaServicio = unServicio.getServicio();
		    }
			else{
				cadenaServicio = 
						cadenaServicio + "\n" + unServicio.getServicio();
			}
		}
		return cadenaServicio;
	}
	
	public List<String> getInfo(){	
		List<String> listaInfo = new ArrayList<String>();
		listaInfo.add("Direccion: " + this.getDireccion());
		listaInfo.add("Zona: " + this.getZona());
		for(Servicio unServicioDisponible : servicios){
			listaInfo.add("Servicio: " + unServicioDisponible.getServicio());
		}	
		return listaInfo;
	}
	
	public String getImagenIcon(){
		return super.getImagenIcon("banco");
	}
}

