public class ThreadDemo extends Thread
{
	private int i;
	
	public void run()
	{
		for(i =0; i < 10; i++)
		{
			System.out.println(Thread.currentThread().getName() + "\t" +i);
			try
			{
				Thread.sleep(2000);	
			}	
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}

	public static void main(String args[])
	{
		ThreadDemo threadRef = new ThreadDemo();
		threadRef.start();

		ThreadDemo threadRef1 = new ThreadDemo();
		threadRef1.start();

		ThreadDemo threadRef2 = new ThreadDemo();
		threadRef2.start();

		System.out.println(" I am Main Thread ");
		
	}
}
