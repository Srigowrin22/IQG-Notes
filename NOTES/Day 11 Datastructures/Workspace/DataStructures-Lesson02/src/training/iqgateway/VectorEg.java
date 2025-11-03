import java.util.*;

public class VectorEg
{
	public static void main(String[] args) 
	{
		Vector v = new Vector(5,5);
		v.add("First");
		int i = 1;
		//v.add(i);
		v.add(new Integer(i));
		v.add(new Integer(i));
		v.add(new Integer(i));
		v.add(new Integer(i));
                
                System.out.println("Object Hashcode " + v.hashCode());
		v.add(new Integer(i));
		
		double d1=1.1;
		v.add(new Double(d1));
		v.add("Second");
		int j=2;
		v.add(new Integer(j));
		double d2=2.2;
		v.add(new Double(d2));
                System.out.println("Object Hashcode " + v.hashCode());
		// Java 5 We Have Capability to Adding Primitives Directly 
		// AutoBoxing [ Language Automatically Convert Primitive to Wrapper Object ] 
		// UnBoxing [ Language Automatically Converts Wrapper Object to Primitive ]

		int a=2, b=3;
		v.add(a); // v.add(new Integer(a));
		v.add(b);
		
                System.out.println("Object Hashcode " + v.hashCode());
		// UnBoxing 
		//  int result = v.get(1).intValue();
		//	int result = v.get(1); 

		Enumeration e=v.elements();
		System.out.println(" The elements of the Vector are as Follows : \n");
		while(e.hasMoreElements())
		{
			System.out.println(e.nextElement());
		} 
		
		/*for(int index = 0; index < v.size(); index++ )
		{
			System.out.println(v.get(index));
		}*/

		System.out.println("\n");
		System.out.println(" The Capacity of the Vector os : "+v.capacity());
		System.out.println(" The Size of the Vector os :" +v.size());

		Float f = new Float("23.26");

		v.insertElementAt(f,1);

		System.out.println("\n\n\n");
		Enumeration e1=v.elements();
		System.out.println(" The elements of the Vector are as Follows : \n");
		while(e1.hasMoreElements())
		{
			System.out.println(e1.nextElement());
		} 

		System.out.println("\n");
		System.out.println(" The Capacity of the Vector os :"+v.capacity());
		System.out.println(" The Size of the Vector os :" +v.size());

		System.out.println(" The Second element of the Vector is : "+v.elementAt(1));

		System.out.println(" The first Element of the Vector os : " + v.firstElement());
		System.out.println(" The last Element of the Vector os : " + v.lastElement());

		v.removeElementAt(1);
		v.removeElementAt(1);
		v.removeElementAt(1);
		v.removeElementAt(1);
		e=v.elements();
		System.out.println(" \n The Vector after removing on of its element os as follows :\n");

		while(e.hasMoreElements())
		{
			System.out.println(e.nextElement());
		}

		System.out.println("\n");
		System.out.println(" The Capacity of the Vector os :"+v.capacity());
		System.out.println(" The Size of the Vector os :" +v.size());

		System.out.println("\n");
			
		System.out.println(v.elementAt(9)); 
	}
}
