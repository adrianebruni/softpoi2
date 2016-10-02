package dds.schedule;

import dds.softpoi.Administrador;
import dds.softpoi.RepoPOI;
import dds.softpoi.Servidor;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		System.out.println("Inicio del Test");

		
		
		Servidor servidorPpal = new Servidor();
		//RepoPOI colPoisPrueba = new RepoPOI();
		//servidorPpal.cargarPOIs(colPoisPrueba.Dame_Bolsa_POI());
		String ruta1 = "/home/alexis/work/softpoi2/src/test/java/dds/softpoi/ComerciosPalabrasClave";
		String ruta2 = "/home/alexis/work/softpoi2/src/test/java/dds/softpoi/ComerciosPalabrasClave2";
		String rutaBajas = "/home/alexis/work/softpoi2/src/test/java/dds/softpoi/archivobajas.xml";
		
		Scheduler sched = new Scheduler();
		
		Job jobA = new JobEjemplo(sched, "A", 1);
		Job jobB = new JobEjemplo(sched, "B", 2);
		Job jobC = new JobEjemplo(sched, "C", 3);
		Job jobD = new JobActualizarLocalesComerciales(sched, "LC1", 4, servidorPpal, ruta1);	
		Job jobE = new JobActualizarLocalesComerciales(sched, "LC2", 5, servidorPpal, ruta2);
		Job jobF = new JobBajaPOIs(sched, "BA", 6, servidorPpal, rutaBajas);
		
		jobA.setRepetir_job(2);
		jobF.setRepetir_job(2);
		
		jobB.agregarPredecesor(1);
		jobC.agregarPredecesor(1);
		jobD.agregarPredecesor(2);
		jobD.agregarPredecesor(3);
		jobE.agregarPredecesor(4);
		jobF.agregarPredecesor(5);
		
		
		//otroJob.start();
		//unJob.start();
		
		
		
		sched.agregarJob(jobA);
		sched.agregarJob(jobB);
		sched.agregarJob(jobC);
		sched.agregarJob(jobD);
		sched.agregarJob(jobE);
		sched.agregarJob(jobF);
		//sched.agregarJob(ultimojob);
		sched.startScheduler();
		
//		System.out.println("Fin del Test");
		
	}

}
