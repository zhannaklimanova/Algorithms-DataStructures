package assignment1;

public class Hotel {
	
	private String hotelName; 
	Room[] rooms; // the rooms in the hotel
	
	public Hotel(String hotelName, Room[] rooms) {
		this.hotelName = hotelName; 
		for (int i = 0; i < rooms.length; i++) {
			this.rooms[i] = new Room(rooms[i]); //TODO what if arrays have different lengths
		}
	}
	
	/*
	 * reserveRoom changes the availability of the first available
	 * room of the specified type in the hotel. If successful, the 
	 * method returns the price of the room, otherwise an 
	 * IllegalArgumentException is thrown. 
	 */
	public int reserveRoom(String roomType) {
		if (rooms != null) {
			Room availableRoom = Room.findAvailableRoom(rooms, roomType);
			availableRoom.changeAvailability();
			return availableRoom.getPrice();
		}
		throw new IllegalArgumentException("Either roomType does not match any of the defined types or there are no rooms available"); 
	}
	
	/*
	 * cancelRoom makes that room of the input type available. Returns 
	 * true if the operation was possible; false otherwise.
	 */
	public boolean cancelRoom(String roomType) {
		if (rooms != null) {
			return Room.makeRoomAvailable(rooms, roomType);
		}
		return false;
	}
}
