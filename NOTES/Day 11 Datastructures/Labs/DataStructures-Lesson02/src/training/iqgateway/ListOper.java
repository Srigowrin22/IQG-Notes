import java.util.*;
public class ListOper
{
	public static void main(String[] args) 
	{
		if(args.length==0)
		{
			System.out.println(" You have to supply the elements for the list as Command Line Arguments..... \n Try Again ....");
			System.exit(0);
		}

		System.out.println("\n");

		List<String> listObj=new ArrayList<>();

		for(int i=0;i<args.length;i++)
		{
			listObj.add(args[i]);
		}

		System.out.println(listObj + "\n");

		Collections.reverse(listObj);

		System.out.println(" The List in the Reverse Order is as follows :");

		System.out.println(listObj + "\n");

		Collections.sort(listObj);
		
		System.out.println(" The List in the Sorted Order is as follows :");

		System.out.println(listObj + "\n");
		
		int position=Collections.binarySearch(listObj,"b");
		System.out.println(" The element 'b' is found at the Position : "+ position + "\n");

		Collections.fill(listObj,"Oracle");

		System.out.println(" The list After filling it by the word 'One' is as follows : ");

		System.out.println(listObj +"\n");
		List<String> l1=new ArrayList<>();

		l1.add("1");
		l1.add("2");
		l1.add("3");

		Collections.copy(listObj,l1);

		System.out.println(" The List After Replacing the elements by those of l1 is as follows : ");

		System.out.println(listObj+"\n");

	}
}


