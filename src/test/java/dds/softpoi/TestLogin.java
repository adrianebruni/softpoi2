package dds.softpoi;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestLogin {

	@Test
	public void test() {
		
		// Instanciamos un servidor
		Servidor unServidor = new Servidor();
			
		// Creamos dos administradores
		Administrador admin1 = new Administrador();
		admin1.setNombre("admin1");
		admin1.setEmail("adrianebruni@hotmail.com");
		admin1.setPass("password1");
		
		Administrador admin2 = new Administrador();
		admin2.setNombre("admin2");
		admin2.setEmail("aleazzi@gmail.com");
		admin2.setPass("password2");
		
		// Agregamos a los administradores dentro del servidor
		unServidor.addAdmin(admin1);
		unServidor.addAdmin(admin2);

		// Creamos a un usuario que no es administrador
		Administrador adminHack = new Administrador();
		adminHack.setNombre("adminHack");
		adminHack.setEmail("rperal@gmail.com");
		adminHack.setPass("password3");
		
		// Verificamos
		assertEquals("El usuario 'adminHack' no cuenta con permisos de administrador", null, unServidor.login(adminHack));	
		assertEquals("El usuario 'admin1' cuenta con permisos de administrador", admin1.getToken(), unServidor.login(admin1));
		assertEquals("El usuario 'admin1' cuenta con token: " + admin1.getToken() , admin1.getToken(), unServidor.login(admin1));
		
	}

}
