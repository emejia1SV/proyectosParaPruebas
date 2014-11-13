public class PruebaTimeout {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private Object value;

	/**
	 * Metodo para invocar un Servicio Web de forma dinamica según los
	 * parametros obligatorios definidos address namespaceURI serviceName
	 * portName operationNameInvoke definitionArgument returnType
	 * 
	 * @return Respuesta del ServicioWeb
	 * @throws Exception
	 */
	public Object respuestaWebService() throws Exception {
		Thread taskInvoke;
		try {
			Runnable run = new Runnable() {
				public void run() {
					try {
						// value = ((Object) call.invoke(parametros.toArray()));
					} catch (Exception ex) {
						System.out.println("Exception: " + ex.getMessage());
						ex.printStackTrace();
					}
				}

			};

			taskInvoke = new Thread(run, "AInvocacionWebService");
			taskInvoke.start();

			int m_seconds = 1;
			int contSeconds = 0;
			while (true) {
				Thread.sleep(m_seconds * 1000);
				contSeconds += m_seconds;
				if (value != null) {
					break;
				}
				if (contSeconds >= 3000/* this.getTimeOutSeconds() */) {
					taskInvoke.interrupt();
					throw new Exception(
							"Network timeout occurred…. terminating");
				}
			}
			if (taskInvoke.isAlive()) {
				taskInvoke.interrupt();
			}
			return value;
		} catch (Exception e) {
			// call = null;
			throw new Exception(e);
		}
	}
}
