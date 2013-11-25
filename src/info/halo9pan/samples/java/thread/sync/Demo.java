package info.halo9pan.samples.java.thread.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public abstract class Demo {

	protected int THREAD_NUMBER = 4;

	private ExecutorService threadPool;
	private List<Invoker> list = new ArrayList<Invoker>();

	protected Runner runner;

	protected abstract Runner createRunner();

	protected void show() {
		this.threadPool = Executors.newFixedThreadPool(THREAD_NUMBER);
		this.runner = createRunner();
		for (int i = 0; i < THREAD_NUMBER; i++) {
			Invoker invoker = createInvoker(i);
			list.add(invoker);
			this.threadPool.execute(invoker);
		}
		this.threadPool.shutdown();
		this.printNumber();
	}

	protected Invoker createInvoker(int i) {
		Invoker invoker = new Invoker(i, this.runner);
		return invoker;
	}

	protected void printNumber() {
		long waitingTime = (long) (this.runner.getRunTime() * THREAD_NUMBER);
		try {
			this.threadPool.awaitTermination(waitingTime * 2, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long maxRunTime = 0;
		for (Invoker i : this.list) {
			long runTime = i.getRunTime();
			maxRunTime = (maxRunTime < runTime) ? runTime : maxRunTime;
		}
		System.out.println("最大耗时：" + maxRunTime);
		System.out.println("运行计数：" + runner.getNumber());
	}

}
