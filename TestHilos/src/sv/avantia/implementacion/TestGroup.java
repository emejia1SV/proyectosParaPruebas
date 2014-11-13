package sv.avantia.implementacion;

import java.util.ArrayList;
import java.util.List;

public class TestGroup {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		List<String> agregadores =  new ArrayList<String>();
		
		agregadores.add("SMT");
		agregadores.add("CDC");
		agregadores.add("OTROS");
		
		for (String string : agregadores) {
			HiloThread t1 = new HiloThread();
			t1.setName(string);
			t1.start();
		}
		
		ThreadGroup currentGroup = HiloThread.currentThread().getThreadGroup();
		int noThreads = currentGroup.activeCount();
		
		Thread[] lstThreads = new Thread[noThreads];
		currentGroup.enumerate(lstThreads);
		for (int i = 0; i < noThreads; i++){
			for (String string : agregadores) {
				if(lstThreads[i].getName().equals(string)){
					if (lstThreads[i] instanceof HiloThread) {
						HiloThread propio = (HiloThread) lstThreads[i];
						if (propio.isAlive()) {
							if(propio.getPrueba()!=null){
								System.out.println(propio.getPrueba());
								propio.stop();
							}
						}
					}
				}
			}
		}
	}

}
