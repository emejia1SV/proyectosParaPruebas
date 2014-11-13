package sv.avantia.depurador.agregadores.object;

import java.io.Serializable;
import java.util.List;

public class Numeros implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<String> numeros;
	private String usuario;
	private String password;

	public List<String> getNumeros() {
		return numeros;
	}

	public void setNumeros(List<String> numeros) {
		this.numeros = numeros;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
