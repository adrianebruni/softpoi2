package dds.schedule;
import java.util.ArrayList;

import dds.softpoi.POI;

public class Scheduler extends Thread {
	private ArrayList<Job> cola_jobs;
	private ArrayList<EstadoJob> cola_estados;
	private Boolean estado_escheduler;
	private ArrayList<Ejecuciones> cola_ejecuciones;
	//variables para el RollBack
	private Boolean begin_RollBack;
	private ArrayList<POI> pois_activos_RollBack;
	private ArrayList<POI> pois_borrados_RollBack;
	
	public Scheduler(){
		cola_jobs = new ArrayList<Job>();
		cola_estados = new ArrayList<EstadoJob>();
		cola_ejecuciones = new ArrayList<Ejecuciones>();
		estado_escheduler = false;
		begin_RollBack = false;
		pois_activos_RollBack = new ArrayList<POI>();
		pois_borrados_RollBack = new ArrayList<POI>();
	}
	
	public Boolean getEstado_escheduler() {
		return estado_escheduler;
	}

	public void agregarResultadoDeEjecucion(Ejecuciones unaEjecucion) {
		this.cola_ejecuciones.add(unaEjecucion);
	}
	
	public void setEstado_escheduler(Boolean estado_escheduler) {
		this.estado_escheduler = estado_escheduler;
	}

	public void vaciarJobs(){
//		System.out.println("vaciarJobs()");
		cola_jobs.clear();
		cola_estados.clear();
	}
	
	public boolean agregarJob(Job unJob){
//		System.out.println("agregarJob()");

		for (Job jobExistente : this.cola_jobs) {
			if (jobExistente.getId_job() == unJob.getId_job()){
				return false;
			}
		}

		cola_jobs.add(unJob);
		cola_estados.add(new EstadoJob(unJob.getId_job()));
		return true;
	}
	
	public ArrayList<EstadoJob> getCola_Estados(){
		return this.cola_estados;
	}
	
	public void eliminarJob(int id){
//		System.out.println("eliminarJob()");
		for (Job jobExistente : this.cola_jobs){
			if (jobExistente.getId_job() == id){
				this.cola_jobs.remove(jobExistente);
				break;
			}
		}
		for (EstadoJob estadoExistente : this.cola_estados){
			if (estadoExistente.id_job == id){
				this.cola_estados.remove(estadoExistente);
				break;
			}
		}
	}
	
	public void run(){
//		System.out.println("run()");
		while (estado_escheduler) {
			try {
				Thread.sleep(1 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.println("Shceduler buscando novedades");
			for (Job jobExistente : this.cola_jobs){
				if(controlarSiSePuedeEjecutar(jobExistente, cola_estados)){
					System.out.println("Ejecutar proceso " + jobExistente.nombre_job);

					jobExistente.start();
				}
			}
		}
	}
	
	public boolean controlarSiSePuedeEjecutar(Job unJob,ArrayList<EstadoJob> colaDeEstados){
		//System.out.println("controlarSiSePuedeEjecutar()");
		if(unJob.getEstado_job() != 'P'){
			return false;
		}
		int cantidadCoincidencias = 0;
		for (int i = 0; i < unJob.getPredecesores_job().size(); i++) {
			for (int j = 0; j < colaDeEstados.size(); j++) {
				if (unJob.getPredecesores_job().get(i) == colaDeEstados.get(j).id_job &&
				   colaDeEstados.get(j).estado == 'F'){
					cantidadCoincidencias++;
				}
			}
		}
		return cantidadCoincidencias==unJob.getPredecesores_job().size();
	}
	
	public void stopScheduler(){
		this.setEstado_escheduler(false);
	}
	
	public void startScheduler(){
//		System.out.println("startScheduler()");
		this.setEstado_escheduler(true);
		this.agregarJobFinal();
		this.start();
	}
	
	public void agregarJobFinal(){
		Job jobFinal = new JobFinal(this,"JobFinal");
		for (int i = 0; i < cola_estados.size() ; i++) {
			jobFinal.agregarPredecesor(cola_estados.get(i).id_job);
		}
//		jobFinal.setNombre_job("Finalizador de Scheduler");
		this.agregarJob(jobFinal);
	}

	public ArrayList<POI> getPois_Activos_RollBack() {
		return pois_activos_RollBack;
	}
	
	public ArrayList<POI> getPois_Borrados_RollBack() {
		return pois_borrados_RollBack;
	}

	public void setPois_Activos_RollBack(ArrayList<POI> pois_activos) {
		this.pois_activos_RollBack.clear();
		this.pois_activos_RollBack.addAll(pois_activos);
	}
	
	public void setPois_Borrados_RollBack(ArrayList<POI> pois_borrados) {
		this.pois_borrados_RollBack.clear();
		this.pois_borrados_RollBack.addAll(pois_borrados);
	}

	public Boolean getBegin_RollBack() {
		return begin_RollBack;
	}

	public void setBegin_RollBack(Boolean begin_RollBack) {
		this.begin_RollBack = begin_RollBack;
	}
	
	
}
