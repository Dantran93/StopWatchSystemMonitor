package model;

import java.util.Date;

/**
 * StopWatchPeriod.java
 * Author: Daniel Tranfaglia
 * 
 * Purpose: Represents a time period in which a system is either on or off.
 */

public class StopWatchPeriod
{
	private static final String INVALID_TIME_PERIOD_MSG = "Start time cannot be after the end time.";
	
	
	
	private Date startTime;
	private Date endTime;
	
	private boolean isOn;
	
	
	
	/**
	 * StopWatchPeriod Constructor ()
	 * 
	 * Purpose: Creates a blank StopWatchPeriod with no start time, no
	 * 		end time, and the system is off.
	 */
	
	public StopWatchPeriod ()
	{
		this.isOn = false;
	} // StopWatchPeriod Constructor ()
	
	
	
	/**
	 * StopWatchPeriod Constructor (Date, Date)
	 * 
	 * Purpose: Creates a StopWatchPeriod with the given start time and
	 * 		end time, and the system is off.
	 */
	
	public StopWatchPeriod (Date startTime, Date endTime)
	{
		if (startTime.getTime() > endTime.getTime())
			throw new IllegalArgumentException(INVALID_TIME_PERIOD_MSG);
		
		this.startTime = startTime;
		this.endTime = endTime;
		this.isOn = false;
	} // StopWatchPeriod Constructor (Date, Date)
	
	
	
	/**
	 * StopWatchPeriod Constructor (Date, Date)
	 * 
	 * Purpose: Creates a StopWatchPeriod with the given start time, end
	 * 		time, and system status.
	 */
	
	public StopWatchPeriod (Date startTime, Date endTime, boolean isOn)
	{
		this(startTime, endTime);
		this.isOn = isOn;
	} // StopWatchPeriod Constructor (Date, Date)
	
	
	
	/**
	 * toString()
	 * 
	 * Purpose: Returns a String representation of the StopWatchPeriod.
	 */
	
	public String toString ()
	{
		String str = "Start Time: " + startTime + "\n";
		str += "End Time:   " + endTime + "\n";
		String status = "Status: " + (isOn ? "On" : "Off");
		return str + status;
	} // toString()
	
	
	
	/**
	 * getStartTime()
	 * 
	 * Purpose: Returns the start time.
	 */
	
	public Date getStartTime ()
	{
		return startTime;
	} // getStartTime()
	
	
	
	/**
	 * setStartTime()
	 * 
	 * Purpose: Sets the start time to a new time.
	 */
	
	public void setStartTime (Date newStartTime)
	{
		startTime = newStartTime;
	} // setStartTime()
	
	
	
	/**
	 * getEndTime()
	 * 
	 * Purpose: Returns the end time.
	 */
	
	public Date getEndTime ()
	{
		return endTime;
	} // getEndTime()
	
	
	
	/**
	 * setEndTime()
	 * 
	 * Purpose: Sets the end time to a new time.
	 */
	
	public void setEndTime (Date newEndTime)
	{
		endTime = newEndTime;
	} // setEndTime()
	
	
	
	/**
	 * isOn()
	 * 
	 * Purpose: Returns the status of the system during this time period.
	 */
	
	public boolean isOn ()
	{
		return isOn;
	} // isOn()
	
	
	
	/**
	 * setIsOn()
	 * 
	 * Purpose: Sets the status of the system to a new status.
	 */
	
	public void setIsOn (boolean newStatus)
	{
		isOn = newStatus;
	} // setIsOn()
	
	
	
	/**
	 * getTimeDiffInHrs()
	 * 
	 * Purpose:
	 */
	
	public double getTimeDiffInHrs ()
	{
		double timeDifferenceMilliseconds = endTime.getTime() - startTime.getTime();
		double timeDifferenceHours = (timeDifferenceMilliseconds) / 1000 / 60 / 60;
		return timeDifferenceHours;
	} // getTimeDiffInHrs()
	
} // class StopWatchPeriod
