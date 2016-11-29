package dds.bean;

import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import dds.bbdd.DBMySQL;
import dds.softpoi.Administrador;

@ManagedBean(name="bnVistaMSGDialog")
@ViewScoped
public class VistaMSGDialog extends VistaPadre {
	
	private DBMySQL objMyDB = new DBMySQL();
	
	private Map<String, String[]> parametros;
	
	private String id;
	
	private Administrador unAdmin;
	private String nombreAdmin;
	private String claveAdmin;
	private String emailAdmin;
	private boolean flagAuditoriaBusquedaAdmin;
	private boolean flagNotificacionesAdmin;
	
	// ***************************************************************************
	// Inicializador
	// ***************************************************************************
	
	public VistaMSGDialog(){}
	
    public void init() {}
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
    public void setNombreAdmin(String unNombre){
    	nombreAdmin = unNombre;
    }
	
    public void setClaveAdmin(String unaClave){
    	claveAdmin = unaClave;
    }
    
    public void setEmailAdmin(String unEmail){
    	emailAdmin = unEmail;
    }
    
    public void setFlagAuditoriaBusquedaAdmin(boolean flagAuditoriaBusqueda){
    	flagAuditoriaBusquedaAdmin = flagAuditoriaBusqueda;
    }   
    
    public void setFlagNotificacionesAdmin(boolean flagNotificaciones){
    	flagNotificacionesAdmin = flagNotificaciones;
    } 
    
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
    public String getId(){
    	return this.id;
    }
    
    public String getNombreAdmin(){
    	//return unAdmin.getNombre();
    	return this.nombreAdmin;
    }
     
    public String getClaveAdmin(){
    	//return unAdmin.getClave();
    	return this.claveAdmin;
    }    
    
    public String getEmailAdmin(){
    	//return unAdmin.getEmail();
    	return this.emailAdmin;
    }  
    
    public boolean getFlagAuditoriaBusquedaAdmin(){
    	//return unAdmin.getFlagAuditoriaBusqueda();
    	return this.flagAuditoriaBusquedaAdmin;
    }
    
    public boolean getFlagNotificacionesAdmin(){
    	//return unAdmin.getFlagNotificaciones();
    	return this.flagNotificacionesAdmin;
    }   
    
	public String getAdministrador(){
		System.out.println("--> getAdministrador"); 
		unAdmin = new Administrador();
		this.recibirParametros();
		VistaABMUsuario objBean = new VistaABMUsuario();
		unAdmin = objBean.getAdministrador(this.id);
		
		System.out.println("admin nombre --> " + unAdmin.getNombre() );

		this.nombreAdmin = unAdmin.getNombre();
		this.claveAdmin = unAdmin.getClave();
		this.emailAdmin = unAdmin.getEmail();
		this.flagAuditoriaBusquedaAdmin = unAdmin.getFlagAuditoriaBusqueda();
		this.flagNotificacionesAdmin = unAdmin.getFlagNotificaciones();	
		
		return "Modificar Administrador";
	}
	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************
	
	public void recibirParametros() {
		parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
				
		String[] lstParam = parametros.get("from");
		String paramFrom = lstParam[0];
		
		switch (paramFrom){		
			case "VistaABMUsuario":
				this.obtenerParametrosVistaABMUsuario();
				break;
							
			case "VistaABMPOI":
				this.obtenerParametrosVistaABMPOI();
				break;
		}
		
	}
		
	private void obtenerParametrosVistaABMUsuario(){
		String[] lstParam;
		lstParam = parametros.get("id");
		String paramID = lstParam[0];
		this.id = paramID;		
	}
	
	
	private void obtenerParametrosVistaABMPOI(){
		System.out.println("Pendiente --> obtenerParametrosVistaABMPOI" );
	}
	
	public void actualizarAdmin(){
		
    	unAdmin.setNombre(this.nombreAdmin);
    	unAdmin.setClave(this.claveAdmin);
    	unAdmin.setEmail(this.emailAdmin);
    	unAdmin.setFlagAuditoriaBusqueda(this.flagAuditoriaBusquedaAdmin);
    	unAdmin.setFlagNotificaciones(this.flagNotificacionesAdmin);
				
		objMyDB.getConexion();
		objMyDB.modificarAdministrador(unAdmin);
		objMyDB.cerrarConexion(); 
		this.cerrarVentana();
	}
	
	public void cerrarVentana(){
		RequestContext.getCurrentInstance().closeDialog("modificarAdministrador");
		RequestContext.getCurrentInstance().update("panelAdmin"); 
		System.out.println("panel");
	}
	
}
