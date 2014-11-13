package test.clases;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.Service;
import javax.xml.rpc.encoding.XMLType;


public class WebServicesClient {

	/*VARIABLES DEFINIDAS POR CLIENTE WEBSERVICE*/
	private String address;							
	private String namespaceURI;					
	private String serviceName;
	private String portName;
	private String opertationNameInvoke;
	private Map<String, Object> definitionArgument;
	private QName returnType;
	private int timeOutMiliseconds;
	private Object value;
		
	/*VARIABLES CLASE BASE*/
	private List<Object> parametros;
	private Call call;
	
	public WebServicesClient() {
		
	}

	public WebServicesClient(String address, String namespaceURI, String serviceName, String portName, String opertationNameInvoke, Map<String, Object> definitionArgument, QName returnType) {
		super();
		this.address = address;
		this.namespaceURI = namespaceURI;
		this.serviceName = serviceName;
		this.portName = portName;
		this.opertationNameInvoke = opertationNameInvoke;
		this.definitionArgument = definitionArgument;
		this.returnType = returnType;
	}

	/**
	 * Metodo para invocar un Servicio Web de forma dinamica según los parametros obligatorios definidos
	 * address
	 * namespaceURI
	 * serviceName
	 * portName
	 * operationNameInvoke
	 * definitionArgument
	 * returnType
	 * @return Respuesta del ServicioWeb
	 * @throws Exception
	 */
	public synchronized Object respuestaWebService() throws Exception{
		validador();
		//Thread taskInvoke;
		
		System.out.println("Create an instance of the Service with the given service QName");
		ServiceFactory factory = ServiceFactory.newInstance();
		
		Service service = factory.createService(new QName(getServiceName()));
        call = service.createCall(new QName(getPortName()));
        call.setTargetEndpointAddress(getAddress());
        call.setOperationName(new QName(namespaceURI,getOpertationNameInvoke()));
        call.setReturnType(returnType);
        
        System.out.println("LLENAR PARAMETROS");
        llenarParametros();
        
        /*Runnable run = new Runnable() {

			public void run() {
				try {
					System.out.println("invocacion: ");
					value = ((Object) call.invoke(parametros.toArray()));
				} catch (Exception ex) {
						System.out.println("Exception: " + ex.getMessage());
						ex.printStackTrace();
				}
			}
	
		};
	
		long init = System.currentTimeMillis();
		taskInvoke = new Thread(run, "AInvocacionWebService");
		taskInvoke.start();*/
	
		long init = System.currentTimeMillis();
        
        try {
			
        	value = ((Object) call.invoke(parametros.toArray()));
        	
        } catch (Exception e) {
			call = null;
			throw new Exception(e);
		}
        
        try{
			long endTimeOut = System.currentTimeMillis() + this.getTimeOutSeconds();
			while(true){
				if(System.currentTimeMillis() > endTimeOut){
					//taskInvoke.interrupt();
					throw new Exception("Network timeout occurred.... terminating");
				}else{
					if (value != null) {
						System.out.println("tiempo tardado en la invocacion: " + (System.currentTimeMillis() - init));
						break;
					}
				}
			}
			
			/*if (taskInvoke.isAlive()) {
				taskInvoke.interrupt();
			}*/
			return value;
		} catch (Exception e) {
			call = null;
			throw new Exception(e);
		}
	}
	
	
	
	
	/**
	 * Metodo para invocar un Servicio Web de forma dinamica según los parametros obligatorios
	 * @param address URL Servicio Web
	 * @param namespaceURI Valor del targetNamespace
	 * @param serviceName Nombre del Servicio Web
	 * @param portName Nombre del port del Servicio Web
	 * @param operationNameInvoke Operacion a invocar del Servicio Web
	 * @param definitionArgument Definicion de los argumentos a ser ingresados en el Metodo a invocar del Servicio Web
	 * @param returnType Tipo de dato a retornar XMLType
	 * @return Respuesta del ServicioWeb
	 * @throws Exception
	 */
	public Object respuestaWebService(String address, String namespaceURI, String serviceName, String portName, String opertationNameInvoke, Map<String, Object> definitionArgument, QName returnType) throws Exception{
		this.address = address;
		this.namespaceURI = namespaceURI;
		this.serviceName = serviceName;
		this.portName = portName;
		this.opertationNameInvoke = opertationNameInvoke;
		this.definitionArgument = definitionArgument;
		this.returnType = returnType;
		
		return respuestaWebService();
	}
	
	
	
	
	private void llenarParametros() throws Exception{
		parametros = new ArrayList<Object>();
		System.out.println("PARAMETROS");
		for(Map.Entry<String, Object> e : definitionArgument.entrySet()){
			
			String key = e.getKey();
			Object value = e.getValue();
			
			System.out.println(key);
			System.out.println(value);
			
			if(e.getKey().equals("arg0")){
				System.out.println("lleno data ");
				call.addParameter(key, XMLType.XSD_STRING, ParameterMode.IN);
				parametros.add(value);
			}
			if(e.getKey().equals("arg1")){
				System.out.println("lleno el tracelevel ");
				call.addParameter(key, XMLType.XSD_INT, ParameterMode.IN);
				parametros.add(((Integer) value));
			}
		}
	}
	
	
	
