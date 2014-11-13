package sv.avantia.agregadores.hilo;

public class ConsultaAgregadorPorHilo extends Thread {

	private String prueba;
	
	public ConsultaAgregadorPorHilo(ThreadGroup pGroup, String name)
	{
        super(pGroup, name);
	}
	
	public ConsultaAgregadorPorHilo()
	{ }
	
	public void run() {
		System.out.println(this.getName());
		try {
			this.sleep(5000);
			setPrueba("pruebaaaa");
			this.sleep(9000);
			System.out.println("Se generara error...." + this.getName());
			System.out.println(20/0);
		} catch (Exception e) {
			System.out.println("Existio un error en " + this.getName());
			this.interrupt();
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
}
