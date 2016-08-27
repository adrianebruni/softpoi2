package dds.softpoi;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Test;

public class TestMail {

	@Test
	public void testEnviarMail() {
		
		System.out.println("Iniciando TestMail");
		
		Mail unMail = new Mail("softpoi.notificaciones@gmail.com","pN2f3WOV");
		
		try {
			unMail.enviarMail("adrianebruni@hotmail.com");
		} catch (AddressException e) {

			e.printStackTrace();
		} catch (MessagingException e) {

			e.printStackTrace();
		}
		
		System.out.println("TestMail Finalizado!");
	}

}
