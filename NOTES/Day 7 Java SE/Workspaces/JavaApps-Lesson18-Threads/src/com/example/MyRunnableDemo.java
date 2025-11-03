class MyRunnableExample implements Runnable 
{
		public void run()
		{
				System.out.println(" Good Evening Everyone ");
		}

}

public class MyRunnableDemo 
{
		public static void main(String args[])
		{
				Runnable Runobj=new MyRunnableExample();

				Thread obj1=new Thread(Runobj);

				obj1.start();

				Thread obj2 = new Thread(Runobj);

				obj2.start();
		}
}