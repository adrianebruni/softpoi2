package dds.softpoi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


//import org.hibernate.mapping.Set;

public class Auditoria {

// ***************************************************************************
// Metodos
// ***************************************************************************
	
	private BuscadorConcreto unBuscadorConcreto = new BuscadorConcreto();
	
	//un metodo por cada accion a auditar
	public ArrayList<POI> auditarBusquedaPOI(String query, Usuario unUsuario){
		
		if(unUsuario.getFlagAuditoriaBusqueda()){			
			return unUsuario.getServidor().getHistoricoConsulta().consultar(query, unUsuario);
		}else{
			return unBuscadorConcreto.consultar(query, unUsuario.getServidor());
		}
	
	
	}
	//para las repeticiones usams sobrecarga
	public ArrayList<POI> auditarBusquedaPOI(ArrayList<String> query, Usuario unUsuario){
		
		if(unUsuario.getFlagAuditoriaBusqueda()){			
			return unUsuario.getServidor().getHistoricoConsulta().consultar(query, unUsuario);
		}else{
			ArrayList<POI> colAux = new ArrayList<POI>();
			for(String unQuery : query){
				colAux.addAll(unBuscadorConcreto.consultar(unQuery, unUsuario.getServidor()));
			}
			Set<POI> colPOIs = new HashSet<POI>();
//			return unBuscadorConcreto.consultar(query, unUsuario.getServidor());
			colPOIs.addAll(colAux);
			return (ArrayList<POI>) colPOIs;
		}
	
	
	}
	
	
}
