package info.halo9pan.samples.java.thread.sync.demo;

import info.halo9pan.samples.java.thread.sync.Demo;
import info.halo9pan.samples.java.thread.sync.Invoker;
import info.halo9pan.samples.java.thread.sync.Runner;

public class VolatileField extends Demo {

	public static void main(String[] args) {
		System.out.println("没有synchronized，成员变量添加volatile修饰");
		VolatileField demo = new VolatileField();
		demo.THREAD_NUMBER = 1000;
		demo.show();
	}

	@Override
	protected Invoker createInvoker(int i) {
		Invoker invoker = new Invoker(i, this.runner){

			@Override
			public void run() {
				super.run();
			}
		};
		return invoker;
	}

	@Override
	protected Runner createRunner() {
		Runner runner = new VolatileFieldRunner();
		return runner;
	}

}

class VolatileFieldRunner extends Runner {

	volatile private int number;

	@Override
	protected void ing() {
		long time = getRunTime();
		try {
			Thread.sleep((long) (time * 0.5));
			int n = this.number;
			this.number = n + 1;
			Thread.sleep((long) (time * 0.5));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getNumber() {
		return this.number;
	}
}
