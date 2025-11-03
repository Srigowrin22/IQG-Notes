class A  extends Thread
{
	public void run()
	{
		System.out.println("Thread A started");

		for(int i=1;i<=5;i++)
		{
			System.out.println("From thread A i= "+i);
		}
		System.out.println("Thread A Exiting .");
	}
}

class B  extends Thread
{
	public void run()
	{
		System.out.println("Thread B started");
		for(int j=1;j<=5;j++)
		{
			System.out.println("From thread B j= "+j);
		}
		System.out.println("Thread B Exiting ..");
	}
}
class C  extends Thread
{
	public void run()
	{
		System.out.println("Thread C started");
		for(int k=1;k<=5;k++)
		{
			System.out.println("From thread C k= " +k);
		}
		System.out.println("Thread C Exiting ...");
	}
}
class ThreadPriority
{
	public static void main(String p[])
	{
		A threadA=new A();
		B threadB=new B();
		C threadC=new C();


		threadC.setPriority(Thread.MAX_PRIORITY);//Max_priority is data member of thread class
		threadB.setPriority(threadA.getPriority()+1);//
		threadA.setPriority(Thread.MIN_PRIORITY);//threadA got 1,B got 6,c got 10 priority

		System.out.println("Main Thread Created Thread A");
		threadA.start();
		System.out.println("Main Thread Created B");
		threadB.start();	
		System.out.println("Main Thread Created C");
		threadC.start();
		
        System.out.println(threadA.getPriority()); 
        System.out.println(threadB.getPriority());
        System.out.println(threadC.getPriority()); 
 
		System.out.println("Main Thread Exiting.......");

 

	}
}
