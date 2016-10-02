package dds.schedule;

import java.util.ArrayList;

import dds.softpoi.POI;
import dds.softpoi.Servidor;

public class JobRollBack extends Job{
	private Servidor unServidor;
	public JobRollBack(Scheduler unScheduler, String unNombre, int unID, Servidor unServidor) {
		super(unScheduler, unNombre, unID);
		// TODO Auto-generated constructor stub
		this.unServidor = unServidor;
	}

	@Override
	public int ejecutar() {
		// TODO Auto-generated method stub
		System.out.println("Inicia proceso " + super.nombre_job);
		int retorno = this.rollBack();
		System.out.println("Finaliza proceso " + super.nombre_job);
		return retorno;
	}
	
	public int rollBack(){
		if (!super.scheduler.getBegin_RollBack()){
			return 1; // no se hizo begin tran, no se puede hacer rollback
		}
		unServidor.getColPOIs().clear();
		unServidor.colPOIsBorrados.clear();
		unServidor.getColPOIs().addAll(super.scheduler.getPois_Activos_RollBack());
		unServidor.colPOIsBorrados.addAll(super.scheduler.getPois_Borrados_RollBack());
		super.scheduler.setPois_Activos_RollBack(new ArrayList<POI>());
		super.scheduler.setPois_Borrados_RollBack(new ArrayList<POI>());
		super.scheduler.setBegin_RollBack(false);
		return 0;
	}

}