	/***
	 * Validaciones de las variables obligatorias
	 * @throws Exception
	 */
	private void validador() throws Exception{
		if(getAddress() == null  || getAddress().equalsIgnoreCase("")){
			throw new Exception("Variable requerida address Nula o Vacia");
		}
		if(getNamespaceURI() == null  || getNamespaceURI().equalsIgnoreCase("")){
			throw new Exception("Variable requerida namespaceURI Nula o Vacia");
		}
		if(getServiceName() == null  || getServiceName().equalsIgnoreCase("")){
			throw new Exception("Variable requerida serviceName Nula o Vacia");
		}
		if(getPortName() == null  || getPortName().equalsIgnoreCase("")){
			throw new Exception("Variable requerida portName Nula o Vacia");
		}
		if(getOpertationNameInvoke() == null  || getOpertationNameInvoke().equalsIgnoreCase("")){
			throw new Exception("Variable requerida opertationNameInvoke Nula o Vacia");
		}
		if(getReturnType() == null){
			throw new Exception("Variable requerida returnType Nula");
		}
	}
	
	
	


	public String getAddress() {
		return address;
	}

	/***
	 * Aplicar URL del Servicio Web
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	public String getNamespaceURI() {
		return namespaceURI;
	}

	/***
	 * Aplicar targetNamespace del Servicio Web
	 * @param namespaceURI
	 */
	public void setNamespaceURI(String namespaceURI) {
		this.namespaceURI = namespaceURI;
	}


	public String getPortName() {
		return portName;
	}

	/***
	 * Aplicar Nombre del Port del Servicio Web
	 * @param portName
	 */
	public void setPortName(String portName) {
		this.portName = portName;
	}


	public String getServiceName() {
		return serviceName;
	}

	/***
	 * Aplicar Nombre del Servicio Web
	 * @param serviceName
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}


	public String getOpertationNameInvoke() {
		return opertationNameInvoke;
	}

	/***
	 * Aplicar Operacion del Servicio Web a invocar
	 * @param opertationNameInvoke
	 */
	public void setOpertationNameInvoke(String opertationNameInvoke) {
		this.opertationNameInvoke = opertationNameInvoke;
	}

	/***
	 * Aplicar tipo de dato retorno a obtener en la respuesta del Servicio Web
	 * @param returnType
	 */
	public void setReturnType(QName returnType) {
		this.returnType = returnType;
	}


	public QName getReturnType() {
		return returnType;
	}

	/***
	 * Aplicar Mapa de argumentos y parametros a ingresar en el metodo a invocar (String (Argumento), String (ValorParametro))
	 * @param definitionArgument
	 */
	public void setDefinitionArgument(Map<String, Object> definitionArgument) {
		this.definitionArgument = definitionArgument;
	}

	public int getTimeOutSeconds() {
		return timeOutMiliseconds;
	}

	public void setTimeOutSeconds(int timeOutMiliseconds) {
		this.timeOutMiliseconds = timeOutMiliseconds;
	}

}
