package assignment1;

public class HotelReservation extends Reservation { // TODO all classes make sure any fields called are called by this.field (better practice)

	Hotel hotel; 
	String roomType; 
	int numNights;
	int price; // price of room in cents 
	
	public HotelReservation(String reservationName, Hotel hotel, String roomType, int numNights) {
		super(reservationName); 
		
		try {
			this.price = hotel.reserveRoom(roomType);
		}
		catch (Exception e){
			throw new IllegalArgumentException("The room you are trying to reserve is not available. Try again later");		
		}
		
		this.hotel = hotel;
		this.roomType = roomType;
		this.numNights = numNights;
	}
	
	public int getNumOfNights() {
		return this.numNights;
	}
	
	/*
	 * getCost is the price to pay for the specified type of room given the number of
	 * nights indicated in the reservation. 
	 */
	@Override
	public int getCost() {
		return this.numNights * this.price;
	}

	/*
	 * equals returns true if input matches this in type, name, hotel, room type, 
	 * number of nights, and total cost. 
	 */
	@Override
	public boolean equals(Object object) { 
		
		if (object instanceof HotelReservation) {
			if  ((this.name.equals(((HotelReservation) object).name)) &&
				(this.hotel.equals(((HotelReservation) object).hotel)) &&
				(this.roomType.equals(((HotelReservation) object).roomType)) &&
				(this.numNights == (((HotelReservation) object).numNights)) &&
				(this.getCost() == (((HotelReservation) object).getCost()))) {
				return true; 
			}
		}
		return false;
	}
	
}
