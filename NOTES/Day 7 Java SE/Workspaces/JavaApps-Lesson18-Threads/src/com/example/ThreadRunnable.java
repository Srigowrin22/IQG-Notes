class ThreadRunnable implements Runnable 
{
	//private int i;
	public void run()
	{
		for(int i = 0 ; i <100; i++)
			{
				System.out.println(" I " + i + " From " + Thread.currentThread());
				try
				{
					Thread.sleep(1000);	
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		
	}

	
	public static void main(String[] args) 
	{
		ThreadRunnable r1 = new ThreadRunnable();
		Thread t1 = new Thread(r1);
		t1.start();

		ThreadRunnable r2 = new ThreadRunnable();
		Thread t2 = new Thread(r2);
		t2.start();
	}
}

