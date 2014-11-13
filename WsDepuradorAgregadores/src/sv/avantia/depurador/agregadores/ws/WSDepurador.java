package sv.avantia.depurador.agregadores.ws;


import sv.avantia.depurador.agregadores.modelo.ModeloLogica;
import sv.avantia.depurador.agregadores.object.Numeros;

public class WSDepurador  {

	public int bajarServicios(Numeros numeros) {
		return new ModeloLogica().acciones(numeros);
	}
}
