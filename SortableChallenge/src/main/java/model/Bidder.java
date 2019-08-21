package model;

public class Bidder 
{
	private String name = "";
	private double adjustment = 0.0;
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public double getAdjustment() 
	{
		return adjustment;
	}
	
	public void setAdjustment(double adjustment) 
	{
		this.adjustment = adjustment;
	}
}
