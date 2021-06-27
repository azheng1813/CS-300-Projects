
// File Header comes here
import java.util.NoSuchElementException;

/**
 * This class implements a binary search tree (BST) which stores a set of
 * Pokemons. The left subtree contains the Pokemons who are less powerful than
 * the Pokemon stored at a parent node. The right subtree contains the Pokemons
 * who are more powerful than the Pokemon stored at a parent node.
 *
 */
public class PokemonTree {
	private PokemonNode root; // root of this binary search tree
	private int size; // total number of Pokemons stored in this tree.

	/**
	 * Checks whether this binary search tree (BST) is empty
	 * 
	 * @return true if this PokemonTree is empty, false otherwise
	 */
	public boolean isEmpty() { //done
		if(size == 0) {
			return true;
		}
		return false; 
	}

	/**
	 * Returns the number of Pokemons stored in this BST.
	 * 
	 * @return the size of this PokemonTree
	 */
	public int size() { //done
		return size;
		}

	/**
	 * Recursive helper method to add a new Pokemon to a PokemonTree rooted at
	 * current.
	 * 
	 * @param current    The "root" of the subtree we are inserting new pokemon
	 *                   into.
	 * @param newPokemon The Pokemon to be added to a BST rooted at current.
	 * @return true if the newPokemon was successfully added to this PokemonTree,
	 *         false otherwise
	 */
	public static boolean addPokemonHelper(Pokemon newPokemon, PokemonNode current) { //done
		if(current.getPokemon().getCP() > newPokemon.getCP()) {
			if(current.getLeftChild() == null) { //base case
				PokemonNode newPoke = new PokemonNode(newPokemon);
				current.setLeftChild(newPoke);
				return true;
			}
			else {
				return addPokemonHelper(newPokemon, current.getLeftChild());
			}
		}
		if(current.getPokemon().getCP() < newPokemon.getCP()) {
			if(current.getRightChild() == null) { //base case
				PokemonNode newPoke = new PokemonNode(newPokemon);
				current.setRightChild(newPoke);
				return true;
			}
			else {
				return addPokemonHelper(newPokemon, current.getRightChild());
			}
		}
		//this is only if CP is same for both
		return false; 
	}

	/**
	 * Adds a new Pokemon to this PokemonTree
	 * 
	 * @param newPokemon a new Pokemon to add to this BST.
	 * @return true if the new was successfully added to this BST, and returns false
	 *         if there is a match with this Pokemon already already stored in this
	 *         BST.
	 */
	public boolean addPokemon(Pokemon newPokemon) { //done.
		if (isEmpty()) { // Add new to an empty PokemonTree
			PokemonNode newPoke = new PokemonNode(newPokemon);
			root = newPoke;
			size++;
			return true;

		} else {
			if(addPokemonHelper(newPokemon, root)) {
				size++;
				return true;
			}
			else {
				return false;
			}
		}
	}

	/**
	 * Recursive helper method which returns a String representation of the BST
	 * rooted at current. An example of the String representation of the contents of
	 * a PokemonTree is provided in the description of the above toString() method.
	 * 
	 * @param current reference to the current PokemonNode within this BST.
	 * @return a String representation of all the Pokemons stored in the sub-tree
	 *         PokemonTree rooted at current in increasing order with respect to the
	 *         CP values. Returns an empty String "" if current is
	 *         null.
	 */
	public static String toStringHelper(PokemonNode current) { //done?
		String pokemonString = "";
		if(current == null) {
			return"";
		}		
		pokemonString = toStringHelper(current.getLeftChild());
		pokemonString = pokemonString + current.getPokemon().toString() + "\n";
		pokemonString = pokemonString + toStringHelper(current.getRightChild());
		
		
		return pokemonString; 
	}

