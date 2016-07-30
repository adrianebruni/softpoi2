package dds.softpoi;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RangoHorario {
	
	private String apertura;
	private String cierre;

	
	// ***************************************************************************
	// Constructor
	// ***************************************************************************
	public RangoHorario(String apertura, String cierre) {
		this.apertura = apertura;
		this.cierre = cierre;
	}
	
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	public void setApertura(String apertura) {
		this.apertura = apertura;
	}
	
	public void setCierre(String cierre) {
		this.cierre = cierre;
	}
	
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	public String getApertura() {
		return apertura;
	}
	
	public String getCierre() {
		return cierre;
	}
	
	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************
	public boolean estaDisponible(String horaActual){
		return this.compararHoras(horaActual);
	}
	
	private boolean compararHoras(String laHoraActual){
		try{
			DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
			
			Date dFecha1, dFecha2, dFechaActual;
			dFecha1 = dateFormat.parse(this.apertura);
			dFecha2 = dateFormat.parse(this.cierre);
			dFechaActual = dateFormat.parse(laHoraActual);
			
			if ((dFecha1.compareTo(dFechaActual) <= 0) && (dFecha2.compareTo(dFechaActual) >= 0)){
				//System.out.println("Disponible");
				return true;
			}else{
				//System.out.println("No Disponible");
				return false;
			}
		}catch (ParseException ex) {
	       //Logger.getLogger(Main2.class.getName()).log(Level.SEVERE, null, ex);
		   System.out.println("Posee errores");
	       return false;
		}
	 }
	 
}