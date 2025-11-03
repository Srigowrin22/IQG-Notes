public class Currentthread
{
	public static void main(String arg[])
	{
		Thread t=Thread.currentThread();
		try
		{
			for(int counter=0;counter<10;counter+=2)
			{
				System.out.println(counter);
				Thread.sleep(1000);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}