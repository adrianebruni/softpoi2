package dds.bean;

//import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

//@SuppressWarnings("serial")
@ManagedBean(name="bnVistaLogin")
@SessionScoped
public class VistaLogin extends VistaPadre  {

	//private static final long serialVersionUID = 6149074039987251332L;
	private String usuario;
	private String clave;
		
	// ***************************************************************************
	// Inicializador
	// ***************************************************************************
	
	public VistaLogin(){}
	
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
			
			// Le seteamos al usuario logeado el servidor
			super.getUnUsuarioLogueado().setServidor(super.getServidor());
			
			if ( super.getUnUsuarioLogueado().getClass().getSimpleName().equals("DispositivoConsulta") ){
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
