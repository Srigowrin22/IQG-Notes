class thread1 implements Runnable
{	
	String name;
	Thread t;

	thread1(String threadname)
	{
		name=threadname;
		t=new Thread(this,name);//changing 
        t.start();
	}

	public void run()
	{
		try
		{
			for(int i=5;i>0;i--)
			{
				System.out.println(name+ ":"+i);
				Thread.sleep(3000);
			}
		}
		catch(InterruptedException e)
		{
			System.out.println(name+" interrupted.");
		}
		System.out.println(name+" exiting.");
	}
}
class JoinSample
{
	public static void main(String p[])
	{
		thread1 ob1 = new thread1("one");
		thread1 ob2 = new thread1("two");
		thread1 ob3 = new thread1("three");
		System.out.println("thread one isalive:"+ob1.t.isAlive());
		System.out.println("thread two isalive:"+ob2.t.isAlive());
		System.out.println("thread three is alive:"+ob3.t.isAlive());
		
		try
		{
			System.out.println("waiting for threads to finish");
			ob1.t.join();
			ob2.t.join();
			ob3.t.join();
		}
		catch(Exception e)
		{
			System.out.println("main thread interrupted");
		} 

		System.out.println("thread one isalive:"+ob1.t.isAlive());
		System.out.println("thread two is alive:"+ob2.t.isAlive());				
        System.out.println("thread three is alive:"+ob3.t.isAlive());

		System.out.println("main thread exiting");	
	}
}
