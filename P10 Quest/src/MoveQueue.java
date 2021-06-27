import java.util.NoSuchElementException;

public class MoveQueue implements PriorityQueueADT<BattleCharacter>{

	private BattleCharacter data[];
	private int size;
	
	/*
	 * Constructor that creates an empty priority queue of size capacity
	 */
	public MoveQueue(int capacity) {
		if(capacity <= 0) {
			throw new IllegalArgumentException();
		}
		data = new BattleCharacter[capacity];
	}
	
	/*
	 * Constructor that creates an empty priority queue of size 10
	 */
	public MoveQueue() {
		data = new BattleCharacter[10];
	}
	
	/*
	 * Corrects the heap-array when a character is killed
	 */
	public void updateCharacter(BattleCharacter updateChara){
		int i = 0;
		while(i < size && !data[i].equals(updateChara) ) {
			i++;
		}
		
		int gapIndex = i; // gapIndex is the index of the dead character
		if(updateChara.getHP() > 0) {
			data[gapIndex] = updateChara;
		}
		else {
			data[gapIndex] = data[size-1];
			data[size-1] = null;
			size--;
		}
		heapify();
	}

	
	/**
	 * Recursively propagates max-heap order violations up.
	 * Checks to see if the current node i violates the max-heap order property by checking its
	 * parent. If it does, swap them and continue to ensure the heap condition is satisfied.
	 * @param i index of the current node in this heap
	 */
	protected void percolateUp(int i) { //doesn't make sense
		
		double node = (i - 1) / 2;
		int parent = (int)node;
		if(i > 0) {
			if(data[i].compareTo(data[parent]) > 0) {
				BattleCharacter temp = data[parent];
				data[parent] = data[i];
				data[i] = temp;
				percolateUp(parent);
			}
		}
		
	}
	
	/**
	 * Recursively propagates max-heap order violations down.
	 * Checks to see if the current node i violates the max-heap order
	 * property by checking its children. If it does, swap it with the optimal
	 * child and continue to ensure the heap condition is met.
	 * @param i index of the current node in this heap
	 */
	protected void percolateDown(int parent) {
		int child = (2 * parent) + 1;
		if(child < size) {
			BattleCharacter maxValue = data[parent];
			for(int i = 0; i < 2 && i + child < size; i++) {
				if(data[i + child].compareTo(maxValue) > 0) {
					maxValue = data[i + child];
					child = child + i;
				}
			}
			if(maxValue.compareTo(data[parent]) > 0) {
				BattleCharacter temp = maxValue;
				data[child] = data[parent];
				data[parent] = temp;
				percolateDown(child);
			}
		}
	}
	
	/**
	 * Eliminates all heap order violations from the heap data array
	 */
	protected void heapify() {
		for(int i = size - 1; i >= 0; i--) {
			percolateUp(i);
		}
		for(int i = 0; i < size; i++) {
			percolateDown(i);
		}
	}
	
	/**
	 * Returns a String representation of the current contents of the MoveQueue
	 * in order from first to last.
	 * @author Michelle
	 */
	@Override
	public String toString() {
		String s = ("[ ");
		for(int i = 0; i < size; i++) {
			s += (data[i].toString() + " | ");
		}
		s += ("]");
		return s;
	}

	/*
	 * Checks if the heap-array is empty
	 * 
	 * @return true if the heap-array is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return this.size;
	}
	
	/*
	 * Returns the first item in the array-heap but doesn't discard it
	 *
	 * @return the first item in the array-heap but doesn't discard it
	 */
	@Override
	public BattleCharacter peekBest() {
		return data[0];
	}
	
	/*
	 * Clears the array-heap
	 */
	@Override
	public void clear() {
		for(int i = 0; i < size; i++) {
			data[i] = null;
		}
		this.size = 0;
	}

	/*
	 * Adds a new element to the end of the heap-array and then percolates it up 
	 */
	@Override
	public void enqueue(BattleCharacter element) {
		if(data.length == size) {
			throw new IllegalStateException();
		}
		if(element == null) {
			throw new IllegalArgumentException();
		}
		int i = size;
		size++;
		data[i] = element;
		percolateUp(i);
	}

	/*
	 * Returns the root of the heap-array and replaces the root with the last element of the heap-array, which percolates down 
	 * 
	 * @return the root of the heap-array
	 */
	@Override
	public BattleCharacter dequeue() {
		if(size == 0) {
			throw new NoSuchElementException();
		}
		BattleCharacter root = data[0];
		data[0] = data[size - 1];
		data[size - 1] = null;
		size--;
		percolateDown(0);
		return root;
	}

	
	
}
