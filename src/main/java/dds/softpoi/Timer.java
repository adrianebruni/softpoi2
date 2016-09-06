package dds.softpoi;

import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class Timer implements BuscadorAbstracto {

	private double tiempoInicio, tiempoFinal;
	private BuscadorConcreto unBuscadorConcreto = new BuscadorConcreto();
	
	private void iniciarContador() {
		tiempoInicio = System.nanoTime();
	}
	
	private void finalizarContador(){
		tiempoFinal = System.nanoTime();
	}
	
	public double duracionConsulta(){
		return (tiempoFinal - tiempoInicio) / 1000000000;
	}
	
	public ArrayList<POI> consultar(String query, Servidor unServidor, Usuario unUsuario){
		ArrayList<POI> poisEncontrados;
		
		this.iniciarContador();
		poisEncontrados =  unBuscadorConcreto.consultar(query, unServidor);
		this.finalizarContador();
		
		if (this.duracionConsulta() > unServidor.getParametros().getDemoraConsulta() ){
			this.enviarMail(unServidor, unUsuario);
		}
		return poisEncontrados;
	}
	
	public void enviarMail(Servidor unServidor, Usuario unUsuario){
		

		Mail unMail = new Mail(unServidor.getParametros().getEmailCuenta(), unServidor.getParametros().getEmailClave());
		
		try {
			unMail.enviarMail(unUsuario.getEmail());
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
