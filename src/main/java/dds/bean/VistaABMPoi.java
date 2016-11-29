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

	//private static final long serialVersionUID = -4368748875565642695L;
	private String nombre;
	private Double latitud;
	private Double longitud;
	private String tipoPOI;
	
	private Map<String, String> tipoPOIs = null;

	private POI POISeleccionado;
	
	
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
		
		System.out.println("VistaABMPoi -> insertarPOI()");
		
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
		
		this.limpiarCampos();

	}
	
	private void limpiarCampos(){
		this.nombre = null;
		this.longitud = null;
		this.latitud = null;
	}

	public List<POI> getListapois(){
		// Obtenemos los parametros enviados desde el bean VistaLogin    	
    	super.setServidor(bnVistaLogin.getServidor());
    	super.setUnUsuarioLogueado(bnVistaLogin.getUnUsuarioLogueado()); 
		List<POI> colPOI = super.getServidor().getColPOIs();
		return colPOI;
	}
	
	public void editarPOI(String IDPOI){
		
		
		// Indicamos las opciones de configuracion de la ventana de dialogo
		Map<String,Object> opciones = new HashMap<String, Object>();
		opciones.put("resizable", false);
		
		// Indicamos los parametros que se encian por URL
		List<String> paramList = new ArrayList<String>();
		paramList.add(IDPOI);
				
		Map<String, List<String>> parametros = new HashMap<String, List<String>>();
		parametros.put("IDPOI", paramList);
		
		RequestContext.getCurrentInstance().openDialog("modificarPOI", opciones, parametros);
		
		
	}	
	
	public void eliminarPOI(String IDPOI){
		((Administrador) super.getUnUsuarioLogueado()).eliminarPOI(Integer.parseInt(IDPOI.substring(1, IDPOI.length()-1)));
		System.out.println("valor: " + Integer.parseInt(IDPOI.substring(1, IDPOI.length()-1)));
	}
	
	public void modificarPOI(POI unPOI){
		System.out.println("lalalalalal: " + unPOI.getNombre());
	}
	
}
