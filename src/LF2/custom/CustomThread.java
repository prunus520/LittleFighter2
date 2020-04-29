package LF2.custom;

public class CustomThread implements Runnable {

	public interface ThreadListener {
		public void ThreadListener();
	}

	ThreadListener threadListener;
	Thread thread;

	public CustomThread() {
	}

	public void addThreadListener(ThreadListener threadListener) {
		this.threadListener = threadListener;

	}

	public void interrupt() {
		thread.interrupt();
	}

	public void start() {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		threadListener.ThreadListener();
	}

	public void join() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
