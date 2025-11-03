class ThreadSample1
{	
	public static void main(String p[])
	{
			//Thread t = Thread.currentThread();	
		
			Thread t = new Thread();

			System.out.println(" Current thread: " + t + "\n");
			
			t.setName("Java Thread");
			
			System.out.println("After name changed: "+ t);


	}
}
