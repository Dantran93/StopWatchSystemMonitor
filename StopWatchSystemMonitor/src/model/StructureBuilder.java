package model;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 
 */

public class StructureBuilder
{
	
	
	
	/**
	 * 
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
	}
	
	
	
	
	/**
	 * 
	 */
	
	public static String[] createOptions (int numOfColumns)
	{
		String[] options = new String[numOfColumns];
		for (int i = 0; i < numOfColumns; i++)
			options[i] = convertColumnToID(i+1);
		return options;
	} // createOptions()
	
	
	
	/**
	 * 
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
} // class StructureFormatter
