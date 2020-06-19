package assignment1;

public class Hotel {
	
	public String hotelName; 
	Room[] rooms; // the rooms in the hotel
	
	public Hotel(String hotelName, Room[] r) {
		this.hotelName = hotelName; 
		this.rooms = new Room[r.length]; 
		for (int i = 0; i < rooms.length; i++) {
			this.rooms[i] = new Room(r[i]); //TODO what if arrays have different lengths
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
			if (availableRoom == null) {
				throw new IllegalArgumentException("Either roomType does not match any of the defined types or there are no rooms of the type available");
			}
			else {
				availableRoom.changeAvailability();
				return availableRoom.getPrice();
			}
		}
		throw new IllegalArgumentException("Either roomType does not match any of the defined types or there are no rooms of the type available"); 
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
