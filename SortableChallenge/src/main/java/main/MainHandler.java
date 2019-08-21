package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import model.Auction;
import model.Bid;
import model.Bidder;
import model.Configuration;
import model.Site;

public class MainHandler 
{
	private static final String CONFIG_FILE_PATH = "config.json";
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static Configuration config = new Configuration();
	
	public static void main(String args[])
	{
		try
		{
			// Read all bytes from config file, transform into String, and parse the string into a Configuration object
			String jsonConfig = new String(Files.readAllBytes(Paths.get(CONFIG_FILE_PATH)));
			config = gson.fromJson(jsonConfig, Configuration.class);
			config.constructBidderConfigMap();
			config.constructSiteConfigMap();
			
			Auction auctions[] = getAuctionsFromInputJson();
			ArrayList<ArrayList<Bid>> winningBidsPerAuction = new ArrayList<>();
			
			for(Auction auction : auctions)
			{
				ArrayList<Bid> winningBids = getWinningBids(auction);
				
				winningBidsPerAuction.add(winningBids);
			}
			
			// Display output in Json format
			System.out.println(gson.toJson(winningBidsPerAuction));
		}
		catch(JsonSyntaxException e)
		{
			e.printStackTrace();
			System.out.println("Invalid configuration JSON supplied");
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("Config file: " + CONFIG_FILE_PATH + " not found");
		}	
	}
	
	private static Auction[] getAuctionsFromInputJson()
	{
		Auction auctions[] = null;
		Scanner scanner = new Scanner(System.in);
		boolean validInput = false;
		
		// Keep asking for input until proper input is loaded
		do
		{
			try
			{
				// Read Json from STDIN and parse into an Auction object
				System.out.println("Please input your JSON input for the Auction:");
				String json = scanner.nextLine();
				auctions = gson.fromJson(json, Auction[].class);
				
				validInput = true;
			}
			catch(JsonSyntaxException e)
			{
				System.out.println("Invalid Auction JSON provided. Try again");
			}
			
		} while(!validInput);
		
		scanner.close();
		
		return auctions;
	}
	
	private static ArrayList<Bid> getWinningBids(Auction auction)
	{
		double siteFloor = config.getSiteConfigMap().get(auction.getSite()).getFloor();
		
		// A HashMap to contain winning bid per Unit
		HashMap<String, Bid> winningBidsPerUnit = new HashMap<>();
		HashMap<String, Bidder> bidderConfigurations = config.getBidderConfigMap();
		
		for(Bid bid : auction.getBids())
		{
			// Get Bidder configuration and calculate adjusted value
			Bidder bidder = bidderConfigurations.get(bid.getBidder());
			double adjustedValue = bid.getBid() * (1 + bidder.getAdjustment());
			
			// If Bid is not valid, ignore and move on
			if(adjustedValue < siteFloor || !validateBidder(bid, auction))
				continue;
			
			Bid winningBid = winningBidsPerUnit.get(bid.getUnit());
			
			if(winningBid == null || adjustedValue > winningBid.getBid())
				winningBidsPerUnit.put(bid.getUnit(), bid);
		}
		
		// Return the winningBids in a List format
		return new ArrayList<>(winningBidsPerUnit.values());
	}
	
	private static boolean validateBidder(Bid bid, Auction auction)
	{
		Site site = config.getSiteConfigMap().get(auction.getSite());
		
		// Bidder is not allowed to bid on the site, or is unknown
		if(!site.getBidders().contains(bid.getBidder()))
			return false;
		
		// Bidder's unit is not involved in Auction's units
		if(!auction.getUnits().contains(bid.getUnit()))
			return false;
		
		return true;
	}
}
