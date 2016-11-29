package dds.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import dds.softpoi.Administrador;
import dds.softpoi.ElementoDeConsulta;
import dds.softpoi.POI;

@ManagedBean(name="bnHistorialBusqueda")
@RequestScoped
public class HistorialBusqueda extends VistaPadre {
	
	//private static final long serialVersionUID = -1058309824881699645L;
	
	private String usuario;
	private String fechaDesde;
	private String fechaHasta;
	private List<ElementoDeConsulta> elementosBuscados;
	
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
