package json.centro;

import java.util.List;


public class Empleado {
		 
    private final int id;
 
    private final String nombre;
 
    private final String empresa;
 
    private final List<SolicitudVacaciones> vacaciones;
 
    public Empleado(int id, String nombre, String empresa, List<SolicitudVacaciones> vacaciones) {
        this.id = id;
        this.nombre = nombre;
        this.empresa = empresa;
        this.vacaciones = vacaciones;
    }

	public Object getVacaciones() {
		return vacaciones;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEmpresa() {
		return empresa;
	} 

}