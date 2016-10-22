package dds.softpoi;

import org.junit.Test;

public class TestPermisoDisponibilidad {
	
	@Test
	public void testPermisoDisponibilidad() {
	
		//crear Servidor
		Servidor servidorPpal = new Servidor();
		
		//crear administrador
		Administrador unAdministrador = new Administrador();
		unAdministrador.setClave("passPrueba");
		unAdministrador.setServidor(servidorPpal);
		servidorPpal.addAdmin(unAdministrador);

		//creo un dispositivo de Consulta
		DispositivoConsulta dispositivo2 = new DispositivoConsulta("disp Flores2", 0.350219708, 0.715584992, "Flores Norte");
		dispositivo2.setFlagAuditoriaBusqueda(true);
		dispositivo2.setFlagNotificaciones(true);
		dispositivo2.setServidor(servidorPpal);
		
		//le doy permiso para que consulte Disponibilidad
		unAdministrador.agregarPermiso(PermisoDisponibilidad.getpermisoDisponibilidadSingleton());
		unAdministrador.commitPermisos(dispositivo2);
		dispositivo2.imprimirPermisos();
		
		//creo rubro, servicio, comercio
		Rubro restaurante = new Rubro("Restaurante", 15);
		RangoHorario rango1 = new RangoHorario("00:00:00", "23:01:08");
		RangoHorario rango2 = new RangoHorario("01:00:00", "02:01:08");
		Disponibilidad unDia = new Disponibilidad();
		unDia.setDia("SABADO");
		unDia.setRangoHorario(rango1);
		unDia.setRangoHorario(rango2);
		Servicio unServicio = new Servicio();
		Servicio otroServicio = new Servicio();
		unServicio.setServicio("apertura de caja de ahorro");
		unServicio.setDisponibilidad(unDia);
		otroServicio.setServicio("consultas varias");
		otroServicio.setDisponibilidad(unDia);
		System.out.println(unDia.getCadenaDiasHorarios());
		System.out.println(unServicio.getCadenaDisponibilidad());
		
		
		Comercio comercio1 = new Comercio("Mc Donalls", -34.5836168,-58.4060661, restaurante);		
		comercio1.setServicios(unServicio);

		System.out.println("Comercio Mc esta disponible hoy? ->" + dispositivo2.estaDisponible(comercio1,"", FechayHora.fechaHoy(), FechayHora.horaHoy()) );
		
		
	}

}
