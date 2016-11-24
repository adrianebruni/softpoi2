package dds.softpoi;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import dds.mongodb.MongoDB;
import dds.mongodb.MongoDBConnection;

import java.util.HashSet;

public class HistoricoConsulta implements BuscadorAbstracto{


    protected ArrayList<ElementoDeConsulta> elementosDeConsulta = new ArrayList<ElementoDeConsulta>();
    

	public void setelementosDeConsulta(ElementoDeConsulta unElementoDeConsulta) {
		this.elementosDeConsulta.add(unElementoDeConsulta);
	}
    
	
	public ArrayList<POI> consultar(String query, Usuario unUsuario){
		/*Timer unTimer = new Timer();
		ArrayList<POI> poisEncontrados =  unTimer.consultar(query,unServidor, unUsuario);
		ElementoDeConsulta unaConsulta = new ElementoDeConsulta(new Date(), query, unTimer.duracionConsulta(), unUsuario.getNombre() , poisEncontrados.size());
		elementosDeConsulta.add(unaConsulta);
		return poisEncontrados;*/
		
		Timer unTimer = new Timer();
		ArrayList<POI> poisEncontrados =  unTimer.consultar(query, unUsuario.getServidor(), unUsuario);
		ElementoDeConsulta unaConsulta = new ElementoDeConsulta(new Date(), query, unTimer.duracionConsulta(), unUsuario.getNombre() , poisEncontrados.size(), poisEncontrados);
		elementosDeConsulta.add(unaConsulta);
		MongoDBConnection.persistirElementoDeConsulta(unaConsulta);
		
		//try {
		//	// Insertamos en la base de datos (MySQL)
		//	unUsuario.getServidor().getRepositorio().elementosDeConsulta().persistir(unaConsulta);
		//} catch (Exception e) {
		//	System.out.println("Error al persistir el ElementoDeConsulta");
		//}
		
		//try {
		//	// Insertamos en la base de datos (MongoDB)
		//	MongoDB miMongo = new MongoDB();
		//	miMongo.crearConexion("db_pois", "pois_consultas");
		//	miMongo.insertarDato(unaConsulta);
		//	
		//	// Buscamos un valor insertado
		//	miMongo.buscarDato("consultaUsuario", "banco");
		//	
		//	
		//} catch (Exception e) {
		//	System.out.println("Error al insertar el ElementoDeConsulta");
		//}
		
		
			
		return poisEncontrados;
		
	}
	
	public ArrayList<ItemReporteFecha> reportePorFecha(Usuario unUsuario){
		//lista donde voy a poner las fechas de cada consulta en formato de println
		ArrayList<String> listaConsulta = new ArrayList<String>();
		
		//voy guardando en la lista la fecha convertida de cada elementoConsulta
		for(ElementoDeConsulta elem: elementosDeConsulta) {
			listaConsulta.add(elem.fechaFormateada("yyyy/MM/dd"));
		}
		//obtengo una coleccion de fechas sin repetidos
		Set<String> fechaSinRep = new HashSet<String>();
		fechaSinRep.addAll(listaConsulta);

		// la lista tiene que ser ordenada
		ArrayList<ItemReporteFecha> lstItem = new ArrayList<ItemReporteFecha>();
		
		//voy recorriendo la lista Sin repetidos y cuento las ocurrencias en la original;
		for(String unaFecha: fechaSinRep) {
			
			ItemReporteFecha unItem = new ItemReporteFecha();
			unItem.setFecha(unaFecha);
			unItem.setCantidad(elementosDeConsulta.stream()
	                .filter( p -> p.fechaFormateada("yyyy/MM/dd").equals(unaFecha) ).collect(Collectors.toList()).size());
			
			lstItem.add(unItem);
		}	
		
		Collections.sort(lstItem, ItemReporteFecha.Comparar_Por_Fecha);
		
		
		
		return lstItem;

		
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
	
	public ArrayList<ElementoDeConsulta> coleccItemsHistorialBusqPantalla(String unUsuario, String fechaInicial, String fechaFinal){
		ArrayList<ElementoDeConsulta> colItemBusqHist = new ArrayList<ElementoDeConsulta>();	
		ArrayList<ElementoDeConsulta> colItemBusqHist2 = new ArrayList<ElementoDeConsulta>();
		ArrayList<ElementoDeConsulta> colItemBusqHist3 = new ArrayList<ElementoDeConsulta>();
		
		//voy guardando en la lista los elementos segun filtro de usuario
		if(unUsuario == null){
			colItemBusqHist.addAll(elementosDeConsulta);
		}else{
			for(ElementoDeConsulta elem: elementosDeConsulta) {
				if(unUsuario == elem.getTipoUsuario()){
					colItemBusqHist.add(elem);
				}
			}
		}
		
		//voy quitando en la lista los elementos segun filtro fecha inicial
		if(fechaInicial == null){
			colItemBusqHist2.addAll(colItemBusqHist);
		}else{
			for(ElementoDeConsulta elem: colItemBusqHist) {
				if(fechaStringAdate(fechaInicial).compareTo(elem.getFechaConsulta())<=0){
					colItemBusqHist2.add(elem);
				}
			}
		}
	
		//voy guardando en la lista los elementos segun filtro fecha final
		if(fechaFinal == null){
			colItemBusqHist3.addAll(colItemBusqHist2);
		}else{
			for(ElementoDeConsulta elem: colItemBusqHist2) {
				if(fechaStringAdate(fechaFinal).compareTo(elem.getFechaConsulta())>=0){
					colItemBusqHist3.add(elem);
				}
			}
		}	

		return colItemBusqHist3;
	}


	public Date fechaStringAdate(String unaFecha){
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date fecha = null;
		try {
			fecha = format.parse(unaFecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fecha;
	}
	
}
