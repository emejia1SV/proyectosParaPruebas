package com.avantia.test;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class TestXMLReader {

	//private static String xml = "<SOAP-ENV:Envelope SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"http://webservices.binbit.com/claro_gt/claro_gt.php\" xmlns:ns2=\"http://xml.apache.org/xml-soap\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><SOAP-ENV:Body><ns1:listaNegraResponse><return SOAP-ENC:arrayType=\"ns2:Map[1]\" xsi:type=\"SOAP-ENC:Array\"><item xsi:type=\"ns2:Map\"><item><key xsi:type=\"xsd:string\">Resp</key><value xsi:type=\"xsd:int\">1</value></item></item></return></ns1:listaNegraResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
	
	//private static String xml ="<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:wsagregadores\"><soapenv:Header/><soapenv:Body><urn:listaNegra soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"><movil xsi:type=\"xsd:string\">_*movil_*</movil><accion xsi:type=\"xsd:string\">_*accion_*</accion><pass xsi:type=\"xsd:string\">_*pass_*</pass></urn:listaNegra></soapenv:Body></soapenv:Envelope>";
	
	private static String xml = "<numeros><movil>50377494676</movil><movil>50377504963</movil><movil>50379748568</movil></numeros>";
	
	//private static String xml = "<respuesta><error><codigoError>10005</codigoError><descripcionEstado>blabla</descripcionEstado></error></respuesta>";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testBuildXmlDom();
	}
	
	private static void test2(){
		TestXMLReader yo = new TestXMLReader();
		Document doc = yo.xmlErrorSDA();
		
		if(doc==null){
			System.out.println("No se obtuvo data en la respuesta recibida...");
		}
		
		if(doc.getDocumentElement()==null){
			System.out.println("No se obtuvo data en la respuesta recibida...");
		}
		
		doc.getDocumentElement().normalize();
		
		System.out.println(doc.getDocumentElement().getNodeName());
		
		//verificamos si obtuvimos algún error al invocar el metodo.
		if (doc.getDocumentElement().getFirstChild().getNodeName().equals("errorX")) {
			System.out.println(doc.getDocumentElement().getFirstChild().getLastChild().getTextContent());
			return;
		}else{
			System.out.println("no se hayo");
		}
	}
	
	private static void testBuildXmlDom(){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();  
		      // Create from whole cloth
		      Element root = (Element)document.createElement("respuesta"); 
		      document.appendChild(root);
		      root.appendChild(document.createTextNode("Some") );
		      root.appendChild( document.createTextNode(" ") );
		      root.appendChild( document.createTextNode("text") );
		      
		      document.getDocumentElement().normalize();
		      
		      
		      
		} catch (ParserConfigurationException e) {
			System.out.println("vaya cosa error al crear la respuesta");
		}
	}
	
	/**
	 * Find the named subnode in a node's sublist. <li>Ignores comments and
	 * processing instructions. <li>Ignores TEXT nodes (likely to exist and
	 * contain ignorable whitespace, if not validating. <li>Ignores CDATA nodes
	 * and EntityRef nodes. <li>Examines element nodes to find one with the
	 * specified name. </ul>
	 * 
	 * @param name
	 *            the tag name for the element to find
	 * @param node
	 *            the element node to start searching from
	 * @return the Node found
	 */
	public Node findSubNode(String name, Node node) 
	{
		if (node.getNodeType() != Node.ELEMENT_NODE) 
		{
			System.err.println("Error: Search node not of element type");
			System.exit(22);
		}

		if (!node.hasChildNodes())
			return null;

		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) 
		{
			Node subnode = list.item(i);
			if (subnode.getNodeType() == Node.ELEMENT_NODE) 
			{
				if (subnode.getNodeName().equals(name))
					return subnode;
			}
		}
		return null;
	}
	
	/**
	 * Return the text that a node contains. This routine:
	 * <ul>
	 * <li>Ignores comments and processing instructions.
	 * <li>Concatenates TEXT nodes, CDATA nodes, and the results of recursively
	 * processing EntityRef nodes.
	 * <li>Ignores any element nodes in the sublist. (Other possible options are
	 * to recurse into element sublists or throw an exception.)
	 * </ul>
	 * 
	 * @param node
	 *            a DOM node
	 * @return a String representing its contents
	 */
	public String getText(Node node) 
	{
		StringBuffer result = new StringBuffer();
		if (!node.hasChildNodes())
			return "";

		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) 
		{
			Node subnode = list.item(i);
			if (subnode.getNodeType() == Node.TEXT_NODE) 
			{
				result.append(subnode.getNodeValue());
			} else if (subnode.getNodeType() == Node.CDATA_SECTION_NODE) 
			{
				result.append(subnode.getNodeValue());
			} else if (subnode.getNodeType() == Node.ENTITY_REFERENCE_NODE) 
			{
				// Recurse into the subtree for text
				// (and ignore comments)
				result.append(getText(subnode));
			}
		}
		return result.toString();
	}
	
	/**
	 * Metodo que me generara el String en formato XML como respuesta para poder
	 * devolver cualquier excepcion que sea expuesta a esta altura de clases de
	 * invoacion a los agregadores
	 * 
	 * @author Edwin Mejia - Avantia Consultores
	 * @param error
	 *            {@link ErroresSDA} return String con formato XML
	 * @return {@link Document}
	 * */
	protected Document xmlErrorSDA()
	{
		return getdocumentFromString("<respuesta><errorX><codigoError>1004</codigoError><descripcionEstado>blablabla</descripcionEstado></errorX></respuesta>");
	}
	
	/**
	 * Metodo para la transformacion de un {@link String} a un {@link Document}
	 * 
	 * @author Edwin Mejia - Avantia Consultores
	 * @param doc
	 *            {@link String} return {@link Document}
	 * */
	protected Document getdocumentFromString(String doc)
	{
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new ByteArrayInputStream(doc.getBytes("utf-8")))  );
			return document;
		} catch (Exception e) {
			System.out.println("NO SE PUEDE GENERAR EL PARSEO DE STRING A DOCUMENT");
			return null;
		}
	}
	
	private static void test(){
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = builderFactory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8")))  );
			doc.getDocumentElement().normalize();
			if (doc.getDocumentElement().hasChildNodes()) 
			{
				NodeList nodeList = doc.getDocumentElement().getChildNodes();
				//lecturaDeDatos(nodeList);
				lecturaListaTelefonos(nodeList, "movil");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo recursivo, para la lectura de nodos del Soap Response que se ha
	 * recibido de la consulta de los {@link Agregadores} a su vez este metodo
	 * se encarga de guardar en la base de datos as {@link Respuesta} obtenidas
	 * 
	 * @author Edwin Mejia - Avantia Consultores
	 * @param nodeList
	 *            {@link NodeList}
	 * @param nodeNameToReader
	 *            {@link String} nombre del nodo que andamos buscando dentro del listado
	 * @return {@link Void}
	 * @throws Exception 
	 * */
	private static void lecturaListaTelefonos(NodeList nodeList, String nodeNameToReader) {
		for (int i = 0; i < nodeList.getLength(); i++) 
		{
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) 
			{
				if(node.getNodeName().equalsIgnoreCase(nodeNameToReader))
				{
					if(node.getFirstChild()!=null || node.getFirstChild().getNodeValue()!=null)
					{
						System.out.println(node.getTextContent());
					}
				}
				
				//recursive
				if (node.hasChildNodes())
					lecturaListaTelefonos(node.getChildNodes(), nodeNameToReader);
			}
		}
	}
	
	private static void lecturaDeDatos(NodeList nodeList) throws Exception {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if (node.hasChildNodes())
					lecturaDeDatos(node.getChildNodes());
				if(node.getTextContent().startsWith("_*") && node.getNodeName().contains(node.getTextContent().substring(2, node.getTextContent().length()-2)))
					System.out.println(node.getTextContent().substring(2, node.getTextContent().length()-2));
			}
		}
	}
	
	private static synchronized void lecturaCompleta(Document doc, String dato) throws Exception {
		
		doc.getDocumentElement().normalize();
		if (doc.getDocumentElement().hasChildNodes()) {
			NodeList nodeList = doc.getDocumentElement().getChildNodes();
			lecturaListadoNodos2(nodeList, dato);
		}
	}
	
	private static void lecturaListadoNodos2(NodeList nodeList, String nodeNameToReader) throws Exception {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if(node.getNodeName().equals(nodeNameToReader))
				{
					if(node.getFirstChild()==null || node.getFirstChild().getNodeValue()==null){
						if(indiceServicios>0){
							//********* BAJA DE SERVICIOS **********//
							System.out.println("Es hora de invocar al servicio de baja Tiene el servicio  activo? " );
							
						}
						indiceServicios ++;
						indice = 1;
					}else{
						
						System.out.println(node.getFirstChild().getNodeValue().trim());
						
						
						indice++;
					}
				}
				
				if (node.hasChildNodes())
					lecturaListadoNodos2(node.getChildNodes(), nodeNameToReader);
			}
		}
		
		
	}
	
	private static int indiceServicios = 0;
	private static int indice = 1;
	
	
	

}
