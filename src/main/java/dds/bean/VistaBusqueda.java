package dds.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import dds.softpoi.POI;

//@SuppressWarnings("serial")
@ManagedBean(name="bnVistaBusqueda")
@ViewScoped
public class VistaBusqueda extends VistaPadre implements Serializable {

	private static final long serialVersionUID = 6149074039987251332L;
	private Set<String> colCriteriosBusqueda = new HashSet<String>();
	private Set<POI> POIs;
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
	
	public VistaBusqueda(){}
	
    public void init() {
    	
    }
    
	// ***************************************************************************
	// Setters
	// ***************************************************************************
    
    public void setTextCriterio(String textCriterio){
    	if (!textCriterio.trim().isEmpty()){
    		colCriteriosBusqueda.add(textCriterio);
    	}
    }

    public void setPOISeleccionado(POI unPOISeleccionado) {
        this.POISeleccionado = unPOISeleccionado;
    }
    
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
    public Set<POI> getPOIs() {
    	return this.POIs;
    }
	
    public String getTextCriterio(){
    	return "";
    }
    
	public List<String> getCriterios(){
    	if (colCriteriosBusqueda.size() == 0){
    		return null;
    	}
    	
    	List<String> lstAux = new ArrayList<String>();
    	try {
        	lstAux.addAll(colCriteriosBusqueda);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return lstAux;
    }
    
    public POI getPOISeleccionado() {
		return POISeleccionado;
    }
    
	// ***************************************************************************
	// Metodos
	// ***************************************************************************
        
    //@SuppressWarnings("unchecked")
	public Set<POI> getPoisEncontrados() {
		System.out.println("|||||||||||||   getPoisEncontrados()   |||||||||||||||||||");
		Set<POI> auxPOIs = new HashSet<POI>(); 
		
		if ( (colCriteriosBusqueda == null) || (colCriteriosBusqueda.isEmpty()) ) {
			System.out.println("Debug: Primer proceso");
			return auxPOIs;
		}
					
		// Obtenemos los parametros enviados desde el bean VistaLogin
    	super.setServidor(bnVistaLogin.getServidor());
    	super.setUnUsuarioLogueado(bnVistaLogin.getUnUsuarioLogueado());    	
    	   	   	
    	// aca tiene que recorrer la coleccion colBusqueda y por cada uno de ellos buscar poi segun el criterio de busqueda.
    	//for(String unCriterio : colCriteriosBusqueda){    		
    	//	// el resultado de cada busqueda se debe almacenar en una coleccion sin repetidos
    	//	auxPOIs.addAll(super.getServidor().buscaPOI(unCriterio, super.getUnUsuarioLogueado()));
    	//}
    	ArrayList<String> cadenasDeQuerys = new ArrayList<String>();
    	cadenasDeQuerys.addAll(colCriteriosBusqueda);
    	auxPOIs.addAll(super.getServidor().buscaPOI( cadenasDeQuerys, super.getUnUsuarioLogueado()));
    	
    	// Cargamos la variable privada de la clase con los POIs sin repetidos
    	POIs = auxPOIs;
    	
    	// Retornamos los la coleccion de POIs (sin repetidos) para mostrarlos por pantalla
		return POIs;
    	
    }
	

    public List<String> getInfoPOI(){
    	return POISeleccionado.getInfo();	
    }

}
