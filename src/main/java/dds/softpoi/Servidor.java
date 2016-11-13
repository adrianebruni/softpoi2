package dds.softpoi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import dds.json.BancoDTO;
import dds.json.CentroDTO;
import dds.repositorio.Repositorio;

import java.security.SecureRandom;
import java.math.BigInteger;

//@ManagedBean(name = "servidor")
//@ApplicationScoped
public class Servidor {
	
	public ArrayList<POI> colPOIs = new ArrayList<POI>();
	public ArrayList<POI> colPOIsBorrados = new ArrayList<POI>();
	public ArrayList<Usuario> colAdmins = new ArrayList<Usuario>();
	public ArrayList<POI> colPOIsExternos = new ArrayList<POI>(); 	//Esta coleccion es para origenes de datos externos
	
	HistoricoConsulta histconsulta = new HistoricoConsulta();
	public Auditoria unaAuditoria = new Auditoria();
	
	public Repositorio repositorio;
	
	SecureRandom random = new SecureRandom();
	Parametros parametros = new Parametros();
	Seguridad objSeguridad = new Seguridad(this);
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	public void setRepositorio(Repositorio unRepositorio){
		this.repositorio = unRepositorio;
	}
	
	public void addAdmin(Usuario unAdmin) {
		unAdmin.setToken(generarToken());
		this.colAdmins.add(unAdmin);
	}
	
	public void cargarPOI(POI unPOI) {
//		unPOI.setIdpoi(this.proximoIdPOI());
		this.getRepositorio().pois().persistir(unPOI);
		this.colPOIs.add(unPOI);
	}
	
	public void cargarPOIs(ArrayList<POI> listaPOIs) {
		this.colPOIs.addAll(listaPOIs);
	}

	public void cargarPOIExterno(POI unPOI) {
		this.colPOIsExternos.add(unPOI);
	}
	
	public void cargarPOIEnBorrados(POI unPOI) {
		this.colPOIsBorrados.add(unPOI);
	}

	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public Repositorio getRepositorio(){
		return repositorio;
	}
	public ArrayList<POI> getColPOIs() {
		return colPOIs;
	}
	
	public ArrayList<Usuario> getColAdmins() {
		return colAdmins;
	}
	
	public ArrayList<POI> getcolPOIsExternos() {
		return colPOIsExternos;
	}
	
	public Parametros getParametros(){
		return parametros;
	}
	
	public HistoricoConsulta getHistoricoConsulta() {
		return histconsulta;
	}
		
	// ***************************************************************************
	// Metodos
	// ***************************************************************************
	
	public Usuario login(String unUsuario, String unaClave){
		Usuario resultado = null;
		
		for (Usuario unUsuarioServidor: colAdmins){		
			if ( (unUsuarioServidor.getNombre().equals(unUsuario)) && (unUsuarioServidor.getClave().equals(unaClave)) ){
				resultado = unUsuarioServidor;
				break;
			}
		}
		
		return resultado;
	}
	
	/*
	public String login(Usuario unUsuario){
		 if (colAdmins.contains(unUsuario)){
			 return unUsuario.getToken();
		 }else{
			 return null;
		 }
	}
	*/
	
	public String generarToken(){
		return new BigInteger(130,random).toString(32);
	}
	
	public void eliminarAdmin(Administrador unAdmin){
		if(colAdmins.contains(unAdmin)){
			colAdmins.remove(unAdmin);
		}
	}	
	
	public void eliminarPOI(POI unPOI){
		if(colPOIs.contains(unPOI)){
			colPOIs.remove(unPOI);
		}
	}	
	
	public void eliminarPOI(int idpoi){
		for(POI unpoi : this.colPOIs){
			if(unpoi.getIdpoi() == idpoi ){
				colPOIs.remove(unpoi);
				break;
			}
		}		
	}	
	
	public void modificarPOI(POI poimodificado) throws IllegalArgumentException, IllegalAccessException {
	
		for(POI unpoi : this.colPOIs){

			if(unpoi.getIdpoi() == poimodificado.getIdpoi()){
				// copia valores de clase hija
				Field[] fields = unpoi.getClass().getDeclaredFields();
				for(Field f : fields){
					f.setAccessible(true);
					f.get(poimodificado);
					f.set(unpoi, f.get(poimodificado));
				}
	
				// copia valores de clase padre
				Field[] fields2 = POI.class.getDeclaredFields();
				for(Field f : fields2){
					f.setAccessible(true);
					f.get(poimodificado);
					f.set(unpoi, f.get(poimodificado));
				}
			}	
		}
	}
	
