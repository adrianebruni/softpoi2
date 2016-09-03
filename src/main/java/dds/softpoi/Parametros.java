package dds.softpoi;

public class Parametros {
	
	static double demoraconsulta = 9.003;
	static String emailCuenta = "softpoi.notificaciones@gmail.com";
	static String emailClave = "pN2f3WOV";
	static String urlJsonBanco = "http://trimatek.org/Consultas/banco";
	//                            http://trimatek.org/Consultas/banco?banco=&servicio=
	static String urlJsonCentro = "http://trimatek.org/Consultas/centro";
	
	
	public double getDemoraConsulta(){
		return demoraconsulta;
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
