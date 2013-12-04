package info.halo9pan.samples.java.thread.sync.demo;

import info.halo9pan.samples.java.thread.Runner;
import info.halo9pan.samples.java.thread.sync.Demo;

public class NoneLock extends Demo {

	public static void main(String[] args) {
		System.out.println("没有synchronized关键字");
		NoneLock demo = new NoneLock();
		demo.show();
	}

	@Override
	protected Runner createRunner() {
		Runner runner = new NoneLockRunner();
		return runner;
	}

}

class NoneLockRunner extends Runner {
	@Override
	public void doSomething() {
		long time = getRunTime();
		try {
			Thread.sleep((long) (time * 0.4));
			int number = this.identifier;
			Thread.sleep((long) (time * 0.2));
			this.identifier = number + 1;
			Thread.sleep((long) (time * 0.4));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
