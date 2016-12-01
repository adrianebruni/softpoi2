package dds.bean;

//import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

import dds.bbdd.DBMySQL;
import dds.softpoi.Administrador;
import dds.softpoi.Banco;
import dds.softpoi.CGP;
import dds.softpoi.Comercio;
import dds.softpoi.POI;
import dds.softpoi.ParadaColectivo;

//@SuppressWarnings("serial")
@ManagedBean(name="bnVistaABMPoi")
@ViewScoped
public class VistaABMPoi extends VistaPadre {

	
	private DBMySQL objMyDB = new DBMySQL();
	
	private String nombre;
	private Double latitud;
	private Double longitud;
	private String tipoPOI;
	
	private Map<String, String> tipoPOIs = null;

	private POI POISeleccionado;
	
	/*
	@ManagedProperty("#{bnVistaLogin}")
	private VistaLogin bnVistaLogin;
	
	public VistaLogin getBnVistaLogin(){
		return bnVistaLogin;
	}
	
	public void setBnVistaLogin(VistaLogin bnVistaLogin){
		this.bnVistaLogin = bnVistaLogin;
	}
	*/
	
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
	
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
	
	public void setTipoPOI(String tipoPOI) {
		this.tipoPOI = tipoPOI;
	}
	
	public void setPOISeleccionado(POI pOISeleccionado) {
		POISeleccionado = pOISeleccionado;
	}
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public String getNombre() {
		return nombre;
	}

	public Double getLatitud() {
		return latitud;
	}

	public Double getLongitud() {
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
	
	public POI getPOISeleccionado() {
		return POISeleccionado;
	}
	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************
	
	public void insertarPOI(String tipoPOI) {
		
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
		
		objMyDB.getConexion();
		objMyDB.altaPOI(unPOI);
		objMyDB.cerrarConexion();
		
		this.limpiarCampos();
		RequestContext.getCurrentInstance().update("formBAJAMOD:panelPOI");
		
	}
	
	private void limpiarCampos(){
		this.setNombre(null);
		this.setLongitud(null);
		this.setLatitud(null);
	}

	/*
	public List<POI> getListapois(){
		// Obtenemos los parametros enviados desde el bean VistaLogin    	
    	super.setServidor(bnVistaLogin.getServidor());
    	super.setUnUsuarioLogueado(bnVistaLogin.getUnUsuarioLogueado()); 
		List<POI> colPOI = super.getServidor().getColPOIs();
		return colPOI;
	}
	*/
	
	public List<POI> getListaPois(){
		objMyDB.getConexion();
		List<POI> colPOI = objMyDB.buscarPOIs(null,null,false);
		objMyDB.cerrarConexion();
		return colPOI;
	}
	
	public void editarPOI(String IDPOI){
			
		// Indicamos las opciones de configuracion de la ventana de dialogo
		Map<String, Object> opcionesVentana = new HashMap<String, Object>();
		opcionesVentana.put("modal", true);
		opcionesVentana.put("resizable", false);
		opcionesVentana.put("width", 480);
		opcionesVentana.put("height", 340);
		opcionesVentana.put("contentWidth", "100%");
		opcionesVentana.put("contentHeight", "100%");
		opcionesVentana.put("headerElement", "customheader");
		
		Map<String, List<String>> parametros = new HashMap<>();
		List<String> parametro;

		parametro = new ArrayList<>();
		parametro.add(this.getClass().getSimpleName());		
		parametros.put("from", parametro);
		
		parametro = new ArrayList<>();
		parametro.add(IDPOI);
		parametros.put("id", parametro);
				
		RequestContext.getCurrentInstance().openDialog("modificarPOI", opcionesVentana, parametros);
	}	
	
	public void eliminarPOI(String IDPOI){
		objMyDB.getConexion();
		objMyDB.bajaPOI(IDPOI);
		objMyDB.cerrarConexion();
		//((Administrador) super.getUnUsuarioLogueado()).eliminarPOI(Integer.parseInt(IDPOI.substring(1, IDPOI.length()-1)));
		//System.out.println("valor: " + Integer.parseInt(IDPOI.substring(1, IDPOI.length()-1)));
	}
	
}
