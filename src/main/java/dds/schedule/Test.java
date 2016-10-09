package dds.schedule;

import dds.softpoi.Banco;
import dds.softpoi.Comercio;
import dds.softpoi.Rubro;
import dds.softpoi.Servidor;

public class Test {
/*
 * El test se hace acá, ya que al parecer con JUnit al parecer cada hilo tiene una consola
 * entonces no se ven los displays de los hilos.
 * 
 * Descomentar las tres lineas del proceso jobRollBack para hacer un rollback
 * 
 * Al servidor le ponemos 3 pois, a la cola de borrados le ponemos uno, y displayamos cantidades
 * Al final volvemos a hacer el display de cantidades (esperamos 20 segundos para dar tiempo a 
 * los hilos a terminar. Sino se podria hacer un nuevo proceso que muestre cantidades
 * 
 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		
		Servidor servidorPpal = new Servidor();
	
		servidorPpal.cargarPOI(new Banco("BANCO FRANCES", -34.603075, -58.381653));
		servidorPpal.cargarPOI(new Comercio("McDonals", -34.6036961,-58.3843145, new Rubro("Restaurante", 900)));
		servidorPpal.cargarPOI(new Comercio("Caballito", -34.6036075,-58.3843653, new Rubro("Restaurante", 900)));
		servidorPpal.cargarPOIEnBorrados(new Comercio("KFC", -34.6036961,-58.3843145, new Rubro("Restaurante", 900)));
		System.out.println("Cantidad de POIs del Servidor : " + servidorPpal.colPOIs.size() + " y tiene Borrados: " + servidorPpal.colPOIsBorrados.size());
		String ruta1 = servidorPpal.getParametros().getRutaComerciosPalabrasClave1();
		String ruta2 = servidorPpal.getParametros().getRutaComerciosPalabrasClave2();
		String rutaBajas = servidorPpal.getParametros().getRutaXMLBajas();
		
		Scheduler sched = new Scheduler();
		
		Job jobBegin = new JobBeginTran(sched, "Begin", 1, servidorPpal);
		Job jobA = new JobEjemplo(sched, "A", 2);
		Job jobB = new JobEjemplo(sched, "B", 3);
		Job jobC = new JobEjemplo(sched, "C", 4);
		Job jobD = new JobActualizarLocalesComerciales(sched, "LC1", 5, servidorPpal, ruta1);	
		Job jobE = new JobActualizarLocalesComerciales(sched, "LC2", 6, servidorPpal, ruta2);
		Job jobF = new JobBajaPOIs(sched, "BA", 7, servidorPpal, rutaBajas);
//		Job jobRollBack = new JobRollBack(sched, "RollBack", 8, servidorPpal);
		
		jobA.setRepetir_job(2);

		
		jobA.agregarPredecesor(jobBegin);               //     jobBegin
		jobB.agregarPredecesor(jobA);                   //     jobA
		jobC.agregarPredecesor(jobA);					// jobB    jobC
		jobD.agregarPredecesor(jobB);					//     jobD
		jobD.agregarPredecesor(jobC);					//     jobE
		jobE.agregarPredecesor(jobD);					//     jobF
		jobF.agregarPredecesor(jobE);					//     jobRollBack
//		jobRollBack.agregarPredecesor(jobF);
		
		
		sched.agregarJob(jobBegin);
		sched.agregarJob(jobA);
		sched.agregarJob(jobB);
		sched.agregarJob(jobC);
		sched.agregarJob(jobD);
		sched.agregarJob(jobE);
		sched.agregarJob(jobF);
//		sched.agregarJob(jobRollBack);

		sched.startScheduler();
		try {
			Thread.sleep( 20 * 1000 );
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Cantidad de POIs del Servidor : " + servidorPpal.colPOIs.size() + " y tiene Borrados: " + servidorPpal.colPOIsBorrados.size());
		
	}

}
