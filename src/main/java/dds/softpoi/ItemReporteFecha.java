package dds.softpoi;

import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class ItemReporteFecha {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int id_itemreportefecha;
	
	private String fecha;
	private int cantidad;

	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public int getId_itemreportefecha() {
		return id_itemreportefecha;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	public void setId_itemreportefecha(int id_itemreportefecha) {
		this.id_itemreportefecha = id_itemreportefecha;
	}
	
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
