package sv.com.avantia.cliente.rpc;

import java.util.HashMap;
import java.util.Map;

import javax.xml.rpc.encoding.XMLType;

public class TestRPC {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestRPC rpc = new TestRPC();
		try {
			rpc.prueba("que ondas ahi vos");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para realizar la actualizacion de las estretgias unicamente
	 * enviando el alias de la estrategia
	 * 
	 * @author Emejia - Avantia Consultores
	 * @throws Exception
	 * */
	public void prueba(String nombre) throws Exception{
		Map<String, Object> definitionArgument = new HashMap<String, Object>();
		try {
			definitionArgument.put("numerosEnXML", "<numeros><movil>50257128949</movil></numeros>");
			//definitionArgument.put("pass", "w@uCEN539");
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		
		try {
			System.out.println(consultarSMG3(definitionArgument, "depuracionMasiva"));
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	/**
	 * Llenar con los parametros requeridos para el llamdo de el web services
	 * para la comunicacion con las librerias del motor SMG3
	 * 
	 * @author Emejia - Avantia Consultores
	 * @param definitionArgument
	 *            - es en objeto vienen los dos marametros listos para consumir
	 *            el web services SMG3 donde viene el objeto y el dato para el
	 *            trace level del SMG3
	 * @param operacion
	 *            - este es el nombre de la operacion que se va a efectuar
	 *            dentro de servicio web ya sea para consultar una estrategia o
	 *            para actualizar dichas estrategias
	 * @throws Exception
	 * */
	private String consultarSMG3(Map<String, Object> definitionArgument, String operacion) throws Exception{
		WebServicesClient stub = new WebServicesClient();
		//1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
		//QName qname = new QName("http://webservices.smg3.bbmass.com.sv/", "DACallWSService");
		//Localmente >> stub.setAddress("http://sv01d000n6116:9081/SMG3_HTTP/DACallWSService");
		//DESARROLLO CLUSTER >> stub.setAddress("http://sv4044aap.daviviendasv.com:9121/SMG3_HTTP/DACallWSService");
		//BUS >>stub.setAddress("http://sv4012lap.daviviendasv.com/SMG3_HTTP/DACallWSService");
		stub.setAddress("http://192.168.0.100:8090/axis2/services/ServicioWebSDA.ServicioWebSDAHttpSoap11Endpoint/");
		stub.setNamespaceURI("http://web.servicio.agregadores.depurador.avantia.sv");
		stub.setReturnType(XMLType.XSD_STRING);//XMLType.XSD_STRING or Qname
		stub.setServiceName("ServicioWebSDA");
		stub.setPortName("ServicioWebSDAHttpSoap11Endpoint");			
		stub.setDefinitionArgument(definitionArgument);
		stub.setOpertationNameInvoke(operacion); //"DACallPREBURO"
		stub.setTimeOutSeconds(125000);
		
		return (String) stub.respuestaWebService();
	}
}
