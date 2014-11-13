package sv.avantia.depurador.agregadores.hilo;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import sv.avantia.depurador.agregadores.object.Numeros;
import sv.claro.agregador1.Agregador1Stub;
import sv.claro.agregador2.Agregador2Stub;
import sv.claro.agregador3.Agregador3Stub;

public class ConsultaAgregadorPorHilo extends Thread {

	private int agregador;
	private Numeros numeros;

	public void run() {
		// consultar un agregador WS
		switch (getAgregador()) {
		case 1:
			agregador1();
			break;

		case 2:
			agregador2();
			break;

		case 3:
			agregador3();
			break;

		default:
			break;
		}
	}

	
	private void agregador1(){
		System.out.println("acciones en el agregador 1");
		try {
			Agregador1Stub stub = new Agregador1Stub();
			sv.claro.agregador1.Agregador1Stub.ListaNegra listaNegra = new sv.claro.agregador1.Agregador1Stub.ListaNegra();
			sv.claro.agregador1.Agregador1Stub.DarBaja darBaja = new sv.claro.agregador1.Agregador1Stub.DarBaja();

			for (int i = 0; i < numeros.getNumeros().size(); i++) {
				darBaja.setMCort(1);
				darBaja.setMovil(numeros.getNumeros().get(i));
				darBaja.setPass(numeros.getPassword());
				darBaja.setServicio("servicio");
				
				
				listaNegra.setAccion(1);
				listaNegra.setMovil(numeros.getNumeros().get(i));
				listaNegra.setPassword(numeros.getPassword());
				
				sv.claro.agregador1.Agregador1Stub.DarBajaResponse respuesta = stub.darBaja(darBaja);
				System.out.println("Respuesta agregador " + getAgregador() + " darDe baja " + respuesta.get_return()[1]);
				
				sv.claro.agregador1.Agregador1Stub.ListaNegraResponse respuesta2 = stub.listaNegra(listaNegra);
				System.out.println("Respuesta agregador " + getAgregador() + " lista negra " +  respuesta2.get_return()[1]);
			}
			
		} catch (AxisFault e) {
			System.out.println("excepcion " + e.getMessage());
			e.printStackTrace();
		} catch (RemoteException e) {
			System.out.println("excepcion " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void agregador2(){
		System.out.println("acciones en el agregador 2");
		try {
			Agregador2Stub stub = new Agregador2Stub();
			sv.claro.agregador2.Agregador2Stub.DarBaja darBaja = new sv.claro.agregador2.Agregador2Stub.DarBaja();
			sv.claro.agregador2.Agregador2Stub.ListaNegra listaNegra = new sv.claro.agregador2.Agregador2Stub.ListaNegra();
			
			for (int i = 0; i < numeros.getNumeros().size(); i++) {
				darBaja.setMCort(1);
				darBaja.setMovil(numeros.getNumeros().get(i));
				darBaja.setPass(numeros.getPassword());
				darBaja.setServicio("servicio");
				
				
				listaNegra.setAccion(1);
				listaNegra.setMovil(numeros.getNumeros().get(i));
				listaNegra.setPassword(numeros.getPassword());
				
				sv.claro.agregador2.Agregador2Stub.DarBajaResponse respuesta = stub.darBaja(darBaja);
				System.out.println("Respuesta agregador " + getAgregador() + " darDe baja " + respuesta.get_return()[1]);
				
				sv.claro.agregador2.Agregador2Stub.ListaNegraResponse respuesta2 = stub.listaNegra(listaNegra);
				System.out.println("Respuesta agregador " + getAgregador() + " lista negra " +  respuesta2.get_return()[1]);
			}
			
		} catch (AxisFault e) {
			System.out.println("excepcion " + e.getMessage());
			e.printStackTrace();
		} catch (RemoteException e) {
			System.out.println("excepcion " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void agregador3(){
		System.out.println("acciones en el agregador 3");
		try {
			Agregador3Stub stub = new Agregador3Stub();
			sv.claro.agregador3.Agregador3Stub.DarBaja darBaja = new sv.claro.agregador3.Agregador3Stub.DarBaja();
			sv.claro.agregador3.Agregador3Stub.ListaNegra listaNegra = new sv.claro.agregador3.Agregador3Stub.ListaNegra();
			
			for (int i = 0; i < numeros.getNumeros().size(); i++) {
				darBaja.setMCort(1);
				darBaja.setMovil(numeros.getNumeros().get(i));
				darBaja.setPass(numeros.getPassword());
				darBaja.setServicio("servicio");				
				
				listaNegra.setAccion(1);
				listaNegra.setMovil(numeros.getNumeros().get(i));
				listaNegra.setPassword(numeros.getPassword());
				
				sv.claro.agregador3.Agregador3Stub.DarBajaResponse respuesta = stub.darBaja(darBaja);
				System.out.println("Respuesta agregador " + getAgregador() + " darDe baja " + respuesta.get_return()[1]);
				
				sv.claro.agregador3.Agregador3Stub.ListaNegraResponse respuesta2 = stub.listaNegra(listaNegra);
				System.out.println("Respuesta agregador " + getAgregador() + " lista negra " +  respuesta2.get_return()[1]);
			}
		} catch (AxisFault e) {
			System.out.println("excepcion " + e.getMessage());
			e.printStackTrace();
		} catch (RemoteException e) {
			System.out.println("excepcion " + e.getMessage());
			e.printStackTrace();
		}
	}
	public int getAgregador() {
		return agregador;
	}

	public Numeros getNumeros() {
		return numeros;
	}

	public void setNumeros(Numeros numeros) {
		this.numeros = numeros;
	}

	public void setAgregador(int agregador) {
		this.agregador = agregador;
	}

}
