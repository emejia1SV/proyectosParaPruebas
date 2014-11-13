package test.clases;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.wsdl.Definition;
import javax.wsdl.Message;
import javax.wsdl.Part;
import javax.wsdl.Port;
import javax.wsdl.Service;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import com.ibm.wsdl.extensions.soap.SOAPAddressImpl;
import com.ibm.wsdl.extensions.soap12.SOAP12AddressImpl;

public class TestReadWSDL {

	private static final String URI_BROWSER = "http://192.168.0.100:8090/axis2/services/depuradorAgregadores.depuradorAgregadoresHttpSoap11Endpoint/";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			// get hold the WSDLFactory
			WSDLFactory factory = WSDLFactory.newInstance();

			// create an object to read the WSDL file
			WSDLReader reader = factory.newWSDLReader();

			// pass the URL to the reader for parsing and get back a WSDL
			// definiton
			Definition wsdlInstance = reader
					.readWSDL(null,
							"http://192.168.0.100:8090/axis2/services/depuradorAgregadores?wsdl");

			leerMessages(wsdlInstance);
			setServiceDefinition(wsdlInstance);
			/*// get a map of the five specific parts a WSDL file
			Types types = wsdlInstance.getTypes();
			Map messages = wsdlInstance.getMessages();
			Map portTypes = wsdlInstance.getPortTypes();
			Map bindings = wsdlInstance.getBindings();
			Map services = wsdlInstance.getServices();

			Iterator<?> it = services.entrySet().iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}*/

			/**
			 * perform lookup of types, messages, portTypes, bindings and
			 * services
			 */
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private static void leerMessages(Definition definition){
		Map messages = definition.getMessages();
		
		Iterator<?> messagesItr = messages.entrySet().iterator();
		while(messagesItr.hasNext()){
			Map.Entry msgEntry = (Map.Entry) messagesItr.next();
			Message message = (Message) msgEntry.getValue();
			System.out.println("--------------MSG");
			System.out.println(message.toString());
			
			Map parts = message.getParts();
			Iterator<?> partsItr = parts.entrySet().iterator();
			while(partsItr.hasNext()){
				Map.Entry partEntry = (Map.Entry) partsItr.next();
				Part part = (Part) partEntry.getValue();
				System.out.println("--------------PART");
				System.out.println(part.getExtensionAttributes().values());
				List<?> x = part.getExtensibilityElements();
				for (int i = 0; i < x.size(); i++) {
					System.out.println(x.get(i));
				}
			}
		}
	}
	
	private static void setServiceDefinition(Definition definition) throws Exception {

		@SuppressWarnings("rawtypes")
		Map serviceMap = definition.getAllServices();
		Iterator<?> serviceItr = serviceMap.entrySet().iterator();
		URL addressURI = null;
		
		
		try {
			while (serviceItr.hasNext()) {
				Map.Entry svcEntry = (Map.Entry) serviceItr.next();
				
				Service svc = (Service) svcEntry.getValue();
				
				Map portMap = svc.getPorts();
				
				Iterator portItr = portMap.entrySet().iterator();
				
				while (portItr.hasNext()) {
					Map.Entry portEntry = (Map.Entry) portItr.next();
					
					Port port = (Port) portEntry.getValue();
					
					ExtensibilityElement extensibilityElement = (ExtensibilityElement) port.getExtensibilityElements().get(0);

					addressURI = new URL(getAddressUrl(extensibilityElement));
					
					if(addressURI.toString().equals(URI_BROWSER)){
						System.out.println(addressURI);
						URL endpoint = new URL(URI_BROWSER);
						msgSoap(endpoint);
						break;
					}
					

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		 
	}
	
	private static String getAddressUrl(ExtensibilityElement exElement){
		if (exElement instanceof SOAP12AddressImpl) {
			 return ((SOAP12AddressImpl) exElement).getLocationURI();
			
		}else if(exElement instanceof SOAPAddressImpl) {
			 return ((SOAPAddressImpl) exElement).getLocationURI();
			
		}else{
			System.out.println("No soporta el WSDL");
		}
		return "";
	}
	
	
	private static void msgSoap(Object endPt){
		try {
			SOAPConnectionFactory myFct = SOAPConnectionFactory.newInstance();
			SOAPConnection myCon = myFct.createConnection();
			MessageFactory myMsgFct = MessageFactory.newInstance();
			SOAPMessage message = myMsgFct.createMessage();
			
			SOAPPart mySPart = message.getSOAPPart();
			SOAPEnvelope myEnvp = mySPart.getEnvelope();
			
			SOAPBody body = myEnvp.getBody();

			Name servicioName = myEnvp.createName("bajarServicios");
			SOAPBodyElement servicio = body.addBodyElement(servicioName);

			message.saveChanges();
			
			Name numerosName = myEnvp.createName("numeros");
			SOAPElement numeros = servicio.addChildElement(numerosName);

			message.saveChanges();
			
			Name numeroName = myEnvp.createName("numeros");
			SOAPElement unNumero = numeros.addChildElement(numeroName);
			unNumero.addTextNode("545154854");

			message.saveChanges();
			
			Name usuarioName = myEnvp.createName("usuario");
			SOAPElement usuario = servicio.addChildElement(usuarioName);
			usuario.addTextNode("ed");

			message.saveChanges();
			
			Name passName = myEnvp.createName("password");
			SOAPElement pass = servicio.addChildElement(passName);
			pass.addTextNode("123");

			message.saveChanges();

			System.out.println("request: ");
			System.out.println(message.getSOAPBody());
			//URLEndpoint endPt = new URLEndpoint("http://eztrade.com//quotes");

			SOAPMessage reply = myCon.call(message, endPt);
			System.out.println("response: ");
			System.out.println(reply.getSOAPBody().getTextContent());
            
            myCon.close();
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


/*
SOAPBody body = myEnvp.getBody();

Name bodyName = myEnvp.createName("bajarServicios", "ws", endPt.toString());
SOAPBodyElement gltp = body.addBodyElement(bodyName);

Name numeros = myEnvp.createName("numeros", "ws", endPt.toString());
SOAPElement losNumero = gltp.addChildElement(numeros);

Name numero = myEnvp.createName("numeros", "xsd", endPt.toString());
SOAPElement unNumero = losNumero.addChildElement(numero);
unNumero.addTextNode("545154854");

Name user = myEnvp.createName("usuario", "ws", endPt.toString());
SOAPElement usuario = gltp.addChildElement(user);
usuario.addTextNode("ed");

Name pass = myEnvp.createName("password", "ws", endPt.toString());
SOAPElement password = gltp.addChildElement(pass);
password.addTextNode("123");

message.saveChanges();

System.out.println("request: ");
System.out.println(message.getSOAPBody().getTextContent());
//URLEndpoint endPt = new URLEndpoint("http://eztrade.com//quotes");

SOAPMessage reply = myCon.call(message, endPt);
System.out.println("response: ");
System.out.println(reply.getSOAPBody().getTextContent());*/
