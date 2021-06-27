//File header comes here
/**
* This class implements unit test methods to check the correctness of LinkedOrders and RestaurantOrders
* classes defined in the CS300 Fall 2020 - P07 Restaurant Orders programming assignment.
*
*/
public class RestaurantOrdersTester {

	/**
	* This method should test and make use of at least the LinkedOrders constructor, an accessor
	* (getter) method, and a mutator (setter) method defined in the LinkedOrders class.
	* 
	* @return true when this test verifies a correct functionality, and false otherwise
	*/
	public static boolean testLinkedOrders() {
		Order newOrder = new Order("Pizza", 1);
		Order orderTwo = new Order("Burger", 2);
		LinkedOrder test = new LinkedOrder(newOrder);
		LinkedOrder nextOrder = new LinkedOrder(orderTwo);
		if(!test.getOrder().equals(newOrder)) {
			return false;
		}
		test.setNext(nextOrder);
		if(!(test.getNext()).equals(nextOrder)){
			return false;
		}
		return true;
	}
	
	/**
	* This method checks for the correctness of both RestaurantOrders constructors and the instance
	* method isEmpty() when called on an empty restaurant orders object.
	* 
	* @return true when this test verifies a correct functionality, and false otherwise
	*/
	public static boolean testRestaurantOrdersConstructorIsEmpty() {
		RestaurantOrders restOne = new RestaurantOrders();
		RestaurantOrders restTwo = new RestaurantOrders(15);
		if(restOne.capacity() != 20) {
			return false;
		}
		if(restTwo.capacity() != 15) {
			return false;
		}
		if(!restOne.isEmpty()) {
			return false;
		}
		if(!restTwo.isEmpty()) {
			return false;
		}
	
		return true;
	}
	
