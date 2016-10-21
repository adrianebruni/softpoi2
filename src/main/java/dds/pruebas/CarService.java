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
        
    public List<POI> createCars() {
    //public List<Car> createCars(int size) {
        
    	//List<Car> list = new ArrayList<Car>();
    	
    	List<POI> list = new ArrayList<POI>();
    	   	
   	
    	Servidor servidorPpal = new Servidor();
		
    	//agrego POIs
		RepoPOI colPoisPrueba = new RepoPOI();
		servidorPpal.cargarPOIs(colPoisPrueba.Dame_Bolsa_POI());
		
		//crear administrador
		Administrador unAdmin = new Administrador();
		unAdmin.setNombre("Juan");
		unAdmin.setClave("passPrueba");
		unAdmin.setServidor(servidorPpal);
		servidorPpal.addAdmin(unAdmin);
		
		
	
		//buscamos coincidencias para COMUNA
		//list.addAll(unAdmin.buscaPOI("COMUNA"));
				
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

}