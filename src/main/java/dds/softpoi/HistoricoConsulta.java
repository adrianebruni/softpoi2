package dds.softpoi;

import java.util.ArrayList;
import java.util.Date;


public class HistoricoConsulta implements BuscadorAbstracto{


    protected ArrayList<ElementoDeConsulta> elementosDeConsulta = new ArrayList<ElementoDeConsulta>();
    

	public void setelementosDeConsulta(ElementoDeConsulta unElementoDeConsulta) {
		this.elementosDeConsulta.add(unElementoDeConsulta);
	}
    
	
	public ArrayList<POI> consultar(String query, Servidor unServidor, Usuario unUsuario){
		Timer unTimer = new Timer();
		ArrayList<POI> poisEncontrados =  unTimer.consultar(query,unServidor);
		ElementoDeConsulta unaConsulta = new ElementoDeConsulta(new Date(), query, unTimer.duracionConsulta(), unUsuario.getNombre() , poisEncontrados.size());
		elementosDeConsulta.add(unaConsulta);
		return poisEncontrados;
		
	}
	
	public void fraseMasBuscada(){
		System.out.println("Banco Frances   35 resultados  tiempo");
	}
	
	public void cantidadBusquedasPorFecha(){
		System.out.println("Usuario: Terminal Abasto Fecha: 2016-08-16 CantidadResult: 15");
	}
	
	public void cantidadBusquedasPorTerminal(){
		
	}
	
}
