package dds.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dds.softpoi.Seguridad;
import dds.softpoi.Usuario;

@ManagedBean(name="bnVistaLogin")
@ViewScoped
//extends VistaPadre 
public class VistaLogin extends VistaPadre implements Serializable {

	private static final long serialVersionUID = 6149074039987251332L;
	private String usuario;
	private String clave;
		
	// ***************************************************************************
	// Inicializador
	// ***************************************************************************
	
    @PostConstruct
    public void init() {
    	super.init();
    }
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
		
	public void setClave(String clave) {
		this.clave = clave;
	}	
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public String getUsuario() {
		return usuario;
	}
	
	public String getClave() {
		return clave;
	}
	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************
	
	public String validar(){
		// Aca va la validacion del usuario y contraseña
		// verificando si existe en el servidor
		String retorno = "";
		String msg = "";
		Severity severity = FacesMessage.SEVERITY_INFO;
		
		// Verificamos si el usuario tiene permiso para logearse
		super.setUnUsuarioLogueado(super.getObjSeguridad().login(usuario, clave));
		
		if (super.getUnUsuarioLogueado() != null) {
			msg = "Usuario Autorizado!";
			if ( super.getUnUsuarioLogueado().getClass().getName().substring(12).equals("DispositivoConsulta") ){
				// es una terminal
				retorno = "user"; //Navegation Rule
				

				
				
			}else{
				// es un administrador
				retorno = "admin"; //Navegation Rule
				
			}
		}else{
			msg = "Usuario No Autorizado!";
			severity = FacesMessage.SEVERITY_ERROR;
			
		}
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, msg, null));
		return retorno;
	}

}
