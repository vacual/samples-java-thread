package info.halo9pan.samples.java.thread.sync.demo;

import info.halo9pan.samples.java.thread.sync.Demo;
import info.halo9pan.samples.java.thread.sync.Runner;

public class LessBlockLock extends Demo {

	public static void main(String[] args) {
		System.out.println("synchronized语句块，this作为锁，局部加锁");
		LessBlockLock demo = new LessBlockLock();
		demo.show();
	}

	@Override
	protected Runner createRunner() {
		Runner runner = new LessBlockLockRunner();
		return runner;
	}

}

class LessBlockLockRunner extends Runner {

	@Override
	protected void ing() {
		long time = getRunTime();
		try {
			Thread.sleep((long) (time * 0.4));
			synchronized (this) {
				int number = this.number;
				Thread.sleep((long) (time * 0.2));
				this.number = number + 1;
			}
			Thread.sleep((long) (time * 0.4));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
