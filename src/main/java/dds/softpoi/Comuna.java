package dds.softpoi;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Comuna {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private double limSur;
	private double limNorte;
	private double limEste;
	private double limOeste;
	private String zonas;
	
	
	// ***************************************************************************
	// Constructor
	// ***************************************************************************
	
	public Comuna(){}
	
	public Comuna(int unID, double unLimSur, double unLimNorte, double unLimEste, double unLimOeste, String zonas) {
		this.id = unID;
		this.limSur = unLimSur;
		this.limNorte = unLimNorte;
		this.limEste = unLimEste;
		this.limOeste = unLimOeste;
		this.zonas = zonas;
	}
	
	// ***************************************************************************
	// Setters
	// ***************************************************************************
	
	public void setLimSur(double unLimSur) {
		this.limSur = unLimSur;
	}
	
	public void setLimNorte(double unLimNorte) {
		this.limNorte = unLimNorte;
	}
	
	public void setLimEste(double unLimEste) {
		this.limEste = unLimEste;
	}
	
	public void setLimOeste(double unLimOeste) {
		this.limOeste = unLimOeste;
	}
	
	public void setID(int unid) {
		this.id = unid;
	}
	
	public void setZonas(String zonas) {
		this.zonas = zonas;
	}
	
	// ***************************************************************************
	// Getters
	// ***************************************************************************
	
	public double getLimSur() {
		return limSur;
	}
	
	public double getLimNorte() {
		return limNorte;
	}

	public double getLimEste() {
		return limEste;
	}
	
	public double getLimOeste() {
		return limOeste;
	}
	
	public int getID() {
		return id;
	}

	public String getZonas() {
		return zonas;
	}
	
}
