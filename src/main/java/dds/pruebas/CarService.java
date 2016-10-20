package dds.pruebas;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;
//import java.util.UUID;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

//import dds.softpoi.Banco;
//import org.primefaces.showcase.domain.Car;

import dds.softpoi.Administrador;
import dds.softpoi.DispositivoConsulta;
import dds.softpoi.PermisoBusqueda;
import dds.softpoi.Servidor;

import dds.softpoi.POI;
import dds.softpoi.RepoPOI;

@ManagedBean(name = "carService")
@ApplicationScoped
public class CarService {
     
    private final static String[] colors;
     
    private final static String[] brands;
     
    static {
        colors = new String[10];
        colors[0] = "Black";
        colors[1] = "White";
        colors[2] = "Green";
        colors[3] = "Red";
        colors[4] = "Blue";
        colors[5] = "Orange";
        colors[6] = "Silver";
        colors[7] = "Yellow";
        colors[8] = "Brown";
        colors[9] = "Maroon";
         
        brands = new String[10];
        brands[0] = "BMW";
        brands[1] = "Mercedes";
        brands[2] = "Volvo";
        brands[3] = "Audi";
        brands[4] = "Renault";
        brands[5] = "Fiat";
        brands[6] = "Volkswagen";
        brands[7] = "Honda";
        brands[8] = "Jaguar";
        brands[9] = "Ford";
    }
    
    public List<POI> createCars(int size) {
    //public List<Car> createCars(int size) {
        
    	//List<Car> list = new ArrayList<Car>();
    	
    	List<POI> list = new ArrayList<POI>();
    	   	
   	
    	Servidor servidorPpal = new Servidor();
		
		//crear administrador
		Administrador juanAdmin = new Administrador();
		juanAdmin.setNombre("Juan");
		juanAdmin.setClave("passPrueba");
		juanAdmin.setServidor(servidorPpal);
		servidorPpal.addAdmin(juanAdmin);
		
		//agrego POIs
		RepoPOI colPoisPrueba = new RepoPOI();
		servidorPpal.cargarPOIs(colPoisPrueba.Dame_Bolsa_POI());
	
		//buscamos coincidencias para COMUNA
		//list.addAll(juanAdmin.buscaPOI("COMUNA"));
				
		list.addAll(colPoisPrueba.Dame_Bolsa_POI());

    	/*
		list.add(new Banco("Banco Frances", 10, 10));
		list.add(new Banco("Banco Nacion", 10, 10));
		list.add(new Banco("Banco Provincia", 10, 10));
		*/
		/*
    	for(int i = 0 ; i < size ; i++) {
        	list.add(new Car(getRandomId(), colPoisPrueba.Dame_Bolsa_POI().get(i).getNombre(), getRandomYear(), getRandomColor(), getRandomPrice(), getRandomSoldState()));
           //list.add(new Car(getRandomId(), getRandomBrand(), getRandomYear(), getRandomColor(), getRandomPrice(), getRandomSoldState()));
    	}
        */
        return list;
    }
     
    /*
    private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
     
    private int getRandomYear() {
        return (int) (Math.random() * 50 + 1960);
    }
     
    private String getRandomColor() {
        return colors[(int) (Math.random() * 10)];
    }
     
    private String getRandomBrand() {
        return brands[(int) (Math.random() * 10)];
    }
     
    public int getRandomPrice() {
        return (int) (Math.random() * 100000);
    }
     
    public boolean getRandomSoldState() {
        return (Math.random() > 0.5) ? true: false;
    }
 
    public List<String> getColors() {
        return Arrays.asList(colors);
    }
     
    public List<String> getBrands() {
        return Arrays.asList(brands);
    }
    */
}