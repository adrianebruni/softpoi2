package dds.bean;

import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import dds.bbdd.DBMySQL;
import dds.softpoi.Administrador;
import dds.softpoi.DispositivoConsulta;
import dds.softpoi.POI;

@ManagedBean(name="bnVistaMSGDialog")
@ViewScoped
public class VistaMSGDialog extends VistaPadre {
	
	private DBMySQL objMyDB = new DBMySQL();
	private VistaABMUsuario objBean = new VistaABMUsuario();
	private Map<String, String[]> parametros;
	
	private String id;
	
	
	private Administrador unAdmin;
	private String nombreAdmin;
	private String claveAdmin;
	private String emailAdmin;
	private boolean flagAuditoriaBusquedaAdmin;
	private boolean flagNotificacionesAdmin;
	
	private DispositivoConsulta unaTerminal;
	private String nombreTerm;
	private String claveTerm;
	private double longitudTerm;
	private double latitudTerm;
	private boolean flagAuditoriaBusquedaTerm;
	private boolean flagNotificacionesTerm;

	private POI unPOI;
	private String nombrePOI;
	private double longitudPOI;
	private double latitudPOI;
	private String tipoPOI;
	
	
	// ***************************************************************************
	// Inicializador
	// ***************************************************************************
	
	public VistaMSGDialog(){}
	
    public void init() {}
	
	// ***************************************************************************
	// 
    // UTILIZADO POR: ABM ADMINISTRADOR
    //
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
    
    public String getNombreAdmin(){
    	return this.nombreAdmin;
    }
     
    public String getClaveAdmin(){
    	return this.claveAdmin;
    }    
    
    public String getEmailAdmin(){
    	return this.emailAdmin;
    }  
    
    public boolean getFlagAuditoriaBusquedaAdmin(){
    	return this.flagAuditoriaBusquedaAdmin;
    }
    
    public boolean getFlagNotificacionesAdmin(){
    	return this.flagNotificacionesAdmin;
    }   
    
	public String getAdministrador(){
		unAdmin = new Administrador();
		this.recibirParametros();
		unAdmin = objBean.getAdministrador(this.id);
		this.nombreAdmin = unAdmin.getNombre();
		this.claveAdmin = unAdmin.getClave();
		this.emailAdmin = unAdmin.getEmail();
		this.flagAuditoriaBusquedaAdmin = unAdmin.getFlagAuditoriaBusqueda();
		this.flagNotificacionesAdmin = unAdmin.getFlagNotificaciones();	
		return "Modificar Administrador";
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
		this.cerrarVentanaAdmin();
	}
	
	public void cerrarVentanaAdmin(){
		RequestContext.getCurrentInstance().closeDialog("modificarAdministrador");		
	}
	
	// ***************************************************************************
	// 
    // UTILIZADO POR: ABM TERMINAL
    //
	// ***************************************************************************

	public String getNombreTerm() {
		return nombreTerm;
	}

	public void setNombreTerm(String nombreTerm) {
		this.nombreTerm = nombreTerm;
	}

	public String getClaveTerm() {
		return claveTerm;
	}

	public void setClaveTerm(String claveTerm) {
		this.claveTerm = claveTerm;
	}

	public double getLongitudTerm() {
		return longitudTerm;
	}

	public void setLongitudTerm(double longitudTerm) {
		this.longitudTerm = longitudTerm;
	}

	public double getLatitudTerm() {
		return latitudTerm;
	}

	public void setLatitudTerm(double latitudTerm) {
		this.latitudTerm = latitudTerm;
	}	
	
	public boolean isFlagAuditoriaBusquedaTerm() {
		return flagAuditoriaBusquedaTerm;
	}

	public void setFlagAuditoriaBusquedaTerm(boolean flagAuditoriaBusquedaTerm) {
		this.flagAuditoriaBusquedaTerm = flagAuditoriaBusquedaTerm;
	}

	public boolean isFlagNotificacionesTerm() {
		return flagNotificacionesTerm;
	}

	public void setFlagNotificacionesTerm(boolean flagNotificacionesTerm) {
		this.flagNotificacionesTerm = flagNotificacionesTerm;
	}
	
