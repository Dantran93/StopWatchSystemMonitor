package model;

import java.util.LinkedList;
import java.util.List;

/**
 * MonitoredSystem.java
 * Author: Daniel Tranfaglia
 * 
 * Purpose: Represents a system whose status is monitored over several time periods.
 */

public class MonitoredSystem
{
	private String name;
	
	private List<StopWatchPeriod> systemHistory;
	
	
	
	/**
	 * MonitoredSystem Constructor (String)
	 * 
	 * Purpose: Creates a MonitoredSystem with the given name and
	 * 		an empty system history.
	 */
	
	public MonitoredSystem (String name)
	{
		this.name = name;
		this.systemHistory = new LinkedList<StopWatchPeriod>();
	} // MonitoredSystem Constructor (String)
	
	
	
	/**
	 * MonitoredSystem Constructor (String, List<StopWatchPeriod>)
	 * 
	 * Purpose: Creates a MonitoredSystem with the given name and
	 * 		system history.
	 */
	
	public MonitoredSystem (String name, List<StopWatchPeriod> systemHistory)
	{
		this.name = name;
		this.systemHistory = systemHistory;
	} // MonitoredSystem Constructor (String, List<StopWatchPeriod>)
	
	
	
	/**
	 * toString()
	 * 
	 * Purpose: Returns a string representation of the system.
	 */
	
	public String toString ()
	{
		String str = name + "\n";
		return str + systemHistory;
	} // toString()
	
	
	
	/**
	 * getName()
	 * 
	 * Purpose: Returns the name of the system.
	 */
	
	public String getName ()
	{
		return name;
	} // getName()
	
	
	
	/**
	 * setName()
	 * 
	 * Purpose: Sets the name of the system to a new name.
	 */
	
	public void setName (String newName)
	{
		name = newName;
	} // setName()
	
	
	
	/**
	 * getSystemHistory()
	 * 
	 * Purpose: Returns the system history as a list.
	 */
	
	public List<StopWatchPeriod> getSystemHistory ()
	{
		return systemHistory;
	} // getSystemHistory()
	
	
	
	/**
	 * setSystemHistory()
	 * 
	 * Purpose: Sets the system history to a new list.
	 */
	
	public void setSystemHistory (List<StopWatchPeriod> newSystemHistory)
	{
		systemHistory = newSystemHistory;
	} // setSystemHistory()
	
} // class MonitoredSystem
