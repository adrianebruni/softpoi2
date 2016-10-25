package dds.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import dds.softpoi.POI;
import dds.softpoi.Servidor;

@ManagedBean(name="bnVistaBusqueda")
@ViewScoped
public class VistaBusqueda extends VistaPadre implements Serializable {

	private static final long serialVersionUID = 6149074039987251332L;
	private List<String> colCriteriosBusqueda = new ArrayList<String>();
	private Set<POI> POIs;
	private POI POISeleccionado;
	
	// ***************************************************************************
	// Inicializador
	// ***************************************************************************
	
    @PostConstruct
    public void init() {
    	//super.init();
    	
    }
	
    public void getServer(String server1){
    	System.out.println(server1); 
    }
    
	// ***************************************************************************
	// Setters
	// ***************************************************************************

    public void setServidor(Servidor unServidor){
    	super.setServidor(unServidor);
    }
    
    public void setText(String text){
    	if (!text.isEmpty() && !text.trim().isEmpty()){
    		colCriteriosBusqueda.add(text);
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
	
    public String getText(){
    	return "";
    }
    
    public List<String> getCriterios(){
    	return colCriteriosBusqueda;
    }
    
    public POI getPOISeleccionado() {
		return POISeleccionado;
    }
    
	// ***************************************************************************
	// Metodos
	// ***************************************************************************
        
    //@SuppressWarnings("unchecked")
	public Set<POI> getPoisEncontrados() {
    	    	
    	Set<POI> auxPOIs = new HashSet<POI>();    	
    	
    	// aca tiene que recorrer la coleccion colBusqueda y por cada uno de ellos buscar poi segun el criterio de busqueda.
    	for(String unCriterio : colCriteriosBusqueda){
    		
    		// el resultado de cada busqueda se debe almacenar en una coleccion sin repetidos
    		auxPOIs.addAll(super.getServidor().buscaPOI(unCriterio, super.getUnUsuarioLogueado()));
    	}
    	
    	// Cargamos la variable privada de la clase con los POIs sin repetidos
    	POIs = auxPOIs;

    	// Retornamos los la coleccion de POIs (sin repetidos) para mostrarlos por pantalla
		return POIs;
    	
    }

    public List<String> getInfoPOI(){
    	return POISeleccionado.getInfo();	
    }

}
