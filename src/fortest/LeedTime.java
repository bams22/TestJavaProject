package fortest;

public class LeedTime extends Thread {
	@Override
	synchronized public void run() {
		long startTime = System.currentTimeMillis();
		
		try {
			Thread.currentThread().wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long secunds = (System.currentTimeMillis() - startTime)/600;
		
		System.out.println("Программа выполнялась " + secunds + " секунд.");
		
	}
}
