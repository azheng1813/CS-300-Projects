import java.lang.Iterable;
import java.util.Iterator;
import java.util.EmptyStackException;

public class MessageStack implements StackADT<Message>, Iterable<Message>{

	private LinkedNode<Message> top; //refers to the top of the linked stack
	private int size; //total number of Message objects in the stack
	
	/*
	 * Adds a new element to the top of the stack
	 * 
	 * @param element: the Message that is to be added to the top of the stack
	 */
	@Override
	public void push(Message element) {
		
		if(size == 0) {
			LinkedNode<Message> newElement = new LinkedNode<Message>(element);
			top = newElement;
		} else {
			LinkedNode<Message> newElement = new LinkedNode<Message>(element);
			LinkedNode<Message> temp = top;
			top = newElement;
			top.setNext(temp);
		}
		
		
		/*LinkedNode<Message> newElement = new LinkedNode<Message>(element);
		top = newElement;*/
		
		size++;
	}

	/*
	 * Removes and returns the element at the top of the stack
	 * 
	 * @return the Message at the top of the stack
	 */
	@Override
	public Message pop() {
		if(size == 0 || top == null) {
			throw new EmptyStackException();
		} else {
		
			LinkedNode<Message> poppedEle = top;
			top = top.getNext();
			size--;
		
			return poppedEle.getData();
		}
	}

	/*
	 * Returns the message at the top of the stack without removing that message
	 * 
	 * @return the message at the top of the stack without removing the message
	 */
	@Override
	public Message peek() {
		if(size == 0 || top == null) {
			throw new EmptyStackException();
		} else {
			return top.getData();	
		}
	}

	/*
	 * Checks if the messageStack is empty, returns true if it is
	 * 
	 * @return true if the stack is empty of Messages
	 */
	@Override
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		return false;
	}

	/*
	 * Returns the size of the stack
	 * 
	 * @return the size of the stack
	 */
	@Override
	public int size() {
		return size;
	}

	/*
	 * 
	 */
	@Override
	public MessageStackIterator iterator() {
		MessageStackIterator newIterator = new MessageStackIterator(top);
		
		return newIterator;
	}

}
