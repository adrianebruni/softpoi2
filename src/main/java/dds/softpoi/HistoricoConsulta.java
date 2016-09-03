package dds.softpoi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.HashSet;
//import java.util.List;
//import java.util.Calendar;
//import java.util.Collection;


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
	
	public ArrayList<ItemReporteFecha> reportePorFecha(Usuario unUsuario){
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
		

		// la lista tiene que ser ordenada
		ArrayList<ItemReporteFecha> lstItem = new ArrayList<ItemReporteFecha>();
		
		//voy recorriendo la lista Sin repetidos y cuento las ocurrencias en la original
		//System.out.println("Fecha\tbusquedas\n");
		for(String unaFecha: fechaSinRep) {
			
			ItemReporteFecha unItem = new ItemReporteFecha();
			unItem.setFecha(unaFecha);
			unItem.setCantidad(elementosDeConsulta.stream()
	                .filter( p -> p.fechaFormateada().equals(unaFecha) ).collect(Collectors.toList()).size());
			
			lstItem.add(unItem);
		}	
		
		return lstItem;
		/*
		System.out.println(unaFecha + "\t" + elementosDeConsulta.stream()
                .filter( p -> p.fechaFormateada().equals(unaFecha) ).collect(Collectors.toList()).size() + "\n");
				}
		*/
		
	}
	
	public ArrayList<ItemReporteTerminal> cantidadBusquedasPorTerminal(){
		ArrayList<ItemReporteTerminal> colDeItems = new ArrayList<ItemReporteTerminal>();
		
		//Genero coleccion de terminales, y las cargo del historico (van a estar repetidas)
		ArrayList<String> terminales = new ArrayList<String>();
		for(ElementoDeConsulta elem: elementosDeConsulta){
			terminales.add(elem.getTipoUsuario());
		}
		
		//filtro terminales, para que no haya repetidas
		Set<String> terminalesSinRepetir = new HashSet<String>();
		terminalesSinRepetir.addAll(terminales);
		
		for(String term: terminalesSinRepetir){
			ItemReporteTerminal item = new ItemReporteTerminal();
			item.setNombreTerminal(term);
			ArrayList<ElementoDeConsulta> colElementosDeUnaTerminal =  (ArrayList<ElementoDeConsulta>) elementosDeConsulta.stream().filter(obj -> obj.getTipoUsuario().equals(term)).collect(Collectors.toList());
			for(ElementoDeConsulta unElemento : colElementosDeUnaTerminal){
				item.addCantidadEncontrados(unElemento.getTotalResultados());
			}
			colDeItems.add(item);	
		}
		return colDeItems;
	}
	
}
