import java.util.concurrent.*;


class ExampleRunnable implements Runnable{

	private int i;
	private final String name;

	public ExampleRunnable(String givenName) {
		this.name = givenName;
	}

	public void run(){
		for(i=0; i<10; i++){
			System.out.println("i: " + i + " Current " + Thread.currentThread());
		}
	}
}


public class ExecutorExmp {
	public static void main(String arg[]) {

		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(new ExampleRunnable("One"));
		es.execute(new ExampleRunnable("Two"));

		es.shutdown();
	}
}