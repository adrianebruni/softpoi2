package dds.softpoi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;


@SuppressWarnings("unused")
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Iniciando Test SoftPOI !!");
		System.out.println("-------------------------");
		//IntelliJ Idea
		
		
		// TODO Auto-generated method stub
		//System.out.println("Aca empieza la verdad de la milanga...");
		//ParadaColectivo parada1 = new ParadaColectivo("",90.0f,91);
//		System.out.println(parada1.estaDisponible());
		//System.out.println(parada1.esValido());
		//System.out.println(parada1.getClass());
		//ParadaColectivo obelisco = new ParadaColectivo("Obelisco",-34.603075, -58.381653);
		//ParadaColectivo plazamayo = new ParadaColectivo("Plaza de Mayo",-34.608333, -58.371944);
//		System.out.println("esta a menos metros que el otro? = " + obelisco.estaAMenosDe(plazamayo, 1300));
		
		String[] dias = {"DOMINGO", "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO"};
		
		System.out.println(dias[1]);
		
		
		/*
		// prueba en base a fechas
		LocalDate date = LocalDate.of(2016, Month.OCTOBER, 15);
		DayOfWeek dia=date.getDayOfWeek();
		
		if (dia.toString() == "SABADO" || dia.toString() == "SATURDAY"){
			System.out.println("Mi cumple es el dia %s y cae un %s", date,dia);
		}else{
			System.out.println("aaaaa %s y cae un %s", date,dia);
		}
		*/
		
		/*
		// *********************************************************************************************
		// Prueba de clase Banco
		// *********************************************************************************************
		// Creamos el objeto rango1 de la clase RangoHorario.
		RangoHorario rango1 = new RangoHorario(LocalTime.of(9, 00, 00), LocalTime.of(13, 00, 00));
		RangoHorario rango2 = new RangoHorario(LocalTime.of(15, 30, 00), LocalTime.of(19, 00, 00));
		RangoHorario rango3 = new RangoHorario(LocalTime.of(7, 35, 10), LocalTime.of(10, 30, 59));
		
		// Creamos el objeto unDia de la clase Disponibilidad y le asignamos valores
		Disponibilidad unDia = new Disponibilidad();
		unDia.setDia("SAB");
		unDia.agregarRangoHorario(rango1);
		unDia.agregarRangoHorario(rango2);
		Disponibilidad otroDia = new Disponibilidad();
		otroDia.setDia("DOM");
		otroDia.agregarRangoHorario(rango3);

		
		// Creamos un servicio y le asignamos valores.
		Servicio unServicio = new Servicio();
		unServicio.setServicio("apertura de cuenta sueldo");
		unServicio.agregarDisponibilidad(unDia);
		unServicio.agregarDisponibilidad(otroDia);
		
		// Creamos el objeto bancoFrances de la clase Banco.
		Banco bancoFrances = new Banco("BANCO FRANCES", -34.603075, -58.381653);
		bancoFrances.agregarServicio(unServicio);
		
		// Mostramos los datos cargados
		System.out.println("BANCO: " + bancoFrances.nombre);
		System.out.println("Longitud: " + bancoFrances.longitud);
		System.out.println("Latitud: " + bancoFrances.latitud);
		System.out.println("TipoPOI: " + bancoFrances.tipoPOI());
		
		System.out.println("Un Servicio: " + bancoFrances.dameUnServicio().getServicio());
		
		System.out.println("Cantidad de Dias Habiles: " + bancoFrances.dameUnServicio().getDisponibilidad().size());
		System.out.println("Dia Habil 1: " + bancoFrances.dameUnServicio().getDisponibilidad().get(0).getDia());
		System.out.println("Dia Habil 1 - Apertura: " + bancoFrances.dameUnServicio().getDisponibilidad().get(0).getHorarios().get(0).getApertura());
		System.out.println("Dia Habil 1 - Cierre: " + bancoFrances.dameUnServicio().getDisponibilidad().get(0).getHorarios().get(0).getCierre());
		System.out.println("Dia Habil 1 - Apertura: " + bancoFrances.dameUnServicio().getDisponibilidad().get(0).getHorarios().get(1).getApertura());
		System.out.println("Dia Habil 1 - Cierre: " + bancoFrances.dameUnServicio().getDisponibilidad().get(0).getHorarios().get(1).getCierre());
		
		System.out.println("Dia Habil 2: " + bancoFrances.dameUnServicio().getDisponibilidad().get(1).getDia());
		System.out.println("Dia Habil 2 - Apertura: " + bancoFrances.dameUnServicio().getDisponibilidad().get(1).getHorarios().get(0).getApertura());
		System.out.println("Dia Habil 2 - Cierre: " + bancoFrances.dameUnServicio().getDisponibilidad().get(1).getHorarios().get(0).getCierre());
		*/
		
		/*
		public static class CentroDeserializer implements JsonDeserializer<CentroDTO>{

			@Override
			public CentroDTO deserialize(final JsonElement jsonCentro,final Type t, JsonDeserializationContext context) throws JsonParseException {
				
				// Creamos un objeto del tipo Json
				final JsonObject jsonObject = jsonCentro.getAsJsonObject();
				
				// Creamos un elemento del tipo Json (indicado en el enunciado del TP)
				final JsonElement jsonComuna = jsonObject.get("comuna");
				
				
				JsonElement comuna = ((JsonObject)arrayCentro.get(0)).get("comuna");
				
				
				// Creamos una variable del mismo tipo y asignamos su valor
				final int comuna = jsonComuna.getAsInt();
				
				// Creamos un objeto del tipo CentroDTO y le asignamos los valores obtenidos de Json.
				final CentroDTO unCentroDTO = new CentroDTO();			
				unCentroDTO.setComuna(comuna);

				return unCentroDTO;
			}
		}
		*/
		
	}

}

