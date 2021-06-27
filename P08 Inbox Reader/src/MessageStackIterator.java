import java.util.Iterator;
import java.util.NoSuchElementException;

public class MessageStackIterator implements Iterator<Message>{
	public LinkedNode<Message> nextElement;
	
	public MessageStackIterator(LinkedNode<Message> message) {
		this.nextElement = message;
		
	}
	
	/*
	 * Checks if there is a message after the current message, returns true if there is another message
	 * 
	 * @return true if there is another message after the current one
	 */
	@Override
	public boolean hasNext() {
		return nextElement != null;
	}

	/*
	 * 
	 * 
	 * @return the next message
	 */
	@Override
	public Message next() {
		if(nextElement == null) {
			throw new NoSuchElementException("The stack is exhausted");
		}
		Message temp = nextElement.getData();
		nextElement = nextElement.getNext();
		return temp;
	}
}
