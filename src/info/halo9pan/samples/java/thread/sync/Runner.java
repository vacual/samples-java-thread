package info.halo9pan.samples.java.thread.sync;

public abstract class Runner {
	
	protected int number;
	protected abstract void ing();
	
	protected long getRunTime(){
		return 1000L;
	}

	public int getNumber() {
		return number;
	}

}
