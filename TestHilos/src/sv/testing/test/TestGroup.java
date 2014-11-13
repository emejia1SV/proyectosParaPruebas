package sv.testing.test;

public class TestGroup extends Thread {

	public static void main(String[] args) {
		TestGroup t1 = new TestGroup();
		t1.setName("thread1");
		t1.start();
		ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
		int noThreads = currentGroup.activeCount();
		Thread[] lstThreads = new Thread[noThreads];
		currentGroup.enumerate(lstThreads);
		for (int i = 0; i < noThreads; i++){
			System.out.println("Thread No:" + i + " = "	+ lstThreads[i].getName());
			
		}
			
	}
	
	
	
	@Override
	public void run() {
		try {
			this.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
