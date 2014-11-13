package test.clases;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import com.test.webservices.cadena.PruebaWsCadenaStub;
import com.test.webservices.cadena.PruebaWsCadenaStub.Saludo;
import com.test.webservices.cadena.PruebaWsCadenaStub.SaludoResponse;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			PruebaWsCadenaStub stub = new PruebaWsCadenaStub();
			Saludo saludo0 = new Saludo();
			saludo0.setTexto("Florentin");
			SaludoResponse out = stub.saludo(saludo0);
			System.out.println(out.get_return());
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
