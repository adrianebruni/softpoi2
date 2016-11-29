package dds.softpoi;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Mail {

	private Properties mailServerProperties;
	private Session getMailSession;
	private MimeMessage generateMailMessage;
	
	private String emailCuenta;
	private String emailClave;
	private String emailPuerto;
	private String emailAutenticacion;
	private String emailCifradoTLS;
	private String emailMetodoEnvio;
	private String emailHostEnvio;
	
	
	// ***************************************************************************
	// Constructor
	// ***************************************************************************
	public Mail(String emailCuenta, String emailClave, String emailPuerto, String emailAutenticacion, String emailCifradoTLS, String emailMetodoEnvio, String emailHostEnvio) {
		this.emailCuenta = emailCuenta;
		this.emailClave = emailClave;	
		this.emailPuerto = emailPuerto;
		this.emailAutenticacion = emailAutenticacion;
		this.emailCifradoTLS = emailCifradoTLS;
		this.emailMetodoEnvio = emailMetodoEnvio;
		this.emailHostEnvio = emailHostEnvio;
	}

/*
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	public void setEmailCuenta(String emailCuenta) {
		this.emailCuenta = emailCuenta;
	}
	
	public void setEmailClave(String emailClave) {
		this.emailClave = emailClave;
	}
			
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public String getEmailCuenta() {
		return emailCuenta;
	}
	
	public String getEmailClave() {
		return emailClave;
	}
*/	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************
	
	public void enviarMail(String destinatario, String query, double duracionConsulta) throws AddressException, MessagingException {
		try{
			// 1er PASO
			//System.out.println("1er PASO ===> Configurando las propiedades del servidor de correo..");
			if ( (emailCuenta == "") || (emailClave == "")){
				System.out.println("1er PASO ===> Configurando las propiedades del servidor de correo.. Falta configurar cuenta de email y clave!");
			}else{
				
				System.out.println("Simulamos que el envio de correo se realizo correctamente");
				System.out.println("Para que funcione realmente, hay que descomentar las lineas de abajo --> Mail.java | enviarMail()");
				
				/*
				mailServerProperties = System.getProperties();
				mailServerProperties.put("mail.smtp.port", this.emailPuerto);
				mailServerProperties.put("mail.smtp.auth", this.emailAutenticacion);
				mailServerProperties.put("mail.smtp.starttls.enable", this.emailCifradoTLS);
				//System.out.println("1er PASO ===> Configurando las propiedades del servidor de correo.. OK!");
		 
				// 2do PASO
				//System.out.println("2do PASO ===> Creando una Sesion de correo..");
				getMailSession = Session.getDefaultInstance(mailServerProperties, null);
				generateMailMessage = new MimeMessage(getMailSession);
				//System.out.println("2do PASO ===> Creando una Sesion de correo.. OK!");
				
				// 3er PASO
				//System.out.println("3er PASO ===> Configurando el correo [destinatario, asunto, mensaje]..");
				
				// Destinatario
				generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
				//generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("test2@crunchify.com"));
				
				// Asunto
				generateMailMessage.setSubject("Alerta SoftPOI");
				
				// Mensaje
				String emailBody = "Se ha detectado que la consulta <b>" + query + "</b> demoró <b>" + duracionConsulta + "</b><br><br> Gracias, <br>SoftPOI";
				generateMailMessage.setContent(emailBody, "text/html");
				
				//System.out.println("3er PASO ===> Configurando el correo [destinatario, asunto, mensaje].. OK!");
							
		 
				// 4to PASO
				//System.out.println("4to PASO ===> Enviando Correo..");
				Transport transport = getMailSession.getTransport(this.emailMetodoEnvio);
				transport.connect(this.emailHostEnvio, this.emailCuenta, this.emailClave);
				transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
				transport.close();
				//System.out.println("4to PASO ===> Enviando Correo.. OK!");
				 
				 */
			}
		}catch (Exception e) {
			System.out.println("falló mail");
		}
	}
}
