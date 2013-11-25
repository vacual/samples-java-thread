package info.halo9pan.samples.java.thread.sync.demo;

import info.halo9pan.samples.java.thread.sync.Demo;
import info.halo9pan.samples.java.thread.sync.Runner;

public class MethodLock extends Demo {

	public static void main(String[] args) {
		System.out.println("synchronized关键字加在方法前");
		MethodLock demo = new MethodLock();
		demo.show();
	}

	@Override
	protected Runner createRunner() {
		Runner runner = new MethodRunner();
		return runner;
	}

}

class MethodRunner extends Runner {

	@Override
	protected synchronized void ing() {
		long time = getRunTime();
		try {
			Thread.sleep((long) (time * 0.4));
			int number = this.number;
			Thread.sleep((long) (time * 0.2));
			this.number = number + 1;
			Thread.sleep((long) (time * 0.4));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}