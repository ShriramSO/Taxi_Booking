package com.TaxiBooking.Main;
import java.util.*;


public class Taxi {
	
		static int taxicount = 0;
		int id;
		char currentSpot;
		int freeTime;
		int totalEarnings;
		List<String> trips;
		
		public Taxi(){
			currentSpot = 'A';
			freeTime = 6;
			totalEarnings = 0;
			trips = new ArrayList<String>();
			taxicount = taxicount + 1;
			id = taxicount;
		}
		
		public void setDetails(char currentSpot , int freeTime , int totalEarnings , String tripDetail) {
			this.currentSpot = currentSpot;
			this.freeTime = freeTime;
			this.totalEarnings = totalEarnings;
			this.trips.add(tripDetail);
		}
		
		public void printDetails() {
			System.out.println("Taxi -    "+this.id + " Total Earnings - " + this.totalEarnings);
			System.out.println("TaxiId   BookingId   CustomerId   From   To   PickUpTime      DropTime   Amount");
			
			for(String t : trips) {
				System.out.println(id + "         "+t );
			}
			System.out.println("-------------------------------------------------------------------------------");
		}
		
		public void printtaxiDetails() {
			System.out.println("Taxi - "+ this.id + " Total Earnings - "+this.totalEarnings+" Current spot - "+this.currentSpot+ " Free time - "+this.freeTime);
		}
}
