package tests;

import java.util.Calendar;
import java.util.Date;

import model.StopWatchPeriod;

/**
 * TestingMain.java
 * Author: Daniel Tranfaglia
 * 
 * Purpose: Provides a main method for quick tests.
 */

public class TestingMain
{
	/**
	 * main ()
	 * 
	 * Purpose: Write code for quick tests here.
	 */
	
	public static void main (String[] args)
	{
		Calendar calendar = Calendar.getInstance();
		
		// Gets the curent date and time
		Date date1 = calendar.getTime();
		System.out.println("Current:  " + date1.toString());
		
		/**
		 * Chooses a date and time
		 * 
		 * year: Choose a year
		 * month: 0 (January) - 11 (December)
		 * date: day of the month (1 - last day)
		 * hour: 0 - 23
		 * minute: 0 - 59
		 */
		
		calendar.set(2018, 11, 4, 14, 0, 0);
		Date date2 = calendar.getTime();
		System.out.println("Selected: " + date2.toString());
		
		double timeDifferenceMilliseconds = date2.getTime() - date1.getTime();
		double timeDifferenceHours = (timeDifferenceMilliseconds) / 1000 / 60 / 60;
		System.out.println(timeDifferenceHours);
		
		/* Time Periods */
		
		calendar.set(2018, 11, 3, 2, 0, 0);
		Date startTime = calendar.getTime();
		
		calendar.set(2018, 11, 3, 5, 15, 0);
		Date endTime = calendar.getTime();
		
		StopWatchPeriod x = new StopWatchPeriod(startTime, endTime, true);
		System.out.println(x);
		System.out.println(x.getTimeDiffInHrs() + " Hours");
	} // main()
	
} // class TestingMain
