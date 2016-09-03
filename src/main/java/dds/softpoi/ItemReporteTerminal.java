package dds.softpoi;

import java.util.ArrayList;

public class ItemReporteTerminal {

	private String nombreTerminal;
	private ArrayList<Integer> cantidadEncontrados = new ArrayList<Integer>();
	
	public String getNombreTerminal() {
		return nombreTerminal;
	}
	public void setNombreTerminal(String nombreTerminal) {
		this.nombreTerminal = nombreTerminal;
	}
	public ArrayList<Integer> getCantidadEncontrados() {
		return cantidadEncontrados;
	}
	public void setCantidadEncontrados(ArrayList<Integer> cantidadEncontrados) {
		this.cantidadEncontrados = cantidadEncontrados;
	}
	
	public void addCantidadEncontrados(int cantidad){
		cantidadEncontrados.add(cantidad);
	}
	public int cantidadResultadosTotales(){
		int aux = 0;
		for ( int i :this.cantidadEncontrados){
			aux = aux + i;
		}
		return aux;
	}
	
	
}
