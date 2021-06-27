
public class BattleSystemTester {
	
	/*
	 * Checks if the Enqueue method works correctly by queuing 3 PartyCharacters into a MoveQueue
	 * 
	 * @return true if the queue is correct, false if not
	 */
	public static boolean testEnqueueMoveQueue() {
		int[] stats1 = {4, 3, 5, 6, 4};
		int[] stats2 = {4, 6, 4, 5, 3};
		int[] stats3 = {4, 6, 6, 5, 2};
		PartyCharacter unit1 = new PartyCharacter("Chief", stats1);
		PartyCharacter unit2 = new PartyCharacter("Leon", stats2);
		PartyCharacter unit3 = new PartyCharacter("Chell", stats3);
		
		MoveQueue test = new MoveQueue(10);
		test.enqueue(unit3);
		test.enqueue(unit2);
		test.enqueue(unit1);
		if(test.toString().equals("[ Chief(1, 4) | Chell(3, 2) | Leon(2, 3) | ]")) {
			return true;
		}
		return false;
	}
	
	/*
	 * Checks the dequeue method by first queuing 3 PartyCharacters and then checking if 2 are in the queue after dequeuing
	 * 
	 * @return true if the queue is one less after dequeuing, false if not
	 */
	public static boolean testDequeueMoveQueue() {
		int[] stats1 = {4, 3, 5, 6, 4};
		int[] stats2 = {4, 6, 4, 5, 3};
		int[] stats3 = {4, 6, 6, 5, 2};
		PartyCharacter unit1 = new PartyCharacter("Chief", stats1);
		PartyCharacter unit2 = new PartyCharacter("Leon", stats2);
		PartyCharacter unit3 = new PartyCharacter("Chell", stats3);
		
		MoveQueue test = new MoveQueue(10);
		test.enqueue(unit2);
		test.enqueue(unit3);
		test.enqueue(unit1);
		test.dequeue();
		if(test.toString().equals("[ Leon(5, 3) | Chell(6, 2) | ]")) {
			return true;
		}
		return false;
	}
	
	/*
	 * Checks the UpdateCharacter method by killing an EnemyCharacter and checking if the queue does not have that BattleCharacter
	 * 
	 * @return true if the queue no longer has the EnemeyCharacter after killing it, false if not
	 */
	public static boolean testUpdateCharacter() {
		int[] stats1 = {4, 3, 5, 6, 4};
		int[] stats2 = {4, 6, 4, 5, 3};
		int[] stats3 = {4, 6, 6, 5, 2};
		PartyCharacter unit1 = new PartyCharacter("Chief", stats1);
		PartyCharacter unit2 = new PartyCharacter("Leon", stats2);
		PartyCharacter unit3 = new PartyCharacter("Chell", stats3);
		
		int[] stats4 = {2, 3, 2, 1, 3};
		int[] stats5 = {4, 4, 5, 0, 1};
		int[] stats6 = {1, 1, 2, 5, 4};
		EnemyCharacter unit4 = new EnemyCharacter("Grunt", stats4, "PASSIVE");
		EnemyCharacter unit5 = new EnemyCharacter("Brute", stats5, "PASSIVE");
		EnemyCharacter unit6 = new EnemyCharacter("Joker", stats6, "PASSIVE");
		
		MoveQueue test2 = new MoveQueue(10);
		test2.enqueue(unit2);
		test2.enqueue(unit3);
		test2.enqueue(unit1);
		test2.enqueue(unit4);
		test2.enqueue(unit5);
		test2.enqueue(unit6);
		unit6.takeDamage(2);
		test2.updateCharacter(unit6);
		if(test2.toString().equals("[ Chief(7, 4) | Grunt(10, 3) | Leon(8, 3) | Chell(9, 2) | Brute(11, 1) | ]")) {
			return true;
		}
		return false;
	}
	
	/*
	 * Checks the other miscellaneous methods by checking with 3 PartyCharacters
	 * Checks equals(), isEmpty(), size(), peekBest(), and clear()
	 * 
	 * @return if the number of correct comparisons is met, false if not
	 */
	public static boolean testMisc() {
		int[] stats1 = {4, 3, 5, 6, 4};
		int[] stats2 = {4, 6, 4, 5, 3};
		int[] stats3 = {4, 6, 6, 5, 2};
		PartyCharacter unit1 = new PartyCharacter("Chief", stats1);
		PartyCharacter unit2 = new PartyCharacter("Leon", stats2);
		PartyCharacter unit3 = new PartyCharacter("Chell", stats3);
		MoveQueue test = new MoveQueue();
		test.enqueue(unit1);
		test.enqueue(unit2);
		test.enqueue(unit3);
		int equalCount = 0;
		if(unit1.equals(unit1)){
			equalCount++;
		}
		if(!unit2.equals(unit3)) {
			equalCount++;
		}
		
		if(!test.isEmpty()) {
			equalCount++;
		}
		if(test.size() == 3) {
			equalCount++;
		}
		if(test.peekBest().equals(unit1)) {
			equalCount++;
		}
		test.clear();
		
		if(test.size() == 0) {
			equalCount++;
		}
		if(equalCount == 6) {
			return true;
		}
		
		return false;
	}
	
	public static void main(String args[]) {
		System.out.println(testEnqueueMoveQueue());
		System.out.println(testDequeueMoveQueue());
		System.out.println(testUpdateCharacter());
		System.out.println(testMisc());
	}
}
