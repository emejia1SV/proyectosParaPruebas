package sv.avantia.soap.test;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;

public class TestSoapComunication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
	        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

	        String xml= "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cad=\"http://cadena.webservices.test.com\"><soapenv:Header/><soapenv:Body><cad:saludo><!--Optional:--><cad:texto>prueba</cad:texto></cad:saludo></soapenv:Body></soapenv:Envelope>";
	        
	        MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage message = messageFactory.createMessage(new MimeHeaders(), new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));
	        
	        // Send SOAP Message to SOAP Server
	        String url = "http://192.168.0.100:8090/axis2/services/pruebaWsCadena.pruebaWsCadenaHttpSoap11Endpoint/";
	        SOAPMessage soapResponse = soapConnection.call(message, url);

	        // print SOAP Response
	        System.out.println("Response SOAP Message:");
	        soapResponse.writeTo(System.out);

	        soapConnection.close();
			//invokeOperation(null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
