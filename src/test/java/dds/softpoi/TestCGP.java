package dds.softpoi;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCGP {

	@Test
	public void testDisponibilidadServicio() {
			//fail("Not yet implemented");
			System.out.println("Iniciando testDisponibilidadServicio");
			System.out.println("------------------------------------");	

			// *********************************************************************************************
			// Prueba de clase Banco
			// *********************************************************************************************
			// Creamos el objeto rango1 de la clase RangoHorario.
			RangoHorario rango1 = new RangoHorario("09:00:00", "13:00:00");
			RangoHorario rango2 = new RangoHorario("15:30:00", "19:00:00");
			RangoHorario rango3 = new RangoHorario("00:00:00", "24:59:59");
			
			// Creamos el objeto unDia de la clase Disponibilidad y le asignamos valores
			Disponibilidad unDia = new Disponibilidad();
			unDia.setDia("VIERNES");
			unDia.setRangoHorario(rango1);
			unDia.setRangoHorario(rango3);
			
			Disponibilidad otroDia = new Disponibilidad();
			otroDia.setDia("JUEVES");
			otroDia.setRangoHorario(rango2);
			otroDia.setRangoHorario(rango3);

			Disponibilidad otroDia1 = new Disponibilidad();
			otroDia1.setDia("MIERCOLES");
			otroDia1.setRangoHorario(rango3);
			
			Disponibilidad otroDia2 = new Disponibilidad();
			otroDia2.setDia("MARTES");
			otroDia2.setRangoHorario(rango3);
			
			Disponibilidad otroDia3 = new Disponibilidad();
			otroDia3.setDia("LUNES");
			otroDia3.setRangoHorario(rango3);
			
			Disponibilidad otroDia4 = new Disponibilidad();
			otroDia4.setDia("SABADO");
			otroDia4.setRangoHorario(rango3);
			
			Disponibilidad otroDia5 = new Disponibilidad();
			otroDia5.setDia("DOMINGO");
			otroDia5.setRangoHorario(rango3);
			
			// Creamos un servicio y le asignamos valores.
			Servicio unServicio = new Servicio();
			unServicio.setServicio("Atencion por infracciones de transito");
			unServicio.setDisponibilidad(otroDia1);
			
			Servicio otroServicio = new Servicio();
			otroServicio.setServicio("Casamientos");
			otroServicio.setDisponibilidad(unDia);
			otroServicio.setDisponibilidad(otroDia1);
			otroServicio.setDisponibilidad(otroDia2);
			otroServicio.setDisponibilidad(otroDia3);
			otroServicio.setDisponibilidad(otroDia4);
			otroServicio.setDisponibilidad(otroDia5);
			
			// Creamos el objeto comuna 12 de la clase cgp.
			CGP comuna12 = new CGP("Comuna 12", -34.603075, -58.381653);
			comuna12.setServicios(unServicio);
			comuna12.setServicios(otroServicio);
			
			System.out.println("Esta Disponible (Casamientos): " + comuna12.estaDisponible("Casamientos", FechayHora.fechaHoy(), FechayHora.horaHoy()));
			assertEquals("Esta Disponible (Casamientos): ", true, comuna12.estaDisponible("Casamientos", FechayHora.fechaHoy(), FechayHora.horaHoy()));
			System.out.println("Test Finalizado !");
		
		
	}

}
