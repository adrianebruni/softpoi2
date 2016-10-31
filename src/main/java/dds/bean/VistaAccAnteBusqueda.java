package dds.bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@ManagedBean(name="bnVistaAccAnteBusqueda")
@ViewScoped
public class VistaAccAnteBusqueda extends VistaPadre {

	private static final long serialVersionUID = 6239774130729760800L;
	private String[] accionesElegidas;   
    private List<String> acciones;
     
    @PostConstruct
    public void init() {
        acciones = new ArrayList<String>();
        acciones.add("Generar Log");
        acciones.add("Totalizar por usuario");
        acciones.add("Totalizar por fecha");

    }
 
    public String[] getAccionesElegidas() {
        return accionesElegidas;
    }
 
    public void setAccionesElegidas(String[] accionesElegidas) {
        this.accionesElegidas = accionesElegidas;
    }
 
    public List<String> getAcciones() {
        return acciones;
    }

}
