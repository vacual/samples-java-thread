package info.halo9pan.samples.java.thread.sync.demo;

import info.halo9pan.samples.java.thread.sync.Demo;
import info.halo9pan.samples.java.thread.sync.Runner;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomField extends Demo {

	public static void main(String[] args) {
		System.out.println("没有synchronized关键字，AtomicInteger++");
		AtomField demo = new AtomField();
		demo.THREAD_NUMBER = 1000;
		demo.show();
	}

	@Override
	protected Runner createRunner() {
		Runner runner = new AtomFieldRunner();
		return runner;
	}

}

class AtomFieldRunner extends Runner {

	private AtomicInteger atom = new AtomicInteger();

	@Override
	protected void ing() {
		long time = getRunTime();
		try {
			Thread.sleep((long) (time * 0.5));
			this.atom.getAndIncrement();
			Thread.sleep((long) (time * 0.5));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.number = this.atom.get();
	}
}
