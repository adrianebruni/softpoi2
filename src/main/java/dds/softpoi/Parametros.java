package dds.softpoi;

public class Parametros {
	
	// Parametro de configuracion que indica si se le envia un email a los administradores
	// si la consulta demora mas que "demoraConsulta"
	static double demoraConsulta = 9.003;
	
	static String rutaXMLBajas = "./src/test/java/dds/softpoi/archivobajas.xml";

	static String rutaComerciosPalabrasClave1 = "./src/test/java/dds/softpoi/ComerciosPalabrasClave";
	static String rutaComerciosPalabrasClave2 = "./src/test/java/dds/softpoi/ComerciosPalabrasClave2";
	
	// ./softpoi2/WebContent/WEB-INF/img/
	// ./WebContent/WEB-INF/img/
	static String rutaImagenesIconos = "/img/";
	static String rutaImagenesIconosAbsoluta = "/home/usuario/dds/Proyectos-Java/softpoi2/WebContent/img/";
	
	// Parametros de configuracion JSON
	static String urlJsonBanco = "http://trimatek.org/Consultas/banco";
	static String urlJsonCentro = "http://trimatek.org/Consultas/centro";
	
	// Parametros de configuracion para el envio de correos de alertas
	static String emailCuenta = "softpoi.notificaciones@gmail.com";
	static String emailClave = "pN2f3WOV";
	static String emailPuerto = "587";
	static String emailAutenticacion = "true";
	static String emailCifradoTLS = "true";
	static String emailMetodoEnvio = "smtp";
	static String emailHostEnvio = "smtp.gmail.com";
	
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
