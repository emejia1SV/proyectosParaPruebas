package sv.avantia.implementacion.hilos;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;


public class TestCallable {

	Document response = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestCallable callable = new TestCallable();
		callable.prueba();
    }
	
	@SuppressWarnings("deprecation")
	public void prueba(){
		//Get ExecutorService from Executors utility class, thread pool size is 10
        ExecutorService executor = Executors.newFixedThreadPool(2);
  
        Callable<Document> callable = new CallableTest();
        final Future<Document> future2 = executor.submit(callable);
        
        System.out.println("probar tiempo" );
        long endTimeOut = System.currentTimeMillis() + 3000;
        Thread taskInvoke;
		try {
			Runnable run = new Runnable() {
				public void run() {
					try {
						response = future2.get();
					} catch (Exception ex) {
						System.out.println("Exception: " + ex.getMessage());
						ex.printStackTrace();
					}
				}

			};

			taskInvoke = new Thread(run, "AInvocacionWebService");
			taskInvoke.start();

			
			while (true) {
				if (response != null) {
					break;
				}
				if (System.currentTimeMillis() > endTimeOut) {
					System.err.println(	"Network timeout occurred…. terminating");
					taskInvoke.stop();
					break;
				}
			}
			if (taskInvoke.isAlive()) {
				taskInvoke.stop();
			}
			xmlOut(response, new StreamResult(System.out));
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        //shut down the executor service
        executor.shutdown();
	}

	/**
	 * Metodo Para darle Salida al archivo document recibido como parametro
	 * 
	 * @author Edwin Mejia - Avantia Consultores
	 * @param document
	 * @return void
	 * @throws javax.xml.transform.TransformerException
	 * */
	public static void xmlOut( org.w3c.dom.Node document, javax.xml.transform.stream.StreamResult result)  
			throws javax.xml.transform.TransformerException 
	{
		// usamos una fabrica de transformacion para la salida del document
		javax.xml.transform.TransformerFactory tFactory = javax.xml.transform.TransformerFactory.newInstance();
		javax.xml.transform.Transformer transformer;
		try {
			transformer = tFactory.newTransformer();
		} 
		catch (javax.xml.transform.TransformerConfigurationException e1) 
		{
			throw new javax.xml.transform.TransformerConfigurationException("error en la fabrica de transformación");
		}

		
		try {
			// cargamos nuestro insumo para la transformacion
			javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(document);
			//transformacion
			transformer.transform(source, result);
		} 
		catch (javax.xml.transform.TransformerException e) 
		{
			throw new javax.xml.transform.TransformerException("error en la transformación");
		}
	}
}