	/**
	* This method checks for the correctness of the RestaurantOrders(int) constructor when passed a
	* negative int value for the capacity.
	* 
	* @return true when this test verifies a correct functionality, and false otherwise
	*/
	public static boolean testRestaurantOrdersConstructorBadInput() {
		try {
			RestaurantOrders badRest = new RestaurantOrders(-1);
		} catch(IllegalArgumentException iae) {
			return true;
		}
		return false;
	}
	
	
	/**
	* This method checks for the correctness of the RestaurantOrders.add() method when it is passed bad
	* inputs. This method must consider at least the test scenarios provided in the detailed
	* description of these javadocs. (1) Try adding a null to the list; (2) Try adding an order which
	* carries a negative timestamp. (3) Try adding an order with an existing timestamp to the list.
	* 
	* @return true when this test verifies a correct functionality, and false otherwise
	*/
	public static boolean testRestaurantOrdersAddBadInput() {
		Order negTime = new Order("Pizza", -1);
		Order sameTimeOne = new Order("Burger", 1);
		Order sameTimeTwo = new Order("Sandwich", 1);
		RestaurantOrders placedOrder = new RestaurantOrders();
		int numTrues = 0;
		
		try {
			placedOrder.placeOrder(null);
		}catch(IllegalArgumentException iae) {
			numTrues++;
		}
		
		try { //scenario 2
			placedOrder.placeOrder(negTime);
		}catch(IllegalArgumentException iae) {
			numTrues++;
		}
		
		try { //scenario 3
			placedOrder.placeOrder(sameTimeOne);
			placedOrder.placeOrder(sameTimeTwo);
		}catch(IllegalArgumentException iae) {
			numTrues++;
		}
		
		
		
		if(numTrues == 3) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	* This method checks for the correctness of the RestaurantOrders.add() considering at least the test
	* scenarios provided in the detailed description of these javadocs. (1) Try adding an order to an
	* empty list; (2) Try adding an order which is expected to be added at the head of a non-empty
	* restaurant list; (3) Try adding an order which is expected to be added at the end of a non-empty
	* restaurant list; (4) Try adding an order which is expected to be added at the middle of a non-empty
	* restaurant list. For each of those scenarios, make sure that the size of the list is
	* appropriately updated after a call without errors of the add() method, and that the contents of
	* the list is as expected whatever if list is read in the forward or backward directions. You can
	* also check the correctness of RestaurantOrders.get(int), RestaurantOrders.indexOf(Order), and
	* RestaurantOrders.size() in this test method.
	* 
	* @return true when this test verifies a correct functionality, and false otherwise
	*/
	public static boolean testRestaurantOrdersAdd() {
		Order ord1 = new Order("Pizza", 1);
		Order ord2 = new Order("Burger", 2);
		Order ord3 = new Order("Sandwich", 3);
		Order ord4 = new Order("Hotdog", 4);
		RestaurantOrders testOrder = new RestaurantOrders();
		
		//scenario 1
		testOrder.placeOrder(ord2);
		if(testOrder.capacity() != 1) {
			return false;
		}
		if(!testOrder.readForward().equals("The list contains 1 order(s): [ Burger ]")) {
			return false;
		}
		
		//scenario 2
		testOrder.placeOrder(ord1);
		if(testOrder.capacity() != 2) {
			return false;
		}
		if(!testOrder.readForward().equals("The list contains 2 order(s): [ Pizza Burger ]")) {
			return false;
		}
		
		//scenario 3
		testOrder.placeOrder(ord4);
		if(testOrder.capacity() != 3) {
			return false;
		}
		if(!testOrder.readForward().equals("The list contains 3 order(s): [ Pizza Burger Hotdog ]")) {
			return false;
		}
		
		//scenario 4
		testOrder.placeOrder(ord3);
		if(testOrder.capacity() != 4) {
			return false;
		}
		if(!testOrder.readForward().equals("The list contains 4 order(s): [Pizza Burger Sandwich Hotdog ]")) {
			return false;
		}
		
		return true;
	}
	
	/**
	* This method checks for the correctness of the RestaurantOrders.remove() considering at least the
	* test scenarios provided in the detailed description of these javadocs. (1) Try removing an order
	* from an empty list or pass a negative index to RestaurantOrders.remove() method; (2) Try removing an
	* order (at position index 0) from a list which contains only one order; (3) Try to remove an order
	* (position index 0) from a list which contains at least 2 orders; (4) Try to remove an order from
	* the middle of a non-empty list containing at least 3 orders; (5) Try to remove the order at the
	* end of a list containing at least two orders. For each of those scenarios, make sure that the 
	* size of the list is appropriately updated after a call without errors of the add() method, 
	* and that the contents of the list is as expected whatever if list is read in the forward or 
	* backward directions.
	* 
	* @return true when this test verifies a correct functionality, and false otherwise
	*/
	public static boolean testRestaurantOrdersRemove() {
		int numTrues = 0;
		Order ord1 = new Order("Pizza", 1);
		Order ord2 = new Order("Burger", 2);
		Order ord3 = new Order("Sandwich", 3);
		Order ord4 = new Order("Hotdog", 4);
		RestaurantOrders testOrder = new RestaurantOrders();
		try {
			testOrder.removeOrder(-1);
		}catch(IllegalArgumentException iae) {
			numTrues++;
		}
		
		testOrder.placeOrder(ord1);
		testOrder.removeOrder(0);
		if(testOrder.size() == 0) {
			numTrues++;
		}
		
		testOrder.placeOrder(ord1);
		testOrder.placeOrder(ord2);
		testOrder.removeOrder(0);
		if(testOrder.size() == 1) {
			numTrues++;
		}
		
		testOrder.placeOrder(ord1);
		testOrder.placeOrder(ord2);
		testOrder.removeOrder(1);
		if(testOrder.size() == 1) {
			numTrues++;
		}
		
		testOrder.placeOrder(ord1);
		testOrder.placeOrder(ord2);
		testOrder.placeOrder(ord3);
		testOrder.removeOrder(1);
		if(testOrder.size() == 2) {
			numTrues++;
		}
		
		testOrder.placeOrder(ord1);
		testOrder.placeOrder(ord2);
		testOrder.placeOrder(ord3);
		testOrder.removeOrder(2);
		if(testOrder.size() == 2) {
			numTrues++;
		}
		
		if(numTrues == 5) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	/**
	* This method calls all the test methods defined and implemented in your RestaurantOrdersTester
	* class.
	* 
	* @return true if all the test methods defined in this class pass, and false otherwise.
	*/
	public static boolean runAllTests() {
		if(testLinkedOrders() && testRestaurantOrdersConstructorIsEmpty() && testRestaurantOrdersConstructorBadInput() && testRestaurantOrdersAddBadInput() && testRestaurantOrdersAdd() && testRestaurantOrdersRemove()) {
			return true;
		}
		return false;
	}
	
	/**
	* Driver method defined in this RestaurantOrdersTester class
	* 
	* @param args input arguments if any.
	*/
	public static void main(String[] args) {
		System.out.println(runAllTests());
		RestaurantOrders orders = new RestaurantOrders(5);
		System.out.println(orders.readForward());
		Order order1 = new Order("Burger", 1);
		orders.placeOrder(order1);
		System.out.println(orders.readForward());
		Order order2 = new Order("Sandwich", 3);
		orders.placeOrder(order2);
		System.out.println(orders.readForward());
		Order order3 = new Order("Pizza", 2);
		orders.placeOrder(order3);
		System.out.println(orders.readForward());
		System.out.println(orders.readBackward()); // Note: this should be in reverse!
		orders.removeOrder(0);
		System.out.println(orders.readForward());
		Order order4 = new Order("Falafel", 4);
		orders.placeOrder(order4);
		Order order5 = new Order("Noodles", 5);
		orders.placeOrder(order5);
		System.out.println(orders.readForward());
		Order order6 = new Order("Pasta", 3);
		orders.placeOrder(order6); // Timestamp 3 already exists; handle exception!!
		System.out.println(orders.readForward());
		orders.removeOrder(1);
		System.out.println(orders.readForward());
		orders.placeOrder(order6); // Conflicting timestamp removed; should place order
		System.out.println(orders.readForward());
		Order order7 = new Order("Waffles", 7);
		orders.placeOrder(order7);
		System.out.println(orders.readForward());
		Order order8 = new Order("Pancakes", 8);
		orders.placeOrder(order8); // Maximum capacity reached; will not place order
		System.out.println(orders.readForward());
		orders.removeOrder(0);
		System.out.println(orders.readForward());
		orders.placeOrder(order8); // Enough capacity; should place order
		System.out.println(orders.readForward());
	}
}