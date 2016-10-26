package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Persistible implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	protected String nombre;

	protected Persistible() {
	}

	@Column(name = "id")
	public Long getId() {
		System.out.println("getId");
		return id;
	}

	public void setId(Long id) {
		System.out.println("setId");
		this.id = id;
	}

	@Column(name = "nombre")
	public String getNombre() {
		System.out.println("getNombre");
		return nombre;
	}

	public void setNombre(String nombre) {
		System.out.println("setNombre");
		this.nombre = nombre;
	}

	public String toString() {
		System.out.println("toString");
		return getId() + "-" + getNombre();
	}
}