package info.halo9pan.samples.java.thread.lock.perf;

import java.util.ArrayList;
import java.util.List;

import info.halo9pan.samples.java.thread.Runner;

public abstract class AbstractDemo {

	protected int threadNumber;

	private List<Thread> threadList;

	protected Runner runner;

	public AbstractDemo(int threadNumber) {
		this.threadNumber = threadNumber;
	}

	protected abstract Runner createRunner();

	protected void batchShow(int times){
		long start = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			this.show();
		}
		long end = System.currentTimeMillis();
		long spentTime = (end - start);
		StringBuffer buffer = new StringBuffer();
		buffer.append("Using thread: ").append(this.threadNumber).append(". ");
		buffer.append("Run times: ").append(times).append(". ");
		buffer.append("Spent time: ").append(spentTime).append("ms. ");
		System.out.println(buffer.toString());
	}
	
	protected void show() {
		this.threadList = new ArrayList<Thread>(threadNumber);
		this.runner = createRunner();
		for (int i = 0; i < threadNumber; i++) {
			Runnable invoker = createInvoker(i);
			Thread t = new Thread(invoker);
			this.threadList.add(t);
		}
		for (Thread t : this.threadList) {
			t.start();
		}
		for (Thread t : this.threadList) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	protected Runnable createInvoker(final int i) {
		Runnable invoker = new Runnable(){
			public void run() {
				runner.doSomething(i);
			}};
		return invoker;
	}

}
