package info.halo9pan.samples.java.thread.atom;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicMarkableReference;

import info.halo9pan.samples.java.thread.Runner;

public class MarkableReference extends AbstractDemo {

	public static void main(String[] args) {
		System.out.println("No synchronized key word，AtomicInteger++");
		MarkableReference demo = new MarkableReference();
		demo.THREAD_NUMBER = 1000;
		demo.show();
	}

	@Override
	protected Runner createRunner() {
		Runner runner = new MarkableReferenceRunner();
		return runner;
	}

}

class MarkableReferenceKey {
	volatile String key;

	protected String getKey() {
		return key;
	}
}

class MarkableReferenceRunner extends Runner {

	private MarkableReferenceKey key = new MarkableReferenceKey();
	private AtomicMarkableReference<MarkableReferenceKey> reference = new AtomicMarkableReference<MarkableReferenceKey>(key, false);

	@Override
	public void doSomething(int invoker) {
			Calendar now = Calendar.getInstance();
			long millis = now.get(Calendar.SECOND);
			boolean mark = (millis % 2 == 0) ? true : false;
			this.reference.attemptMark(this.key, mark);
	}
	
	@Override
	public Object getIdentifier() {
		return this.reference.isMarked();
	}
}
