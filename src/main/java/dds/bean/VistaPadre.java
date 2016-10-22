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

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import dds.softpoi.Administrador;
import dds.softpoi.POI;
import dds.softpoi.RepoPOI;
import dds.softpoi.Servidor;

@ManagedBean(name="bnVistaPadre")
@ViewScoped
public class VistaPadre implements Serializable {
     
	private static final long serialVersionUID = 5533699653210560793L;

	private Set<POI> POIs;
	private List<String> colBusqueda = new ArrayList<String>();
	private Administrador unAdmin = new Administrador();
	private POI POISeleccionado;
	
	
	@ManagedProperty("#{servidor}")
    private Servidor servidorPpal;
    
    @PostConstruct
    public void init() {
    	//POIs = service.createCars();
    	
    	POIs  = new HashSet<POI>(); 

    	colBusqueda = new ArrayList<String>();
    	
    	RepoPOI colPoisPrueba = new RepoPOI();
    	servidorPpal.cargarPOIs(colPoisPrueba.Dame_Bolsa_POI());
    	
		// Crear administrador
		unAdmin.setNombre("Juan");
		unAdmin.setClave("passPrueba");
		unAdmin.setServidor(servidorPpal);
		servidorPpal.addAdmin(unAdmin);
    }

    public Set<POI> getPOIs() {
    	return this.POIs;
    }
    
    public void setServidorPpal(Servidor unServidor) {
        this.servidorPpal = unServidor;
    }
    
    public List<String> getDatosBusqueda(){
    	return colBusqueda;
    }
    
    /*
    public List<String> getUsuario(){
    
    	return colBusqueda;
    }
	*/
    
    public String getText(){
    	return "";
    }
    
    public void setText(String text){
    	if (!text.isEmpty() && !text.trim().isEmpty()){
    		colBusqueda.add(text);
    	}
    }
    
    //@SuppressWarnings("unchecked")
	public Set<POI> getPoisEncontrados() {
    	    	
    	Set<POI> auxPOIs = new HashSet<POI>();    	
    	
    	// aca tiene que recorrer la coleccion colBusqueda y por cada uno de ellos buscar poi segun el criterio de busqueda.
    	for(String unCriterio : colBusqueda){
    		
    		// el resultado de cada busqueda se debe almacenar en una coleccion sin repetidos
    		auxPOIs.addAll(servidorPpal.buscaPOI(unCriterio, unAdmin));
    	}
    	
    	// Cargamos la variable privada de la clase con los POIs sin repetidos
    	POIs = auxPOIs;

    	// Retornamos los la coleccion de POIs (sin repetidos) para mostrarlos por pantalla
		return POIs;
    	
    }
    
	
	/* 
	 * Metodos utilizados para la seleccion de un elemento en la tabla
	 * 
	 * */
	
    public POI getPOISeleccionado() {
		return POISeleccionado;
    }
 
    public void setPOISeleccionado(POI unPOISeleccionado) {
        this.POISeleccionado = unPOISeleccionado;
    }
      
    public List<String> getInfoPOI(){

    	return POISeleccionado.getInfo();
    	
    }
	
}