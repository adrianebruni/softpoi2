package dds.softpoi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Procesos {
	public void procesoBajaDePOI(Servidor servidor, Usuario unUsuario, String rutaArchivoBajas){
	    //Se crea un SAXBuilder para poder parsear el archivo
	    SAXBuilder builder = new SAXBuilder();
	    File xmlFile = new File(rutaArchivoBajas);
	    try
	    {
	        //Se crea el documento a traves del archivo
	        Document document = (Document) builder.build( xmlFile );
	 
	        //Se obtiene la raiz 'tables'
	        Element rootNode = document.getRootElement();
	 
	        //Se obtiene la lista de hijos de la raiz 'tables'
	        List list = rootNode.getChildren( "tipoPOI" );
	 
	        //Se recorre la lista de hijos de 'tables'
	        for ( int i = 0; i < list.size(); i++ )
	        {
	            //Se obtiene el elemento 'tabla'
	            Element tabla = (Element) list.get(i);
	 
	            //Se obtiene el atributo 'nombre' que esta en el tag 'tabla'
	            String clasePOI = tabla.getAttributeValue("clase");
	 
//	            System.out.println( "Tipo de POI: " + clasePOI );
	 
	            //Se obtiene la lista de hijos del tag 'tabla'
	            List lista_campos = tabla.getChildren();
	 
//	            System.out.println( "\tNombre POI \tFecha Baja" );
	 
	            //Se recorre la lista de campos
	            for ( int j = 0; j < lista_campos.size(); j++ )
	            {
	                //Se obtiene el elemento 'campo'
	                Element campo = (Element)lista_campos.get( j );
	         
	                //Se obtienen los valores que estan entre los tags '<campo></campo>'
	                //Se obtiene el valor que esta entre los tags '<nombre></nombre>'
	                String nombre = campo.getChildTextTrim("nombre");
	 
	                //Se obtiene el valor que esta entre los tags '<tipo></tipo>'
	                String fechaDeBaja = campo.getChildTextTrim("fechaBaja");
	                int añoBaja = Integer.parseInt(fechaDeBaja.substring(0,4));
	                int mesBaja = Integer.parseInt(fechaDeBaja.substring(4,6));
	                int diaBaja = Integer.parseInt(fechaDeBaja.substring(6,8));
	                
//	                System.out.println( "\t"+nombre+"\t\t"+fechaDeBaja);
	                
	                ArrayList<POI> listaParaBajar = servidor.buscaPOI(nombre, unUsuario);
	                for (POI unPoi : listaParaBajar) {
						unPoi.setIdpoi(0);
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
