import java.util.NoSuchElementException;

// File Header comes here
/**
 * This class checks the correctness of the implementation of the methods
 * defined in the class PokemonTree.
 *
 */

public class PokemonTreeTester {

	/**
	 * Checks the correctness of the implementation of both addPokemon() and
	 * toString() methods implemented in the PokemonTree class. This unit test
	 * considers at least the following scenarios. (1) Create a new empty
	 * PokemonTree, and check that its size is 0, it is empty, and that its string
	 * representation is an empty string "". (2) try adding one Pokemon and then
	 * check that the addPokemon() method call returns true, the tree is not empty,
	 * its size is 1, and the .toString() called on the tree returns the expected
	 * output. (3) Try adding another Pokemon which is more powerful than the one at
	 * the root, (4) Try adding a third Pokemon which is less powerful than the one
	 * at the root, (5) Try adding at least two further Pokemons such that one must
	 * be added at the left subtree, and the other at the right subtree. For all the
	 * above scenarios, and more, double check each time that size() method returns
	 * the expected value, the add method call returns true, and that the
	 * .toString() method returns the expected string representation of the contents
	 * of the binary search tree in an ascendant order from the most powerful
	 * Pokemon to the least powerful one. (6) Try adding a Pokemon whose CP value
	 * was used as a key for a Pokemon already stored in the tree. Make sure that
	 * the addPokemon() method call returned false, and that the size of the tree
	 * did not change.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testAddPokemonToStringSize() {
		//scenario 1
		PokemonTree testTree = new PokemonTree();
		if(testTree.size() != 0) {
			System.out.println("scenario 1: size");
			return false;
		}
		if(!testTree.isEmpty()) {
			System.out.println("scenario 1: empty");
			return false;
		}
		if(!testTree.toString().equals("")) {
			System.out.println("scenario 1: toString");
		}
		
		//scenario 2
		Pokemon poke1 = new Pokemon("Pikachu", "3,4,5");
		if(!testTree.addPokemon(poke1)) {
			System.out.println("scenario 2: add");
			return false;
		}
		if(testTree.size() != 1) {
			System.out.println("scenario 2: size");
			return false;
		}
		if(testTree.isEmpty()) {
			System.out.println("scenrio 2: empty");
			return false;
		}
		if(!testTree.toString().equals("[Pikachu CP:345 (A:3 S:4 D:5)]")) {
			System.out.println("scenario 2: toString");
			return false;
		}
		
		//scenario 3
		Pokemon poke2 = new Pokemon("Charizard", "6,7,8");
		if(!testTree.addPokemon(poke2)) {
			System.out.println("scenario 3: add");
			return false;
		}
		if(!testTree.toString().equals("[Pikachu CP:345 (A:3 S:4 D:5)]\n[Charizard CP:678 (A:6 S:7 D:8)]")) {
			System.out.println("scenario 3: toString");
			return false;
		}
		if(testTree.size() != 2) {
			System.out.println("scenario 3: size");
			return false;
		}
		
		//scenario 4
		Pokemon poke3 = new Pokemon("Venasaur", "1,2,3");
		if(!testTree.addPokemon(poke3)) {
			System.out.println("scenario 4: add");
			return false;
		}
		if(!testTree.toString().equals("[Venasaur CP:123 (A:1 S:2 D:3)]\n[Pikachu CP:345 (A:3 S:4 D:5)]\n[Charizard CP:678 (A:6 S:7 D:8)]")) {
			System.out.println("scenario 4: toString");
			return false;
		}
		if(testTree.size() != 3) {
			System.out.println("scenario 4: size");
			return false;
		}
		
		//scenario 5
		Pokemon poke4 = new Pokemon("Blastoise", "7,8,9");
		Pokemon poke5 = new Pokemon("Caterpie", "1,1,1");
		if(!testTree.addPokemon(poke4)) {
			System.out.println("scenario 5: add1");
			return false;
		}
		if(!testTree.addPokemon(poke5)) {
			System.out.println("scenario 5: add2");
			return false;
		}
		if(!testTree.toString().equals("[Caterpie CP:111 (A:1 S:1 D:1)]\n[Venasaur CP:123 (A:1 S:2 D:3)]\n[Pikachu CP:345 (A:3 S:4 D:5)]\n[Charizard CP:678 (A:6 S:7 D:8)]\n[Blastoise CP:789 (A:7 S:8 D:9)]")) {
			System.out.println("scenario 5: toString");
			return false;
		}
		if(testTree.size() != 5) {
			System.out.println("scenario 5: size");
			return false;
		}
		
		//scenario 6
		Pokemon poke6 = new Pokemon("Magikarp", "1,1,1");
		if(testTree.addPokemon(poke6)) {
			System.out.println("scenario 6: add");
			return false;
		}
		if(testTree.size() != 5) {
			System.out.println("scenario 6: size");
			return false;
		}
		
		return true;
	}

	/**
	 * This method checks mainly for the correctness of the PokemonTree.lookup()
	 * method. It must consider at least the following test scenarios. (1) Create a
	 * new PokemonTree. Then, check that calling the lookup() method with any valid
	 * CP value must throw a NoSuchElementException. (2) Consider a PokemonTree of
	 * height 3 which consists of at least 5 PokemonNodes. Then, try to call
	 * lookup() method to search for the Pokemon at the root of the tree, then a
	 * Pokemon at the right and left subtrees at different levels. Make sure that
	 * the lookup() method returns the expected output for every method call. (3)
	 * Consider calling .lookup() method on a non-empty PokemonTree with a CP value 
	 * not stored in the tree, and ensure that the method call throws a
	 * NoSuchElementException.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testAddPokemonAndLookup() {
		//scenario 1
		PokemonTree testTree = new PokemonTree();
		int numException = 0;
		try {
			testTree.lookup(123);
		} catch(NoSuchElementException nsee) {
			numException++;
		}
		if(numException != 1) {
			System.out.println("scenario 1: lookup");
			return false;
		}
		
		//scenario 2
		Pokemon poke1 = new Pokemon("Pikachu", "3,4,5");
		Pokemon poke2 = new Pokemon("Charizard", "6,7,8");
		Pokemon poke3 = new Pokemon("Venasaur", "1,2,3");
		Pokemon poke4 = new Pokemon("Blastoise", "7,8,9");
		Pokemon poke5 = new Pokemon("Caterpie", "1,1,1");
		testTree.addPokemon(poke1);
		testTree.addPokemon(poke2);
		testTree.addPokemon(poke3);
		testTree.addPokemon(poke4);
		testTree.addPokemon(poke5);
		if(!testTree.lookup(345).equals(poke1)) {
			System.out.println("scenario 2: lookup1");
			return false;
		}
		if(!testTree.lookup(111).equals(poke5)) {
			System.out.println("scenario 2: lookup2");
			return false;
		}
		if(!testTree.lookup(789).equals(poke4)) {
			System.out.println("scenario 2: lookup3");
			return false;
		}
		
		//scenario 3
		try {
			testTree.lookup(101);
		} catch(NoSuchElementException nsee) {
			numException++;
		}
		if(numException != 2) {
			System.out.println("scenario 3: lookup");
			return false;
		}
		return true;
	}

	/**
	 * Checks for the correctness of PokemonTree.height() method. This test must
	 * consider several scenarios such as, (1) ensures that the height of an empty
	 * Pokemon tree is zero. (2) ensures that the height of a tree which consists of
	 * only one node is 1. (3) ensures that the height of a PokemonTree with the
	 * following structure for instance, is 4. 
	 *      (*) 
	 *     /   \ 
	 *   (*)   (*) 
	 *     \   / \ 
	 *    (*)(*) (*) 
	 *           /
	 *         (*)
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testHeight() {
		//scenario 1
		PokemonTree testTree = new PokemonTree();
		if(testTree.height() != 0) {
			System.out.println("scenario 1: height");
			return false;
		}
		
		//scenario 2
		Pokemon poke1 = new Pokemon("Pikachu", "3,4,5");
		testTree.addPokemon(poke1);
		if(testTree.height() != 1) {
			System.out.println("scenario 2: height");
			return false;
		}
		
		//scenario 3
		Pokemon poke2 = new Pokemon("Charizard", "6,7,8");
		Pokemon poke3 = new Pokemon("Venasaur", "1,2,3");
		Pokemon poke4 = new Pokemon("Blastoise", "7,8,9");
		Pokemon poke5 = new Pokemon("Caterpie", "1,3,1");
		Pokemon poke6 = new Pokemon("Vulpix", "6,5,6");
		Pokemon poke7 = new Pokemon("Jigglypuff", "7,7,7");
		testTree.addPokemon(poke2);
		testTree.addPokemon(poke3);
		testTree.addPokemon(poke4);
		testTree.addPokemon(poke5);
		testTree.addPokemon(poke6);
		testTree.addPokemon(poke7);
		if(testTree.height() != 4) {
			System.out.println("scenario 3: height");
			return false;
		}
		return true;
	}

	/**
	 * Checks for the correctness of PokemonTree.getLeastPowerfulPokemon() method.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testGetLeastPowerfulPokemon() {
		PokemonTree testTree = new PokemonTree();
		Pokemon poke1 = new Pokemon("Pikachu", "3,4,5");
		Pokemon poke2 = new Pokemon("Charizard", "6,7,8");
		Pokemon poke3 = new Pokemon("Venasaur", "1,2,3");
		Pokemon poke4 = new Pokemon("Blastoise", "7,8,9");
		Pokemon poke5 = new Pokemon("Caterpie", "1,1,1");
		testTree.addPokemon(poke1);
		testTree.addPokemon(poke2);
		testTree.addPokemon(poke3);
		testTree.addPokemon(poke4);
		testTree.addPokemon(poke5);
		if(!testTree.getLeastPowerfulPokemon().equals(poke5)) {
			System.out.println("getLeastPowerful");
			return false;
		}
		return true;
	}

	/**
	 * Checks for the correctness of PokemonTree.getMostPowerfulPokemon() method.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testGetMostPowerfulPokemon() {
		PokemonTree testTree = new PokemonTree();
		Pokemon poke1 = new Pokemon("Pikachu", "3,4,5");
		Pokemon poke2 = new Pokemon("Charizard", "6,7,8");
		Pokemon poke3 = new Pokemon("Venasaur", "1,2,3");
		Pokemon poke4 = new Pokemon("Blastoise", "7,8,9");
		Pokemon poke5 = new Pokemon("Caterpie", "1,1,1");
		testTree.addPokemon(poke1);
		testTree.addPokemon(poke2);
		testTree.addPokemon(poke3);
		testTree.addPokemon(poke4);
		testTree.addPokemon(poke5);
		if(!testTree.getMostPowerfulPokemon().equals(poke4)) {
			System.out.println("getMostPowerful");
			return false;
		}
		return true;
	}

	/**
	 * Calls the test methods
	 * 
	 * @param args input arguments if any
	 */
	public static void main(String[] args) {
		
		System.out.println(testAddPokemonToStringSize());
		System.out.println(testAddPokemonAndLookup());
		System.out.println(testHeight());
		System.out.println(testGetLeastPowerfulPokemon());
		System.out.println(testGetMostPowerfulPokemon());
		
	}

}
