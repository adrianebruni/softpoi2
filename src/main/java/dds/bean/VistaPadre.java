package dds.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import dds.softpoi.Administrador;
import dds.softpoi.DispositivoConsulta;
import dds.softpoi.PermisoBusqueda;
import dds.softpoi.Seguridad;
import dds.softpoi.Servidor;
import dds.softpoi.Usuario;


@ManagedBean(name="bnVistaPadre")
@SessionScoped
public abstract class VistaPadre {
     
	//private static final long serialVersionUID = 5533699653210560793L;

	//private List<String> colBusqueda = new ArrayList<String>();
	//private Administrador unAdmin = new Administrador();
	private Usuario unUsuarioLogueado;
    private Servidor unServidor;
    private Seguridad objSeguridad;
    
    public void init() {
    	
    	System.out.println("Iniciando: VistaPadre.java");
    	unServidor = new Servidor();
    	objSeguridad = new Seguridad(unServidor);
//    	Administrador unAdmin = new Administrador();
    	
    	//colBusqueda = new ArrayList<String>();
//    	RepoPOI colPoisPrueba = new RepoPOI();
    	
//    	unServidor.cargarPOIs(colPoisPrueba.Dame_Bolsa_POI());
    	
		// Crear Administrador
//		unAdmin.setNombre("root");
//		unAdmin.setClave("root");
//		unAdmin.setServidor(unServidor);
		//unAdmin.setFlagAuditoriaBusqueda(true);
//		unServidor.addAdmin(unAdmin);

/*    	
		DispositivoConsulta terminalAbasto = new DispositivoConsulta("terminalAbasto", 0.350219708, 0.715584992, "Abasto");
		terminalAbasto.setClave("terminal1");
		terminalAbasto.setFlagAuditoriaBusqueda(true);
		terminalAbasto.setFlagNotificaciones(false);
		terminalAbasto.setServidor(unServidor);
		unServidor.addAdmin(terminalAbasto);
*/	

    	/*
    	// ESTO ESTA HARDCODEADO PERO DEBERIA IR EN UNA PANTALLA DE ADMINISTRACION
		// es decir que un administrador tiene que logearse y darle permisos a una terminal para que pueda hacer consultas.
		unAdmin.agregarPermiso(PermisoBusqueda.getpermisoBusquedaSingleton());
		unAdmin.commitPermisos(terminalAbasto);	
*/		
		

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