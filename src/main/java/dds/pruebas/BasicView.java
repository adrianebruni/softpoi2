package dds.pruebas;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
//import org.primefaces.showcase.domain.Car;
//import org.primefaces.showcase.service.CarService;

import dds.softpoi.POI;

@ManagedBean(name="dtBasicView")
@ViewScoped
public class BasicView implements Serializable {
     
    //private List<Car> cars;
	private List<POI> POIs;
     
    @ManagedProperty("#{carService}")
    private CarService service;
 
    @PostConstruct
    public void init() {
        //cars = service.createCars(3);
    	POIs = service.createCars(3);
    }
     
    /*
    public List<Car> getCars() {
        return cars;
    }
    */
    public List<POI> getPOIs() {
    	return POIs;
    }
    
    public void setService(CarService service) {
        this.service = service;
    }
}