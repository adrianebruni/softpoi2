package dds.softpoi;

public class Parametros {
	
	// Parametro de configuracion que indica si se le envia un email a los administradores
	// si la consulta demora mas que "demoraConsulta"
	private static double demoraConsulta = 9.003;
	
	private static String rutaXMLBajas = "./src/test/java/dds/softpoi/archivobajas.xml";

	private static String rutaComerciosPalabrasClave1 = "./src/test/java/dds/softpoi/ComerciosPalabrasClave";
	private static String rutaComerciosPalabrasClave2 = "./src/test/java/dds/softpoi/ComerciosPalabrasClave2";
	
	// ./softpoi2/WebContent/WEB-INF/img/
	// ./WebContent/WEB-INF/img/
	private static String rutaImagenesIconos = "/img/";
	private static String rutaImagenesIconosAbsoluta = "/home/usuario/dds/Proyectos-Java/softpoi2/WebContent/img/";
	
	// Parametros de configuracion JSON
	private static String urlJsonBanco = "http://trimatek.org/Consultas/banco";
	private static String urlJsonCentro = "http://trimatek.org/Consultas/centro";
	
	// Parametros de configuracion para el envio de correos de alertas
	private static String emailCuenta = "softpoi.notificaciones@gmail.com";
	private static String emailClave = "pN2f3WOV";
	private static String emailPuerto = "587";
	private static String emailAutenticacion = "true";
	private static String emailCifradoTLS = "true";
	private static String emailMetodoEnvio = "smtp";
	private static String emailHostEnvio = "smtp.gmail.com";
	
	// Parametros de configuracion para MongoDB
	private static String servidorMongoDB = "localhost";
	private static int puertoMongoDB = 27017;
	private static String baseMongoDB = "db_mongo_softpoi";
	//static String tablaMongoPOIsExternosBancos = "pois_extero_banco";
	//static String tablaMongoPOIsExternosCentros = "pois_extero_centro";
	private static String tablaMongoPOIsExternos = "pois_exteros";
	private static String tablaMongoHistoricoConsultas = "historico";
	
	// Parametros de configuracion para MySQL
	private static final String MySQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final int MySQL_PORT = 3306;
	private static final String MySQL_HOST = "localhost";
	private static final String MySQL_DBNAME = "DDS";
	private static final String MySQL_URL = "jdbc:mysql://" + MySQL_HOST + ":" + MySQL_PORT + "/" + MySQL_DBNAME;
	private static final String MySQL_USER = "root";
	private static final String MySQL_PASS = "";
	
	public String getMysqlJdbcDriver() {
		return MySQL_JDBC_DRIVER;
	}

	public int getMysqlPort() {
		return MySQL_PORT;
	}

	public String getMysqlHost() {
		return MySQL_HOST;
	}

	public String getMysqlDBName() {
		return MySQL_DBNAME;
	}

	public String getMysqlURL() {
		return MySQL_URL;
	}

	public String getMysqlUser() {
		return MySQL_USER;
	}

	public String getMysqlPass() {
		return MySQL_PASS;
	}
	
	public String getBaseMongoDB(){
		return baseMongoDB;
	}
	
	public String getTablaMongoPOIsExternos(){
		return tablaMongoPOIsExternos;
	}
	
	/*
	public String getTablaMongoPOIsExternosCentros(){
		return tablaMongoPOIsExternosCentros;
	}
	
	public String getTablaMongoPOIsExternosBancos(){
		return tablaMongoPOIsExternosBancos;
	}
	*/
	
	public String getTablaMongoHistoricoConsultas(){
		return tablaMongoHistoricoConsultas;
	}
	
	public String getServidorMongoDB(){
		return servidorMongoDB;
	}

	public int getPuertoMongoDB(){
		return puertoMongoDB;
	}
	
	public String getRutaImagenesIconosAbsoluta(){
		return rutaImagenesIconosAbsoluta;
	}
	
	public String getRutaImagenesIconos(){
		return rutaImagenesIconos;
	}
	
	public String getEmailPuerto() {
		return emailPuerto;
	}

	public String getEmailAutenticacion() {
		return emailAutenticacion;
	}

	public String getEmailCifradoTLS() {
		return emailCifradoTLS;
	}

	public String getEmailMetodoEnvio() {
		return emailMetodoEnvio;
	}

	public String getEmailHostEnvio() {
		return emailHostEnvio;
	}	
	
	public double getDemoraConsulta(){
		return demoraConsulta;
	}
	
	public String getEmailCuenta(){
		return emailCuenta;
	}
	
	public String getEmailClave(){
		return emailClave;
	}
	
	public String getUrlJsonBanco(){
		return urlJsonBanco;
	}

	public String getUrlJsonCentro(){
		return urlJsonCentro;
	}
	
	public String getRutaXMLBajas() {
		return rutaXMLBajas;
	}

	public void setRutaXMLBajas(String rutaXMLBajas) {
		Parametros.rutaXMLBajas = rutaXMLBajas;
	}

	public String getRutaComerciosPalabrasClave1() {
		return rutaComerciosPalabrasClave1;
	}

	public void setRutaComerciosPalabrasClave1(String rutaComerciosPalabrasClave1) {
		Parametros.rutaComerciosPalabrasClave1 = rutaComerciosPalabrasClave1;
	}

	public String getRutaComerciosPalabrasClave2() {
		return rutaComerciosPalabrasClave2;
	}

	public void setRutaComerciosPalabrasClave2(String rutaComerciosPalabrasClave2) {
		Parametros.rutaComerciosPalabrasClave2 = rutaComerciosPalabrasClave2;
	}

	
}
