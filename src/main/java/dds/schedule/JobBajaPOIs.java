package dds.schedule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import dds.softpoi.POI;
import dds.softpoi.Servidor;


public class JobBajaPOIs extends Job{
	private Servidor unServidor;
	private String rutaArchivo;
	public JobBajaPOIs(Scheduler unScheduler, String unNombre, int unID, Servidor unServidor, String rutaArchivo) {
		super(unScheduler, unNombre, unID);
		// TODO Auto-generated constructor stub
		this.unServidor = unServidor;
		this.rutaArchivo = rutaArchivo;
	}
	@Override
	public int ejecutar() {
		// TODO Auto-generated method stub
		System.out.println("Inicia proceso " + super.nombre_job);
		this.procesoBajaDePOI(unServidor, rutaArchivo);
		System.out.println("Finaliza proceso " + super.nombre_job);
		return 0;
	}
	
	public void procesoBajaDePOI(Servidor servidor, String rutaArchivoBajas){
	    SAXBuilder builder = new SAXBuilder();
	    File xmlFile = new File(rutaArchivoBajas);
	    try
	    {
	        Document document = (Document) builder.build( xmlFile );
	        Element rootNode = document.getRootElement();
	        List<?> list = rootNode.getChildren( "tipoPOI" );
	        for ( int i = 0; i < list.size(); i++ )
	        {
	            Element tabla = (Element) list.get(i);
//	            String clasePOI = tabla.getAttributeValue("clase");
	            List<?> lista_campos = tabla.getChildren();
	            for ( int j = 0; j < lista_campos.size(); j++ )
	            {
	                Element campo = (Element)lista_campos.get( j );
	                String nombre = campo.getChildTextTrim("nombre");
	                String fechaDeBaja = campo.getChildTextTrim("fechaBaja");
	                int añoBaja = Integer.parseInt(fechaDeBaja.substring(0,4));
	                int mesBaja = Integer.parseInt(fechaDeBaja.substring(4,6));
	                int diaBaja = Integer.parseInt(fechaDeBaja.substring(6,8));
	                
	                ArrayList<POI> listaParaBajar = new ArrayList<POI>();
	                for(POI unPoi : servidor.colPOIs){
	                	if(nombre.equals(unPoi.getNombre())){
	                		listaParaBajar.add(unPoi);
	                	}
	                }
	        
	                for (POI unPoi : listaParaBajar) {
						unPoi.setIdpoi(0);
						@SuppressWarnings("deprecation")
						Date fechaBaja = new Date(añoBaja,mesBaja,diaBaja);
						unPoi.setFechaBaja(fechaBaja);
						servidor.eliminarPOI(unPoi);
						servidor.cargarPOIEnBorrados(unPoi);
						System.out.println("Se borra el POI : " + unPoi.getNombre());
					}	                
	            }
	        }
	    }catch ( IOException io ) {
	        System.out.println( io.getMessage() );
	    }catch ( JDOMException jdomex ) {
	        System.out.println( jdomex.getMessage() );
	    }
	}
}
