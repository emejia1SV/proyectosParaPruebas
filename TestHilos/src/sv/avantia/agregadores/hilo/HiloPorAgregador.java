package sv.avantia.agregadores.hilo;

public class HiloPorAgregador implements Runnable {

	public HiloPorAgregador()
	{ }
	
	public HiloPorAgregador(String name)
	{ 
		setIdentificador(name);
	}
	
	private String prueba;
	private String identificador;

	@Override
	public void run() {
		try {
			setPrueba("pruebaaaa");
			System.out.println("Se generara error...." + getIdentificador());
			System.out.println(20/0);
		} catch (Exception e) {
			System.out.println("Existio un error en " + getIdentificador());
		}
	}

	/**
	 * @return the prueba
	 */
	public String getPrueba() {
		return prueba;
	}

	/**
	 * @param prueba the prueba to set
	 */
	public void setPrueba(String prueba) {
		this.prueba = prueba;
	}

	/**
	 * @return the identificador
	 */
	private String getIdentificador() {
		return identificador;
	}

	/**
	 * @param identificador the identificador to set
	 */
	private void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
}
