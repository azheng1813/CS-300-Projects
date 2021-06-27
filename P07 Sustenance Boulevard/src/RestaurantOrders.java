import java.lang.IllegalArgumentException;

public class RestaurantOrders implements SortedListADT<Order>{
	private LinkedOrder head;    // front of the doubly-linked list
	private LinkedOrder tail;    // end of the doubly-linked list
	private int size;            // number of orders currently in the list
	private final int CAPACITY;  // maximum number of orders for this list
	
	/*
	 * Constructor that takes no parameters
	 * Sets the maximum capacity to 20
	 */
	public RestaurantOrders() {
		this.CAPACITY = 20;
		this.size = 0;;
		this.head = null;
		this.tail = null;
		//this.head.setNext(tail);
		//this.head.setPrevious(null);
		//this.tail.setPrevious(head);
		//this.tail.setNext(null);
		
	}
	
	/*
	 * Constructor that takes 1 parameter
	 * Sets the maximum capacity to the provided capacity
	 * 
	 * @param capacity: Represents the maximum capacity of the Restaurant Order
	 */
	public RestaurantOrders(int capacity) {
		this.CAPACITY = capacity;
		this.size = 0;
		if(capacity == 0 || capacity < 0) {
			throw new IllegalArgumentException("The capacity of the order is either 0 or negative, which is not allowed");
		}
	}
	
	/*
	 * Returns the capacity of the restaurant order as an integer
	 * 
	 * @return the capacity of the restaurant order as an integer
	 */
	public int capacity() {
		return CAPACITY;
	}
	
	
	/*
	 * Returns the orders in the restaurant order as a string in the order of time stamps
	 * 
	 * @return the orders in the restaurant order as a string in the order of time stamps
	 */
	public String readForward() { //need to figure out how to get orders
		String dishes = "The list contains " + this.size + " order(s)";
		LinkedOrder current = this.head;

		if(size > 0) {
			dishes = dishes + ": [ ";
			int currentInd = 0;
			while(currentInd < this.size) {
				dishes = dishes + (current.getOrder()).getDishes() + " ";
				if(!current.equals(null) || !(current.getNext()).equals(null)) {
					current = current.getNext();
				}
			}
			dishes = dishes + "]";
			return dishes;
		}
		else {
			return dishes;
		}
	}
	
	/*
	 * Returns the orders of the restaurant order as a string in the reverse order of time stamps
	 * 
	 * @return the orders of the restaurant order as a string in the reverse order of time stamps
	 */
	public String readBackward() { //need to figure out how to get orders in reverse order
		String dishes = "The list contains " + this.size + " order(s)";
		LinkedOrder current = this.tail;
		if(size > 0) {
			dishes = dishes + ": [ ";
			int currentInd = size - 1;
			while(currentInd > 0) {
				dishes = dishes + current.getOrder().getDishes() + " ";
				if(!current.getNext().equals(null)) {
					current = current.getNext();
				}
			}
			
			dishes = dishes + "]";
			return dishes;
		}
		else {
			return dishes;
		}
	}
	
	//SortedListADT interface methods
	
	/*
	 * Clears the list of restaurant orders
	 */
	@Override
	public void clear() {
		this.head = null;
		this.tail = null;
		this.size = 0;
		
	}
	
	/*
	 * Returns the order at the provided index of the list 
	 * 
	 * @param index: index of the order that is returned
	 * @return the order at the provided index of the list
	 */
	@Override
	public Order get(int index) {
		if(index >= this.size) {
			return null;
		}
		int indexCounter = 0;
		LinkedOrder current = this.head;
		while (indexCounter < index) {
			indexCounter++;
			current = current.getNext();
		}
		return current.getOrder();
	}
	
	/*
	 * Returns the index of the provided order
	 * 
	 * @param findOrder: order whose index is to be found
	 * @return the index of the provided order
	 */
	@Override
	public int indexOf(Order findOrder) {
		int index = 0;
		LinkedOrder current = this.head;
		while(current.getOrder().equals(findOrder)) {
			index++;
			current = current.getNext();
		}
		return index;
	}
	
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		return false;
	}
	
	/*
	 * Adds an order to the list of Restaurant orders
	 * Will throw exceptions if the order is null, has a negative time stamp, or has the same time stamp as another order
	 * 
	 * @param newOrder: new order to be added to the list of restaurant orders
	 */
	@Override
	public void placeOrder(Order newOrder) {
		LinkedOrder placedOrder = new LinkedOrder(newOrder);
		if(newOrder == null) {
			throw new IllegalArgumentException("Order was null");
		}
		
		Order testOrder = new Order("Dish", 0);
		if(newOrder.compareTo(testOrder) < 0) {
			throw new IllegalArgumentException("Order has a negative timestamp");
		}
		
		for(int i = 0; i < size; i++) {
			LinkedOrder current = this.head;
			if(current.getOrder().compareTo(newOrder) == 0) {
				throw new IllegalArgumentException("The timestampe is the same as another order");
			}
			current = current.getNext();
		}
		
		if(size == 0 && size < CAPACITY) {
			head = placedOrder;
			tail = placedOrder;
			this.size++;
		}
		else if(size == 1 && size < CAPACITY) {
			LinkedOrder current = this.head;
			if(current.getOrder().compareTo(newOrder) > 0) {
				placedOrder.setNext(current);
				current.setPrevious(placedOrder);
				head = placedOrder;
				tail = current;
			}
			if(current.getOrder().compareTo(newOrder) < 0) {
				current.setNext(placedOrder);
				placedOrder.setPrevious(current);
				head = current;
				tail = placedOrder;
			}
			this.size++;
		}
		else if(size < CAPACITY) {
			LinkedOrder current = this.head;
			while(current.getOrder().compareTo(newOrder) < 0) {
				if(!current.getNext().equals(null)) {
					current = current.getNext();
				}
				
			}
			current = current.getPrevious();
			LinkedOrder temp = current.getNext();
			current.setNext(placedOrder);
			placedOrder.setNext(temp);
			placedOrder.setPrevious(current);
			temp.setPrevious(placedOrder);
			this.size++;
		}
		
		
	}
	
	/*
	 * Removes the order from the list of orders at the given index
	 * 
	 * @param index: index of the order to be removed from the list
	 * @return the order that was removed from the list
	 */
	@Override
	public Order removeOrder(int index) {
		LinkedOrder current = this.head;
		int indexCounter = 0;
		
		if(size == 0) {
			throw new IndexOutOfBoundsException("There are no orders");
		}
		if(index < 0) {
			throw new IndexOutOfBoundsException("Index is negative");
		}
		if (index + 1 >= size) {
			throw new IndexOutOfBoundsException("Index is not found");
		}
		
		if(size == 1 && index == 0) {
			head = null;
			tail = null;
		}
		else if(index == 0) {
			head = current.getNext();
			current.getNext().setPrevious(null);
		}
		else if(index == size - 1) {
			indexCounter = 0;
			while(indexCounter < index) {
				indexCounter++;
				current = current.getNext();
			}
			tail = current.getPrevious();
			current.getPrevious().setNext(null);
			
		}
		else {
			indexCounter = 0;
			while(indexCounter < index) {
				indexCounter++;
				current = current.getNext();
			}
			current.getPrevious().setNext(current.getNext());
			current.getNext().setPrevious(current.getPrevious());
		}
		this.size--;
		return current.getOrder();
	}
	
	/*
	 * Returns the size of the list
	 * 
	 * @return the size of the list
	 */
	public int size() {
		return this.size;
	}
}
