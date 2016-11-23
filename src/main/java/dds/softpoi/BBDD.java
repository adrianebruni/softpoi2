package dds.softpoi;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BBDD {
	private Connection connection = null;
	
	public Connection getConexion(){
		return this.connection;
	}
	
	public void crearConexionBBDD(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// El Jar Connector/J debe ser parte del build path
		} catch (ClassNotFoundException e) {
			System.out.println("No se puede cargar el driver JDBC");
			e.printStackTrace();
			return;
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "Unificada");
			// Parámetros: ("string de conexión a la base", "usuario", "contraseña") autorizados para acceder a la base sys

		} catch (SQLException e) {
			System.out.println("Error de conexión, ver mensaje:");
			e.printStackTrace();
			return;
		}
	}
	
	public void conectarBBDD(){
		System.out.println("-------- Prueba de conexión JDBC a MySQL ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// El Jar Connector/J debe ser parte del build path
		} catch (ClassNotFoundException e) {
			System.out.println("No se puede cargar el driver JDBC");
			e.printStackTrace();
			return;
		}

		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "Unificada");
			// Parámetros: ("string de conexión a la base", "usuario", "contraseña") autorizados para acceder a la base sys

		} catch (SQLException e) {
			System.out.println("Error de conexión, ver mensaje:");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println("Conectado a la base de datos");

			System.out.println("Creando consulta...");
			Statement stmt;
			try {
				stmt = connection.createStatement();
				String sql = "SELECT * FROM sys_config";
				ResultSet rs = stmt.executeQuery(sql);
				

				System.out.println("Registros de la columna VARIABLE de la tabla SYS_CONFIG:");
				while (rs.next()) {
					System.out.println("\t" + rs.getNString("variable"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		} else {
			System.out.println("Falló la conexión");
		}
	}
	
	public void persistirPOI(POI unPoi){
		String tipopoi;
		if (this.getConexion() == null){
			this.crearConexionBBDD();
		}
		//if(unPoi.getClass().getSimpleName() == "Banco"){
		//	tipopoi = unPoi.getClass().getSimpleName();
		//}
		
		tipopoi = unPoi.getClass().getSimpleName();
	
		String queryinsert;
		int idpoi = unPoi.getIdpoi();
		String nombre = unPoi.getNombre();
		double latitud = unPoi.getLatitud();
		double longitud = unPoi.getLongitud();
		queryinsert = "INSERT INTO DDS.POI (IDPOI, NOMBRE, LATITUD, LONGITUD, TIPOPOI)" +
		               "VALUES( " + idpoi + ", '" + 
				                    nombre   + "', " + 
				                    latitud +  ", " + 
		                            longitud + ", '" +
		                            tipopoi + "')" ;
		Statement stmt;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(queryinsert);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	public int obtenerProximoIdBBDD(){
		int proximoId=0;
		if (this.getConexion() == null){
			this.crearConexionBBDD();
		}
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT MAX(IDPOI) AS IDPOI FROM DDS.POI";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				proximoId = Integer.parseInt(rs.getString("IDPOI"));
			}

		} catch (SQLException e) { 	
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch(NumberFormatException ex) {
			System.out.println("Primer poi de la base");
			proximoId = 0;
		}
		proximoId = proximoId + 1;
		return proximoId;
	}
	
	public ArrayList<POI> obtenerTodaLaBBDD(){
		ArrayList<POI> coleccionPois = new ArrayList<POI>();
		if (this.getConexion() == null){
			this.crearConexionBBDD();
		}
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM DDS.POI";
			ResultSet rs = stmt.executeQuery(sql);
			POI unPoi;
			while (rs.next()) {
				if (rs.getString("TIPOPOI").equalsIgnoreCase("Banco")){
					unPoi = new Banco();
					unPoi.setIdpoi(Integer.parseInt(rs.getString("IDPOI")));
					unPoi.setNombre(rs.getString("NOMBRE"));
					unPoi.setLatitud(Double.parseDouble(rs.getString("LATITUD")));
					unPoi.setLongitud(Double.parseDouble(rs.getString("LONGITUD")));
					coleccionPois.add(unPoi);					
				}
			
				if (rs.getString("TIPOPOI").equalsIgnoreCase("Comercio")){
					unPoi = new Comercio();
					unPoi.setIdpoi(Integer.parseInt(rs.getString("IDPOI")));
					unPoi.setNombre(rs.getString("NOMBRE"));
					unPoi.setLatitud(Double.parseDouble(rs.getString("LATITUD")));
					unPoi.setLongitud(Double.parseDouble(rs.getString("LONGITUD")));
					coleccionPois.add(unPoi);					
				}
				
				if (rs.getString("TIPOPOI").equalsIgnoreCase("CGP")){
					unPoi = new CGP();
					unPoi.setIdpoi(Integer.parseInt(rs.getString("IDPOI")));
					unPoi.setNombre(rs.getString("NOMBRE"));
					unPoi.setLatitud(Double.parseDouble(rs.getString("LATITUD")));
					unPoi.setLongitud(Double.parseDouble(rs.getString("LONGITUD")));
					coleccionPois.add(unPoi);					
				}
				
				if (rs.getString("TIPOPOI").equalsIgnoreCase("ParadaColectivo")){
					unPoi = new ParadaColectivo();
					unPoi.setIdpoi(Integer.parseInt(rs.getString("IDPOI")));
					unPoi.setNombre(rs.getString("NOMBRE"));
					unPoi.setLatitud(Double.parseDouble(rs.getString("LATITUD")));
					unPoi.setLongitud(Double.parseDouble(rs.getString("LONGITUD")));
					coleccionPois.add(unPoi);					
				}
				
				
				System.out.println("\t" + rs.getString("NOMBRE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return coleccionPois;			
	}

	
}
