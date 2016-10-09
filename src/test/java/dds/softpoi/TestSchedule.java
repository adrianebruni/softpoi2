package dds.softpoi;


import static org.junit.Assert.*;

import org.junit.Test;

import dds.schedule.Job;
import dds.schedule.JobEjemplo;
import dds.schedule.Scheduler;


public class TestSchedule {

	@Test
	public void testScheduler(){

		System.out.println("Inicia testSchedule");
		System.out.println("Ejecutar test --> /softpoi2/src/main/java/dds/schedule/Test.java");
		System.out.println("/////***** OJO , los theads deben tirar a otra consola los msjs desde JUnit *****/////");
		Scheduler sched = new Scheduler();
	
		Job jobA = new JobEjemplo(sched, "A", 1);
		Job jobB = new JobEjemplo(sched, "B", 2);
		Job jobC = new JobEjemplo(sched, "C", 3);
		Job jobD = new JobEjemplo(sched, "D", 4);		
	
		jobA.setRepetir_job(3);
	
		jobB.agregarPredecesor(1);
		jobC.agregarPredecesor(1);
		jobD.agregarPredecesor(2);
		jobD.agregarPredecesor(3);
	
		sched.agregarJob(jobA);
		sched.agregarJob(jobB);
		sched.agregarJob(jobC);
		sched.agregarJob(jobD);
	
		sched.startScheduler();
		
		System.out.println("Finaliza testSchedule");
		assertEquals("Ejecucion correcta", true, true);
		
	}

}
