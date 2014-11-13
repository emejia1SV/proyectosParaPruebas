package test.testing;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import sv.avantia.depurador.agregadores.ws.WsDepuradorAgregadoresStub;
import sv.avantia.depurador.agregadores.ws.WsDepuradorAgregadoresStub.BajarServicios;
import sv.avantia.depurador.agregadores.ws.WsDepuradorAgregadoresStub.BajarServiciosResponse;
import sv.avantia.depurador.agregadores.ws.WsDepuradorAgregadoresStub.Numeros;


public class TesMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			WsDepuradorAgregadoresStub stub = new WsDepuradorAgregadoresStub();
			BajarServicios bajarServicios = new BajarServicios();
			Numeros numeros = new Numeros();
			numeros.setUsuario("ed");
			numeros.setPassword("pass");
			//numeros.setNumeros(new String[]{"234983290", "8472389"});
			bajarServicios.setNumeros(numeros);
			BajarServiciosResponse respuesta = stub.bajarServicios(bajarServicios);
			System.out.println(respuesta.get_return());
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
/*//		try {
//			WsTestStub stub = new WsTestStub();
//			Test test0 = new Test();
//			Obj param = new Obj();
//			String[] numeros = new String[]{"4545545"};
//			param.setNumeros(numeros);
//			test0.setObj(param);
//			TestResponse response = stub.test(test0);
//			System.out.println(response.get_return());
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
*/	}

}
