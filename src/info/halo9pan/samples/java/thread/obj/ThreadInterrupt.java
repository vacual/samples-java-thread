package info.halo9pan.samples.java.thread.obj;

public class ThreadInterrupt {

	public static void main(String[] args) {
		final Object lock = new Object();
		class WaitThread extends Thread{

			@Override
			public void run() {
				try {
					System.out.println("Wait Thread was started.");
					synchronized(lock){
						lock.wait();
					}
					System.out.println("Wait Thread was finished.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		final WaitThread waitThread = new WaitThread();
		Thread interruptThread = new Thread() {
			@Override
			public void run() {
				try {
					System.out.println("Interrupt Thread was started.");
					Thread.sleep(1000L);
					System.out.println("Wait Thread state." + waitThread.getState());
					waitThread.interrupt();
					System.out.println("Interrupt Thread was finished.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		waitThread.start();
		interruptThread.start();
	}

}
