package dds.schedule;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import dds.softpoi.Comercio;
import dds.softpoi.POI;
import dds.softpoi.Servidor;

public class JobActualizarLocalesComerciales extends Job {
	Servidor unServidor;
	String rutaArchivo;
	private JobActualizarLocalesComerciales(Scheduler unScheduler, String unNombre, int unID) {
		super(unScheduler, unNombre, unID);
		// TODO Auto-generated constructor stub
	}
	
	public JobActualizarLocalesComerciales(Scheduler unScheduler, String unNombre, int unID, Servidor unServidor, String rutaArchivo) {
		super(unScheduler, unNombre, unID);
		// TODO Auto-generated constructor stub
		this.unServidor = unServidor;
		this.rutaArchivo = rutaArchivo;
	}
	
	public int ejecutar(){
		System.out.println("Inicia proceso " + super.nombre_job);
		
		try {
			this.actualizarLocalesComerciales(this.rutaArchivo);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Finaliza proceso " + super.nombre_job);
		return 0;
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
            comercio = cadena.substring(0, pos);
            palabrasClave = cadena.substring(pos + 1,cadena.length());
            encontroPois = false;
    		for(POI unPoi : unServidor.colPOIs){
    			if (unPoi.getNombre().toUpperCase().indexOf(comercio.toUpperCase()) > -1){
    				encontroPois = true;
    				System.out.print("Se modifica el POI " + unPoi.getNombre() + " " + unPoi.getPalabrasClave());
    				unPoi.setPalabrasClave(palabrasClave);
    				System.out.println(" - Nuevas palabras clave " + unPoi.getPalabrasClave());
    			}
    		}
    		if(encontroPois == false){
    			System.out.println("POI no encontrado, se da de alta : " + comercio + " " + palabrasClave);
    			POI poiNuevo = new Comercio(comercio, 0, 0, null);
    			poiNuevo.setPalabrasClave(palabrasClave);
    			unServidor.cargarPOI(poiNuevo);
    		}
        }
        b.close();
    }


}
