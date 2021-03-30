package assignment1;

public class ZzTester {

	public static void main(String[] args) {
		
		// Airport Class Tester //////////////////////////////////////////////////////////////////////////////
//		Airport a1 = new Airport(50, 30 , 30);
//		Airport a2 = new Airport(-20, 40, 30); 
//		System.out.println(Airport.getDistance(a1, a2)); 
//
//		System.out.println(a1.getFees());
		
		// Room Class Tester /////////////////////////////
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
		
		// Room Contstructor2 Tester /////////////////////////////////////////////////////////////////////////
//		Room c = new Room("double"); 
//		Room copy = new Room(c); 
//		
//		c.priceRoom = 10000;
//		System.out.println(c.roomType + c.priceRoom + c.roomAvailability); // outputs double10000true
//		c.priceRoom = 10000;
//		System.out.println(copy.roomType + copy.priceRoom + copy.roomAvailability); // outputs double9000true
		
		// changeAvailability Tester /////////////////////////////////////////////////////////////////////////
//		Room q = new Room("double"); 
//		q.changeAvailability();
//		System.out.println(q.getRoomAvailability()); // was true now is false
//		q.changeAvailability();
//		System.out.println(q.getRoomAvailability()); // was false now is true;
		
		// findAvailableRoom and makeRoomAvailable Tester
//		Room w = new Room("double"); 
//		Room w1 = new Room("double");
//		Room w2 = new Room("double");
//		Room w3 = new Room("king");
//		Room w4 = new Room("queen");
//		w4.changeAvailability();
//		System.out.println(w4.getRoomAvailability());
//		Room[] arrayOfRooms = {w, w1, w2, w3, w4};
//		System.out.println((Room.findAvailableRoom(arrayOfRooms, "king")));
//		Room[] nullArray = new Room[3];
//		System.out.println((Room.findAvailableRoom(nullArray, "king")));
//		System.out.println(Room.makeRoomAvailable(arrayOfRooms, "queen"));
		
		// Hotel Class Tester ///////////////////////////////////////////////////////////////////////////////
//		Room p = new Room("double"); 
//		Room p1 = new Room("double");
//		Room p2 = new Room("double");
//		Room p3 = new Room("king");
//		Room p4 = new Room("queen");
//		Room p5 = new Room("king"); 
//		Room[] testerArray = {p, p1, p2, p3, p4, p5};
//		Hotel newHotel = new Hotel("Hiltin", testerArray); 
//		System.out.println(newHotel.hotelName); // returns Hitlin 
//		testerArray[3] = null;
//		System.out.println(testerArray[3]); // returns null
//		System.out.println(newHotel.rooms[3].getPrice()); // returns 15000
//		System.out.println(newHotel.reserveRoom("queen"));
//		System.out.println(newHotel.rooms[4].getRoomAvailability()); // returns false
//		System.out.println(newHotel.cancelRoom("queen"));
//		System.out.println(newHotel.rooms[4].getRoomAvailability()); // returns true 
	
	    // FlightReservation Tester //////////////////////////////////////////////////////////////////////////
	    // getCost() Tester
//	    Airport a1 = new Airport(4, 30000, 20000);
//	    Airport a2 = new Airport(7, 80000, 30000);
//	    FlightReservation f1 = new FlightReservation("myFlight", a1, a2); // get cost should return 92387
//	    int cost = f1.getCost();
//	    System.out.println(cost);
//		
//		// equals() Tester
//		FlightReservation f2 = new FlightReservation("myFlight", a1, a2);
//		FlightReservation f3 = new FlightReservation("sameFlight", a1, a2);
//		System.out.println(f1.equals(f2)); // true
//		System.out.println(f1.equals(f3)); // false
//		
//		// HotelReservation Tester /////////////////////////////////////////////////////////////////////////// 
//		// getCost() Tester
//		Hotel hotel1 = new Hotel("h1", testerArray);
//		HotelReservation h1 = new HotelReservation("h1", hotel1, "double", 2);
//		HotelReservation h2 = new HotelReservation("h1", hotel1, "double", 2);
//		HotelReservation h3 = new HotelReservation("h1", hotel1, "king", 2);
//		System.out.println(h1.getCost());
//		
//		// equals() Tester 
//		System.out.println(h1.equals(h2)); // true
//		System.out.println(h1.equals(h3)); // false
//		
//		// BnBReservation Tester  ////////////////////////////////////////////////////////////////////////////
//		// getCost Tester 
//		BnBReservation bb = new BnBReservation("bednbreakfast", hotel1, "double", 2);
//		System.out.println(bb.getCost()); // 20000
		
		// Basket Tester
		// add() Tester 
//		Basket b = new Basket();
//		Airport a1 = new Airport(7, 80000, 30000);
//	    Airport a2 = new Airport(8, 50000, 10000);
//	    FlightReservation r1 = new FlightReservation("myReservation", a1, a2);
//	    FlightReservation r2 = new FlightReservation("secondReservation", a2, a1);
//	    System.out.println(b.add(r1)); // size 1
//	    System.out.println(b.add(r2)); // size 2
	    
	    //getProducts() Tester
//	    b.getProducts();
//	    System.out.println(r2.equals(Basket.reservations[1])); // true (must set field in Basket to public static)
//	    System.out.println(r1.equals(Basket.reservations[0])); // true (must set field to public static)

	    // remove() Tester 
//	    Basket b1 = new Basket();
//	    Airport o1 = new Airport(7, 80000, 30000);
//	    Airport o2 = new Airport(8, 50000, 10000);
//	    Airport o3 = new Airport(1400, 70000, 10000);
//	    FlightReservation u1 = new FlightReservation("myReservation", o1, o2);
//	    FlightReservation u2 = new FlightReservation("secondReservation", o1, o3);
//	    FlightReservation u3 = new FlightReservation("thirdReservation", o2, o3);
//	    b1.add(u1);
//	    b1.add(u2);
//	    b1.add(u3);
//	    boolean res = b1.remove(u2);
//		System.out.println(res); // true
//		System.out.println(b1.getProducts().length); // 2
//		System.out.println("correct order:" + b1.getProducts()[0].equals(u1)); // true (correct order)
//		System.out.println("correct order:" + b1.getProducts()[1].equals(u3)); // true (correct order)
//	    boolean res2 = b1.remove(u3);
//		System.out.println(res2); // true
//		System.out.println(b1.getProducts().length); // 1
//	    boolean res3 = b1.remove(u1);
//		System.out.println(res2); // true
//		System.out.println(b1.getProducts().length); // 0
		
		// getNumOfReservations() Tester
//		System.out.println(b1.getNumOfReservations()); 
		
		// getTotalCost() Tester
//		System.out.println(b1.getTotalCost()); // 160648 cents 
		
		// Customer Tester ///////////////////////////////////////////////////////////////////////////////////////
		// addToBasket() Tester

//		Customer c1 = new Customer("John", 200000);
//		c1.customerReservations.clear();
//		System.out.println(c1.getBasket().getNumOfReservations()); // 0 reservations for now
//		Airport a1 = new Airport(7, 80000, 30000);
//		Airport a2 = new Airport(8, 50000, 10000);
//		Airport a2 = new Airport(7, 80000, 30000);
//		FlightReservation r1 = new FlightReservation("John", a1, a2);
//		
//		c1.addToBasket(r1);
//		
//		Room room1 = new Room("double");
//		Room room2 = new Room("queen");
//		Room room3 = new Room("king");
//		Room[] rooms = {room1, room2, room3};
//		Hotel h1 = new Hotel("myHotel", rooms);
//		HotelReservation r2 = new HotelReservation("John", h1, "double", 3);
//		
//		c1.addToBasket(r2);
//		System.out.println(c1.removeFromBasket(r2)); // true 
//		System.out.println(c1.customerReservations.getNumOfReservations()); // was 2 now 1
//		
//		System.out.println(c1.checkOut());

		
	}

}
