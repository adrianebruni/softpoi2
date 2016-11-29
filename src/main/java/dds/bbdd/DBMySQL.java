package dds.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import dds.softpoi.Administrador;
import dds.softpoi.Banco;
import dds.softpoi.CGP;
import dds.softpoi.Comercio;
import dds.softpoi.DispositivoConsulta;
import dds.softpoi.POI;
import dds.softpoi.ParadaColectivo;
import dds.softpoi.Parametros;

public class DBMySQL {
	
	// Parametros de configuracion de MySQL
	private Parametros objParametro = new Parametros();
		
	private Connection connection = null;
	
	public Connection getConexion(){
		if (connection == null){
			this.crearConexionBBDD();		
		}
		return this.connection;
	}
	
	public void cerrarConexion(){
		try {
			connection.close();
		} catch (SQLException e) {
			//e.printStackTrace();  
		}
		connection = null;
	}
	
	private void crearConexionBBDD(){
		try {
			Class.forName(objParametro.getMysqlJdbcDriver());
			// El Jar Connector/J debe ser parte del build path
		} catch (ClassNotFoundException e) {
			System.out.println("No se puede cargar el driver JDBC");
			e.printStackTrace();
			return;
		}
		
		try {
			connection = DriverManager.getConnection(objParametro.getMysqlURL(), objParametro.getMysqlUser(), objParametro.getMysqlPass());
		} catch (SQLException e) {
			System.out.println("Error de conexión:");
			e.printStackTrace();
			return;
		}
	}

	/************************************************************************************************************ 
	 * 
	 * 
	 * 	BUSQUEDAS
	 * 
	 * 
	 ************************************************************************************************************ */
	
	// Devuelve todos los administradores que se encuentran en la base de datos
	public ArrayList<Administrador> buscarAdministradores(){
		String Query = "SELECT * FROM DDS.ADMINISTRADOR";
		
		ArrayList<Administrador> colAux = new ArrayList<Administrador>();
		Administrador unAdmin = null;
		ResultSet rs = null;
		
		// Verificamos que exista la conexion con la BBDD
		if (this.getConexion() == null){
			this.crearConexionBBDD();
		}
		
		Statement stmt;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(Query);
		} catch (Exception e) {
			System.out.println("ERROR: BBDD.java - buscarAdministradores >> No se pudo crear Statement o ResultSet");
			return colAux;
		} 
		
		// Obtenemos los datos de la base en MySQL
		try {
			while (rs.next()) {
				unAdmin = new Administrador();
				
				unAdmin.setId_usuario(rs.getInt("id_usuario"));
				unAdmin.setClave(rs.getString("clave"));
				unAdmin.setNombre(rs.getString("nombre"));
				unAdmin.setEmail(rs.getString("email"));
				unAdmin.setFlagAuditoriaBusqueda(rs.getBoolean("flagAuditoriaBusqueda"));
				unAdmin.setFlagNotificaciones(rs.getBoolean("flagNotificaciones"));
				
				colAux.add(unAdmin);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: BBDD.java - buscarAdministradores >> No se pudo obtener los datos");
			e.printStackTrace();
		}
		
		// retornamos los administradores
		return colAux;
		
	}
	
	// Devuelve el administrador que se encuentran en la base de datos segun el ID solicitado.
	public Administrador buscarAdministrador(String id_usuario){
		
		Administrador unAdmin = null;
		
		if ( (id_usuario.trim().isEmpty()) || (id_usuario == null) ){
			return unAdmin;
		}
		
		String Query = "SELECT * FROM DDS.ADMINISTRADOR WHERE id_usuario = " + id_usuario;
				
		ResultSet rs = null;
		
		// Verificamos que exista la conexion con la BBDD
		if (this.getConexion() == null){
			this.crearConexionBBDD();
		}
		
		Statement stmt;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(Query);
		} catch (Exception e) {
			System.out.println("ERROR: BBDD.java - buscarAdministradores >> No se pudo crear Statement o ResultSet");
			return unAdmin;
		} 
		
		// Obtenemos los datos de la base en MySQL
		try {
			while (rs.next()) {
				unAdmin = new Administrador();
				unAdmin.setId_usuario(rs.getInt("id_usuario"));
				unAdmin.setClave(rs.getString("clave"));
				unAdmin.setNombre(rs.getString("nombre"));
				unAdmin.setEmail(rs.getString("email"));
				unAdmin.setFlagAuditoriaBusqueda(rs.getBoolean("flagAuditoriaBusqueda"));
				unAdmin.setFlagNotificaciones(rs.getBoolean("flagNotificaciones"));
			}
		} catch (SQLException e) {
			System.out.println("ERROR: BBDD.java - buscarAdministradores >> No se pudo obtener los datos");
			e.printStackTrace();
		}
		
