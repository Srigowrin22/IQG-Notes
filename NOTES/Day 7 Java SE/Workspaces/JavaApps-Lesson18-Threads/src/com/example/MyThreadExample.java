public class MyThreadExample extends Thread
{
	int i = 0;
	public MyThreadExample(String a)
	{
		super(a);
	}

	/// Providing Implementation for Run Method...

	public void run()
	{	
			try
			{
				for(i = 0; i<10; i++)
				{
				//System.out.println(" Hello My Friends in Oracle ");
					System.out.println(i + " " + Thread.currentThread());
				}

			}
			catch(Exception e)
			{
				e.printStackTrace();		
			}
	}
	public static void main(String[] args) 
	{

		MyThreadExample obj1 = new MyThreadExample("Oracle Thread-1");
		obj1.start();

		MyThreadExample obj2 = new MyThreadExample("Oracle Thread-2");
		obj2.start();
				
		
	}
}
