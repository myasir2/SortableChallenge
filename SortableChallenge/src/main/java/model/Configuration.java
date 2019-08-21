package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Configuration 
{
	private ArrayList<Site> sites = null;
	
	// A map to retrieve a Site configuration in constant time by providing it's name
	private HashMap<String, Site> siteConfigMap = new HashMap<>();
	
	private ArrayList<Bidder> bidders = null;
	
	// A map to retrieve a Bidder configuration in constant time by providing it's name
	private HashMap<String, Bidder> bidderConfigMap = new HashMap<>();	
	
	public HashMap<String, Site> constructSiteConfigMap()
	{
		for(Site site : sites)
			siteConfigMap.put(site.getName(), site);
		
		return siteConfigMap;
	}
	
	public HashMap<String, Bidder> constructBidderConfigMap()
	{
		for(Bidder bidder : bidders)
			bidderConfigMap.put(bidder.getName(), bidder);
		
		return bidderConfigMap;
	}
	
	public ArrayList<Site> getSites() 
	{
		return sites;
	}
	
	public void setSites(ArrayList<Site> sites) 
	{
		this.sites = sites;
	}
	
	public ArrayList<Bidder> getBidders() 
	{
		return bidders;
	}
	
	public void setBidders(ArrayList<Bidder> bidders)
	{
		this.bidders = bidders;
	}

	public HashMap<String, Bidder> getBidderConfigMap() 
	{
		return bidderConfigMap;
	}

	public void setBidderConfigMap(HashMap<String, Bidder> bidderConfigMap) 
	{
		this.bidderConfigMap = bidderConfigMap;
	}

	public HashMap<String, Site> getSiteConfigMap() 
	{
		return siteConfigMap;
	}

	public void setSiteConfigMap(HashMap<String, Site> siteConfigMap) 
	{
		this.siteConfigMap = siteConfigMap;
	}
}
