package sv.avantia.depurador.agregadores.servicio.client;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;

import sv.avantia.depurador.agregadores.servicio.web.ServicioWebSDAStub;
import sv.avantia.depurador.agregadores.servicio.web.ServicioWebSDAStub.DepuracionMasiva;
import sv.avantia.depurador.agregadores.servicio.web.ServicioWebSDAStub.DepuracionMasivaResponse;

public class TestClienteSDA {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			ServicioWebSDAStub stub =new ServicioWebSDAStub();
			Options options = new Options();
			options.setProperty(HTTPConstants.SO_TIMEOUT, new Integer(125000));
			options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer(125000));
			stub._getServiceClient().setOptions(options);
			DepuracionMasiva masiva = new DepuracionMasiva();
			masiva.setNumerosEnXML("<numeros><movil>50257128949</movil></numeros>");
			DepuracionMasivaResponse response = stub.depuracionMasiva(masiva);
			System.out.println(response.get_return());
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
