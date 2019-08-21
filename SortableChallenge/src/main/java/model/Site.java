package model;

import java.util.HashSet;

public class Site 
{
	private String name = "";
	private HashSet<String> bidders = null;
	private double floor = 0.0;
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public HashSet<String> getBidders() 
	{
		return bidders;
	}
	
	public void setBidders(HashSet<String> bidders) 
	{
		this.bidders = bidders;
	}
	
	public double getFloor() 
	{
		return floor;
	}
	
	public void setFloor(double floor) 
	{
		this.floor = floor;
	}
}
