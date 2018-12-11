package tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
		String[] options = null;
		try {
			File csvFile = new File("test_spreadsheet.csv");
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
			
			String line = csvReader.readLine();
			String[] items = line.split(",");
			
			int numCol = items.length;
			options = createOptions(numCol);
		} catch (Exception e) { e.printStackTrace(); }
		
		for (String option : options)
			System.out.println(option);
		
		
		/*
		try {
			File csvFile = new File("test_spreadsheet.csv");
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
			
			String line = csvReader.readLine();
			String[] titles = line.split(",");
			for (int i = 0; i < titles.length; i++)
			{
				titles[i] = titles[i].replace('_', ' ');
				titles[i] = capitalize(titles[i]);
				//System.out.println(titles[i]);
			}
			
			line = csvReader.readLine();
			String[] data = line.split(",");
			for (String item : data)
				System.out.println(item);
			
			csvReader.close();
		} catch (Exception e) { e.printStackTrace(); }
		*/
	} // main()
	
	
	
	private static String[] createOptions (int numOfColumns)
	{
		String[] options = new String[numOfColumns];
		for (int i = 0; i < numOfColumns; i++)
			options[i] = convertColumnToID(i+1);
		return options;
	}
	
	
	
	
	
	private static String capitalize (String line)
	{
		line = line.toLowerCase();
		char[] characters = line.toCharArray();
		for (int i = 0; i < characters.length; i++)
		{
			if (i == 0 && !Character.isWhitespace(characters[i]))
				characters[i] = Character.toUpperCase(characters[i]);
			if (i > 0 && Character.isWhitespace(characters[i-1]) && !Character.isWhitespace(characters[i]))
				characters[i] = Character.toUpperCase(characters[i]);
		}
		return new String(characters);
	}
	
	
	
	private static String convertColumnToID (int columnNum)
	{
		final int BASE = 26;
		String id = "";
		int quotient = columnNum;
		int remainder;
		while (quotient > 0) 
        { 
            remainder = quotient % BASE; 
            
            if (remainder == 0) 
            { 
                id = "Z" + id; 
                quotient = (quotient / BASE) - 1; 
            }
            else
            { 
                id = (char)((remainder - 1) + 'A') + id; 
                quotient = quotient / BASE; 
            } 
        } 
		return id;
	}
	
	
	
	/**
	 * runTimeDateTest()
	 * 
	 * Purpose: Contains code to guide with time and dates.
	 */
	
	public static void runTimeDateTest ()
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
	} // runTimeDateTest
	
} // class TestingMain
