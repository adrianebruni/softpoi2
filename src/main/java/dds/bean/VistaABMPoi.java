package dds.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dds.repositorio.Repositorio;
import dds.softpoi.Administrador;
import dds.softpoi.Banco;
import dds.softpoi.CGP;
import dds.softpoi.Comercio;
import dds.softpoi.POI;
import dds.softpoi.ParadaColectivo;

@ManagedBean(name="bnVistaABMPoi")
@ViewScoped
public class VistaABMPoi extends VistaPadre implements Serializable {

	private static final long serialVersionUID = -4368748875565642695L;
	private String nombre;
	private Long latitud;
	private Long longitud;
	private String tipoPOI;
	private Map<String, String> tipoPOIs = null;

	private String datoPOIText;
	private String datoPOILabel;
	
	
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
	
	public VistaABMPoi(){}
	
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

	public void setDatoPOIText(String datoDeUnPOI) {
		this.datoPOIText = datoDeUnPOI;
	}
	
	public void setDatoPOILabel(String datoDeUnPOI) {
		this.datoPOILabel = datoDeUnPOI;
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
	
	public String getDatoPOIText() {
		return datoPOIText;
	}
	
	public String getDatoPOILabel() {
		return datoPOILabel;
	}
	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************
	
	public void insertarPOI(String tipoPOI) {
		
		System.out.println("inicio");

		// Configuracion de persistencia
		
//		final String PERSISTENCE_UNIT_NAME = "DDS";
//		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//		Repositorio unRepositorio;
		//emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//		unRepositorio = new Repositorio(emFactory.createEntityManager());
//		super.getServidor().setRepositorio(unRepositorio);
		
		POI unPOI = null;
		switch (this.tipoPOI){		
			case "Banco":
				unPOI = new Banco();
				break;
			
			case "CGP":
				unPOI = new CGP();
				break;
				
			case "Comercio":
				unPOI = new Comercio();
				break;
				
			case "ParadaColectivo":
				unPOI = new ParadaColectivo();
		}
		
		unPOI.setNombre(this.nombre);
		unPOI.setLongitud(this.longitud);
		unPOI.setLatitud(this.latitud);
		((Administrador) super.getUnUsuarioLogueado()).cargarPOI(unPOI);
		System.out.println("inicia persitencia");
		//super.getServidor().cargarPOI(unPOI);
		System.out.println("fin");
	}
	
	public List<POI> listapois(){	
		
		// Obtenemos los parametros enviados desde el bean VistaLogin    	
    	super.setServidor(bnVistaLogin.getServidor());
    	super.setUnUsuarioLogueado(bnVistaLogin.getUnUsuarioLogueado()); 
    	
		
		List<POI> colPOI = super.getServidor().getColPOIs();
		return colPOI;
	}

	public List<POI> getListapois(){
		
		// Obtenemos los parametros enviados desde el bean VistaLogin    	
    	super.setServidor(bnVistaLogin.getServidor());
    	super.setUnUsuarioLogueado(bnVistaLogin.getUnUsuarioLogueado()); 
    	
		
		List<POI> colPOI = super.getServidor().getColPOIs();
		return colPOI;
	}
	
	public void editarPOI(){
		// 
	}	
	
	public void eliminarPOI(){
		//((Administrador) super.getUnUsuarioLogueado()).eliminarPOI(unPOI);
	}
	
	public void tipoPoiSeleccionado(){
		
		datoPOIText = "zzzz";
		
		// Verifico que tipo de poi esta seleccionado
		switch (this.tipoPOI){		
			case "Banco":
				datoPOILabel = "Gerente: ";
				break;
			
			case "CGP":
				datoPOILabel = "CGP";
				break;
				
			case "Comercio":
				datoPOILabel = "Comercio";
				break;
				
			case "ParadaColectivo":
				datoPOILabel = "ParadaColectivo";
				break;
			default:
				datoPOILabel = "";
		}

	}
	
}
