package dds.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import dds.softpoi.Administrador;
import dds.softpoi.ElementoDeConsulta;
import dds.softpoi.POI;

@ManagedBean(name="bnHistorialBusqueda")
@ViewScoped
public class HistorialBusqueda extends VistaPadre {
     
	private static final long serialVersionUID = -3883257579910217559L;
	
	private String usuario;
	private String fechaDesde;
	private String fechaHasta;
	
	private List<ElementoDeConsulta> elementosBuscados;
	



	private ElementoDeConsulta unElementoSeleccionado;
		
	//@ManagedProperty("#{servidor}")
    //private Servidor servidorPpal;
    
	// ***************************************************************************
	// Inicializador
	// ***************************************************************************
	
	@PostConstruct
    public void init() {

    }
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	public void setUsuario(String usuario) {
		if (!usuario.isEmpty() && !usuario.trim().isEmpty()){
			this.usuario = usuario;
		}
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	public void setElementosBuscados(List<ElementoDeConsulta> elementosBuscados) {
		this.elementosBuscados = elementosBuscados;
	}

	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
    public String getUsuario() {
		return usuario;
	}
	
    public String getFechaDesde() {
		return fechaDesde;
	}
	
	public String getFechaHasta() {
		return fechaHasta;
	}
	
	public List<ElementoDeConsulta> getElementosBuscados() {
		return elementosBuscados;
	}
	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************

	public List<POI> getDetallesPOIs(){
		return null;
		//return this.getElemSeleccionado().getColPOIs();
	}

	// Este atributo es del tipo List<  >
	public void resultadoBusqueda(){
		elementosBuscados = ((Administrador) super.getUnUsuarioLogueado()).historialBusquedaPantalla(this.usuario,this.fechaDesde,this.fechaHasta);
	}
 
	
}
