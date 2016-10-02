package dds.schedule;

public class EstadoJob {
	public int id_job;
	public char estado;
	
	public EstadoJob(int id){
		this.id_job = id;
		this.estado = 'P';
	}
}
