package dds.pruebas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
//import org.primefaces.showcase.domain.Car;
//import org.primefaces.showcase.service.CarService;

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
		Administrador unAdmin = new Administrador();
		unAdmin.setNombre("Juan");
		unAdmin.setClave("passPrueba");
		unAdmin.setServidor(servidorPpal);
		servidorPpal.addAdmin(unAdmin);
    	
		//ArrayList<POI> colPOIs = new ArrayList<POI>();
		//colPOIs.addAll(servidorPpal.buscaPOI("banco", unAdmin));
		
		//List<POI> POIs = new ArrayList<POI>();
		
		POIs = servidorPpal.buscaPOI("banco", unAdmin);
		
		/*
		for(POI unPoi : colPOIs){
			try{
				POIs.add(unPoi);
			}catch (Exception e) {
				System.out.println("no encuentro errores" + e.getMessage().toString() + e.toString());
			}
		}
		*/
		//POIs.addAll(colPOIs);
		//List<String> usuarios = new ArrayList<String>();
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
    
}