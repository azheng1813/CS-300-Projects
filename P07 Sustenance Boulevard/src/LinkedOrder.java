import java.lang.IllegalArgumentException;

public class LinkedOrder {
	private final Order ORDER;      // data field of this LinkedOrder
	private LinkedOrder previous;   // reference to the Order before this one
	private LinkedOrder next;       // reference to the Order after this one
	
	/*
	 * Constructor that only takes in the order
	 * Previous and next orders are null
	 * 
	 * @param order: the order that will be placed into the list of orders
	 */
	public LinkedOrder (Order order) {
		previous = null;
		next = null;
		this.ORDER = order;
		Order testOrder = new Order("dish", 0);
		if(order == null || ORDER.compareTo(testOrder) < 0 ) {
			throw new IllegalArgumentException("The order has a negative timestamp");
		}
	}
	
	/*
	 * Constructor that takes the order, previous order, and next order
	 * 
	 * @param order: the new order that will be placed in the list
	 * @param prev: the order prior to the new order
	 * @param next: the order after the new order
	 */
	public LinkedOrder (Order order, LinkedOrder prev, LinkedOrder next) {
		this.previous = prev;
		this.next = next;
		this.ORDER = order;
		Order testOrder = new Order("dish", 0);
		if(ORDER.compareTo(testOrder) < 0) {
			throw new IllegalArgumentException("The order has a negative timestamp");
		}
	}
	
	/*
	 * Returns the order of the LinkedOrder
	 * 
	 * @return the order of the LinekdOrder
	 */
	public Order getOrder() {
		return this.ORDER;
	}
	
	/*
	 * Returns the LinkedOrder prior to the current LinkedOrder
	 * 
	 * @return the LinkedOrder prior to the current LinkedOrder
	 */
	public LinkedOrder getPrevious() {
		return previous;
	}
	
	/*
	 * Returns the LinkedOrder after the current LinkedOrder
	 * 
	 * @return the LinkedOrder after the current LinkedOrder
	 */
	public LinkedOrder getNext() {
		return next;
	}
	
	/*
	 * Sets the previous LinkedOrder to the given LinkedOrder
	 * 
	 * @param previous: the LinkedOrder that will be set to be before the current LinkedOrder
	 */
	public void setPrevious(LinkedOrder previous) {
		this.previous = previous;
	}
	
	/*
	 * Sets the next LinkedOrder to the given LinkedOrder
	 * 
	 * @param next: the LinkedOrder that will be set to be after the current LinkedOrder
	 */
	public void setNext(LinkedOrder next) {
		this.next = next;
	}
}
