package dds.softpoi;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Test;

public class TestMail {

	@Test
	public void testEnviarMail() {
		
		System.out.println("Iniciando TestMail");
		
		Servidor unServidor = new Servidor();
		Parametros losParametros = unServidor.getParametros();
		Mail unMail = new Mail(losParametros.getEmailCuenta(), losParametros.getEmailClave(), losParametros.getEmailPuerto(), losParametros.getEmailAutenticacion(),losParametros.getEmailCifradoTLS(), losParametros.getEmailMetodoEnvio(),losParametros.getEmailHostEnvio());
			
		try {
			unMail.enviarMail("adrianebruni@hotmail.com","Alguna consulta",3.14);
		} catch (AddressException e) {

			e.printStackTrace();
		} catch (MessagingException e) {

			e.printStackTrace();
		}
		
		System.out.println("TestMail Finalizado!");
	}

}
