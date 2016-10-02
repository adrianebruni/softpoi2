package dds.schedule;
public class JobFinal extends Job {


	public JobFinal(Scheduler unScheduler, String unNombre) {
		super(unScheduler, unNombre);
		// TODO Auto-generated constructor stub
	}

//	public void run(){
//		this.setEstado_job('E');
//		super.scheduler.stopScheduler();
//		this.setEstado_job('F');
//	}

	@Override
	public int ejecutar() {
		super.scheduler.stopScheduler();
		return -1; //retorno -1 para que no meter el proceso en el registro de ejecuciones
	}

}
