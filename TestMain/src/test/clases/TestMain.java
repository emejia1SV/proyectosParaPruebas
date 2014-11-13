package test.clases;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.rpc.encoding.XMLType;
import javax.xml.soap.SOAPException;

import org.w3c.dom.Document;

public class TestMain {

	private static ResourceBundle rb = ResourceBundle.getBundle("sv.avantia.depurador.agregadores.propiedades.parametrosSistema");
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String x = "Soy leyenda (si son pajas)";
		System.out.println(x);
		System.out.println(x.replaceAll(" ", "_").replaceAll("\\(", "").replaceAll("\\)", ""));
		
		Set<String> myset = new HashSet<String>();
		myset.add("pepe");
		myset.add("pipo");
		
		for (String string : myset) {
			System.out.println(string);
		}
		
		System.out.println(myset.iterator().next());
	}
	
	
	public static void test5(){
		try {
			System.out.println("print1");
		} 
		finally{
			System.out.println("print2");
		}
		
		try {
			System.out.println("print3");
			System.out.println(20/0);
		} catch (Exception e) {
			System.out.println("print4");
		}finally{
			System.out.println("print5");
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			System.out.println("otro mas");
		}
		
		try {
			throw new SOAPException("timeOutExcepction");
		} catch (Exception e) {
			throw new RuntimeException("te pelas");
		}

		
		//String numero = "5037745";
		//System.out.println(numero.substring(0, 3));
		//System.out.println(java.util.UUID.randomUUID());
		
		/*String prueba ="este es el caso _*caso_* donde yo deseo _*caso_* reemplazar";
		String test = ("_*".concat("caso").concat("_*")).toString();
		System.out.println(test);
		System.out.println(prueba);
		prueba = prueba.replace(test, "COSAAAA");
		System.out.println(prueba);*/
	}
	
	public static void test4(){
		try {
			String urlWSDLFile = "C:/Users/Edwin/Desktop/pruebaWSDL.xml";
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	        File file = new File(urlWSDLFile);
	        Document doc = docBuilder.parse(file);
	        System.out.println(doc.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void test3(){
		Enumeration <String> keys = rb.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String value = rb.getString(key);
			System.out.println(key + ": " + value);
		}
	}
	
	public static void test1(){
		String movil="";
		//String[] out = {"","","","","","","",""};
		String[] out = new String[10];
		if(movil.isEmpty()){
			out[1]="1";
			System.out.println(out[1]);
		}
		else{
			out[1]="0";
			System.out.println(out[1]);
		}
	}
	
	public static void test2(){
		WebServicesClient stub = new WebServicesClient();
		Map<String, Object> definitionArgument = new HashMap<String, Object>();
		
		definitionArgument.put("movil", "");
		definitionArgument.put("Servicio", "servicio");
		definitionArgument.put("mCort", 0);
		definitionArgument.put("pass", "pass");

		try {			
			stub.setAddress("http://localhost:8090/axis2/services/WS_Agregador2.WS_Agregador2HttpEndpoint/");
			stub.setNamespaceURI("http://webservices.agregador1.test");
			stub.setReturnType(XMLType.SOAP_ARRAY);
			stub.setServiceName("WS_Agregador2");
			stub.setPortName("WS_Agregador2HttpEndpoin");
			stub.setDefinitionArgument(definitionArgument);
			stub.setOpertationNameInvoke("darBaja");
			stub.setTimeOutSeconds(4000);
			
			System.out.println("Respuesta recibida: ");
			System.out.println(stub.respuestaWebService());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}