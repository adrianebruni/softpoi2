package dds.bean;

//import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dds.softpoi.Administrador;
import dds.softpoi.DispositivoConsulta;
import dds.softpoi.POI;
import dds.softpoi.PermisoBusqueda;
import dds.softpoi.RepoPOI;
import dds.softpoi.Seguridad;
import dds.softpoi.Servidor;
import dds.softpoi.Usuario;

@ManagedBean(name="bnVistaPadre")
@ViewScoped
public abstract class VistaPadre {
     
	//private static final long serialVersionUID = 5533699653210560793L;

	private Set<POI> POIs;
	private List<String> colBusqueda = new ArrayList<String>();
	private Administrador unAdmin = new Administrador();
	private Usuario unUsuarioLogueado;
	
	private POI POISeleccionado;
    private Servidor unServidor = new Servidor();
    private Seguridad objSeguridad;
    
    @PostConstruct
    public void init() {
    	
    	//unServidor = new Servidor();
    	
    	POIs = new HashSet<POI>(); 
    	colBusqueda = new ArrayList<String>();
    	RepoPOI colPoisPrueba = new RepoPOI();
    	
    	unServidor.cargarPOIs(colPoisPrueba.Dame_Bolsa_POI());
    	
		// Crear Administrador
		unAdmin.setNombre("root");
		unAdmin.setClave("root");
		unAdmin.setServidor(unServidor);
		unServidor.addAdmin(unAdmin);
				
		DispositivoConsulta terminalAbasto = new DispositivoConsulta("terminalAbasto", 0.350219708, 0.715584992, "Abasto");
		terminalAbasto.setClave("terminal1");
		terminalAbasto.setFlagAuditoriaBusqueda(true);
		terminalAbasto.setFlagNotificaciones(false);
		terminalAbasto.setServidor(unServidor);
		unServidor.addAdmin(terminalAbasto);
		
		// ESTO ESTA HARDCODEADO PERO DEBERIA IR EN UNA PANTALLA DE ADMINISTRACION
		// es decir que un administrador tiene que logearse y darle permisos a una terminal para que pueda hacer consultas.
		unAdmin.agregarPermiso(PermisoBusqueda.getpermisoBusquedaSingleton());
		unAdmin.commitPermisos(terminalAbasto);	
		
		objSeguridad = new Seguridad(unServidor);
		
		
		//HARDCODEO UNA CONSULTA DE USUARIO PARA TENER DATOS PARA LA BUSQUEDA DE HISTORIAL
		//buscamos coincidencias para COMUNA
		//terminalAbasto.buscaPOI("COMUNA");
		//2da busqueda
		//terminalAbasto.buscaPOI("FRA");
		
		
    }
    
    public Seguridad getObjSeguridad(){
    	return objSeguridad;
    }
    
    
    public Set<POI> getPOIs() {
    	return this.POIs;
    }
    
    
    public void setServidor(Servidor unServidor) {
        this.unServidor = unServidor;
    }
    
    
    public Servidor getServidor(){
    	return unServidor;
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
    
    /*
    //@SuppressWarnings("unchecked")
	public Set<POI> getPoisEncontrados() {
    	    	
    	Set<POI> auxPOIs = new HashSet<POI>();    	
    	
    	// aca tiene que recorrer la coleccion colBusqueda y por cada uno de ellos buscar poi segun el criterio de busqueda.
    	for(String unCriterio : colBusqueda){
    		
    		// el resultado de cada busqueda se debe almacenar en una coleccion sin repetidos
    		auxPOIs.addAll(unServidor.buscaPOI(unCriterio, unAdmin));
    	}
    	
    	// Cargamos la variable privada de la clase con los POIs sin repetidos
    	POIs = auxPOIs;

    	// Retornamos los la coleccion de POIs (sin repetidos) para mostrarlos por pantalla
		return POIs;
    	
    }
    */
	
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

	public Usuario getUnUsuarioLogueado() {
		return unUsuarioLogueado;
	}

	public void setUnUsuarioLogueado(Usuario unUsuarioLogueado) {
		this.unUsuarioLogueado = unUsuarioLogueado;
	}
	
}