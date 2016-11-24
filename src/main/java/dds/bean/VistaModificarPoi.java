package dds.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.primefaces.context.RequestContext;

import dds.repositorio.Repositorio;
import dds.softpoi.Administrador;
import dds.softpoi.Banco;
import dds.softpoi.CGP;
import dds.softpoi.Comercio;
import dds.softpoi.POI;
import dds.softpoi.ParadaColectivo;

@ManagedBean(name="bnVistaModificarPoi")
@ViewScoped
//@SessionScoped
public class VistaModificarPoi extends VistaPadre implements Serializable {

	private static final long serialVersionUID = -4368748875565642695L;
	private String nombre;
	private Long latitud;
	private Long longitud;
	private String tipoPOI;
	
	private Map<String, String> tipoPOIs = null;
	
	@ManagedProperty("#{bnVistaABMPoi}")
	private VistaABMPoi bnVistaABMPoi;
	
	public VistaABMPoi getBnVistaABMPoi(){
		return bnVistaABMPoi;
	}
	
	public void setBnVistaABMPoi(VistaABMPoi bnVistaABMPoi){
		this.bnVistaABMPoi = bnVistaABMPoi;
	}
	
	// ***************************************************************************
	// Inicializador
	// ***************************************************************************
	
	public VistaModificarPoi(){}
	
    public void init() {}
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setLatitud(Long latitud) {
		this.latitud = latitud;
	}
	
	public void setLongitud(Long longitud) {
		this.longitud = longitud;
	}
	
	public void setTipoPOI(String tipoPOI) {
		this.tipoPOI = tipoPOI;
	}

	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public String getNombre() {
		return nombre;
	}

	public Long getLatitud() {
		return latitud;
	}

	public Long getLongitud() {
		return longitud;
	}

	public String getTipoPOI() {
		return tipoPOI;
	}

	public Map<String, String> getTipoPOIs() {
		tipoPOIs = new HashMap<String, String>();
		tipoPOIs.put("Banco","Banco");
		tipoPOIs.put("CGP","CGP");
		tipoPOIs.put("Comercio","Comercio");
		tipoPOIs.put("ParadaColectivo","ParadaColectivo");
		return tipoPOIs;
	}
	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************

	
	public void editarPOI(String IDPOI){
		
		
		
		
	}	
	

	
}
