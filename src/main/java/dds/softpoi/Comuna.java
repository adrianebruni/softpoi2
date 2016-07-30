package dds.softpoi;

public class Comuna {
	
	// ***************************************************************************
	// Constructor
	// ***************************************************************************
	private double limSur;
	private double limNorte;
	private double limEste;
	private double limOeste;
	
	public Comuna(double unLimSur, double unLimNorte, double unLimEste, double unLimOeste) {
		this.limSur = unLimSur;
		this.limNorte = unLimNorte;
		this.limEste = unLimEste;
		this.limOeste = unLimOeste;
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
}
