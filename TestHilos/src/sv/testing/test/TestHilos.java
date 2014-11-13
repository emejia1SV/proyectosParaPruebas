package sv.testing.test;

import sv.avantia.agregadores.hilo.ConsultaAgregadorPorHilo;
import sv.avantia.agregadores.hilo.ThreadUtilities;

public class TestHilos {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestHilos tc = new TestHilos();
		tc.testHilos();
	}
	
	@SuppressWarnings("unused")
	private static void testHilos(){
		try {
			
			//ConsultaAgregadorPorHilo hilo2 = new ConsultaAgregadorPorHilo("hilo2");
			//ConsultaAgregadorPorHilo hilo3 = new ConsultaAgregadorPorHilo("hilo3");
			
			boolean detenido1 = false;
			boolean detenido2 = false;
			boolean detenido3 = false;
			
			int indice = 3;
			for (int i = 0; i < indice; i++) {
				ConsultaAgregadorPorHilo hilo = new ConsultaAgregadorPorHilo(null,"hi	lo"+i);
				hilo.start();
			}
			
			/*while (true) {
				if (hilo1.isAlive()) {
					if(hilo1.getPrueba()!=null){
						System.out.println("Hilo1 " + hilo1.getPrueba());
						hilo1.stop();
						detenido1 = true;
					}
				}
				
				if (hilo2.isAlive()) {
					if(hilo2.getPrueba()!=null){
						System.out.println("Hilo2 " + hilo2.getPrueba());
						hilo2.stop();
						detenido2 = true;
					}
				}
				
				if (hilo3.isAlive()) {
					if(hilo3.getPrueba()!=null){
						System.out.println("Hilo3 " + hilo3.getPrueba());
						hilo3.stop();
						detenido3 = true;
					}
				}
				
				if(detenido1 && detenido2 && detenido3){
					System.out.println("Salgamonos");
					break;
				}
			}*/
			
			System.out.println("se termino la ejecucion del programa");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	private void func() {
	      try {     
	    
	         for (int i = 0; i < 3; i++) {
					ConsultaAgregadorPorHilo hilo = new ConsultaAgregadorPorHilo(ThreadUtilities.getRootThreadGroup(), "hilo"+i);
					hilo.start();
			}
	            
	        for (ConsultaAgregadorPorHilo hilox : (ConsultaAgregadorPorHilo[])ThreadUtilities.getAllThreads()) {
	        	if (hilox.isAlive()) {
					if(hilox.getPrueba()!=null){
						System.out.println(hilox.getName()+" " + hilox.getPrueba());
						hilox.stop();
					}
				}
			} 
	      }
	      catch (Exception ex) {
	         System.out.println(ex.toString());
	      }
	   }
}
