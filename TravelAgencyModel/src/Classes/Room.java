public class Room {

	private String roomType; // type of room: double, queen, king 
	private int priceRoom; // price of room in cents
	private boolean roomAvailability; 
	
	public Room(String roomType) {
		
		/*
		 * if input does not match one of these room types, then constructor
		 * should throw an IllegalArgumentException.
		 */
		
		switch(roomType.toLowerCase()) { // 638621 nanoseconds (seems that switch is faster; however it was hard to tell as time values kept oscillating due to daemons
			case "double":
				this.roomType = "double";
				this.priceRoom = 9000;
				this.roomAvailability = true;
				break;
			case "queen":
				this.roomType = "queen"; 
				this.priceRoom = 11000;
				this.roomAvailability = true;
				break;
			case "king": 
				this.roomType = "king";
				this.priceRoom = 15000; 
				this.roomAvailability = true; 
				break;
			default: 
				throw new IllegalArgumentException("No room of such type can be created because the input does not match the given room types"); 
		}
	
//		if (roomType.equalsIgnoreCase("double")) { // 756088 nanoseconds 
//			this.roomType = "double";
//			this.priceRoom = 9000;
//			this.roomAvailability = true;
//		}
//		else if (roomType.equalsIgnoreCase("queen")) {
//			this.roomType = "queen"; 
//			this.priceRoom = 11000;
//			this.roomAvailability = true;
//		}
//		else if (roomType.equalsIgnoreCase("king")) { 
//			this.roomType = "king";
//			this.priceRoom = 15000; 
//			this.roomAvailability = true;
//		}
//		else {
//			throw new IllegalArgumentException("No room of such type can be created because the input does not match the given room types");
//		}
	}
	
	// copy constructor
	// link on copy constructors https://dzone.com/articles/java-copy-shallow-vs-deep-in-which-you-will-swim
	// in general if fields are primitive or immutable types then shallow copy works/ otherwise deep copy is needed
	public Room(Room room) {
		this.roomType = room.roomType; 
		this.priceRoom = room.priceRoom;
		this.roomAvailability = room.roomAvailability;
	}
	
	public String getType() {
		return this.roomType;
	}
	
	public int getPrice() {
		return this.priceRoom;
	}
	
	private boolean getRoomAvailability() { 
		return this.roomAvailability;
	}
	
	public void changeAvailability() {
		this.roomAvailability = !(this.roomAvailability); 
	}
	
	/*
	 * findAvailableRoom returns the first available room in the array 
	 * of the indicated type. If no such room exists (either because all 
	 * rooms of said type are occupied, or because no room of such type 
	 * is in the array), the method returns null. 
	 */
	public static Room findAvailableRoom(Room[] rooms, String roomType) {
		if (rooms != null) {
			for (Room room: rooms) {
				if ((room != null) && (room.getType().equals(roomType)) && room.getRoomAvailability() != false) {
					return room;
				}
			}
		}
		return null;
	}
	
	/*
	 * Helper method to find the first unavailable room. 
	 */
	private static Room findUnavailableRoom(Room[] rooms, String roomType) {
		if (rooms != null) {
			for (Room room: rooms) {
				if ((room != null) && (room.getType().equals(roomType)) && room.getRoomAvailability() == false) {
					return room;
				}
			}
		}
		return null;
	}
	
	/*
	 * makeRoomAvailagle will make the first unavailable room in the 
	 * array of the indicated type available again. If successful, the 
	 * method should return true, otherwise the method should return
	 * false;
	 */
	public static boolean makeRoomAvailable(Room[] rooms, String roomType) {
		Room unavailableRoom = findUnavailableRoom(rooms, roomType); 
		if (unavailableRoom != null) {
			unavailableRoom.changeAvailability();
			return true;
		}
		return false;
	}
}
