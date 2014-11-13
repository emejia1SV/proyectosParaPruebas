package sv.avantia.implementacion.hilos;

import java.util.concurrent.Callable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CallableTest implements Callable<Document> {

	@Override
	public Document call() throws Exception {
		return generar();
	}

	public Document generar() throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();
		// Create from whole cloth
		Element root = (Element) document.createElement("respuesta");
		document.appendChild(root);
		
		Element agregador = (Element) document.createElement("nombreagregador");
		Element depuracion = (Element) document.createElement("Depuracion");
		Element listaNegra = (Element) document.createElement("listaNegra");
		Element consulta = (Element) document.createElement("consulta");
		Element baja = (Element) document.createElement("baja");
		
		Element numero = (Element) document.createElement("numero");
		Element codigoError = (Element) document.createElement("codigoError");
		Element descripcionEstado = (Element) document.createElement("descripcionEstado");
		
		root.appendChild(agregador);
		agregador.appendChild(depuracion);
		depuracion.appendChild(listaNegra);
		depuracion.appendChild(consulta);
		depuracion.appendChild(baja);
		
		listaNegra.appendChild(numero);
		listaNegra.appendChild(codigoError);
		listaNegra.appendChild(descripcionEstado);
		
		consulta.appendChild(numero);
		consulta.appendChild(codigoError);
		consulta.appendChild(descripcionEstado);
		
		baja.appendChild(numero);
		baja.appendChild(codigoError);
		baja.appendChild(descripcionEstado);
		
		numero.appendChild(document.createTextNode("23423"));
		codigoError.appendChild(document.createTextNode("sdfsdfsdf sf sdf d "));
		descripcionEstado.appendChild(document.createTextNode("sf d fsd f sdf sd"));

		document.getDocumentElement().normalize();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return document;

	}
	

}