		// retornamos los administrador
		return unAdmin;
		
	}	
	
	// Devuelve todas las terminales que se encuentran en la base de datos
	public ArrayList<DispositivoConsulta> buscarTerminales(){
		String Query = "SELECT * FROM DDS.TERMINAL";
		
		ArrayList<DispositivoConsulta> colAux = new ArrayList<DispositivoConsulta>();
		DispositivoConsulta unaTerminal = null;
		ResultSet rs = null;
		
		// Verificamos que exista la conexion con la BBDD
		if (this.getConexion() == null){
			this.crearConexionBBDD();
		}
		
		Statement stmt;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(Query);
		} catch (Exception e) {
			System.out.println("ERROR: BBDD.java - buscarTerminales >> No se pudo crear Statement o ResultSet");
			return colAux;
		}
		
		// Obtenemos los datos de la base en MySQL
		try {
			while (rs.next()) {
				unaTerminal = new DispositivoConsulta();
				
				unaTerminal.setId_usuario(rs.getInt("id_usuario"));
				unaTerminal.setClave(rs.getString("clave"));
				unaTerminal.setNombre(rs.getString("nombre"));
				//unaTerminal.setZona(rs.getString("zona"));
				unaTerminal.setLongitud(rs.getDouble("longitud"));
				unaTerminal.setLatitud(rs.getDouble("latitud"));
				unaTerminal.setFlagAuditoriaBusqueda(rs.getBoolean("flagAuditoriaBusqueda"));
				unaTerminal.setFlagNotificaciones(rs.getBoolean("flagNotificaciones"));
				
				colAux.add(unaTerminal);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: BBDD.java - buscarTerminales >> No se pudo obtener los datos");
			e.printStackTrace();
		}	
		
		// retornamos los administradores
		return colAux;
		
	}

	
	
	// Devuelve todas las terminales que se encuentran en la base de datos
	public DispositivoConsulta buscarTerminal(String id_usuario){
		
		DispositivoConsulta unaTerminal = null;
		
		if ( (id_usuario.trim().isEmpty()) || (id_usuario == null) ){
			return unaTerminal;
		}
		
		String Query = "SELECT * FROM DDS.TERMINAL WHERE id_usuario = " + id_usuario;
				
		ResultSet rs = null;
		
		// Verificamos que exista la conexion con la BBDD
		if (this.getConexion() == null){
			this.crearConexionBBDD();
		}
		
		Statement stmt;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(Query);
		} catch (Exception e) {
			System.out.println("ERROR: BBDD.java - buscarTerminal >> No se pudo crear Statement o ResultSet");
			e.printStackTrace();
			return unaTerminal;
		} 
		
		// Obtenemos los datos de la base en MySQL
		try {
			while (rs.next()) {
				unaTerminal = new DispositivoConsulta();
				
				unaTerminal.setId_usuario(rs.getInt("id_usuario"));
				unaTerminal.setClave(rs.getString("clave"));
				unaTerminal.setNombre(rs.getString("nombre"));
				//unaTerminal.setZona(rs.getString("zona"));
				unaTerminal.setLongitud(rs.getDouble("longitud"));
				unaTerminal.setLatitud(rs.getDouble("latitud"));
				unaTerminal.setFlagAuditoriaBusqueda(rs.getBoolean("flagAuditoriaBusqueda"));
				unaTerminal.setFlagNotificaciones(rs.getBoolean("flagNotificaciones"));
			}
		} catch (SQLException e) {
			System.out.println("ERROR: BBDD.java - buscarTerminal >> No se pudo obtener los datos");
			e.printStackTrace();
		}	
		
		// retornamos los administradores
		return unaTerminal;
		
	}	
	
	
	
	// Devuelve todos los POIs que se encuentran en la base de datos, segun el 'valorBuscado' por el campo 'key'
	// si 'busquedaExacta' es falso, entonces hace 'like %%'
	public ArrayList<POI> buscarPOIs(String key, String valorBuscado, boolean busquedaExacta){
		String Query = "SELECT * FROM DDS.POI WHERE " + key;
		
		ArrayList<POI> colPois = new ArrayList<POI>();
		ResultSet rs = null;
		
		// Verificamos que el valor buscado no este vacio o null
		if (valorBuscado.trim().isEmpty() || valorBuscado == null){
			return colPois;
		}
			
		
		// Verificamos que exista la conexion con la BBDD
		if (this.getConexion() == null){
			this.crearConexionBBDD();
		}
		

		if (busquedaExacta){
			Query = Query + " = '" + valorBuscado + "'";
		}else{
			Query = Query + " like '%" + valorBuscado + "%'";
		}
		
		
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(Query);
		} catch (Exception e) {
			System.out.println("ERROR: BBDD.java - buscarPOIs >> No se pudo crear Statement o ResultSet");
			return colPois;
		}
		
		POI unPoi;
		try {
			while (rs.next()) {
				
				switch (rs.getString("TIPOPOI")){		
					case "Banco":
						unPoi = new Banco();
						break;
					
					case "CGP":
						unPoi = new CGP();
						break;
						
					case "Comercio":
						unPoi = new Comercio();
						break;
						
					case "ParadaColectivo":
						unPoi = new ParadaColectivo();
						break;
					default:
						return null;
				}
				
				// Obtenemos los datos de la base
				unPoi.setIdpoi(Integer.parseInt(rs.getString("IDPOI")));
				unPoi.setNombre(rs.getString("NOMBRE"));
				unPoi.setLatitud(Double.parseDouble(rs.getString("LATITUD")));
				unPoi.setLongitud(Double.parseDouble(rs.getString("LONGITUD")));

				// Agrego el poi a la coleccion
				colPois.add(unPoi);	
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return colPois;			
	}
	
	public int obtenerProximoIdBBDD(){
		int proximoId=0;
		if (this.getConexion() == null){
			this.crearConexionBBDD();
		}
		try {
			Statement stmt = connection.createStatement();
			String Query = "SELECT MAX(IDPOI) AS IDPOI FROM DDS.POI";
			ResultSet rs = stmt.executeQuery(Query);
			while (rs.next()) {
				proximoId = Integer.parseInt(rs.getString("IDPOI"));
			}

		} catch (SQLException e) { 	
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
			
			String Query = "SELECT * FROM DDS.POI";
			
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(Query);
			POI unPoi;
			while (rs.next()) {
				
				switch (rs.getString("TIPOPOI")){		
					case "Banco":
						unPoi = new Banco();
						break;
					
					case "CGP":
						unPoi = new CGP();
						break;
						
					case "Comercio":
						unPoi = new Comercio();
						break;
						
					case "ParadaColectivo":
						unPoi = new ParadaColectivo();
						break;
					default:
						return null;
				}
				
				// Obtenemos los datos de la base
				unPoi.setIdpoi(Integer.parseInt(rs.getString("IDPOI")));
				unPoi.setNombre(rs.getString("NOMBRE"));
				unPoi.setLatitud(Double.parseDouble(rs.getString("LATITUD")));
				unPoi.setLongitud(Double.parseDouble(rs.getString("LONGITUD")));
				
				coleccionPois.add(unPoi);					
				
				System.out.println("\t" + rs.getString("NOMBRE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coleccionPois;			
	}
	
	/************************************************************************************************************ 
	 * 
	 * 
	 * 	ALTAS
	 * 
	 * 
	 ************************************************************************************************************ */
	
	public void altaAdministrador(Administrador unAdmin){
		
		if (this.connection == null){
			this.crearConexionBBDD();
		}
		
		String nombre = unAdmin.getNombre();
		String clave = unAdmin.getClave();
		String email = unAdmin.getEmail();
		boolean flagAuditoriaBusqueda = unAdmin.getFlagAuditoriaBusqueda();
		boolean flagNotificaciones = unAdmin.getFlagNotificaciones();
		
		String Query = "INSERT INTO DDS.ADMINISTRADOR (nombre, clave, email, flagAuditoriaBusqueda, flagNotificaciones) VALUES(?, ?, ?, ?, ?)"; 
		
		try {
			PreparedStatement PStmt = (PreparedStatement) connection.prepareStatement(Query);
			
			PStmt.setString(1, nombre);
			PStmt.setString(2, clave);
			PStmt.setString(3, email);
			PStmt.setBoolean(4, flagAuditoriaBusqueda);
			PStmt.setBoolean(5, flagNotificaciones);
			
			PStmt.execute();
			PStmt.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		
	}
	
	public void altaTerminal(DispositivoConsulta unaTerminal){
		
		if (this.connection == null){
			this.crearConexionBBDD();
		}
		
		int idusr = unaTerminal.getId_usuario();
		String nombre = unaTerminal.getNombre();
		String clave = unaTerminal.getClave();
		Double latitud = unaTerminal.getLatitud();
		Double longitud = unaTerminal.getLongitud();
		//String zona = unaTerminal.getZona();
		boolean flagAuditoriaBusqueda = unaTerminal.getFlagAuditoriaBusqueda();
		boolean flagNotificaciones = unaTerminal.getFlagNotificaciones();
		
		String Query = "INSERT INTO DDS.TERMINAL (id_usuario, nombre, clave, latitud, longitud, flagAuditoriaBusqueda, flagNotificaciones)" +
		              "VALUES( " + idusr + ","
				                 + "'" + nombre + "'," 
				                 + "'" + clave +  "'," 
				                 + latitud + ","
				                 + longitud + ","
		                         + flagAuditoriaBusqueda + ","
		                         + flagNotificaciones + ")";
		
		Statement stmt;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(Query);
		} catch (SQLException e) {
			e.printStackTrace();
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
	
		int idpoi = unPoi.getIdpoi();
		String nombre = unPoi.getNombre();
		double latitud = unPoi.getLatitud();
		double longitud = unPoi.getLongitud();	
		String Query = "INSERT INTO DDS.POI (IDPOI, NOMBRE, LATITUD, LONGITUD, TIPOPOI)" +
	               	  "VALUES(" + idpoi + ","
	               	  			+ "'" + nombre + "',"
	               	            + latitud + ","
	               	  			+ longitud + ","
	               	            + "'" + tipopoi + "'"
	               	      + ")";
		
		
		
		Statement stmt;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(Query);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}

	/************************************************************************************************************ 
	 * 
	 * 
	 * 	BAJAS
	 * 
	 * 
	 ************************************************************************************************************ */
	
	public void bajaAdministrador(String id_usuario){
		
		if (this.connection == null){
			this.crearConexionBBDD();
		}
				
		String query = "DELETE FROM DDS.ADMINISTRADOR WHERE id_usuario = " + id_usuario;
		
		/* DEBUG
		System.out.println("--------------------------------------------------------------");
		System.out.println(query);
		System.out.println("--------------------------------------------------------------");
		*/
		
		Statement stmt;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void bajaTerminal(String id_usuario){
		
		if (this.connection == null){
			this.crearConexionBBDD();
		}
				
		String query = "DELETE FROM DDS.TERMINAL WHERE id_usuario = " + id_usuario;
		
		Statement stmt;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void eliminarPOI(int idPoi){
		
		if (this.connection == null){
			this.crearConexionBBDD();
		}
		
		String query;
	
		query = "DELETE FROM DDS.POI WHERE IDPOI = " + idPoi;
		Statement stmt;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
	/************************************************************************************************************ 
	 * 
	 * 
	 * 	MODIFICACIONES
	 * 
	 * 
	 ************************************************************************************************************ */	

	public void modificarAdministrador(Administrador unAdmin){
		
		if (this.connection == null){
			this.crearConexionBBDD();
		}
		
		int id_usuario = unAdmin.getId_usuario();
		String nombre = unAdmin.getNombre();
		String clave = unAdmin.getClave();
		String email = unAdmin.getEmail();
		boolean flagAuditoriaBusqueda = unAdmin.getFlagAuditoriaBusqueda();
		boolean flagNotificaciones = unAdmin.getFlagNotificaciones();
				
		String Query = "UPDATE DDS.ADMINISTRADOR SET nombre = ?, clave = ?, email = ?, flagAuditoriaBusqueda = ?, flagNotificaciones = ? WHERE id_usuario = ?"; 
		
		try {
			PreparedStatement PStmt = (PreparedStatement) connection.prepareStatement(Query);
			
			// Valores
			PStmt.setString(1, nombre);
			PStmt.setString(2, clave);
			PStmt.setString(3, email);
			PStmt.setBoolean(4, flagAuditoriaBusqueda);
			PStmt.setBoolean(5, flagNotificaciones);
			
			// Condicion
			PStmt.setInt(6, id_usuario);
			
			PStmt.execute();
			PStmt.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}


	public void modificarTerminal(DispositivoConsulta unaTerminal){
		
		if (this.connection == null){
			this.crearConexionBBDD();
		}
		
		int id_usuario = unaTerminal.getId_usuario();
		String nombre = unaTerminal.getNombre();
		String clave = unaTerminal.getClave();
		double longitud = unaTerminal.getLongitud();
		double latitud = unaTerminal.getLatitud();
		boolean flagAuditoriaBusqueda = unaTerminal.getFlagAuditoriaBusqueda();
		boolean flagNotificaciones = unaTerminal.getFlagNotificaciones();
				
		String Query = "UPDATE DDS.TERMINAL SET "
				+ "nombre = ?, clave = ?, longitud = ?, latitud = ?, flagAuditoriaBusqueda = ?, flagNotificaciones = ? "
				+ "WHERE id_usuario = ?"; 
		
		try {
			PreparedStatement PStmt = (PreparedStatement) connection.prepareStatement(Query);
			
			// Valores
			PStmt.setString(1, nombre);
			PStmt.setString(2, clave);
			PStmt.setDouble(3, longitud);
			PStmt.setDouble(4, latitud);
			PStmt.setBoolean(5, flagAuditoriaBusqueda);
			PStmt.setBoolean(6, flagNotificaciones);
			
			// Condicion
			PStmt.setInt(7, id_usuario);
			
			PStmt.execute();
			PStmt.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
