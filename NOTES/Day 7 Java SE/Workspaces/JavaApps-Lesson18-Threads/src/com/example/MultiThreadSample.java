class A1 extends Thread
{
	public void run()
	{
		for(int i=1;i<=5;i++)
		{
			if(i==2)
                         yield();
			System.out.println("from thread A   i= "+i);
		}
		System.out.println("exit from A");
	}
}
class B1 extends Thread
{
	public void run()
	{
		for(int j=1;j<=5;j++)
		{
			if(j==3)
					stop();
			System.out.println("from thread b j = " +j);
		}
		System.out.println("exit from b");
	}
}
class C1 extends Thread
{
	public void run() 
	{
		for(int k=1;k<=5;k++)
		{
			System.out.println("from thread c  k= " +k);
			{
				if(k==1)
				{
					try
					{
						sleep(2000);
					}
					catch(Exception e)
					{

					}
				}
				
			}

		}
					System.out.println("exit from c"); 
	}
}
class MultiThreadSample
{
	public static void main(String p[])
	{
		A1 threadA = new A1(); 
		B1 threadB = new B1();
		C1 threadC = new C1();

		System.out.println("start thread A");
		threadA.start();

		System.out.println("start thread b");
		threadB.start();
		
		System.out.println("start thread c"); 
		threadC.start();
		
		System.out.println("end of main thread"); 

		System.out.println("end of main thread1"); 
		System.out.println("end of main thread2"); 
		System.out.println("end of main thread3"); 



	}
}
