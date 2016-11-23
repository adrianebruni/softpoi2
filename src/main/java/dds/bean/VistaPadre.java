package dds.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dds.repositorio.Repositorio;
import dds.softpoi.Administrador;
import dds.softpoi.DispositivoConsulta;
import dds.softpoi.PermisoBusqueda;
import dds.softpoi.RepoPOI;
import dds.softpoi.Seguridad;
import dds.softpoi.Servidor;
import dds.softpoi.Usuario;

@ManagedBean(name="bnVistaPadre")
@SessionScoped
public abstract class VistaPadre implements Serializable {
     
	private static final long serialVersionUID = 5533699653210560793L;

	//private List<String> colBusqueda = new ArrayList<String>();
	//private Administrador unAdmin = new Administrador();
	private Usuario unUsuarioLogueado;
    private Servidor unServidor = new Servidor();
    private Seguridad objSeguridad;
    
    public void init() {
    	
    	//unServidor = new Servidor();
    	
    	Administrador unAdmin = new Administrador();
    	
    	//colBusqueda = new ArrayList<String>();
//    	RepoPOI colPoisPrueba = new RepoPOI();
    	
//    	unServidor.cargarPOIs(colPoisPrueba.Dame_Bolsa_POI());
    	
		// Crear Administrador
		unAdmin.setNombre("root");
		unAdmin.setClave("root");
		unAdmin.setServidor(unServidor);
		//unAdmin.setFlagAuditoriaBusqueda(true);
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
    
    public void setServidor(Servidor unServidor) {
        this.unServidor = unServidor;
    }
    
    public Servidor getServidor(){
    	return unServidor;
    }
    
    //public List<String> getDatosBusqueda(){
    //	return colBusqueda;
    //}
    
	public Usuario getUnUsuarioLogueado() {
		return unUsuarioLogueado;
	}

	public void setUnUsuarioLogueado(Usuario unUsuarioLogueado) {
		this.unUsuarioLogueado = unUsuarioLogueado;
	}
	
}