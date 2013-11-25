package info.halo9pan.samples.java.thread.sync;

public class Invoker implements Runnable {
	
	protected int id;
	protected Runner runner;
	protected long runTime;

	public Invoker(int id, Runner runner) {
		super();
		this.id = id;
		this.runner = runner;
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		this.runner.ing();
		long end = System.currentTimeMillis();
		this.runTime = (end - start);
		System.out.println("[" + this.id + "]" + "结束运行，耗时" + this.runTime);
	}
	
	public long getRunTime(){
		return this.runTime;
	}

}
