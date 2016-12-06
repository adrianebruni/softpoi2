package dds.softpoi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;

import java.text.SimpleDateFormat;


import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;


//@Entity
//@Table(name = "ElementoDeConsulta")

@Entity("registro")
public class ElementoDeConsulta {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private ObjectId mongoId;
	//private int idconsulta;
	private Date fechaConsulta;
	private String consultaUsuario;
	private double tiempoRespuesta;
	private String tipoUsuario;
	private int totalResultados;

//	@ManyToMany
//	@JoinTable
//	  (
//	      name="POIS_POR_CONSULTA",
//	      joinColumns={ @JoinColumn(name="ridconsulta", referencedColumnName="idconsulta") },
//	      inverseJoinColumns={ @JoinColumn(name="ridpoi", referencedColumnName="idpoi") }
//	  )
	@Embedded
	private List<POI> colPOIs;
    
    // ***************************************************************************
 	// Constructor
 	// ***************************************************************************
	public ElementoDeConsulta(){}
	
    public ElementoDeConsulta(Date fechaConsulta,String consultaUsuario,double tiempoRespuesta,String tipoUsuario,int totalResultados,ArrayList<POI> colPOIs) {
    	this.fechaConsulta = fechaConsulta;
    	this.consultaUsuario = consultaUsuario;
    	this.tiempoRespuesta = tiempoRespuesta;
    	this.tipoUsuario = tipoUsuario;
    	this.totalResultados = totalResultados;
    	this.colPOIs= colPOIs;
    }
    
    // ***************************************************************************
 	// Setters
 	// ***************************************************************************
  
    public void setFechaConsulta(Date fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}
    public void setConsultaUsuario(String consultaUsuario) {
		this.consultaUsuario = consultaUsuario;
	}
    public void setTiempoRespuesta(float tiempoRespuesta) {
		this.tiempoRespuesta = tiempoRespuesta;
    }    
        
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	public void setTotalResultados(int totalResultados) {
		this.totalResultados = totalResultados;
	}

	public void setColPOIs(List<POI> colPOIs) {
		this.colPOIs = colPOIs;
	}

	// ***************************************************************************
	// Getters
	// ***************************************************************************

	public Date getFechaConsulta() {
		return fechaConsulta;
	}
	public String getConsultaUsuario() {
		return consultaUsuario;
	}
	public Double getTiempoRespuesta() {
		return tiempoRespuesta;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public int getTotalResultados() {
		return totalResultados;
	}
	public List<POI> getColPOIs() {
		return colPOIs;
	}
	
	// ***************************************************************************
	// Metodos
	// ***************************************************************************
	
	

	@SuppressWarnings("null")
	public String fechaFormateada(String formatoElegido){
		if(formatoElegido != null || formatoElegido.isEmpty()){
			String DATE_FORMAT = formatoElegido;
			SimpleDateFormat fechaConformato = new SimpleDateFormat(DATE_FORMAT);
			return fechaConformato.format(fechaConsulta);
		}else{
			String DATE_FORMAT = "yyyy/MM/dd";
			SimpleDateFormat fechaConformato = new SimpleDateFormat(DATE_FORMAT);
			return fechaConformato.format(fechaConsulta);
		}
	}
	

	
	
}