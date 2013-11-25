package info.halo9pan.samples.java.thread.sync.demo;

import info.halo9pan.samples.java.thread.sync.Demo;
import info.halo9pan.samples.java.thread.sync.Runner;

public class SelfLock extends Demo {

	public static void main(String[] args) {
		System.out.println("synchronized语句块，this作为锁");
		SelfLock demo = new SelfLock();
		demo.show();
	}

	@Override
	protected Runner createRunner() {
		Runner runner = new SelfRunner();
		return runner;
	}

}

class SelfRunner extends Runner {

	@Override
	protected void ing() {
		long time = getRunTime();
		try {
			synchronized (this) {
				Thread.sleep((long) (time * 0.4));
				int number = this.number;
				Thread.sleep((long) (time * 0.2));
				this.number = number + 1;
				Thread.sleep((long) (time * 0.4));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}