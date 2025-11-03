import java.util.concurrent.*;


class ExampleCallable implements Callable {

	private final int len;
	private final String name;
	private int sum = 0;

	public ExampleCallable(String givenName, int givenlen) {
		this.len = givenlen;
		this.name = givenName;
	}

	public String call() throws Exception{
		for(int i=0; i<10; i++){
			System.out.println(name + ":" + i + " " + Thread.currentThread());
			sum += i;
		}
		return "Sum is: " + sum;
	}
}


public class ExecutorExmpCallable {
	public static void main(String arg[]) {

		ExecutorService es = Executors.newFixedThreadPool(4);
		Future<String> f1 = es.submit(new ExampleCallable("One", 10));
		Future<String> f2 = es.submit(new ExampleCallable("Two", 20));
		
		try{
			es.shutdown();
			es.awaitTermination(5, TimeUnit.SECONDS);
			String result1 = f1.get(); // reads the response
			System.out.println("Result of one: " + result1);

			String result2 = f2.get();
			System.out.println("Result of two: " + result2);
		}
		catch(ExecutionException | InterruptedException ex){
			System.out.println("Exception: " + ex);
		}
	}
}