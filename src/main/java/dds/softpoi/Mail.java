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

	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
	
	String emailCuenta; //softpoi.notificaciones@gmail.com
	String emailClave; //pN2f3WOV
	
	// ***************************************************************************
	// Constructor
	// ***************************************************************************
	public Mail(String emailCuenta, String emailClave) {
		this.emailCuenta = emailCuenta;
		this.emailClave = emailClave;	
	}
		
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
	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************
	
	public void enviarMail(String destinatario) throws AddressException, MessagingException {
 
		// 1er PASO
		System.out.println("1er PASO ===> Configurando las propiedades del servidor de correo..");
		if ( (emailCuenta == "") || (emailClave == "")){
			System.out.println("1er PASO ===> Configurando las propiedades del servidor de correo.. Falta configurar cuenta de email y clave!");
		}else{
		
			mailServerProperties = System.getProperties();
			mailServerProperties.put("mail.smtp.port", "587");
			mailServerProperties.put("mail.smtp.auth", "true");
			mailServerProperties.put("mail.smtp.starttls.enable", "true");
			System.out.println("1er PASO ===> Configurando las propiedades del servidor de correo.. OK!");
	 
			// 2do PASO
			System.out.println("2do PASO ===> Creando una Sesion de correo..");
			getMailSession = Session.getDefaultInstance(mailServerProperties, null);
			generateMailMessage = new MimeMessage(getMailSession);
			System.out.println("2do PASO ===> Creando una Sesion de correo.. OK!");
			
			// 3er PASO
			System.out.println("3er PASO ===> Configurando el correo [destinatario, asunto, mensaje]..");
			
			// Destinatario
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			//generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("test2@crunchify.com"));
			
			// Asunto
			generateMailMessage.setSubject("Alerta SoftPOI");
			
			// Mensaje
			String emailBody = "Mensaje de prueba de <b>SOFTPOI</b><br><br> Gracias, <br>Administrador";
			generateMailMessage.setContent(emailBody, "text/html");
			
			System.out.println("3er PASO ===> Configurando el correo [destinatario, asunto, mensaje].. OK!");
						
	 
			// 4to PASO
			System.out.println("4to PASO ===> Enviando Correo..");
			Transport transport = getMailSession.getTransport("smtp");
			transport.connect("smtp.gmail.com", emailCuenta, emailClave);
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			transport.close();
			System.out.println("4to PASO ===> Enviando Correo.. OK!");
		}
	}
	
}
