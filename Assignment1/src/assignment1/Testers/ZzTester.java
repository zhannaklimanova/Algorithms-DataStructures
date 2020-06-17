package assignment1;

public class ZzTester {

	public static void main(String[] args) {
		
		// Airport Class Tester 
//		Airport a1 = new Airport(50, 30 , 30);
//		Airport a2 = new Airport(-20, 40, 30); 
//		System.out.println(Airport.getDistance(a1, a2)); 
//
//		System.out.println(a1.getFees());
		
		// Room Class Tester
		// Room Constructor1 Tester
//		long t = System.nanoTime();
//		Room r = new Room("double"); 
//		System.out.println(r.getType() + r.getPrice() + r.getRoomAvailability());
//		Room r1 = new Room("queen");
//		System.out.println(r1.getType() + r1.getPrice() + r1.getRoomAvailability());
//		Room r2 = new Room("king");
//		System.out.println(r2.getType() + r2.getPrice() + r2.getRoomAvailability());
//		Room r3 = new Room("Double");
//		System.out.println(r3.getType() + r3.getPrice() + r3.getRoomAvailability());
//		long t2 = System.nanoTime();
//		System.out.println(t2 - t);
//		Room r4 = new Room("doe"); // IllegalArgumentException
		
		// Room Contstructor2 Tester
//		Room c = new Room("double"); 
//		Room copy = new Room(c); 
//		
//		c.priceRoom = 10000;
//		System.out.println(c.roomType + c.priceRoom + c.roomAvailability); // outputs double10000true
//		c.priceRoom = 10000;
//		System.out.println(copy.roomType + copy.priceRoom + copy.roomAvailability); // outputs double9000true
		
		// changeAvailability Tester
		Room q = new Room("double"); 
		q.changeAvailability();
		System.out.println(q.getRoomAvailability()); // was true now is false
		q.changeAvailability();
		System.out.println(q.getRoomAvailability()); // was false now is true;
		
	}

}