	/**
	 * Returns a String representation of all the Pokemons stored within this BST in
	 * the increasing order, separated by a newline "\n". For instance:
	 * "[Pikachu CP:123 (A:1 S:2 D:3)]" + "\n" + "[Eevee CP:224 (A:2 S:2 D:4)]" + "\n" + 
	 * [Lapras CP:735 (A:7 S:3 D:5)] + "\n" + "[Mewtwo CP:999 (A:9 S:9 D:9)]" + "\n"
	 * 
	 * @return a String representation of all the Pokemons stored within this BST
	 *         sorted in an increasing order with respect to the CP values.
	 *         Returns an empty string "" if this BST is empty.
	 */
	public String toString() { //done?
		String pokemonString = toStringHelper(root);
		if(pokemonString.length() > 0) {
			pokemonString = pokemonString.substring(0, pokemonString.length() - 1);
		}
		return pokemonString;
	}

	/**
	 * Search for a Pokemon (Pokemon) given the CP value as lookup key.
	 * 
	 * @param cp combat power of a Pokemon
	 * @return the Pokemon whose CP value equals our lookup key.
	 * @throws a NoSuchElementException with a descriptive error message if there is
	 *           no Pokemon found in this BST having the provided CP value
	 */
	public Pokemon lookup(int cp) {
		if (height() == 0) {
			throw new NoSuchElementException("The tree is empty");
		}
		return lookupHelper(cp, root);
	}

	/**
	 * Recursive helper method to lookup a Pokemon given a reference Pokemon with
	 * the same CP in the subtree rooted at current
	 * 
	 * @param cp      the CP of the Pokemon target we are looking for in the BST
	 *                rooted at current.
	 * @param current "root" of the subtree we are looking for a match to find
	 *                within it.
	 * @return reference to the Pokemon stored stored in this BST whose CP matches
	 *         the query value.
	 * @throws NoSuchElementException with a descriptive error message if there is
	 *                                no Pokemon whose CP value matches target value
	 *                                stored in this BST.
	 */
	public static Pokemon lookupHelper(int cp, PokemonNode current) { //need to check if pokemon isn't there, may be done
		// TODO Complete the implementation of this method
		if(current.getPokemon().getCP() == cp) { //base case
			return current.getPokemon();
		} 
		if(current.getPokemon().getCP() < cp) {
			if(current.getRightChild() != null) {
				return lookupHelper(cp, current.getRightChild());
			}
		}
		if(current.getPokemon().getCP() > cp) {
			if(current.getLeftChild() != null) {
				return lookupHelper(cp, current.getLeftChild());
			}
		} 
			throw new NoSuchElementException("The pokemon is not in this tree"); 
		
	}

	/**
	 * Computes and returns the height of this BST, counting the number of nodes
	 * (PokemonNodes) from root to the deepest leaf.
	 * 
	 * @return the height of this Binary Search Tree
	 */
	public int height() {
		return heightHelper(root);
	}

	/**
	 * Recursive helper method that computes the height of the subtree rooted at
	 * current
	 * 
	 * @param current pointer to the current PokemonNode within a PokemonTree
	 * @return height of the subtree rooted at current, counting the number of
	 *         PokemonNodes
	 */
	public static int heightHelper(PokemonNode current) { //done?
		if(current == null) {
			return 0;
		}
		
		int leftHeight = heightHelper(current.getLeftChild());
		int rightHeight = heightHelper(current.getRightChild());
		// TODO Complete this implementation of this method
		int max = 0;
		if(leftHeight < rightHeight) {
			max = rightHeight;
		}
		else {
			max = leftHeight;
		}
		return 1 + max; 
	}

	/**
	 * Returns the Pokemon of the least powerful Pokemon in this BST.
	 * 
	 * @return the Pokemon of the least powerful Pokemon in this BST and null if this tree
	 *         is empty.
	 */
	public Pokemon getLeastPowerfulPokemon() { //done
		PokemonNode current = root;
		if(height() == 0) {
			return null;
		}
		while(current.getLeftChild() != null) {
			current = current.getLeftChild();
		}
		
		return current.getPokemon();
	}

	/**
	 * Returns the Pokemon of the most powerful Pokemon in this BST.
	 * 
	 * @return the Pokemon of the most powerful Pokemon in this BST, and null if this tree
	 *         is empty.
	 */
	public Pokemon getMostPowerfulPokemon() { //done
		PokemonNode current = root;
		if(height() == 0) {
			return null;
		}
		while(current.getRightChild() != null) {
			current = current.getRightChild();
		}
		
		return current.getPokemon();
	}

}
