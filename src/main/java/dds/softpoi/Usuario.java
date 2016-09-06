package dds.softpoi;

import java.util.ArrayList;

public abstract class Usuario {

	protected String nombre;
	protected String email;
	protected String clave;
	protected String token;
	protected Servidor serv;
	protected ArrayList<ItemReporteFecha> lstItemReporteFecha = new ArrayList<ItemReporteFecha>();
	private boolean flagAuditoriaBusqueda;
	private boolean flagNotificaciones;
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	public void setNombre(String unNombre){
		nombre = unNombre;
	}

	public void setEmail(String unEmail){
		email = unEmail;
	}

	public void setPass(String unaClave) {
		clave = unaClave;
	}
	
	public void setToken (String unToken) {
		token = unToken;
	}
	
	public void setServidor (Servidor elServidor) {
		this.serv = elServidor;
	}
	
	public void setFlagAuditoriaBusqueda(boolean flagAuditoriaBusqueda) {
		this.flagAuditoriaBusqueda = flagAuditoriaBusqueda;
	}

	public void setFlagNotificaciones(boolean flagNotificaciones) {
		this.flagNotificaciones = flagNotificaciones;
	}
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getClave() {
		return this.clave;
	}
	
	public String getToken() {
		return this.token;
	}
	
	public Servidor getServidor() {
		return serv;
	}
	
	public boolean getFlagAuditoriaBusqueda() {
		return flagAuditoriaBusqueda;
	}
	
	public boolean getFlagNotificaciones() {
		return flagNotificaciones;
	}	

	

	
	// ***************************************************************************
	// Metodos (material ver para interfa grafica)
	// ***************************************************************************

	public void reportePorFecha (){
		this.lstItemReporteFecha = this.serv.reportePorFecha(this);
		
		System.out.println("FECHA \t CANTIDAD");
		for(ItemReporteFecha unItemReporteFecha: lstItemReporteFecha) {	
			System.out.println(unItemReporteFecha.getFecha() + "\t  " + unItemReporteFecha.getCantidad());
		}
		
	}
	
	public ArrayList<POI> buscaPOI (String cadenadebusqueda){
		return this.getServidor().buscaPOI(cadenadebusqueda, this);
		}

	
}