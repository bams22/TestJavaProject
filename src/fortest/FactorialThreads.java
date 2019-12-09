package fortest;

import java.math.BigInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FactorialThreads{
	public BigInteger count(int max) {
		BigInteger result = new BigInteger("1");
		int dev = Runtime.getRuntime().availableProcessors();
		System.out.println("Колличество процессоров: " + dev);
		@SuppressWarnings("unchecked")
		Callable<BigInteger>[] callable = new Callable[dev]; 
		for (int i = 0; i < dev; i++) {
			callable[i] = new FactorialCallable(dev, i, max);
		}
		@SuppressWarnings("unchecked")
		FutureTask<BigInteger>[] futureTask = new FutureTask[dev];
		for (int i = 0; i < futureTask.length; i++) {
			futureTask[i] = new FutureTask<BigInteger>(callable[i]);
		}
		for (int i = 0; i < futureTask.length; i++) {
			new Thread(futureTask[i]).start();
		}
		for (int i = 0; i < futureTask.length; i++) {
			try {
				result = result.multiply(futureTask[i].get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}

class FactorialCallable implements Callable<BigInteger> {
	
	private int dev;
	private int multiple;
	private int max;
	
	public FactorialCallable(int dev, int multiple, int max) {
		this.dev = dev;
		this.multiple = multiple;
		this.max = max;
	}

	@Override
	public BigInteger call() {
		int iMax = max / dev + 1;
		BigInteger result = new BigInteger("1");
		Integer val;
		for (int i = 0; i < iMax; i++) {
			val = i * dev + multiple + 1;
			if (val > max) {
				break;
			}
			result = result.multiply(new BigInteger(val.toString()));
		}
		return result;
	}
	
}
