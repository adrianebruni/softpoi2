package dds.schedule;

public class JobEjemplo extends Job {


	public JobEjemplo(Scheduler unScheduler, String unNombre, int unID) {
		super(unScheduler, unNombre, unID);
		// TODO Auto-generated constructor stub
	}

//	public void run(){
//		this.setEstado_job('E');
//		for (int i = 0; i < super.repetir_job; i++) {
//			this.ejecutar();
//		}
//		this.setEstado_job('F');
//	}
	
	public int ejecutar(){
		System.out.println("Inicia proceso " + super.nombre_job);
		for ( int i = 0 ; i < 3 ; i++){
			System.out.println("Proceso " + nombre_job + " " + i);
			try {
				Thread.sleep(1 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
		System.out.println("Finaliza proceso " + super.nombre_job);
		return 0;
	}

}