	public String getTerminal(){
		this.recibirParametros();
		unaTerminal = new DispositivoConsulta();
		unaTerminal = objBean.getTerminal(this.id);

		this.nombreTerm = unaTerminal.getNombre();
		this.claveTerm = unaTerminal.getClave();
		this.longitudTerm = unaTerminal.getLongitud();
		this.latitudTerm = unaTerminal.getLatitud();
		this.flagAuditoriaBusquedaTerm = unaTerminal.getFlagAuditoriaBusqueda();
		this.flagNotificacionesTerm = unaTerminal.getFlagNotificaciones();	
		
		return "Modificar Terminal";
	}
	
	public void actualizarTerm(){
		
		unaTerminal.setNombre(this.nombreTerm);
		unaTerminal.setClave(this.claveTerm);
		unaTerminal.setLongitud(this.longitudTerm);
		unaTerminal.setLatitud(this.latitudTerm);
		unaTerminal.setFlagAuditoriaBusqueda(this.flagAuditoriaBusquedaTerm);
		unaTerminal.setFlagNotificaciones(this.flagNotificacionesTerm);
				
		objMyDB.getConexion();
		objMyDB.modificarTerminal(unaTerminal);
		objMyDB.cerrarConexion(); 
		this.cerrarVentanaTerm();
	}
	
	public void cerrarVentanaTerm(){
		RequestContext.getCurrentInstance().closeDialog("modificarTerminal");
		RequestContext.getCurrentInstance().update("formBAJAMOD:panelTerm");
	}
	
	
	// ***************************************************************************
	// 
    // UTILIZADO POR: ABM POI
    //
	// ***************************************************************************	
	
	public String getNombrePOI() {
		return nombrePOI;
	}

	public void setNombrePOI(String nombrePOI) {
		this.nombrePOI = nombrePOI;
	}

	public double getLongitudPOI() {
		return longitudPOI;
	}

	public void setLongitudPOI(double longitudPOI) {
		this.longitudPOI = longitudPOI;
	}

	public double getLatitudPOI() {
		return latitudPOI;
	}

	public void setLatitudPOI(double latitudPOI) {
		this.latitudPOI = latitudPOI;
	}

	public String getTipoPOI() {
		return tipoPOI;
	}

	public void setTipoPOI(String tipoPOI) {
		this.tipoPOI = tipoPOI;
	}
	
	public String getDatoPOI(){
		this.recibirParametros();
		
		objMyDB.getConexion();
		unPOI = objMyDB.buscarPOI(this.id);
		objMyDB.cerrarConexion();

		try {
			if (unPOI != null){
				this.nombrePOI = unPOI.getNombre();
				this.longitudPOI = unPOI.getLongitud();
				this.latitudPOI = unPOI.getLatitud();
				this.tipoPOI = unPOI.getTipoPOI();
			}
		} catch (Exception e) {

		}
		
		return "Modificar POI";
	}
	
	public void actualizarPOI(){
		
		unPOI.setNombre(this.nombrePOI);
		unPOI.setLongitud(this.longitudPOI);
		unPOI.setLatitud(this.latitudPOI);
		
				
		objMyDB.getConexion();
		objMyDB.modificarPOI(unPOI);
		objMyDB.cerrarConexion(); 
		this.cerrarVentanaPOI();
	}
	
	public void cerrarVentanaPOI(){
		RequestContext.getCurrentInstance().closeDialog("modificarPOI");
		RequestContext.getCurrentInstance().update("formBAJAMOD:panelPOI");
	}
	
	// ***************************************************************************
	//
	// COMUN PARA TODOS
	//
	// ***************************************************************************
	
    public String getId(){
    	return this.id;
    }
    
	public void recibirParametros() {
		parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
				
		String[] lstParam1 = parametros.get("from");
		String paramFrom = lstParam1[0]; // <--- Indica nombre de la clase (vista) desde donde se estan enviando los parametros
		
		String[] lstParam2 = parametros.get("id");
		String paramID = lstParam2[0];
		this.id = paramID;
		
	}
	

	
}
