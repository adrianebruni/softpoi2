package dds.pruebas;



import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
 
@ManagedBean
public class AccAnteBusqueda {
         
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