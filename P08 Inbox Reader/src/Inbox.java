import java.lang.Iterable;
import java.util.Iterator;

public class Inbox {
	
	private MessageStack readMessageBox; //stack of read messages
	private MessageStack unreadMessageBox; //stack of unread messages
	
	/*
	 * Constructor of an inbox to house the read and unread messages. Initially sets both messageStacks to be empty
	 */
	public Inbox() { //creates new empty inbox and initializes instance fields
		readMessageBox = new MessageStack();
		unreadMessageBox = new MessageStack();
	}
	
	/*
	 * Returns the top message in the unreadMessageBox
	 * 
	 * @return the top message in the unreadMessageBox
	 */
	public String readMessage() {
		String message = "Nothing in unread";
		if(!unreadMessageBox.isEmpty()) {
			message = unreadMessageBox.peek().getTEXT();
			readMessageBox.push(unreadMessageBox.pop());
		}
		return message;
	}
	
	/*
	 * Returns the top message in the readMessageStack without removing it
	 * 
	 * @return the top message in the readMessageStack without removing it
	 */
	public String peekReadMessage() {
		String message = "Nothing in read";
		if(!readMessageBox.isEmpty()) {
			message = readMessageBox.peek().getTEXT();
		}
		return message;
	}
	
	/*
	 * Returns the number of unread messages that was marked as read
	 * 
	 * @return the number of unread messages that was marked as read
	 */
	public int markAllMessagesAsRead() {
		int messagesRead = 0;
		while(unreadMessageBox.size() > 0) {
			readMessageBox.push(unreadMessageBox.pop());
			messagesRead++;
		}
		//check
		if(!unreadMessageBox.isEmpty()) {
			while(!unreadMessageBox.isEmpty()) {
				readMessageBox.push(unreadMessageBox.pop());
				messagesRead++;
			}
		}
		return messagesRead;
	}
	
	/*
	 * Adds an unread Message to the unreadMessageStack 
	 */
	public void receiveMessage(Message newMessage) {
		unreadMessageBox.push(newMessage);
	}
	
	public int emptyReadMessageBox() {
		int messagesRemoved = 0;
		while(!readMessageBox.isEmpty()) {
			readMessageBox.pop();
			messagesRemoved++;
		}
		return messagesRemoved;
	}
	
	/*
	 * Returns the number of unread Messages and read Messages
	 * @return the number of unread Messages and read Messages
	 */
	public String getStatistics() {
		int size1 = unreadMessageBox.size();
		int size2 = readMessageBox.size();
		String stats = "Unread " + size1 + "\n" + "Read " + size2;
		return stats;
	}
	
	/*
	 * Returns the unread messages from the top of the stack to the bottom
	 * 
	 * @return the unread messages from the top of the stack to the bottom
	 */
	public String traverseUnreadMessages() {
		String list = "";
		//Iterator<Message> stackIterator = unreadMessageBox.iterator();
		
		//Message current = unreadMessageBox.peek();
		for(Message current : unreadMessageBox) {
			list = list + "\n" + current.getID() + " " + current.getSUBJECT();
			//stackIterator.next(); 
		}
		list = "Unread(" + unreadMessageBox.size() + ")\n" + list; 
		return list;
	}
	
	/* 	
	 * Returns the read messages from the top of the stack to the bottom
	 * 
	 * @return the read messages from the top of the stack to the bottom
	 */
	public String traverseReadMessages() {
		String list = "";
		//Iterator<Message> stackIterator = readMessageBox.iterator();
		
		for(Message current : readMessageBox) {
			list = list + "\n" + current.getID() + " " + current.getSUBJECT();
			//stackIterator.next(); 
		}
		list = "Read(" + readMessageBox.size() + ")\n" + list; 
		return list;
	}
	
	
}

