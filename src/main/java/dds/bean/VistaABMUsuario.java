package dds.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import dds.bbdd.DBMySQL;
import dds.softpoi.Administrador;
import dds.softpoi.DispositivoConsulta;


@ManagedBean(name="bnVistaABMUsuario")
@ViewScoped
public class VistaABMUsuario extends VistaPadre {

	private String nombre;
	private String clave;
	private String email;
	private boolean notificaciones;
	private boolean auditoriaBusqueda;
	
	private Double longitud;
	private Double latitud;
	
	private DBMySQL objMyDB = new DBMySQL();
	
	/*
	@ManagedProperty("#{bnVistaAdministrador}")
	private VistaAdministrador bnVistaAdministrador;
	
	public VistaAdministrador getBnVistaAdministrador(){
		return bnVistaAdministrador;
	}
	
	public void setBnVistaAdministrador(VistaAdministrador bnVistaAdministrador){
		this.bnVistaAdministrador = bnVistaAdministrador;
	}
	*/
	
	// ***************************************************************************
	// Inicializador
	// ***************************************************************************
	
	public VistaABMUsuario(){}
    public void init() {}
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	
	public void setNotificaciones(boolean notificaciones) {
		this.notificaciones = notificaciones;
	}

	public void setAuditoriaBusqueda(boolean auditoriaBusqueda) {
		this.auditoriaBusqueda = auditoriaBusqueda;
	}
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public String getNombre() {
		return nombre;
	}

	public String getClave() {
		return clave;
	}

	public String getEmail() {
		return email;
	}

	public Double getLongitud() {
		return longitud;
	}
	
	public Double getLatitud() {
		return latitud;
	}
	
	public boolean isNotificaciones() {
		return notificaciones;
	}
	
	public boolean isAuditoriaBusqueda() {
		return auditoriaBusqueda;
	}
	
	// ***************************************************************************
	//
	// 			METODOS ADMINITRADOR
	//
	// ***************************************************************************
	
	public void altaAdministrador(){
		objMyDB.getConexion();
		Administrador unAdmin = new Administrador();
		unAdmin.setNombre(this.nombre);
		unAdmin.setClave(this.clave);
		unAdmin.setFlagAuditoriaBusqueda(this.auditoriaBusqueda);
		unAdmin.setFlagNotificaciones(this.notificaciones);
		unAdmin.setEmail(this.email);
		objMyDB.altaAdministrador(unAdmin);
		objMyDB.cerrarConexion();
		RequestContext.getCurrentInstance().update("formBAJAMOD:panelAdmin");
	}
	
	public List<Administrador> getAdministradores(){
		objMyDB.getConexion();
		List<Administrador> colAdmins = objMyDB.buscarAdministradores();
		objMyDB.cerrarConexion();
		return colAdmins;
	}
	
	public void eliminarAdministrador(String id_usuario){
		objMyDB.getConexion();
		objMyDB.bajaAdministrador(id_usuario);
		objMyDB.cerrarConexion();
	}

	public void editarAdministrador(String id_usuario){
		Map<String, Object> opcionesVentana = new HashMap<String, Object>();
		opcionesVentana.put("modal", true);
		opcionesVentana.put("resizable", false);
		opcionesVentana.put("width", 480);
		opcionesVentana.put("height", 340);
		opcionesVentana.put("contentWidth", "100%");
		opcionesVentana.put("contentHeight", "100%");
		opcionesVentana.put("headerElement", "customheader");
		
		Map<String, List<String>> parametros = new HashMap<>();
		List<String> parametro;

		parametro = new ArrayList<>();
		parametro.add(this.getClass().getSimpleName());		
		parametros.put("from", parametro);
		
		parametro = new ArrayList<>();
		parametro.add(id_usuario);
		parametros.put("id", parametro);
				
		RequestContext.getCurrentInstance().openDialog("modificarAdministrador", opcionesVentana, parametros);
		
	}
	
	public Administrador getAdministrador(String id_usuario){
		objMyDB.getConexion();
		Administrador unAdmin = objMyDB.buscarAdministrador(id_usuario);
		objMyDB.cerrarConexion();
		return unAdmin;
	}
	
	// ***************************************************************************
	//
	// 			METODOS TERMINAL
	//
	// ***************************************************************************	
	
	public List<DispositivoConsulta> getTerminales(){
		objMyDB.getConexion();
		List<DispositivoConsulta> colAdmins = objMyDB.buscarTerminales();
		objMyDB.cerrarConexion();
		return colAdmins; 
	}
	
	public DispositivoConsulta getTerminal(String id_usuario){
		objMyDB.getConexion();
		DispositivoConsulta unaTerminal = objMyDB.buscarTerminal(id_usuario);
		objMyDB.cerrarConexion();
		return unaTerminal;
	}
	
	public void eliminarTerminal(String id_usuario){
		objMyDB.getConexion();
		objMyDB.bajaTerminal(id_usuario);
		objMyDB.cerrarConexion();
	}

	public void editarTerminal(String id_usuario){
		Map<String, Object> opcionesVentana = new HashMap<String, Object>();
		opcionesVentana.put("modal", true);
		opcionesVentana.put("resizable", false);
		opcionesVentana.put("width", 480);
		opcionesVentana.put("height", 340);
		opcionesVentana.put("contentWidth", "100%");
		opcionesVentana.put("contentHeight", "100%");
		opcionesVentana.put("headerElement", "customheader");
		
		Map<String, List<String>> parametros = new HashMap<>();
		List<String> parametro;

		parametro = new ArrayList<>();
		parametro.add(this.getClass().getSimpleName());		
		parametros.put("from", parametro);
		
		parametro = new ArrayList<>();
		parametro.add(id_usuario);
		parametros.put("id", parametro);
				
		RequestContext.getCurrentInstance().openDialog("modificarTerminal", opcionesVentana, parametros);

	}
	
	public void altaTerminal(){		
		objMyDB.getConexion();
		DispositivoConsulta unaTerminal= new DispositivoConsulta();
		unaTerminal.setNombre(this.nombre);
		unaTerminal.setClave(this.clave);
		unaTerminal.setFlagAuditoriaBusqueda(this.auditoriaBusqueda);
		unaTerminal.setFlagNotificaciones(this.notificaciones);
		unaTerminal.setLongitud(this.longitud);
		unaTerminal.setLatitud(this.latitud);
		objMyDB.altaTerminal(unaTerminal);
		objMyDB.cerrarConexion();
		RequestContext.getCurrentInstance().update("formBAJAMOD:panelTerm");
	}
	
	
}
