/* This program illustrates the Usage of Array at the Time of Initializing. */
public class ArraySample2
{
	public static void main(String args[ ])
	{
  			// Declaring an array
			int[ ] array;                  

			// Step 1 and 2 together
			//int array [] = new int[10];

			 // Step 2 and 3 Together
			 array = new int[ ]{1,2,3,4,5,6,7,8,9,10}; 
			 //allocating and initializing 

			//array[0] = 123;

			for(int i=0;i<array.length;i++)
			{
				  System.out.print(array[i]+" ");
				//displaying elements of an array
		    }
			System.out.println("\n");

			//array[2] = 23;
	         
			 // All 3 In One Statement 
			int oneShotArray[] = {1,1,3,4,5};

			for(int i=0;i<oneShotArray.length;i++)
			{
				  System.out.print(oneShotArray[i]+" ");
				//displaying elements of an array
		    }
	}
}
        

 