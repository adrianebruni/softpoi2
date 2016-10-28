package modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//@Entity
//@Table(name = "COMUNA")
public class Comuna extends Persistible {
//	@OneToMany(mappedBy = "comuna", cascade = CascadeType.ALL)
	private Set<Poi> pois;

	public Comuna() {
	}

	public Set<Poi> getPois() {
		return pois;
	}

	public void setPois(Set<Poi> pois) {
		this.pois = pois;
	}

	public void addPoi(Poi poi) {
		if (pois == null) {
			pois = new HashSet<Poi>();
		}
		pois.add(poi);
	}
}
