package test.manageBean;

import java.rmi.RemoteException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.axis2.AxisFault;

import com.test.webservices.cadena.PruebaWsCadenaStub;
import com.test.webservices.cadena.PruebaWsCadenaStub.Saludo;
import com.test.webservices.cadena.PruebaWsCadenaStub.SaludoResponse;

@ViewScoped
@ManagedBean(name = "manageTest")
public class ManageTest {

	private String respuesta = "";

	public void test() {
		try {
			PruebaWsCadenaStub stub = new PruebaWsCadenaStub();
			Saludo saludo0 = new Saludo();
			saludo0.setTexto("Florentin");
			SaludoResponse out = stub.saludo(saludo0);
			setRespuesta(out.get_return());
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

}
