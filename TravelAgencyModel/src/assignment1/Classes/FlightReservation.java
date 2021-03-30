package assignment1;

public class FlightReservation extends Reservation {

	Airport departureLocation; 
	Airport arrivalLocation;
	
	public FlightReservation(String reservationName, Airport departureLocation, Airport arrivalLocation) {
		super(reservationName); 
		
		if (departureLocation.equals(arrivalLocation)) {
			throw new IllegalArgumentException("The two airport locations are the same."); 
		}
		
		this.departureLocation = departureLocation;
		this.arrivalLocation = arrivalLocation;
	
	}

	/*
	 * getCost returns the cost of the reservation in cents. Cost is computed by
	 * adding together the fuels cost, the airports fees, and $53.75 (costs related
	 * to plane plus taxes). Assuming that: 
	 * planes pay $1.24/fuelGallon
	 * planes fly 167.52 km/fuelGallon
	 */
	@Override
	public int getCost() {
		double planeAndTaxesCost = 53.75 * 100;
		double fuelsCost = ((Airport.getDistance(this.departureLocation, this.arrivalLocation) / 167.52) * 124);
		double airportFees = this.departureLocation.getFees() + this.arrivalLocation.getFees();
		
		int totalCost = (int) Math.ceil(planeAndTaxesCost + fuelsCost + airportFees);
		return totalCost;
	}

	/*
	 * equals returns true if input matches this in type, name, and airports. Otherwise, 
	 * it returns false. 
	 */
	@Override
	public boolean equals(Object object) {  
		if (object instanceof FlightReservation) {
			if ((this.reservationName().equals(((FlightReservation) object).reservationName())) &&
				(this.arrivalLocation.equals(((FlightReservation) object).arrivalLocation)) && 
				(this.departureLocation.equals(((FlightReservation) object).departureLocation))) {
					return true;
			}
		}
		return false;
	}
	
}
