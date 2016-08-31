package dds.softpoi;

import java.util.ArrayList;

public class BuscadorConcreto implements BuscadorAbstracto {

	
	public ArrayList<POI> consultar(String query, Servidor unServidor){
		ArrayList<POI> poiEncontrados = new ArrayList<POI>();

		//Esta es la logica que le agrego para considerar los pois de origen de datos externos
		ArrayList<POI> todosLosPoi = new ArrayList<POI>();
		todosLosPoi.addAll(unServidor.getColPOIs());
		//Aca busco en los datos externosa ver que pois hay cargados
//		actualizoDesdeDatosExternos(query);
//		todoslospoi.addAll(getcolPOIsExternos());
		//luego hago el for sobre la conjuncion de los pois, los del sistema y los externos		
		for(POI unPoi : todosLosPoi){
			if (unPoi.getNombre().toUpperCase().indexOf(query.toUpperCase()) > -1){
				poiEncontrados.add(unPoi);
			}else{
				for(Servicio unservicio : unPoi.servicios){
					if (unservicio.getServicio().toUpperCase().indexOf(query.toUpperCase()) > -1){
						poiEncontrados.add(unPoi);
						break;
					}
				}
				if (unPoi.tipoPOI().equalsIgnoreCase("Comercio"))
					if (((Comercio)(unPoi)).getRubro().getRubro().toUpperCase().indexOf(query.toUpperCase()) > -1)
						poiEncontrados.add(unPoi);
			}
				
		}
		return poiEncontrados;
	}	
	
	
	
	
	
//	public void buscaPOIs(String cadenadebusqueda){
//		//timer.start()    metodo de Timer (Flor)
//		logicaDeBusqueda(cadenadebusqueda);
//		//timer.stop()     metodo de Timer (Flor)
//		//tiempodebusqueda = timer.obtenerTiempo()  metodo de Timer (Flor)
//		//historicoconsulta.guardar(parametros)     metodo historico (Rodo)
//	}
}
