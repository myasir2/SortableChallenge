package model;

public class Bid 
{
	private String bidder = "";
	private String unit = "";
	private double bid = 0.0;
	
	public String getBidder() 
	{
		return bidder;
	}
	
	public void setBidder(String bidder) 
	{
		this.bidder = bidder;
	}
	
	public String getUnit() 
	{
		return unit;
	}
	
	public void setUnit(String unit)
	{
		this.unit = unit;
	}
	
	public double getBid() 
	{
		return bid;
	}
	
	public void setBid(double bid)
	{
		this.bid = bid;
	}
}
