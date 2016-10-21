package dds.pruebas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import dds.softpoi.Administrador;
import dds.softpoi.POI;
import dds.softpoi.RepoPOI;
import dds.softpoi.Servidor;

@ManagedBean(name="dtBasicView")
@ViewScoped
public class BasicView implements Serializable {
     
    //private List<Car> cars;
	private List<POI> POIs;
	private String text;
	
	private List<String> colBusqueda = new ArrayList<String>();
	private Administrador unAdmin = new Administrador();
	
	/*
    @ManagedProperty("#{carService}")
    private CarService service;
    */
	
    @ManagedProperty("#{servidor}")
    private Servidor servidorPpal;
    
    
    @PostConstruct
    public void init() {
    	//POIs = service.createCars();
    	
    	RepoPOI colPoisPrueba = new RepoPOI();
    	servidorPpal.cargarPOIs(colPoisPrueba.Dame_Bolsa_POI());
    	
		//crear administrador
		//Administrador unAdmin = new Administrador();
		unAdmin.setNombre("Juan");
		unAdmin.setClave("passPrueba");
		unAdmin.setServidor(servidorPpal);
		servidorPpal.addAdmin(unAdmin);
    }
    
    public List<POI> getPOIs() {
    	return this.POIs;
    }
    
    /*
    public void setService(CarService service) {
        this.service = service;
    }
    */
    public void setServidorPpal(Servidor unServidor) {
        this.servidorPpal = unServidor;
    }
    
    public List<String> getUsuario(){
    	return colBusqueda;
    }
    
    public String getText(){
    	return "";
    }
    
    public void setText(String text){
    	colBusqueda.add(text);
    }
    
    public List<POI> getPoisEncontrados() {
    	    	
    	List<POI> auxPOIs = new ArrayList<POI>();
    	
    	// aca tiene que recorrer la coleccion colBusqueda y por cada uno de ellos buscar poi segun el criterio de busqueda.
    	for(String unCriterio : colBusqueda){
    		
    		// el resultado de cada busqueda se debe almacenar en una coleccion sin repetidos
    		auxPOIs.addAll(servidorPpal.buscaPOI(unCriterio, unAdmin));

    	}
    	
    	POIs = auxPOIs;
    	
    	// se debe mostrar la coleccion de POIs (sin repetidos)
		return POIs;
    	
    }
    
}