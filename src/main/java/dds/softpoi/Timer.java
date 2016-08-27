package dds.softpoi;

import java.util.ArrayList;

public class Timer implements BuscadorAbstracto {

	double tiempoinicio,tiempofinal;
	int duracion;
	
	public void iniciarConsulta() {
		tiempoinicio = System.nanoTime();
	}
	
	public void finalizarConsulta(){
		tiempofinal = System.nanoTime();
	}
	public double duracionConsulta(){
		return (tiempofinal - tiempoinicio) / 1000000000;
	}
	
	public ArrayList<POI> consultar(String query, Servidor unServidor){
		ArrayList<POI> poisencontrados;
		BuscadorConcreto unBuscadorConcreto = new BuscadorConcreto();
		this.iniciarConsulta();
		poisencontrados =  unBuscadorConcreto.consultar(query, unServidor);
		this.finalizarConsulta();
		return poisencontrados;
	}
	
	public void enviarMail(){
		//Mail unMail = new Mail();
		//unMAil.enviarMail();
		System.out.println("Mail enviado");
	}
	
}
