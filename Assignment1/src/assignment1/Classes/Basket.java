package assignment1;

public class Basket {

	Reservation[] reservations; 
	
	// empty array of size 0 (created a reference to an array somewhere)
	public Basket() {
		this.reservations = new Reservation[0]; 
	}
	
	/*
	 * getProducts returns a shallow copy of the array of Reservations of the 
	 * basket. The array should contain all the reservations in the basket in 
	 * the same order in which they were added.
	 */
	public Reservation[] getProducts() {
		return this.reservations.clone();
	}
	
	/*
	 * add adds the reservation at the end of the list of reservations
	 * of the basket and returns how many reservations are now there.
	 */
	public int add(Reservation reservation) {
		this.reservations = increaseSize(this.reservations);
		
		for (int i = 0; i < this.reservations.length; i++) {
			if (this.reservations[i] == null) {
				this.reservations[i] = reservation;
				return this.reservations.length;
			}
		}
		return 0;
	}
	
	/*
	 * Helper method to increase the size of the array by 1. 
	 */
	private Reservation[] increaseSize(Reservation[] reservation) {
		int size = reservation.length;
		Reservation[] newReservation = new Reservation[size + 1];
		
		for (int i = 0; i < reservation.length; i++) {
			newReservation[i] = reservation[i];
		}
		return newReservation;
	}
	
	/*
	 * remove takes as input a Reservation and returns a boolean. The 
	 * method removes the first occurrence of the specified element from 
	 * the array of reservations of the basket. If no such reservation 
	 * exists, then the method returns false, otherwise, after removing it,
	 * the method returns true. 
	 * This method removes a reservation 
	 * from the list if and only if such reservation equals to the input
	 * received. After the reservation has been removed from the array, the 
	 * subsequent elements should be shifted down by one position, leaving no
	 * un-utilized slots in the middle array.
	 * 
	 * e.g. 
	 * 2 flight reservations from Montreal to Vancouver are not considered 
	 * equal if they were created under two different names. 
	 */
	public boolean remove(Reservation reservation) {
		for (int i = 0; i < this.reservations.length; i++) {
			if (this.reservations[i].equals(reservation)) {
				this.reservations[i] = null;
				this.reservations = decreaseSize(this.reservations);
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * Helper method to decrease the size of the reservation array. So that
	 * there is no null spots.
	 */
	public Reservation[] decreaseSize(Reservation[] reservations) {
		Reservation[] newReservation = new Reservation[reservations.length - 1]; 
		int counter = 0;
		
		for (int i = 0; i < reservations.length; i++) {
			if (reservations[i] == null) {
				continue;
			}
			newReservation[counter++] = reservations[i];
		}
		return newReservation;
	}
	
	/*
	 * clear takes on inputs, returns no values, and empties the array of reservations
	 * in the basket.
	 */
	public void clear() {
		this.reservations = new Reservation[0];
	}
	
	/*
	 * getNumOfReservations takes no inputs and returns the number of reservations
	 * in the basket.
	 */
	public int getNumOfReservations() {
		return this.reservations.length;
	}
	
	/*
	 * getTotalCost takes no inputs and returns the cost (in cents) of all the reservations
	 * in the basket.
	 */
	public int getTotalCost() {
		int totalCost = 0;
		
		for (Reservation r: this.reservations) {
			totalCost += r.getCost(); 
		}
		return totalCost;
	}
}
