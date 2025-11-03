package oe;
public class OrderEntry
{
	public static void main(String[] args)
	{
		double item1 = 2.95F;
		double item2 = 3.50F;
		double itemTotal;
		int line = 1, numOfDays = 2;
		
		float itm1 = 2.59f, itm2 = 3.50f;

		System.out.println("Item 1 cost: " + item1);
		System.out.println("Item 2 cost: " + item2);

		itemTotal = ((item1 * numOfDays) + (item2 * numOfDays));
		System.out.println("Total cost: " + itemTotal);

		System.out.println("Item" + line++ + " is " + item1 + " * " + numOfDays + " days = " + (item1 * numOfDays));
		System.out.println("Item" + line++ + " is " + item2 + " * " + numOfDays + " days = " + (item2 * numOfDays));
		
		System.out.println("-----------------------------------");
		System.out.println("Printing current date:");

		int day = 28, mth = 2, yr = 2001;
		boolean isLeapYear = (yr % 4 == 0) && ((yr % 100 != 0)||(yr % 400 == 0));

		System.out.println(day + "/" + mth + "/" + yr);

		int daysInMonth;
		switch(mth){
		case 4:
		case 6:
		case 9:
		case 11: daysInMonth = 30;
			break;
		case 2: daysInMonth = ((isLeapYear)? 29 : 28);
			break;
		default: daysInMonth = 31;
			break;
		}
	
		System.out.println(daysInMonth + " days in month");

		System.out.println("-----------------------------------");
		System.out.println("Printing all days to end of the month using while loop...");

		int temp = day; 
		while(temp <= daysInMonth){
			System.out.println(temp + "/" + mth + "/" + yr);
			temp++;
		}

		System.out.println("-----------------------------------");
		System.out.println("Printing maximum of 10 days to end of the month using while loop...");

		temp = day;
		int itr = 0; 
		while(temp <= daysInMonth && itr < 10){
			System.out.println(temp + "/" + mth + "/" + yr);
			 temp++; itr++;
		}

		System.out.println("-----------------------------------");
		System.out.println("Check due date");

		int dueDay, dueMth, dueYr;
		int rentDays = 3;
	
		dueDay = rentDays+ day;
		dueMth = mth;
		dueYr = yr;

		System.out.println("Rental Date: " + day + "/" + mth + "/" + yr);
		System.out.println("Number of rental days: " + rentDays);
	
		// Check for month
		if(dueDay >= daysInMonth){
			dueDay = (dueDay - daysInMonth);
			dueMth = (++dueMth);

			//Check for year
			if (dueMth >= 12){
				dueMth = 1;
				++dueYr;
			}
		}

			
		System.out.println("Date Due back: " + dueDay + "/" + dueMth + "/" + dueYr);
		

		
	}
}