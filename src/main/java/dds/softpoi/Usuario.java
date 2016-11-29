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
	
	public void setId_usuario(int Id_usuario){
		this.id_usuario = Id_usuario;
	}
	
	public void setNombre(String unNombre){
		this.nombre = unNombre;
	}

	public void setEmail(String unEmail){
		this.email = unEmail;
	}

	public void setClave(String unaClave) {
		this.clave = unaClave;
	}
	
	public void setToken (String unToken) {
		this.token = unToken;
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
		return id_usuario;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getClave() {
		return clave;
	}
	
	public String getToken() {
		return token;
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