package dds.schedule;

import dds.softpoi.Servidor;

public class JobBeginTran extends Job{
	private Servidor unServidor;
	
	public JobBeginTran(Scheduler unScheduler, String unNombre, int unID, Servidor unServidor) {
		super(unScheduler, unNombre, unID);
		// TODO Auto-generated constructor stub
		this.unServidor = unServidor;
	}

	@Override
	public int ejecutar() {
		// TODO Auto-generated method stub
		System.out.println("Inicia proceso " + super.nombre_job);
		this.beginTran();
		System.out.println("Finaliza proceso " + super.nombre_job);
		return 0;
	}
	
	public void beginTran(){
		super.scheduler.setPois_Activos_RollBack(unServidor.getColPOIs());
		super.scheduler.setPois_Borrados_RollBack(unServidor.colPOIsBorrados);
		super.scheduler.setBegin_RollBack(true);
	}
}
