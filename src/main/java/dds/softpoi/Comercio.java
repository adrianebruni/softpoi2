package dds.softpoi;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Comercio extends POI{
	
	private int altura;
	private int piso;
	private String departamento;
	private String unidad;
	private String codigoPostal;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rubro_id", referencedColumnName = "id")
	private Rubro rubro;
	
	// ***************************************************************************
	// Constructor
	// ***************************************************************************
	public Comercio(){
		
	};	
	public Comercio(String nombre, double latitud, double longitud, Rubro unRubro) {
			super.nombre = nombre;
			super.latitud = latitud;
			super.longitud = longitud;
			this.rubro = unRubro;
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
		
		public void setRubro(Rubro unRubro){
			this.rubro = unRubro;
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
		
		public Rubro getRubro(){
			return rubro;
		}
		
		// ***************************************************************************
		// Metodos
		// ***************************************************************************
		
		public boolean estaCercaDe(DispositivoConsulta unDispositivo){
			
			if (distancia(unDispositivo.getLatitud(),unDispositivo.getLongitud(),this.latitud,this.longitud) < rubro.getRadioCercania()) {
				return true;
			}else{
				return false;
			}
		}

		public String getDireccion(){
			String direccion;
			direccion= this.getCalle() +" "+ this.getAltura() +" "+ this.getPiso() +"° "+ this.getDepartamento() +" "+ this.getUnidad();
			return direccion;
		}
		
		public List<String> getInfo(){	
			List<String> listaInfo = new ArrayList<String>();
			listaInfo.add("Direccion: " + this.getDireccion());
			listaInfo.add("Nombre de Comercio: " + super.getNombre());
			listaInfo.add("Rubro: " + this.getRubro().getRubro());
			return listaInfo;
		}
		
		public String getImagenIcon(){
			return super.getImagenIcon("comercio");
		}
		
		public String tipoPOI(){
			return "Comercio";
		}
		
}
