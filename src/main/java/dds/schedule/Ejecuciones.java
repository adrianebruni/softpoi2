package dds.schedule;
import java.util.Date;

public class Ejecuciones {
	@SuppressWarnings("unused")
	private Date fecha_ejecucion;
	@SuppressWarnings("unused")
	private Job job_ejecucion;
	
	public Ejecuciones(Date unaFecha , Job unJob){
		this.fecha_ejecucion = unaFecha;
		this.job_ejecucion = unJob;
	}
}