	public ArrayList<POI> buscaPOI(String cadenadebusqueda, Usuario unUsuario){
		
		return unaAuditoria.auditarBusquedaPOI(cadenadebusqueda, unUsuario);
	}
	

	public void actualizoDesdeDatosExternos(String cadena) {
	
		colPOIsExternos.removeAll(colPOIsExternos);
		ArrayList<POI> colAUX = new ArrayList<POI>();

		
		BancoDTO bancosExternos = new BancoDTO();
		try{
			colAUX.addAll(bancosExternos.dameDatosExternos(parametros.getUrlJsonBanco() + "?banco="+cadena));
		}catch (Exception e) {
			//System.out.println("No se encontraron banco.nombre externos");
		}
		
		try{
			colAUX.addAll(bancosExternos.dameDatosExternos(parametros.getUrlJsonBanco() + "?servicio="+cadena));
		}catch (Exception e) {
			//System.out.println("No se encontraron banco.servic externos");
		}
		
		CentroDTO centrosExternos = new CentroDTO();
		
		try{
			colAUX.addAll(centrosExternos.dameDatosExternos(parametros.getUrlJsonCentro() + "?zona="+cadena));
		}catch (Exception e) {
			//System.out.println("No se encontraron centros externos");
		}
		
		
		Set<POI> datosExternosSinRepetir = new HashSet<POI>();
		datosExternosSinRepetir.addAll(colAUX);
		
		colPOIsExternos.addAll(datosExternosSinRepetir);
	}
	
	//ver de optimizar con la funcion sort de la coleccion
	// despues de ordenarlo, obtenemos el maximo idpoi+1  *ver
    public int proximoIdPOI(){
		int idaux = 1;
		for(POI unpoi : colPOIs){
			if( unpoi.getIdpoi() >= idaux ){
				idaux = unpoi.getIdpoi() + 1;
			}
		}
		return idaux;	
	}

    public ArrayList<ItemReporteFecha> reportePorFecha(Usuario unUsuario){
     	if (objSeguridad.validarUsuarioAdmin(unUsuario)){
    		return this.histconsulta.reportePorFecha(unUsuario);
    	}else{
    		return null;
    	}
    }
    
    public ArrayList<ItemReporteTerminal> reportePorTerminal(Usuario unUsuario){	
    	if (objSeguridad.validarUsuarioAdmin(unUsuario)){
    		return this.histconsulta.cantidadBusquedasPorTerminal();
    	}else{
    		return null;
    	}
    }
    

  
    public void actualizarLocalesComerciales(String rutaArchivo) throws FileNotFoundException, IOException {
    	String cadena;
    	String comercio,palabrasClave;
    	int pos;
    	FileReader f = new FileReader(rutaArchivo);
    	BufferedReader b = new BufferedReader(f);
    	boolean encontroPois;
    	
        while((cadena = b.readLine())!=null) {
            //System.out.println(cadena);

            pos = cadena.indexOf(";");
            comercio = cadena.substring(0, pos-1);
            palabrasClave = cadena.substring(pos + 1,cadena.length());
            encontroPois = false;
    		for(POI unPoi : colPOIs){
    			if (unPoi.getNombre().toUpperCase().indexOf(comercio.toUpperCase()) > -1){
    				encontroPois = true;
    				System.out.println("Se modifica el POI " + unPoi.getNombre() + " " + unPoi.getPalabrasClave());
    				unPoi.setPalabrasClave(palabrasClave);
    				System.out.println("Nuevas palabras clave " + unPoi.getPalabrasClave());
    			}
    		}
    		if(encontroPois == false){
    			System.out.println("POI no encontrado, se da de alta : " + comercio + " " + palabrasClave);
    			POI poiNuevo = new Comercio(comercio, 0, 0, null);
    			poiNuevo.setPalabrasClave(palabrasClave);
    			this.cargarPOI(poiNuevo);
    		}
        }
        b.close();
    }

    public ArrayList<ElementoDeConsulta> historialBusquedaPantalla(String unUsuario, String fechaInicial, String fechaFinal){	
 
    	return this.histconsulta.coleccItemsHistorialBusqPantalla(unUsuario,fechaInicial,fechaFinal);

    }
    
}
