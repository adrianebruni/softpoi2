package dds.softpoi;

import java.util.ArrayList;

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
	
}
