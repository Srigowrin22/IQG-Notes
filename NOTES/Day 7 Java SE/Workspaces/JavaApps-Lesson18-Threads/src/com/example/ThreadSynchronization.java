class callme
{
    synchronized void call(String msg)
	{
		System.out.print("[  " + msg);
		try
		{
			//System.out.println(Thread.currentThread());
			Thread.sleep(5000);
		
		}
		catch(InterruptedException e)
		{
			System.out.println("interrupted");
		}
		System.out.print("  ]\n\n");
	}
}

class caller implements Runnable
{
	String msg;
	callme target;
	Thread t;
	public caller(callme targ,String s)
	{
		target =targ;
		msg=s;
		t=new Thread(this);
		t.start();
	}

	public void run()
	{
		//synchronized(target)
		{
			target.call(msg);
		}
	}
}
class ThreadSynchronization
{
	public static void main (String p[])
	{
		callme target=new callme();
		caller ob1=new caller(target,"Hello ");
		caller ob2=new caller(target,"Synchronised ");
		caller ob3=new caller(target,"World ");
		try
		{
			ob1.t.join();
			ob2.t.join();
			ob3.t.join();
		}
		catch(InterruptedException e)
		{
			System.out.println("interrupted");
		}

		System.out.println(" Exiting Main ");
	}
}

