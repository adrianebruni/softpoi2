package dds.schedule;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public abstract class Job extends Thread{
	protected int id_job;
	protected String nombre_job;
	protected char estado_job;
	protected ArrayList<Integer> predecesores_job;
	protected Scheduler scheduler;
	protected int repetir_job;           //cuantas veces se debe procesar el job
	protected int reintentos_job;        //cuantos reintentos hacer ante error del job
	protected int accionAnteError_job;   //que accion tomar ante un error
	                                     // = 0 no hacer nada
	                                     // = 1 reintentar la cantidad de reintentos_job
	protected boolean envioMail_job;
	protected int resultadoEjecucion_job;
	//estados:
	//  P = Pendiente de ejecucion
	//  E = Ejecutando
	//  F = Finalizado

	public int getRepetir_job() {
		return repetir_job;
	}

	public void setRepetir_job(int repetir_job) {
		this.repetir_job = repetir_job;
	}

	public int getReintentos_job() {
		return reintentos_job;
	}

	public void setReintentos_job(int reintentos_job) {
		this.reintentos_job = reintentos_job;
	}

	public int getAccionAnteError_job() {
		return accionAnteError_job;
	}

	public void setAccionAnteError_job(int accionAnteError_job) {
		this.accionAnteError_job = accionAnteError_job;
	}
	
	public Job(Scheduler unScheduler, String unNombre , int unID){
		predecesores_job = new ArrayList<Integer>();
		estado_job = 'P';
		scheduler = unScheduler;
		id_job = unID;
		nombre_job = unNombre;
		repetir_job = 1;
		reintentos_job = 0;
		accionAnteError_job = 0;
	}
	
	public Job(Scheduler unScheduler, String unNombre){
		int proximoID = 0;
		for (EstadoJob unEstado : unScheduler.getCola_Estados()){
			if (unEstado.id_job >= proximoID){
				proximoID = unEstado.id_job;
			}
		}
		proximoID++;
		predecesores_job = new ArrayList<Integer>();
		estado_job = 'P';
		scheduler = unScheduler;
		id_job = proximoID;
		nombre_job = unNombre;
		repetir_job = 1;
		reintentos_job = 0;
		accionAnteError_job = 0;
	}
	
	public int getId_job() {
		return id_job;
	}
	public void setId_job(int id_job) {
		this.id_job = id_job;
	}
	public String getNombre_job() {
		return nombre_job;
	}
	public void setNombre_job(String nombre_job) {
		this.nombre_job = nombre_job;
	}
	public char getEstado_job() {
		return estado_job;
	}
	public void setEstado_job(char estado_job) {
		this.estado_job = estado_job;
		for (int i = 0; i < this.scheduler.getCola_Estados().size() ; i++) {
			if (this.scheduler.getCola_Estados().get(i).id_job == this.id_job){
				this.scheduler.getCola_Estados().get(i).estado = estado_job;
			}
		}
	}
	
	
	public void run(){
		
		this.setEstado_job('E');
		for (int i = 0; i < this.repetir_job; i++) {
			this.resultadoEjecucion_job = this.ejecutar();
		}
		if (this.resultadoEjecucion_job > 0 && this.envioMail_job){
			System.out.println("Enviar e-mail por ejecución erronea");
		}
		//Aca guardo los resultados de la ejecucion
		if(resultadoEjecucion_job != -1){   //si es -1 es que es el proceso final, no guardarlo
			Ejecuciones unaEjecucion = new Ejecuciones(new Date(), this);
			this.scheduler.agregarResultadoDeEjecucion(unaEjecucion);	
		}
		this.setEstado_job('F');
	}
	public abstract int ejecutar();
	public ArrayList<Integer> getPredecesores_job() {
		return predecesores_job;
	}
	public void agregarPredecesor(int idPredecesor){
		this.predecesores_job.add(idPredecesor);
		Collections.sort(this.predecesores_job);
	}
	
	
	
}
