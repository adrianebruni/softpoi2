package dds.softpoi;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestBanco {

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
		unDia.setRangoHorario(rango2);
		
		Disponibilidad otroDia = new Disponibilidad();
		otroDia.setDia("JUEVES");
		otroDia.setRangoHorario(rango3);

		Disponibilidad otroDia1 = new Disponibilidad();
		otroDia1.setDia("DOMINGO");
		otroDia1.setRangoHorario(rango3);
		
		// Creamos un servicio y le asignamos valores.
		Servicio unServicio = new Servicio();
		unServicio.setServicio("apertura de cuenta corriente");
		unServicio.setDisponibilidad(otroDia1);
		
		Servicio otroServicio = new Servicio();
		otroServicio.setServicio("apertura de caja de ahorro");
		otroServicio.setDisponibilidad(unDia);
		otroServicio.setDisponibilidad(otroDia);
		
		// Creamos el objeto bancoFrances de la clase Banco.
		Banco bancoFrances = new Banco("BANCO FRANCES", -34.603075, -58.381653);
		bancoFrances.setServicios(unServicio);
		bancoFrances.setServicios(otroServicio);
		
		// Mostramos los datos cargados
		//System.out.println("TipoPOI: " + bancoFrances.tipoPOI());
		//System.out.println("BANCO: " + bancoFrances.nombre);
		//System.out.println("Longitud: " + bancoFrances.longitud);
		//System.out.println("Latitud: " + bancoFrances.latitud);
	
		System.out.println("Esta Disponible (apertura de cuenta corriente): " + bancoFrances.estaDisponible("apertura de cuenta corriente", FechayHora.fechaHoy(), FechayHora.horaHoy()));
		assertEquals("Esta Disponible (apertura de cuenta corriente): ", true, bancoFrances.estaDisponible("apertura de cuenta corriente", FechayHora.fechaHoy(), FechayHora.horaHoy()));
		System.out.println("Test Finalizado !");
	}

}
