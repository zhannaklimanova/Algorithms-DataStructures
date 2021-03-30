public class Airport {

	private int x_coordinate; // x coordinate of the airport on a world map with a scale to 1 km
	private int y_coordinate; // y coordinate of the airport on a world map with a scale to 1 km
	private int airportFees; // fees associated to this airport in cents 
	
	public Airport(int x_coordinate, int y_coordinate, int airportFees) {
		this.x_coordinate = x_coordinate; 
		this.y_coordinate = y_coordinate; 
		this.airportFees = airportFees;
	}
	
	public int getFees() { // retrieves the fees of the airport
		return this.airportFees; 
	}
	
	/*
	 * getDistance returns an integer indicating the distance in kilometers between the two airports. 
	 * It rounds the distance up. 
	 */
	public static int getDistance(Airport a1, Airport a2) {
		int distance = (int) Math.ceil((Math.sqrt(Math.pow((a2.x_coordinate - a1.x_coordinate), 2) + Math.pow((a2.y_coordinate - a1.y_coordinate), 2))));
		return distance; 
	}
}
