package dds.softpoi;

import java.util.ArrayList;

public class RepoPOI {
	
	
	// aca vamos a cargar el manojo de pois 
	public ArrayList<POI> Dame_Bolsa_POI(){
		ArrayList<POI> bolsapois = new ArrayList<POI>();
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
		
		Servicio servicioCGP = new Servicio ();
		servicioCGP.setServicio("Cobro de Infracciones");
		servicioCGP.setDisponibilidad(unDia);
		
		Servicio servicioCGP2 = new Servicio();
		servicioCGP2.setServicio("Registro de Casamientos");
		servicioCGP2.setDisponibilidad(otroDia);
		servicioCGP2.setDisponibilidad(otroDia1);
		
		Servicio servRestaurant = new Servicio();
		servRestaurant.setServicio("Servir comidas");
		servRestaurant.setDisponibilidad(otroDia);
		
		Servicio servComercioRopa = new Servicio();
		servComercioRopa.setServicio("Vender zapatillas");
		servComercioRopa.setDisponibilidad(otroDia);
		
		
		// ***************************************************************************
		// Creo POIs
		// ***************************************************************************
		
		// Creamos el objeto bancoFrances de la clase Banco.
		Banco bancoFrances = new Banco("BANCO FRANCES", -34.603075, -58.381653);
		bancoFrances.setIdpoi(1);
		bancoFrances.setServicios(unServicio);
		bancoFrances.setServicios(otroServicio);
		
		bolsapois.add(bancoFrances);
		
		// Creamos el objeto bancoSantanderRio de la clase Banco.
		Banco bancoSantanderRio = new Banco("BANCO SANTANDER RIO",-34.605864, -58.380966);
		bancoSantanderRio.setIdpoi(2);
		bancoSantanderRio.setServicios(unServicio);
		
		bolsapois.add(bancoSantanderRio);
		
		// Creamos el objeto comuna 1 de la clase CGP.
		CGP comuna1 = new CGP ("COMUNA 1",-34.6001373,-58.3890716);
		comuna1.setIdpoi(3);
		comuna1.setServicios(servicioCGP);
		comuna1.setServicios(servicioCGP2);
		
		bolsapois.add(comuna1);
		
		// Creamos el objeto comuna 3 de la clase CGP.
		CGP comuna3 = new CGP ("COMUNA 3",-34.6029359,-58.3990901);
		comuna3.setIdpoi(4);
		comuna3.setServicios(servicioCGP);
		
		bolsapois.add(comuna3);
		
		// Creamos el objeto parada 1 de la clase ParadaColectivo.
		ParadaColectivo parada1 = new ParadaColectivo("Parada 59 Las Heras y Billingurst", -34.5832488, -58.4042595);
		parada1.setIdpoi(5);
		
		bolsapois.add(parada1);
		
		// Creamos el objeto parada 2 de la clase ParadaColectivo.
		ParadaColectivo parada2 = new ParadaColectivo("Parada 10 Las Heras y Ocampo", -34.5831289, -58.40384);
		parada2.setIdpoi(6);
		
		bolsapois.add(parada2);
		
		Comercio comercio1 = new Comercio("McWonalds", -34.6036961,-58.3843145, new Rubro("Restaurante", 900));
		comercio1.setPalabrasClave("Hamburguesas Patitas Gusanos");
		comercio1.setIdpoi(7);
		comercio1.setServicios(servRestaurant);
		
		bolsapois.add(comercio1);
		
		Comercio comercio2 = new Comercio("Adadis", -34.6033166,-58.3774477, new Rubro("Comercio Textil", 900));
		comercio2.setIdpoi(8);
		comercio2.setServicios(servComercioRopa);
		
		bolsapois.add(comercio2);
		//seguir agregando POIs de distintos tipos para usar en todos los test
			
		return bolsapois;
	}
}
