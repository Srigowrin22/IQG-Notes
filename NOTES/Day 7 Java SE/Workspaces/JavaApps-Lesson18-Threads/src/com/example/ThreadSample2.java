class ThreadSample2
{
	public static void main(String p[])
	{
		//Thread t = Thread.currentThread();

		
		System.out.println(" Main Threads  Priority is "+ 	Thread.currentThread().getPriority());


		Thread.currentThread().setPriority(10);


		System.out.println(" Main Threads  Priority is "+ Thread.currentThread().getPriority());

		try
		{
			for(int n=5;n>0;n--)
			{
				System.out.println(n);
				Thread.sleep(3000);
			}
		}
		catch(InterruptedException e)
		{
			System.out.println("main thread interrupted");
		}

		
	}
}

