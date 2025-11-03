class ArrayCopy
{
	public static void main(String[] args) 
	{
		
		int Arr1[]={0,1,2,3,4,5};

		int Arr2[]={10,9,8,7,6,5,4,3,2,1};

		System.arraycopy(Arr1,0,Arr2,4,3);

		/* Syntax

		System.arraycopy(SourceArray, From Where to Copy, Destination Array, Where to Start Copying, Number of Elements to copied);

		*/

								

		for(int i=0;i<10;i++)
		{
			System.out.println(Arr2[i]+" \t");
		}
	}
}


