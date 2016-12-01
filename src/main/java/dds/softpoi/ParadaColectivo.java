package dds.softpoi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;

@Entity
public class ParadaColectivo extends POI {

	private String empresa;
	private int numeroDeLinea;
		
	// ***************************************************************************
	// Constructores
	// ***************************************************************************
	
	public ParadaColectivo(){};
	
	public ParadaColectivo(String nombre, double latitud, double longitud) {
		super.nombre = nombre;
		super.latitud = latitud;
		super.longitud = longitud;		
	}
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	public void setNumeroDeLinea(int numeroDeLinea){
		this.numeroDeLinea= numeroDeLinea;
	}

	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public String getEmpresa() {
		return empresa;
	}
	
	public int getNumeroDeLinea(){
		return numeroDeLinea;
	}
	
	
	
	
	
	// ***************************************************************************
	// Methods
	// ***************************************************************************
	
	public boolean estaDisponible(String unServicio, Date unDia, String unaHora){
		return true;
	}
	
	public boolean estaCercaDe(DispositivoConsulta unDispositivo){
		
		if (distancia(unDispositivo.getLatitud(),unDispositivo.getLongitud(),this.latitud,this.longitud) <100) {
			return true;
		}else{
			return false;
		}
	}
	
	public List<String> getInfo(){	
		List<String> listaInfo = new ArrayList<String>();
		listaInfo.add("Numero de Linea: " + numeroDeLinea);
		return listaInfo;
	}
	
	public String getImagenIcon(){
		return super.getImagenIcon(this.getTipoPOI());
	}
	
	public String getTipoPOI(){
		return this.getClass().getSimpleName().toString();
	}
	
	
}