
import java.text.*;

public class DecimalFormatExample 
{
	public static void main(String[] args) 
	{
		DecimalFormat df = new DecimalFormat("###,###.##");

		double salary = 123999000000.434343;


		System.out.println(df.format(salary));

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy G EEEE");

		java.util.Date dobj = new java.util.Date();


		System.out.println(sdf.format(dobj));

	}
}
