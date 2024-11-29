//Fionntan Doherty L00172559
package ie.atu.hotel;

import java.text.DecimalFormat;

public class Room implements Reservable{
	private int roomNumber =0;
	private String roomType;
	private double pricePerNight;
   private boolean allocated;

	public Room(int roomNumber, String roomType, double pricePerNight, boolean allocated){
		
		 if(pricePerNight <100)
			 throw new IllegalArgumentException("The minimum price per room must be €100.00");
		
		 if(!"Double".equalsIgnoreCase(roomType)&& !"King".equalsIgnoreCase(roomType) && !"Family".equalsIgnoreCase(roomType))
			 throw new IllegalArgumentException("Invalid Room Type, must be Double, King or Family");
			 
		this.roomNumber=roomNumber;
		this.roomType=roomType;
		this.pricePerNight=pricePerNight;
		this.allocated=false;
      
	}
	@Override
	public void reserve() {
		if(roomNumber != 0)
			allocated = true;
			System.out.println("Room [" + roomNumber +"] is reserved");
		
	}	
	
	@Override
	public void cancel() {
		if(roomNumber > 0)
			allocated = false;
			System.out.println("Room [" + roomNumber +"] is available");
		
	}
	
	@Override
	public String toString() {
		DecimalFormat df=new DecimalFormat("#.00");

		return roomNumber + " " + roomType + " ROOM @ €" + df.format(pricePerNight) + " per night " + (allocated ? "BOOKED" : "FREE");
	}
	
	@Override
	public boolean equals(Object obj){
	   Room rObject;
	   if (obj instanceof Room)
		   rObject = (Room)obj;
	   else
	       return false;
	 
	   return this.roomNumber == (rObject.roomNumber);
	}

	public void setRoomNumber(int roomNumber){
		this.roomNumber=roomNumber;
	}
	public void setRoomType(String roomType){
		this.roomType=roomType;
	}
	public void setPricePerNight(double pricePerNight){
		 if(pricePerNight <100)
			 throw new IllegalArgumentException("The minimum price per room must be €100.00");
		this.pricePerNight=pricePerNight;
	}
	public void setAllocated(boolean allocated) {
		this.allocated=allocated;
	}
	public int getNumber() {
		return roomNumber;
	}
	public String getRoomType() {
		return roomType;
	}
	public double getPricePerNight() {
		return pricePerNight;
	}
	public boolean getAllocated() {
		return allocated;
	}
}
