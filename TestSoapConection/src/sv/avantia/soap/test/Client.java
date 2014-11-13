package sv.avantia.soap.test;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class Client {

	public static void main(String[] args) {
		String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:loc=\"http://www.csapi.org/schema/parlayx/blackgray/v1_0/local\"><soapenv:Header><wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\"><wsse:UsernameToken xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\"><wsse:Username>PA00000737</wsse:Username><wsse:Password Type=\"...#PasswordDigest\">x9t/yLcnC3VYKCb6v0uezTwYJNk=</wsse:Password><wsse:Nonce>096459e93f20a2b39ab6c5ddd493e44f58bc3a91</wsse:Nonce><wsse:Created>2012-10-17T11:51:50.263Z</wsse:Created></wsse:UsernameToken></wsse:Security><tns:RequestSOAPHeader xmlns:tns=\"http://www.huawei.com.cn/schema/common/v2_1\"><tns:AppId>35000001000001</tns:AppId><tns:TransId>2014011716010012345</tns:TransId><tns:OA>50279451598</tns:OA><tns:FA>50279451598</tns:FA></tns:RequestSOAPHeader></soapenv:Header><soapenv:Body><loc:addGrayList><loc:version>1.0</loc:version><loc:grayList><grayee><msisdn>50279451598</msisdn>				            </grayee></loc:grayList></loc:addGrayList></soapenv:Body></soapenv:Envelope>";

		talk("https://hub.americamovil.com/sag/services/blackgrayService", xml);
	}
	
	/**
	 * Metodo que nos carga un certificado digital
	 * 
	 * @author Edwin Mejia - Avantia Consultores
	 * @return {@link Void}
	 * */
	static public void doTrustToCertificates() throws Exception {
		// Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		TrustManager[] trustAllCerts = new TrustManager[] { 
				new X509TrustManager() {
					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}
		
					public void checkServerTrusted(X509Certificate[] certs,
							String authType) throws CertificateException {
						return;
					}
		
					public void checkClientTrusted(X509Certificate[] certs,
							String authType) throws CertificateException {
						return;
					}
				} 
		};

		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HostnameVerifier hv = new HostnameVerifier() {
			public boolean verify(String urlHostName, SSLSession session) {
				if (!urlHostName.equalsIgnoreCase(session.getPeerHost())) {
					System.out.println("Warning: URL host '" + urlHostName	+ "' is different to SSLSession host '"	+ session.getPeerHost() + "'.");
				}
				return true;
			}
		};
		HttpsURLConnection.setDefaultHostnameVerifier(hv);
	}
	

	/**
	 * Metodo que realiza el envio del archivo request y espera el archivo
	 * response para el metodo web
	 * 
	 * @author Edwin Mejia - Avantia Consultores
	 * @param urlval
	 *            - {@link String}
	 * @param inputMessage
	 *            - {@link String}
	 * @return {@link Void}
	 * */
	public static void talk(String urlval, String inputMessage) {
		try {
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage msg = messageFactory.createMessage(
					new MimeHeaders(),
					new ByteArrayInputStream(inputMessage.getBytes(Charset.forName("UTF-8"))));

			// View input
			System.out.println("Soap request:");
			msg.writeTo(System.out);

			// Trust to certificates
			doTrustToCertificates();

			// SOAPMessage rp = conn.call(msg, urlval);
			SOAPMessage rp = sendMessage(msg, urlval);

			// View the output
			System.out.println("Soap response");
			rp.writeTo(System.out);
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Metodo que envia realiza la conexion al servicio web e invoca al metodo a
	 * ejecutar
	 * 
	 * @author Edwin Mejia - Avantia Consultores
	 * @param message
	 *            - {@link SOAPMessage}
	 * @param endPoint
	 *            - {@link String}
	 * @return {@link SOAPMessage}
	 * */
	static public SOAPMessage sendMessage(SOAPMessage message, String endPoint)
			throws MalformedURLException, SOAPException {
		SOAPMessage result = null;
		if (endPoint != null && message != null) {
			URL url = new URL(endPoint);
			SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
			SOAPConnection connection = null;
			long time = System.currentTimeMillis();
			try {
				connection = scf.createConnection(); // point-to-point connection
				result = connection.call(message, url);
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (SOAPException soape) {
						System.out.print("Can't close SOAPConnection:" + soape);
					}
				}
			}
			System.out.println("Respuesta en " + (System.currentTimeMillis() - time));
		}
		return result;
	}	
}