package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * StructureBuilder.java
 * Author: Daniel Tranfaglia
 * 
 * Purpose:
 */

public class StructureBuilder
{
	/**
	 * readFileGetNumColumns()
	 * 
	 * Purpose:
	 */
	
	public static int readFileGetNumColumns (BufferedReader csvReader)
	{
		String line;
		String[] items = new String[0];
		try {
			line = csvReader.readLine();
			items = line.split(",");
		} catch (IOException e) { e.printStackTrace(); }
		return items.length;
	} // readFileGetNumColumns()
	
	
	
	/**
	 * readFileBuildStructure()
	 * 
	 * Purpose:
	 */
	
	public static List<MonitoredSystem> readFileBuildStructure (BufferedReader csvReader)
	{
		List<MonitoredSystem> systemList = new LinkedList<MonitoredSystem>();
		Calendar calendar = Calendar.getInstance();
		
		String line, itemNumber, startDateString, endDateString;
		String[] items;
		try {
			while ((line = csvReader.readLine()) != null)
			{
				items = line.split(",");
				itemNumber = items[0];
				startDateString = items[1];
				endDateString = items[2];
				
				int[] startDate = parseTimestamp(startDateString);
				calendar.set(startDate[0], startDate[1], startDate[2], startDate[3], startDate[4]);
				Date start = calendar.getTime();
				
				int[] endDate = parseTimestamp(endDateString);
				calendar.set(endDate[0], endDate[1], endDate[2], endDate[3], endDate[4]);
				Date end = calendar.getTime();
				
				boolean itemNumberFound = false;
				for (MonitoredSystem currSystem : systemList)
				{
					if (currSystem.getName().equals(itemNumber))
					{
						itemNumberFound = true;
						currSystem.getSystemHistory().add(new StopWatchPeriod(start, end, true));
						break;
					}
				}
				
				if (!itemNumberFound)
				{
					MonitoredSystem newSystem = new MonitoredSystem(itemNumber);
					newSystem.getSystemHistory().add(new StopWatchPeriod(start, end, true));
					systemList.add(newSystem);
				}
				
			}
		} catch (IOException e) { e.printStackTrace(); }
		
		return systemList;
	} // readFileBuildStructure()
	
	
	
	/**
	 * parseTimestamp()
	 * 
	 * Purpose:
	 */
	
	private static int[] parseTimestamp (String timestampString)
	{
		int[] timestamp = new int[5];
		
		String[] components = timestampString.split(" ");
		String[] date = components[0].split("/");
		String[] time = components[1].split(":");
		
		timestamp[0] = Integer.parseInt(date[2]);
		timestamp[1] = Integer.parseInt(date[0]);
		timestamp[2] = Integer.parseInt(date[1]);
		timestamp[3] = Integer.parseInt(time[0]);
		timestamp[4] = Integer.parseInt(time[1]);
		
		return timestamp;
	} // parseTimestamp()
	
	
	
	/**
	 * createOptions()
	 * 
	 * Purpose:
	 */
	
	public static String[] createOptions (int numOfColumns)
	{
		String[] options = new String[numOfColumns];
		for (int i = 0; i < numOfColumns; i++)
			options[i] = convertColumnToID(i+1);
		return options;
	} // createOptions()
	
	
	
	/**
	 * convertColumnToID()
	 * 
	 * Purpose:
	 */
	
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
	} // convertColumnToID()
	
} // class StructureBuilder
