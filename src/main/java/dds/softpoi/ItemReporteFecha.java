package dds.softpoi;

import java.util.Comparator;

public class ItemReporteFecha {

	private String fecha;
	private int cantidad;
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public String getFecha() {
		return fecha;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************
	
	
	public static Comparator <ItemReporteFecha> Comparar_Por_Fecha = new Comparator <ItemReporteFecha> () {
		public int compare(ItemReporteFecha item1, ItemReporteFecha item2) {
			return item1.getFecha().compareTo(item2.getFecha());
		}
	};
	
}
