package test.ws;

import test.obj.Obj;

public class WSTest {

	public int test(Obj obj){
		System.out.println(obj.getNumeros());
		if(obj.getNumeros()!=null)
			if(obj.getNumeros().length>0)
				System.out.println(obj.getNumeros()[0]);
		
		System.out.println("se proceso el WS");
		return 1;
	}
}
