import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Iterator;

// File header comes here
/**
 * This class implements unit test methods to check the correctness of the implementation of the
 * MessageStack, Inbox, and MessageStackIterator classes defined in the CS300 Fall 2020 - P08 LIFO
 * Inbox Reader programming assignment.
 *
 */
public class InboxReaderTester {

	
	/**
	 * Calls your tester methods
	 * 
	 * @param args input arguments if any
	 */
	public static void main(String[] args) {
		
		System.out.println(runInboxReaderTestSuite());
	}
	
	// add the runInboxReaderTestSuite() method and your additional tester methods
	
	/**
	 * Checks for the correctness of the constructor of the MessageStack, MessageStack.push(),
	 * MessageStack.peek(), MessageStack.isEmpty(), and MessageStack.size() methods. This method must
	 * consider at least the test scenarios provided in the detailed description of these javadocs.
	 * (1) Create a new MessageStack object. The new created stack must be empty and its size must be
	 * zero. (2) You can consider calling peek method on the empty stack. An EmptyStackException is
	 * expected to be thrown by the peek method call. (3) Then, push a Message onto the stack and then
	 * use peek to verify that the correct item is at the top of the stack. Make sure also to check
	 * that isEmpty() must return false and the size of the stack is one. The peek() method call
	 * should not make any change to the contents of the stack. (4) You can further consider pushing
	 * other elements onto the stack. Then, each call of peek() method should return the correct
	 * Message object that should be at the top of the stack. After pushing a new message to the stack
	 * double check that the size of the stack was incremented by one.
	 * 
	 * @return true when this test verifies a correct functionality, and false otherwise.
	 */
	public static boolean testStackConstructorIsEmptyPushPeek() {
		MessageStack newStack = new MessageStack();
		int exceptionCount = 0;
		
		//scenario 1
		if(newStack.size() != 0) { 
			System.out.println("Scenario 1: size");
			return false;
		}
		if(!newStack.isEmpty()) {
			System.out.println("Scenario 1: isEmpty");
			return false;
		}
		
		//scenario 2
		try {
			newStack.peek();
		} catch(EmptyStackException ese) {
			exceptionCount++;
		}
		
		//scenario 3
		Message firstMess = new Message("First Topic", "First Message");
		newStack.push(firstMess);
		if(newStack.isEmpty()) {
			System.out.println("Scenario 3: isEmpty");
			return false;
		}
		if(newStack.size() != 1) {
			System.out.println("Scenario 3: size");
			return false;
		}
		if(newStack.peek() != firstMess) {
			System.out.println("Scenario 3: peek");
			return false;
		}
		
		//scenario 4
		Message secMess = new Message("Second Topic", "Second Message");
		newStack.push(secMess);
		if(newStack.isEmpty()) {
			System.out.println("Scenario 4; isEmpty");
			return false;
		}
		if(newStack.size() != 2) {
			System.out.println("Scenario 4: size");
			return false;
		}
		if(newStack.peek() != secMess) {
			System.out.println("Scenario 4: peek");
			return false;
		}
		
		if(exceptionCount != 1) {
			System.out.println("Scenario 2: peek");
			return false;
		}
		return true;
	} 
	
	
	/**
	 * Checks for the correctness of MessageStack.pop(). It calls MessageStack.push() and
	 * MessageStack.peek() methods. This method must consider at least the test scenarios provided in
	 * the detailed description of these javadocs. (1) Try to create a new empty MessageStack. Then,
	 * try calling pop method on the empty stack. An EmptyStackException is expected to be thrown as a
	 * result. (2) Try to push a message to the stack. Then, try to call the pop method on the stack
	 * which contains only one element. Make sure that the pop() operation returned the expected
	 * message, and that the stack is empty and its size is zero. (3) Then, try to push at least three
	 * messages, then call pop method on the stack which contains 3 elements, the element at the top
	 * of the stack must be returned and removed from the stack and its size must be decremented by
	 * one. You can further keep popping the elements of the stack one by one until it becomes empty
	 * and check each time that the pop operation performs appropriately.This test method must return
	 * false if it detects at least one defect.
	 * 
	 * @return true when this test verifies a correct functionality, and false otherwise.
	 */
	public static boolean testStackPop() {
		MessageStack testStack = new MessageStack();
		int exceptionCount = 0;
		
		//scenario 1
		try {
			testStack.pop();
		} catch (EmptyStackException est) {
			exceptionCount++;
		}
		if(exceptionCount != 1) {
			return false;
		}
		
		//scenario 2;
		Message Mess1 = new Message("Topic 1", "Message 1");
		testStack.push(Mess1);
		if(!testStack.pop().equals(Mess1)) {
			return false;
		}
		if(testStack.size() != 0 && testStack.isEmpty()) {
			return false;
		}
		
		//scenario 3
		Message Mess2 = new Message("Topic 2", "Message 2");
		Message Mess3 = new Message("Topic 3", "Message 3");
		testStack.push(Mess1);
		testStack.push(Mess2);
		testStack.push(Mess3);
		if(!testStack.pop().equals(Mess3)) {
			return false;
		}
		if(testStack.size() != 2) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Checks for the correctness of the Inbox.ReadMessage() method.(1) Calls readMessage() on an empty inbox, which will return "Nothing in unread".
	 * (2) Calls readMessage() on an inbox of 1, which should return "Message 1". Checks using getStatistics() if the message was received, and peekReadMessage()
	 * if the message was moved to the read message stack. (3) Calls readMessage() on an inbox of 2, with Mess2 and Mess3 added in that order, which should return 
	 * "Message 3". The checks using getStatistic() and peekReadMessge() were used again.
	 * 
	 * @return true when this test verifies a correct functionality, and false otherwise.
	 */
	public static boolean testInboxReadMessage() {		
		//scenario 1
		Inbox testInbox = new Inbox();
		
		if(!testInbox.readMessage().equals("Nothing in unread")) {
			System.out.println("Scenario 1: empty read");
			return false;
		}
		
		//scenario 2
		Message Mess1 = new Message("Topic 1", "Message 1");
		testInbox.receiveMessage(Mess1);
		if(!testInbox.getStatistics().equals("Unread 1\nRead 0")) {
			System.out.println("Scenario 2: stats");
			return false;
		}
		if(!testInbox.readMessage().equals("Message 1")) {
			System.out.println("Scenario 2: read");
			return false;
		}
		if(!testInbox.peekReadMessage().equals("Message 1")) {
			System.out.println("Scenario 2: peek");
			return false;
		}
		
		//scenario 3
		Message Mess2 = new Message("Topic 2", "Message 2");
		Message Mess3 = new Message("Topic 3", "Message 3");
		testInbox.receiveMessage(Mess2);
		testInbox.receiveMessage(Mess3);
		if(!testInbox.getStatistics().equals("Unread 2\nRead 1")) {
			System.out.println("Scenario 3: stats");
			return false;
		}
		if(!testInbox.readMessage().equals("Message 3")) {
			System.out.println("Scenario 3: read");
			return false;
		}
		if(!testInbox.peekReadMessage().equals("Message 3")) {
			System.out.println("Scenario 3: peek");
			return false;
		}
		return true;
	}
	
	
	/**
	 * Checks for the correctness of the Inbox.ReceiveMessage() method. Add 3 unread messages to the inbox and read one message.
	 * Use getStatistics() and peekReadMessage to check if the receiveMessage() method works correctly, as there should be 1 read message and 2 unread messages.
	 * The read message should have a text of "Message 3".
	 * 
	 * @return true when this test verifies a correct functionality, and false otherwise.
	 */
	public static boolean testInboxReceiveMessage() {
		Inbox testInbox = new Inbox();
		Message Mess1 = new Message("Topic 1", "Message 1");
		Message Mess2 = new Message("Topic 2", "Message 2");
		Message Mess3 = new Message("Topic 3", "Message 3");
		testInbox.receiveMessage(Mess1);
		testInbox.receiveMessage(Mess2);
		testInbox.receiveMessage(Mess3);
		testInbox.readMessage();
		if(!testInbox.getStatistics().equals("Unread 2\nRead 1")) {
			System.out.println("receiveMessage: stats");
			return false;
		}
		if(!testInbox.peekReadMessage().equals("Message 3")) {
			System.out.println("receiveMessage: read");
			return false;
		}
		return true;
	}
	  
	/**
	 * Checks for the correctness of the Inbox.markAllMessagesAsRead() method. Add 3 unread messages to the inbox and call the markAllMessagesRead() method.
	 * Use the getStatistics() method to check that all messages were sent to the read message stack, and use peekReadMessage() method to check that the top 
	 * message was Mess1, which was the first added to the unread message stack and therefore the last to be added to the read message stack
	 * 
	 * @return true when this test verifies a correct functionality, and false otherwise.
	 */
	public static boolean testInboxMarkAllMessagesAsRead() {
		Inbox testInbox = new Inbox();
		Message Mess1 = new Message("Topic 1", "Message 1");
		Message Mess2 = new Message("Topic 2", "Message 2");
		Message Mess3 = new Message("Topic 3", "Message 3");
		testInbox.receiveMessage(Mess1);
		testInbox.receiveMessage(Mess2);
		testInbox.receiveMessage(Mess3);
		testInbox.markAllMessagesAsRead(); //problem here
		if(!testInbox.getStatistics().equals("Unread 0\nRead 3")) {
			System.out.println("allRead: stats");
			return false;
		}
		if(!testInbox.peekReadMessage().equals("Message 1")) {
			System.out.println("allRead peek");
			return false;
		}
		return true;
	}
	/**
	 * Checks for the correctness of MessageStackIterator.hasNext() and MessageStackIterator.next()
	 * methods. You can rely on the constructor of the LinkedNode class which takes two input
	 * parameters (setting both data and next instance fields) to create a chain of linked nodes (at
	 * least 3 linked nodes) which carry messages as data fields. Then, create a new
	 * MessageStackIterator() and pass it the head of the chain of linked nodes that you created. We
	 * recommend that you consider at least the following test scenarios in this tester method. (1)
	 * Try to call next() on the iterator. The first call of next() must return the message at the
	 * head of your chain of linked nodes. (2) Try to call hasNext() on your iterator, it must return
	 * true. (3) The second call of next() must return the message which has been initially at index 1
	 * of your chain of linked nodes. (4) The third call of next() on your iterator must return the
	 * message initially at index 2 of your chain of linked nodes. (4) If you defined a chain of 3
	 * linked nodes in this scenario, hasNext() should return false, and the fourth call of next() on
	 * the iterator must throw a NoSuchElementException.
	 * 
	 * @return true when this test verifies a correct functionality, and false otherwise.
	 */
	public static boolean testMessageStackIterator() {
		
		Message Mess1 = new Message("Topic 1", "Message 1");
		Message Mess2 = new Message("Topic 2", "Message 2");
		Message Mess3 = new Message("Topic 3", "Message 3");
		LinkedNode<Message> third = new LinkedNode<Message>(Mess3);
		LinkedNode<Message> sec = new LinkedNode<Message>(Mess2, third);
		LinkedNode<Message> first = new LinkedNode<Message>(Mess1, sec);
		
		MessageStackIterator stackIterator = new MessageStackIterator(first);
		
		//scenario 1
		if(!stackIterator.next().equals(Mess1)) {
			return false;
		}
		
		//scneario 2
		if(!stackIterator.hasNext()) {
			return false;
		}
		
		//scenario 3
		if(!stackIterator.next().equals(Mess2)) {
			return false;
		}
		
		//scenario 4
		if(!stackIterator.next().equals(Mess3)) {
			return false;
		}
		
		//scenario 5
		if(stackIterator.hasNext()) {
			return false;
		}
		int exceptioncount = 0;
		try {
			stackIterator.next();
		} catch(NoSuchElementException nsee) {
			exceptioncount++;
		}
		if(exceptioncount != 1) {
			return false;
		}
		return true;
	}
	/*
	 * 
	 */
	public static boolean runInboxReaderTestSuite() {
		if(testStackConstructorIsEmptyPushPeek() && testStackPop() && testInboxReadMessage() && testInboxReceiveMessage() && testInboxMarkAllMessagesAsRead() && testMessageStackIterator()) {
			return true;
		}
		return false;
	}
}