package modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//@Entity
//@Table(name = "POI")
//@NamedQuery(name = "buscarPoiPorNombre", query = "SELECT p FROM Poi p WHERE p.nombre LIKE :pnombre")
public class Poi extends Persistible {
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "comuna_id", referencedColumnName = "id")
	private Comuna comuna;
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "georef_id", referencedColumnName = "id")
	private Georef georef;

	public Poi() {
	}

	public Comuna getComuna() {
		return comuna;
	}

	public void setComuna(Comuna comuna) {
		this.comuna = comuna;
	}

	public Georef getGeoref() {
		return georef;
	}

	public void setGeoref(Georef georef) {
		this.georef = georef;
	}
}