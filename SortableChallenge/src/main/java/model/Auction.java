package model;

import java.util.HashSet;

public class Auction 
{
	private String site = "";
	private HashSet<String> units = null;
	private Bid bids[] = null;
	
	public String getSite() 
	{
		return site;
	}
	
	public void setSite(String site) 
	{
		this.site = site;
	}
	
	public HashSet<String> getUnits() 
	{
		return units;
	}
	
	public void setUnits(HashSet<String> units) 
	{
		this.units = units;
	}
	
	public Bid[] getBids()
	{
		return bids;
	}
	
	public void setBids(Bid[] bids) 
	{
		this.bids = bids;
	}
}
