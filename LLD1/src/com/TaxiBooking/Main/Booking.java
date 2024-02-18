package com.TaxiBooking.Main;
import java.util.*;
public class Booking {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			List<Taxi> taxis = createtaxis(4);
			
			Scanner sc = new Scanner(System.in);
			int id = 1;
			
			while(true) {
				
				System.out.println(" 0 --> Book Taxi");
				System.out.println(" 1 --> Taxi Details");
				int choice = sc.nextInt();
				
				switch(choice) {
				case 0:
				{
					int customerId = id;
					System.out.println("Enter pickup point");
					char pickUpPoint = sc.next().charAt(0);	
					System.out.println("Enter drop point");
					char dropPoint = sc.next().charAt(0);
					System.out.println("Enter pickup time");
					int pickUpTime = sc.nextInt();
					
					
					if(pickUpPoint < 'A' || pickUpPoint > 'F' || dropPoint < 'A' || dropPoint > 'F') {
						System.out.println("Valid PickUp and drop are A, B, C, D, E, F. Exitting");
						return;
					}
					
					List<Taxi> freetaxis = getFreeTaxis(taxis,pickUpTime,pickUpPoint);
					
					if(freetaxis.size() == 0) {
						System.out.println("No Taxi can be alloted, Exitting");
						return;
					}
					
					Collections.sort(freetaxis , (a,b)->a.totalEarnings - b.totalEarnings);
					
					bookTaxi(id, pickUpPoint , dropPoint , pickUpTime , freetaxis);
						
					id++;
					break;
					}
				case 1:
				{
					for(Taxi t : taxis) 
						t.printtaxiDetails();
						
					for(Taxi t : taxis)
						t.printDetails();
					break;
				}
				default:
				{
					return;
				}
				}
			}
	}

	private static void bookTaxi(int customerId, char pickUpPoint, char dropPoint, int pickUpTime, List<Taxi> freetaxis) {
		// TODO Auto-generated method stub
		int min = Integer.MAX_VALUE;
		
		int distanceBetwPickAndDrop = 0;
		
		int earnings = 0;
		
		int nextfreeTime = 0;
		
		char nextSpot = 'Z';
		
		Taxi bookedTaxi = null;
		
		String tripDetail = "";
		
		
		for(Taxi t : freetaxis) {
			distanceBetwPickAndDrop = Math.abs((t.currentSpot - '0') - (pickUpPoint - '0'))*15;
			
			if(distanceBetwPickAndDrop < min) {
				bookedTaxi = t;
				
				distanceBetwPickAndDrop = Math.abs((dropPoint - '0') - (pickUpPoint - '0'))*15;
				
				earnings = (distanceBetwPickAndDrop-5) * 10 + 100;
				
				int dropTime = pickUpTime + distanceBetwPickAndDrop/15;
				
				nextfreeTime = dropTime;
				
				nextSpot = dropPoint;
				
				tripDetail = customerId +"      "+customerId+"      "+pickUpPoint+"      "+dropPoint+"      "+pickUpTime+"      "+dropTime+"      "+earnings;
				
				min = distanceBetwPickAndDrop;
			}
		}
		
		bookedTaxi.setDetails(nextSpot, nextfreeTime, bookedTaxi.totalEarnings+earnings, tripDetail);
		
		System.out.println("Taxi "+ bookedTaxi.id+"  booked");
	}

	private static List<Taxi> getFreeTaxis(List<Taxi> taxis, int pickUpTime, char pickUpPoint) {
		// TODO Auto-generated method stub
		List<Taxi> freetaxis = new ArrayList<Taxi>();
		
		for(Taxi t : taxis) {
			if(t.freeTime <= pickUpTime && (Math.abs((t.currentSpot - '0') - (pickUpPoint - '0')) <= pickUpTime - t.freeTime));
			freetaxis.add(t);
			
		}
		return freetaxis;
	}

	private static List<Taxi> createtaxis(int n) {
		// TODO Auto-generated method stub
		List<Taxi> taxis = new ArrayList<Taxi>();
		for(int i=1;i<=n;i++) {
			Taxi t = new Taxi();
			taxis.add(t);
		}
		return taxis;
	}

}
