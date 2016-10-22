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

import dds.softpoi.POI;

@ManagedBean(name="bnHistorialBusqueda")
@ViewScoped
public class HistorialBusqueda extends VistaPadre {
     
	private static final long serialVersionUID = -3883257579910217559L;
	
	private String usuario;
	private String fechaDesde;
	private String fechaHasta;
	
// Este atributo es del tipo List<  >
	private List<String> elemSeleccionado;
		
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

// Este atributo es del tipo List<  >
	public void setElemSeleccionado(List<String> fechaHasta) {
		this.elemSeleccionado = fechaHasta;
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

// Este atributo es del tipo List<  >
	public List<String> getElemSeleccionado(){
		return elemSeleccionado;
	}
	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************

	public List<POI> getDetallesPOIs(){
		return this.getElemSeleccionado().getPOIs();
	}

// Este atributo es del tipo List<  >
	public List<String> getResultadoBusqueda(){
		
		/*
		Crear un objeto que tenga los siguientes atributos:
		1) Fecha de busqueda --> el metodo que devuelve la la fecha, debe ser un string y se debe llamar "getFecha"
		2) usuario que realizo la busqueda --> el metodo que devuelve el nombre del usuario, debe ser un string y se debe llamar "getUsuario"
		3) Parametro buscado --> el metodo que devuelve el parametro de busqueda, debe ser un string y se debe llamar "getParametro"
		4) POIs encontrados (coleccion de POIs) --> el metodo que devuelve la coleccion se debe llamar "getPOIs"
		
		<< La busqueda a realizar debe devole un array de este tipo de objeto >>
		
		*/
		
		if (!this.usuario.isEmpty()){
			// Busco por usuario exacto
		}else{
			if ( !this.fechaDesde.isEmpty() || !this.fechaHasta.isEmpty() ){
				// Busco por fecha desde y fecha hasta
			}

			if ( !this.fechaDesde.isEmpty() || this.fechaHasta.isEmpty() ){
				// Busco por la fecha desde hasta hoy
			}
			
			if ( this.fechaDesde.isEmpty() || !this.fechaHasta.isEmpty() ){
				// Busco por la fecha hasta (desde los inicios de la prehistoria hasta la fecha hasta :P )
			}
		}
		
		return null;
	}
 
	
}
