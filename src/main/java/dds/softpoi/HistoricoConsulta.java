package dds.softpoi;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.List;
import java.util.Calendar;
import java.util.Collection;


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
		//lista donde voy a poner las fechas de cada consulta en formato de println
		ArrayList<String> listaConsulta = new ArrayList<String>();
		
		//voy guardando en la lista la fecha convertida de cada elementoConsulta
		for(ElementoDeConsulta elem: elementosDeConsulta) {
		listaConsulta.add(elem.fechaFormateada());
		//System.out.println(elem.fechaFormateada());
		}
		//obtengo una coleccion de fechas sin repetidos
		Set<String> fechaSinRep = new HashSet<String>();
		fechaSinRep.addAll(listaConsulta);
		
		//voy recorriendo la lista Sin repetidos y cuento las ocurrencias en la original
		System.out.println("Fecha\tbusquedas\n");
		for(String elem: fechaSinRep) {
			
			System.out.println(elem + "\t" + elementosDeConsulta.stream()
	                .filter( p -> p.fechaFormateada().equals(elem) ).collect(Collectors.toList()).size() + "\n");
					}
		
	}
	
	public void cantidadBusquedasPorTerminal(){
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
