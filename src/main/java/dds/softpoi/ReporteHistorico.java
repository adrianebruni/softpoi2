package dds.softpoi;

import java.util.ArrayList;

public class ReporteHistorico extends Reporte {

	
	public void displayReportePorFecha(ArrayList<ItemReporteFecha> colResult) {
	
		if(colResult == null){
	    	System.out.println("Reporte generado por un usuario sin permisos...\n\n");
	    }else{
	    	System.out.println("Reporte generado por un usuario con permisos...\n\n");
	    	System.out.println("FECHA \t\t CANTIDAD");
	    	for(ItemReporteFecha unItemReporteFecha: colResult) {	
				System.out.println(unItemReporteFecha.getFecha() + "\t  " + unItemReporteFecha.getCantidad());
			}
	    }
	}
	
	public void displayReportePorTerminal(ArrayList<ItemReporteTerminal> colResult) {
		
	    if(colResult == null){
	    	System.out.println("Reporte generado por un usuario sin permisos...\n\n");
	    	
	    }else{
	    	System.out.println("Reporte generado por un usuario con permisos...\n\n");
	        
			System.out.println("Parciales por Terminal");
			System.out.println("");
			
			for(ItemReporteTerminal unitem : colResult){
				System.out.println("Usuario: " + unitem.getNombreTerminal());
				for(int i : unitem.getCantidadEncontrados()){
					System.out.println(i);
				}
				System.out.println("");
			}
			System.out.println("Totales por Usuarios");
			System.out.println("");
			System.out.println("Usuario\tCantidad Resultados Totales");
			for(ItemReporteTerminal unitem : colResult){
				System.out.println( unitem.getNombreTerminal() + "\t" + unitem.cantidadResultadosTotales());
			}
			
	    }
	}
	
}