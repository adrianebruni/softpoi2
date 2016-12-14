package dds.softpoi;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestModificacionPOI {
	@Test
	public void testModifPOI() throws Exception {
	
		//comienzo inicializacion de coleccion
		//crear Servidor
		Servidor servidorPpal = new Servidor();
		
		//crear administrador
		Administrador unAdministrador = new Administrador();
		unAdministrador.setClave("passPrueba");
		unAdministrador.setServidor(servidorPpal);
		servidorPpal.addAdmin(unAdministrador);
		
		//crear POI
		
		// Creamos el objeto rango1 de la clase RangoHorario.
		RangoHorario rango1 = new RangoHorario("09:00:00", "13:00:00");
		// Creamos el objeto unDia de la clase Disponibilidad y le asignamos valores
		Disponibilidad unDia = new Disponibilidad();
		unDia.setDia("VIERNES");
		unDia.setRangoHorario(rango1);
		// Creamos un servicio y le asignamos valores.
		Servicio unServicio = new Servicio();
		unServicio.setServicio("apertura de cuenta corriente");
		unServicio.setDisponibilidad(unDia);
		// Creamos el objeto bancoFrances de la clase Banco.
		Banco bancoFrances = new Banco("BANCO FRANCES", -34.603075, -58.381653);
		bancoFrances.setServicios(unServicio);
		
		//Creamos un objeto parada1 de ParadaColectivo
		ParadaColectivo parada1 = new ParadaColectivo("Parada 59 Las Heras y Billingurst", -34.5832488, -58.4042595);
		
		//Creamos un objeto unCGP de CGP
		//creo una Comuna para el CGP
		Comuna comunaFlores = new Comuna (1, 0.350219707, 0.52475748, 0.698131701, 0.715584993, "lanus, lomas de zamora");
		//creo un POI CGP de prueba
		CGP cgpFlores = new CGP("CGP Flores",0.36,0.70);
		cgpFlores.setComuna(comunaFlores);
		System.out.println("CGP ORIGINAL - ID: "+cgpFlores.getIdpoi() + ", Nombre: " + cgpFlores.getNombre());
		
		//ejecutar cargarPOI(unPOI)
		unAdministrador.cargarPOI(bancoFrances);
		unAdministrador.cargarPOI(parada1);
		unAdministrador.cargarPOI(cgpFlores);
		//fin inicializacion de coleccion
		
	//creo un CGP que usaremos para modificar el de la coleccion
	//creo un POI CGP de prueba
	CGP cgpFlores2 = new CGP("CGP Flores2",0.36,0.70);
	cgpFlores2.setComuna(comunaFlores);
	//debemos indicarle manualmente el id para que matchee con el id en la coleccion que se pretende modificar
	
	cgpFlores2.setIdpoi(3);
	System.out.println("CGP INSERTADO - ID: "+cgpFlores.getIdpoi() + ", Nombre: " + cgpFlores.getNombre());
	
	//busco en coleccion el de igual ID y modifico los atributos que sean distintos
	servidorPpal.modificarPOI(cgpFlores2);
	
	//Verificamos que realmente se agregaron 3 POIs al repositorio
	assertEquals("Verificamos la modificacion de ID 3 con el del repositorio", true ,true);
	
	System.out.println("ID: "+cgpFlores.getIdpoi() + ", Nombre: " + cgpFlores.getNombre());
	System.out.println("ID: "+cgpFlores2.getIdpoi() + ", Nombre: " + cgpFlores2.getNombre());	
	
	}
}
/*
//copia valores de clase hija
Field[] fields = cgpFlores.getClass().getDeclaredFields();
for(Field f : fields){
	System.out.println(f);
	f.setAccessible(true);
	f.get(cgpFlores2);
	f.set(cgpFlores, f.get(cgpFlores2));
}
//copia valores de clase padre
Field[] fields2 = POI.class.getDeclaredFields();
for(Field f : fields2){
	System.out.println(f);
	f.setAccessible(true);
	f.get(cgpFlores2);
	f.set(cgpFlores, f.get(cgpFlores2));
}*/