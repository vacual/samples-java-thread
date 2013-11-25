package info.halo9pan.samples.java.thread.sync.demo;

import info.halo9pan.samples.java.thread.sync.Demo;
import info.halo9pan.samples.java.thread.sync.Runner;

public class FieldPlus extends Demo {

	public static void main(String[] args) {
		System.out.println("没有synchronized关键字，number++");
		FieldPlus demo = new FieldPlus();
		demo.THREAD_NUMBER = 1000;
		demo.show();
	}

	@Override
	protected Runner createRunner() {
		Runner runner = new FieldPlusRunner();
		return runner;
	}

}

class FieldPlusRunner extends Runner {

	@Override
	public void ing() {
		long time = getRunTime();
		try {
			Thread.sleep((long) (time * 0.5));
			this.number++;
			Thread.sleep((long) (time * 0.5));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
