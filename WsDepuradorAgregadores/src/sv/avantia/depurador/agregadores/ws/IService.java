package sv.avantia.depurador.agregadores.ws;

import sv.avantia.depurador.agregadores.object.Numeros;

public interface IService {

	/**
	 * Metodo web para dar de baja a los servicios de los agregadores
	 * 
	 * @author Edwin Mejia - Avantia Consultores
	 * @param numeros
	 * @param usuario
	 * @param password
	 * */
	public int bajarServicios(Numeros numeros, String usuario, String password);
	
	/**
	 * Metodo web para limpiar la lista negra de los agregadores
	 * 
	 * @author Edwin Mejia - Avantia Consultores
	 * @param numeros
	 * @param usuario
	 * @param password
	 * */
	//public String[] limpiarListaNegra(String[][] numeros, String usuario, String password);
}
