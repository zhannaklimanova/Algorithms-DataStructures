package assignment1;

public class BnBReservation extends HotelReservation { // Bed and breakfast reservation

	public BnBReservation(String reservationName, Hotel hotel, String typeRoom, int numNights) {
		super(reservationName, hotel, typeRoom, numNights); 
	}
	
	/*
	 * getCost takes no input and returns the cost of the reservation in cents. 
	 * Since this reservation includes breakfast, to the cost of reserving the 
	 * room in the hotel you should add $10/night. 
	 */
	@Override
	public int getCost() {
		return super.getCost() + (1000 * super.numNights);
	}
}
