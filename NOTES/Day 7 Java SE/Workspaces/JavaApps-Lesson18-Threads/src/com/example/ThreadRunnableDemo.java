public class ThreadRunnableDemo implements Runnable
{
	
	public void run()
	{
		for(int i =0; i < 10; i++)
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

		ThreadRunnableDemo threadRef = new ThreadRunnableDemo();

		Thread t1 = new Thread(threadRef);
		t1.start();

		ThreadRunnableDemo threadRef1 = new ThreadRunnableDemo();
		
		Thread t2 = new Thread(threadRef);
		t2.start();

	
	}
}
