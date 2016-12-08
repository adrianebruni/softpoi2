package dds.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.context.RequestContext;

import dds.softpoi.Administrador;
import dds.softpoi.ElementoDeConsulta;
import dds.softpoi.POI;

@ManagedBean(name="bnHistorialBusqueda")
@RequestScoped
public class HistorialBusqueda extends VistaPadre {
	
	private String usuario;
	private String fechaDesde;
	private String fechaHasta;
	private List<ElementoDeConsulta> elementosBuscados;
	
	private List<POI> colPOIsSeleccionado;
	
	@ManagedProperty("#{bnVistaLogin}")
	private VistaLogin bnVistaLogin;
	
	public VistaLogin getBnVistaLogin(){
		return bnVistaLogin;
	}
	
	public void setBnVistaLogin(VistaLogin bnVistaLogin){
		this.bnVistaLogin = bnVistaLogin;
	}
    
	// ***************************************************************************
	// Inicializador
	// ***************************************************************************
	
	@PostConstruct
    public void init() {
    	// Obtenemos los parametros enviados desde el bean VistaLogin    	
    	super.setServidor(bnVistaLogin.getServidor());
    	super.setUnUsuarioLogueado(bnVistaLogin.getUnUsuarioLogueado());
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

	public void setColPOIsSeleccionado(List<POI> colPOI){
		System.out.println("Pase por aca");
		this.colPOIsSeleccionado = colPOI;
	}
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public List<POI> getColPOIsSeleccionado(){
		System.out.println("Pase por aca --> getColPOIsSeleccionado");
		System.out.println("getColPOIsSeleccionado: --> " + colPOIsSeleccionado);
		return colPOIsSeleccionado;
	}
	
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

	// Este atributo es del tipo List<  >
	public void resultadoBusqueda(){
		elementosBuscados = ((Administrador) super.getUnUsuarioLogueado()).historialBusquedaPantalla(this.usuario,this.fechaDesde,this.fechaHasta);
		
	}

    public void showMessage() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "info del poi", "bla bla bla");
         System.out.println("paseeeee");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
	
}
