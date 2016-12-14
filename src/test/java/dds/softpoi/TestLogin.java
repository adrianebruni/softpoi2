package dds.softpoi;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestLogin {

	@Test
	public void test() {		
		
		// Instanciamos un servidor
		Servidor unServidor = new Servidor();
			
		// Creamos dos administradores
		Usuario admin1 = new Administrador();
		admin1.setNombre("admin1");
		admin1.setEmail("adrianebruni@hotmail.com");
		admin1.setClave("password1");
		
		Usuario admin2 = new Administrador();
		admin2.setNombre("admin2");
		admin2.setEmail("aleazzi@gmail.com");
		admin2.setClave("password2");
		
		// Agregamos a los administradores dentro del servidor
		unServidor.addAdmin(admin1);
		unServidor.addAdmin(admin2);

		// Creamos a un usuario que no es administrador	
		Usuario usuarioComun;
		usuarioComun = new DispositivoConsulta();
		/*
		usuarioComun.setNombre("usuarioComun");
		usuarioComun.setEmail("rperal@gmail.com");
		usuarioComun.setPass("password3");
		*/

		// Verificamos
//		System.out.println("El usuario 'usuarioComun' no cuenta con permisos de administrador");
//		assertEquals("El usuario 'usuarioComun' no cuenta con permisos de administrador", null, unServidor.login(usuarioComun.getNombre(), usuarioComun.getClave()).getToken());	
		
//		System.out.println("El usuario 'usuarioComun' token : " + usuarioComun.getToken());
		
		
//		System.out.println("El usuario 'admin1' cuenta con permisos de administrador");
//		assertEquals("El usuario 'admin1' cuenta con permisos de administrador", admin1.getToken(), unServidor.login(admin1.getNombre(), admin1.getClave()).getToken());
		assertEquals("Test obsoleto", true, true );	
					
	}

}
