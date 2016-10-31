package dds.softpoi;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "USUARIO")
public abstract class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int id_usuario;

	@OneToMany
	protected List<ItemReporteFecha> lstItemReporteFecha = new ArrayList<ItemReporteFecha>();
	
	protected String nombre;
	protected String email;
	protected String clave;
	protected String token;
	private boolean flagAuditoriaBusqueda;
	private boolean flagNotificaciones;
	
	@Transient
	private Servidor serv;
	
	// ***************************************************************************
	// CONTRUCTORES
	// ***************************************************************************
	
	public Usuario(){}
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	public void setNombre(String unNombre){
		nombre = unNombre;
	}

	public void setEmail(String unEmail){
		email = unEmail;
	}

	public void setClave(String unaClave) {
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
	
	public int getId_usuario(){
		return this.id_usuario;
	}
	
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
	// Metodos
	// ***************************************************************************

	public ArrayList<ItemReporteFecha> reportePorFecha (){
//		this.lstItemReporteFecha = this.serv.reportePorFecha(this);
//		
//		System.out.println("FECHA \t CANTIDAD");
//		for(ItemReporteFecha unItemReporteFecha: lstItemReporteFecha) {	
//			System.out.println(unItemReporteFecha.getFecha() + "\t  " + unItemReporteFecha.getCantidad());
//		}
		return this.serv.reportePorFecha(this);
	}
	
	public ArrayList<ItemReporteTerminal> reportePorTerminal(){
		return this.serv.reportePorTerminal(this);
	}
	
	
	public ArrayList<POI> buscaPOI (String cadenadebusqueda){
		return this.getServidor().buscaPOI(cadenadebusqueda, this);
		}

	
}