package sv.avantia.depurador.agregadores.test;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import sv.avantia.depurador.agregadores.object.Numeros;
import sv.avantia.depurador.agregadores.ws.WSDepurador;

public class TestMain {

	public static final String propertiesFile = "parametrosSistema.properties";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
test2();
	}

	private static void test1() {
		// TODO Auto-generated method stub
		ResourceBundle properties = ResourceBundle
				.getBundle("sv.avantia.depurador.agregadores.propiedades.parametrosSistema");
		for (Enumeration e = properties.getKeys(); e.hasMoreElements();) {
			Object obj = e.nextElement();
			System.out.println(properties.getString(obj.toString()));
		}
	}

	private static void test2() {
		WSDepurador stub = new WSDepurador();
		Numeros numeros = new Numeros();
		List<String> numeros2 = new ArrayList<String>();
		numeros2.add("34723894");
		numeros.setNumeros(numeros2);
		numeros.setPassword("pass");
		numeros.setUsuario("user");
		stub.bajarServicios(numeros);
	}
}
