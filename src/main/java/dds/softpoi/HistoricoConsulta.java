package dds.softpoi;

import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;


public class HistoricoConsulta implements BuscadorAbstracto{


    protected ArrayList<ElementoDeConsulta> elementosDeConsulta = new ArrayList<ElementoDeConsulta>();
    

	public void setelementosDeConsulta(ElementoDeConsulta unElementoDeConsulta) {
		this.elementosDeConsulta.add(unElementoDeConsulta);
	}
    
	
	public ArrayList<POI> consultar(String query, Servidor unServidor, Usuario unUsuario){
		Timer unTimer = new Timer();
		ArrayList<POI> poisEncontrados =  unTimer.consultar(query,unServidor, unUsuario);
		ElementoDeConsulta unaConsulta = new ElementoDeConsulta(new Date(), query, unTimer.duracionConsulta(), unUsuario.getNombre() , poisEncontrados.size());
		elementosDeConsulta.add(unaConsulta);
		return poisEncontrados;
		
	}
	
	public void fraseMasBuscada(){
		System.out.println("Banco Frances   35 resultados  tiempo");
	}
	
	public void cantidadBusquedasPorFecha(Usuario unUsuario){
		Collections.sort(elementosDeConsulta, ElementoDeConsulta.Comparar_Por_Fecha);
		for(ElementoDeConsulta elem: elementosDeConsulta) {
			System.out.println("Fecha: " + elem.getFechaConsulta());
			System.out.println("String:" + elem.getConsultaUsuario());
			System.out.println("Tiempo: " + elem.getTiempoRespuesta());
			System.out.println("Usuario: " + elem.getTipoUsuario());
			System.out.println("Rtados: " + elem.getTotalResultados());
		}
	}
	
	public void cantidadBusquedasPorTerminal(Usuario unUsuario){
		Collections.sort(elementosDeConsulta, ElementoDeConsulta.Comparar_Por_Usuario);
		String usuarioAUX = "";
		int cant = 0;
		ArrayList<String> usuarios = new ArrayList<String>();
		ArrayList<Integer> totalresult = new ArrayList<Integer>();
		
		System.out.println("Parciales por terminal");
		System.out.println(" ");
		
		for(ElementoDeConsulta elem: elementosDeConsulta){
			if ( !usuarioAUX.equalsIgnoreCase(elem.tipoUsuario)) {
				if (!usuarioAUX.isEmpty()){
					usuarios.add(usuarioAUX);
					totalresult.add(cant);
				}
				usuarioAUX = elem.tipoUsuario;
				cant = 0;
				System.out.println("Usuario: " + usuarioAUX);
				System.out.println(" ");
				System.out.println("Resultados Parciales");
			}
			cant = cant + elem.totalResultados;
			System.out.println(elem.totalResultados);
		}
		usuarios.add(usuarioAUX);
		totalresult.add(cant);
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Totales por Usuario");
		for (int i=0;i<=usuarios.size();i++){
			System.out.println(usuarios.get(i) + " ---> " + totalresult.get(i));
		}
	}
	
}
