package info.halo9pan.samples.java.thread;

public abstract class Runner {

	protected static final long DEFAULT_RUN_TIME = 1000L;
	protected int identifier;
	protected long runTime = DEFAULT_RUN_TIME;

	public abstract void doSomething(int invoker);

	public Object getIdentifier() {
		return identifier;
	}

	public long getRunTime() {
		return this.runTime;
	}

	protected void setRunTime(long runTime) {
		this.runTime = runTime;
	}

}
