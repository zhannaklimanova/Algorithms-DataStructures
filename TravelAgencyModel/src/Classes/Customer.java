public class Customer {
	
	String customerName;
	int customerBalance; // in cents
	Basket customerReservations;
	
	public Customer(String customerName, int customerBalance) {
		this.customerName = customerName;
		this.customerBalance = customerBalance;
		this.customerReservations = new Basket();
	}
	
	public String getName() {
		return this.customerName;
	}
	
	public int getBalance() {
		return this.customerBalance;
	}
	
	public Basket getBasket() {
		return this.customerReservations;
	}
	
	/*
	 * addFunds takes an int as input representing the amount of cents to be 
	 * added to the balance of the customer. If the input received is negative,
	 * the method should throw an IllegalArgumentException with an appropriate 
	 * message. Otherwise, the method will simply update the balance and return 
	 * new balance in cents.
	 */
	public int addFunds(int amountToAdd) {
		if (amountToAdd < 0) {
			throw new IllegalArgumentException("The number of cents added cannot be negative");
		}
		return this.customerBalance += amountToAdd;
	}

	/*
	 * addToBasket takes a Reservation as input and adds it to the basket
	 * of the customer if the name on the reservation matches the name of the 
	 * customer. If the method is successful it should return the number of 
	 * reservations in the basket of this customer. Otherwise, the method should 
	 * throw an IllegalArgumentException.
	 */
	public int addToBasket(Reservation reservation) {
		if (reservation.reservationName().equals(this.customerName)) {
			return this.customerReservations.add(reservation);
		}
		throw new IllegalArgumentException("The name on the reservation does not match input name");
	}
	
	/*
	 * addToBasket takes a Hotel, a String representing a room type, an int 
	 * representing the number of nights, and a boolean representing whether 
	 * or not the customer wants breakfast to be included. The method adds the 
	 * corresponding reservation to the basket of the customer and returns the 
	 * number of reservations that are now in the basket of this customer.
	 */
	public int addToBasket(Hotel hotel, String roomType, int numNights, boolean bnbIncluded) {
		try {
			if (bnbIncluded == true) {
				return this.customerReservations.add(new BnBReservation(this.customerName, hotel, roomType, numNights));
			}
			else {
				return this.customerReservations.add(new HotelReservation(this.customerName, hotel, roomType, numNights));
			}
		} 
		catch (Exception e) {
			return this.customerReservations.getNumOfReservations();
		}
	}
	
	/*
	 * addToBasket takes two Airports as input. The method adds the corresponding 
	 * reservation to the basket of the customer and returns the number of 
	 * reservations that are now in their basket, whether or not the flight 
	 * reservation was created successfully
	 */
	public int addToBasket(Airport a1, Airport a2) {
		try {
			return this.customerReservations.add(new FlightReservation(this.customerName, a1, a2));
		}
		catch (Exception e) {
			return this.customerReservations.getNumOfReservations();
		}
	}
	
	/*
	 * removeFromBasket takes a Reservation as input and removes it from the 
	 * basket of the customer. The method returns a boolean indicating whether 
	 * of not the operation was successful.
	 */
	public boolean removeFromBasket(Reservation reservation) {
		return this.customerReservations.remove(reservation);
	}
	
	/*
	 * checkOut takes no input. If the customer’s balance is not enough to cover 
	 * the total cost of their basket, then the method throws an IllegalStateException. 
	 * Otherwise, the customer is charged the total cost of the basket, the basket
	 * is cleared, and balance left is returned.
	 */
	public int checkOut() {
		int balanceLeftover;
		
		if (this.getBalance() >= customerReservations.getTotalCost()) {
			balanceLeftover = this.getBalance() - customerReservations.getTotalCost();
			customerReservations.clear();
			return balanceLeftover;
		}
		throw new IllegalStateException("Not enough balance to cover the basket costs");
	}
	
}
