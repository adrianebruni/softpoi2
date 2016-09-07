package dds.softpoi;

public class Parametros {
	
	// Parametro de configuracion que indica si se le envia un email a los administradores
	// si la consulta demora mas que "demoraConsulta"
	static double demoraConsulta = 9.003;
	
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
	
}
