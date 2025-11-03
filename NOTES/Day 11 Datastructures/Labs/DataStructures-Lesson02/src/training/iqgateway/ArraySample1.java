/* This Program is to Illustrate the Usage of Single Dimensional Array */

 public class ArraySample1 
 {
	 // Creating class 
 	public static void main(String args[ ])
	{
		// Declaring an array 
	      int array[];                       

	    // Allocating or constructing
		  array = new int[10];                                
	
	      for(int i=0;i<10;i++) 
		  { 
			  // Displaying the elements of array
			     System.out.print(array[i]+ " ");
		  }	
	
	
		  // Initializing the array index 0 to 9
			  array[0]=1; 
	          array[1]=2;
	          array[2]=3;
	          array[3]=4;
	          array[4]=5;
	          array[5]=6;
	          array[6]=7;
	          array[7]=8;
	          array[8]=9;
	          array[9]=10;

          System.out.println("\n\n");
          for(int i=0;i<10;i++) 
		  { 
				  // Displaying the elements of array
			     System.out.print(array[i]+ " ");
		  }	
	  }
 }                                                                                    
 // end of the class


//Alternative way of declaring and initializing an array

/*public class ArrayExample
{
        public static void main(String args[ ])
		{
			int array[ ];                                                    
			// Declaring an array
	        
			array = new int[10];                      
			// Allocating the size of an array
            
			for(int i = 0;i < 10;i ++)
			{ 
				// Initializing the values of array index
	               array[i]=i + 1;

			}
			
	        for(int j=0;j<10;j++)
			{               
				// displaying the elements of array
		         System.out.print(array[j]+" ");

	        }
      }
}
*/