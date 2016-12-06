package dds.softpoi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import dds.mongodb.MongoDB;
import dds.mongodb.MongoDBConnection;

import java.util.HashSet;
import java.util.Locale;

public class HistoricoConsulta implements BuscadorAbstracto{


    protected ArrayList<ElementoDeConsulta> elementosDeConsulta = new ArrayList<ElementoDeConsulta>();
	private Parametros objParam = new Parametros();
	private MongoDB objMongo = new MongoDB();

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
		ElementoDeConsulta unaConsulta = new ElementoDeConsulta(new Date(), query.toUpperCase(), unTimer.duracionConsulta(), unUsuario.getNombre().toUpperCase() , poisEncontrados.size(), poisEncontrados);
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

		ArrayList<ElementoDeConsulta> colElemHist = new ArrayList<ElementoDeConsulta>();
		
		if(unUsuario != null){
			if (fechaInicial != null){
				if (fechaFinal != null){
					// Buscamos por nombre, fecha ini, fecha fin
				}else{
					// buscamos por nombre y fecha ini
				}
			}else{
				if (fechaFinal != null){
					// Buscamos por nombre y fecha fin
				}else{
					// buscamos por nombre
					colElemHist.addAll(busquedaHistoricoPorUsuario(unUsuario));
				}
			}
		}else{
			if (fechaInicial != null){
				if (fechaFinal != null){
					// Buscamos por fecha ini, fecha fin
				}else{
					// buscamos por fecha ini
					colElemHist.addAll(busquedaHistoricoPorFechaInicio(fechaInicial));
				}
			}else{
				if (fechaFinal != null){
					// Buscamos por fecha fin
					
				}
			}
			
			
		}

		return colElemHist;
	}

	public ArrayList<ElementoDeConsulta> busquedaHistoricoPorUsuario(String nombreUsuario){
		
		ElementoDeConsulta unElemConsulta;
		ArrayList<ElementoDeConsulta> colElemHistPorUsuario = new ArrayList<ElementoDeConsulta>();
		
		objMongo.crearConexion(objParam.getBaseMongoDB(), objParam.getTablaMongoHistoricoConsultas());
		DBCursor cursor = null;
		cursor = objMongo.buscarDato("tipoUsuario", nombreUsuario, true);	
		
		BasicDBObject unDBObj;
		BasicDBList lstPOI;
		BasicDBObject unDBObjPOI;
		ArrayList<POI> colPOIHist;
		
		while(cursor.hasNext()){

			unDBObj = (BasicDBObject) cursor.next();
			
			unElemConsulta = new ElementoDeConsulta();
			unElemConsulta.setFechaConsulta(unDBObj.getDate("fechaConsulta"));	
			unElemConsulta.setConsultaUsuario(unDBObj.getString("consultaUsuario"));
			unElemConsulta.setTiempoRespuesta(unDBObj.getLong("tiempoRespuesta"));
			unElemConsulta.setTipoUsuario(unDBObj.getString("tipoUsuario"));
			unElemConsulta.setTotalResultados(unDBObj.getInt("totalResultados"));
			
			lstPOI = (BasicDBList) unDBObj.get("colPOIs");
			System.out.println(lstPOI);
			colPOIHist = new ArrayList<POI>();
			
			for (Object objPOI : lstPOI) {
				System.out.println(objPOI);
				
				try {
					unDBObjPOI = (BasicDBObject) objPOI;
					
					String TipoPoi = unDBObjPOI.get("className").toString().substring(12);
					
					switch (TipoPoi) {
						case "Banco":
							Banco unBanco = new Banco();
							unBanco.setAltura(unDBObjPOI.getInt("altura"));
							unBanco.setPiso(unDBObjPOI.getInt("piso"));
							unBanco.setGerente(unDBObjPOI.getString("gerente"));
							unBanco.setZona(unDBObjPOI.getString("zona"));
							unBanco.setIdpoi(unDBObjPOI.getInt("idpoi"));
							unBanco.setNombre(unDBObjPOI.getString("nombre"));
							unBanco.setLatitud(unDBObjPOI.getDouble("latitud"));
							unBanco.setLongitud(unDBObjPOI.getDouble("longitud"));
							colPOIHist.add(unBanco);
							break;
							
						case "CGP":
							CGP unCGP = new CGP();	
							System.out.println("PENDIENTE CGP: " + this.getClass().getSimpleName() + " --> busquedaHistoricoPorUsuario() ");
							break;

						case "Comercio":
							Comercio unComercio = new Comercio();
							System.out.println("PENDIENTE Comercio: " + this.getClass().getSimpleName() + " --> busquedaHistoricoPorUsuario() ");
							break;	
							
						case "ParadaColectivo":
							ParadaColectivo unaParadaColectivo = new ParadaColectivo();
							System.out.println("PENDIENTE ParadaColectivo: " + this.getClass().getSimpleName() + " --> busquedaHistoricoPorUsuario() ");
							break;							
					}
					
					
				} catch (Exception e) {
					System.out.println("ERROR: " + this.getClass().getSimpleName() + " --> busquedaHistoricoPorUsuario() ");
					e.printStackTrace();
				}
				
			}
			
			// Agrego los pois a la coleccion del elementoConsulta
			unElemConsulta.setColPOIs(colPOIHist);
			
			// Agrego el elementoConsulta a la coleccion para retornarla
			colElemHistPorUsuario.add(unElemConsulta);			
			
		}
		objMongo.cerrarConexion();
		return colElemHistPorUsuario;
	}

	
	
	public ArrayList<ElementoDeConsulta> busquedaHistoricoPorFechaInicio(String FechaInicio){
		
		ElementoDeConsulta unElemConsulta;
		ArrayList<ElementoDeConsulta> colElemHistPorUsuario = new ArrayList<ElementoDeConsulta>();
		
		
		DBCollection dbCol;
		
		objMongo.crearConexion(objParam.getBaseMongoDB(), objParam.getTablaMongoHistoricoConsultas());
		dbCol = objMongo.dameColeccion();
		
		System.out.println(fechaStringAdate(FechaInicio));
		
		// Calendar objCalendar;
		// objCalendar.
		
		Date fecha = new Date();
		
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		System.out.println("FECHA: " + df.format(fecha));
		
		
		
		
		@SuppressWarnings("deprecation")
		BasicDBObject query = new BasicDBObject("fechaConsulta", //
							  new BasicDBObject("$gte", new Date("11/01/2016")).append("$lt", new Date("12/05/2016"))	);
		
		//dbCol.findOne("{'fechaConsulta' : { $gte : new ISODate('2016-12-04T20:21:37')}}");
		System.out.println(query);
		
		DBCursor unCursor;
		
		unCursor = dbCol.find(query);
		
		
		//DBCursor cursor = null;
		//cursor = objMongo.buscarDato("fechaConsulta", "", false);	
		
		BasicDBObject unDBObj;
		BasicDBList lstPOI;
		BasicDBObject unDBObjPOI;
		ArrayList<POI> colPOIHist;
		
		while(unCursor.hasNext()){
			System.out.println(unCursor.next());
		}
		
		return colElemHistPorUsuario;
		
		
		
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
