package info.halo9pan.samples.java.thread.lock.perf;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import info.halo9pan.samples.java.thread.Runner;

public class LockBlock extends AbstractDemo {

	public static void main(String[] args) {
		System.out.println("Using lock，performance test.");
		int[] numbers = { 1, 2, 4, 8, 16, 32 };
		int times = 100;
		for (int i = 0; i < numbers.length; i++) {
			int j = numbers[i];
			LockBlock demo = new LockBlock(j);
			demo.batchShow(times);
		}
	}

	public LockBlock(int threadNumber) {
		super(threadNumber);
	}

	@Override
	protected Runner createRunner() {
		Runner runner = new LockBlockRunner();
		return runner;
	}

}

class LockBlockRunner extends Runner {

	private Lock lock = new ReentrantLock();
	
	@Override
	public void doSomething(int invoker) {
		double random = Math.random();
		long total = 1000L;
		long sleep = (long) (total * random);
		lock.lock();
		try {
			try {
				TimeUnit.MICROSECONDS.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.identifier++;
		} finally {
			lock.unlock();
		}
	}

}
