package info.halo9pan.samples.java.thread.sync.demo;

import info.halo9pan.samples.java.thread.sync.Demo;
import info.halo9pan.samples.java.thread.sync.Invoker;
import info.halo9pan.samples.java.thread.sync.Runner;

public class DoubleMethodLock extends Demo {

	public static void main(String[] args) {
		System.out.println("synchronized关键字加在方法前");
		DoubleMethodLock demo = new DoubleMethodLock();
		demo.show();
	}

	@Override
	protected Runner createRunner() {
		Runner runner = new DoubleMethodRunner();
		return runner;
	}

	protected Invoker createInvoker(int i) {
		Invoker invoker = new Invoker(i, this.runner){

			@Override
			public void run() {
				long start = System.currentTimeMillis();
				DoubleMethodRunner dmr = (DoubleMethodRunner)this.runner;
				if((this.id % 2) == 0){
					dmr.doSomething();
				} else {
					dmr.doAnother();
				}
				long end = System.currentTimeMillis();
				this.runTime = (end - start);
				System.out.println("[" + this.id + "]" + "结束运行，耗时" + this.runTime);
			}
			
		};
		return invoker;
	}

}

class DoubleMethodRunner extends Runner {

	@Override
	public synchronized void doSomething() {
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

	public synchronized void doAnother() {
		long time = getRunTime();
		try {
			Thread.sleep((long) (time * 0.8));
			int number = this.number;
			Thread.sleep((long) (time * 0.1));
			this.number = number + 1;
			Thread.sleep((long) (time * 0.1));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}