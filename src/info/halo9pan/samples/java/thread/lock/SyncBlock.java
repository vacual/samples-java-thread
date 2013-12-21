package info.halo9pan.samples.java.thread.lock;

import java.util.concurrent.TimeUnit;

import info.halo9pan.samples.java.thread.Runner;

public class SyncBlock extends AbstractDemo {

	public static void main(String[] args) {
		System.out.println("Using synchronized，performance test.");
		int[] numbers = { 1, 2, 4, 8, 16, 32 };
		int times = 100;
		for (int i = 0; i < numbers.length; i++) {
			int j = numbers[i];
			SyncBlock demo = new SyncBlock(j);
			demo.batchShow(times);
		}
	}

	public SyncBlock(int threadNumber) {
		super(threadNumber);
	}

	@Override
	protected Runner createRunner() {
		Runner runner = new SyncBlockRunner();
		return runner;
	}

}

class SyncBlockRunner extends Runner {

	@Override
	public void doSomething(int invoker) {
		double random = Math.random();
		long total = 1000L;
		long sleep = (long) (total * random);
		synchronized (this) {
			try {
				TimeUnit.MICROSECONDS.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.identifier++;
		}
	}

}
