package sv.avantia.implementacion;

public class HiloThread extends Thread{

	private String prueba;
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		setPrueba("MMMMM" + this.getName());
		try {
			this.sleep(5000);
			System.out.println(50/0);
		} catch (InterruptedException e) {
			e.printStackTrace();
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
	private void setPrueba(String prueba) {
		this.prueba = prueba;
	}
}
