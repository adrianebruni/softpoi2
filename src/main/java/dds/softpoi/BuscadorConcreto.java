package dds.softpoi;

import java.util.ArrayList;

public class BuscadorConcreto implements BuscadorAbstracto {

	
	public ArrayList<POI> consultar(String query, Servidor unServidor){
		ArrayList<POI> poiencontrados = new ArrayList<POI>();

		//Esta es la logica que le agrego para considerar los pois de origen de datos externos
		ArrayList<POI> todoslospoi = new ArrayList<POI>();
		todoslospoi.addAll(unServidor.getcolPOIs());
		//Aca busco en los datos externosa ver que pois hay cargados
//		actualizoDesdeDatosExternos(query);
//		todoslospoi.addAll(getcolPOIsExternos());
		//luego hago el for sobre la conjuncion de los pois, los del sistema y los externos		
		for(POI unpoi : todoslospoi){
			if (unpoi.getNombre().toUpperCase().indexOf(query.toUpperCase()) > -1){
				poiencontrados.add(unpoi);
			}else{
				for(Servicio unservicio : unpoi.servicios){
					if (unservicio.getServicio().toUpperCase().indexOf(query.toUpperCase()) > -1){
						poiencontrados.add(unpoi);
						break;
					}
				}
				if (unpoi.tipoPOI().equalsIgnoreCase("Comercio"))
					if (((Comercio)(unpoi)).getRubro().getRubro().toUpperCase().indexOf(query.toUpperCase()) > -1)
						poiencontrados.add(unpoi);
			}
				
		}
		return poiencontrados;
	}	
	
	
	
	
	
//	public void buscaPOIs(String cadenadebusqueda){
//		//timer.start()    metodo de Timer (Flor)
//		logicaDeBusqueda(cadenadebusqueda);
//		//timer.stop()     metodo de Timer (Flor)
//		//tiempodebusqueda = timer.obtenerTiempo()  metodo de Timer (Flor)
//		//historicoconsulta.guardar(parametros)     metodo historico (Rodo)
//	}
}
