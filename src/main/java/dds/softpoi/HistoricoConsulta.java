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
import java.util.List;
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
	
	public ArrayList<ElementoDeConsulta> coleccItemsHistorialBusqPantalla(String usuario, String fechaInicial, String fechaFinal){
		
		ArrayList<ElementoDeConsulta> colElemHist = new ArrayList<ElementoDeConsulta>();
		
		BasicDBObject dbQuery = new BasicDBObject();
		List<BasicDBObject> objConsulta = new ArrayList<BasicDBObject>();
		
		System.out.println("INICIO EL QUERY");
		
		
		if(usuario != null){
			String unUsuario = usuario.toUpperCase();
			objConsulta.add(new BasicDBObject("tipoUsuario", unUsuario));
		}
		
		if (fechaInicial != null){
			objConsulta.add(new BasicDBObject("fechaConsulta", new BasicDBObject("$gte", fechaFormato(fechaInicial) )));
		}
		
		if (fechaFinal != null){
			objConsulta.add(new BasicDBObject("fechaConsulta", new BasicDBObject("$lt",  fechaFormato(fechaFinal)  )));
		}
			
		dbQuery.put("$and", objConsulta);
		System.out.println("CONSULTA: " + dbQuery.toString());
		
		objMongo.crearConexion(objParam.getBaseMongoDB(), objParam.getTablaMongoHistoricoConsultas());
		DBCursor cursor = null;
		cursor = objMongo.buscarDatos(dbQuery);

		BasicDBObject unDBObj;
		BasicDBList lstPOI;
		BasicDBObject unDBObjPOI;
		ArrayList<POI> colPOIHist;
		ElementoDeConsulta unElemConsulta;
		
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
			colElemHist.add(unElemConsulta);			
			
		}
		objMongo.cerrarConexion();

		return colElemHist;
	}

	/*
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
	 */
	
	
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
	
	public String fechaFormato(String fechastr){
		//String fechastr = "Wed Dec 21 00:00:00 ART 2016"; 
		String[] meses = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		int anio = Integer.parseInt(fechastr.substring(24, 28)) - 1900;
		int dia  = Integer.parseInt(fechastr.substring(8, 10));
		int mes = -1;
		
		for (int i = 0; i < meses.length; i++) {
			if (meses[i].equalsIgnoreCase(fechastr.substring(4, 7))) {
				mes = i;   //va a devolver uno menos porque asi lo toma la funcion date
			}
		}
	
		@SuppressWarnings("deprecation")
		Date fecha = new Date(anio,mes,dia);
		//DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
		//df.setTimeZone(TimeZone.getTimeZone("UTC"));

		return df.format(fecha);
	}	
	
}
