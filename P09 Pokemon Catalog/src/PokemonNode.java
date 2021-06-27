
public class PokemonNode {
	
	private Pokemon data;
	private PokemonNode leftChild;
	private PokemonNode rightChild;
	
	/*
	 * Constructor for the PokemonNode object. Takes one parameter 
	 * 
	 * @param data: The data of the pokemon
	 */
	public PokemonNode(Pokemon data) {
		leftChild = null;
		rightChild = null;
		this.data = data;
		if (data == null) {
			throw new IllegalArgumentException();
		}
	}
	
	/*
	 * Gets the left child of the current node
	 * 
	 * @return the left child of the current node
	 */
	public PokemonNode getLeftChild() {
		return this.leftChild;
	}

	/*
	 * Gets the right child of the current node
	 * 
	 * @return the right child of the current node
	 */
	public PokemonNode getRightChild() {
		return this.rightChild;
	}
	
	/*
	 * Gets the pokemon of the current node
	 * 
	 * @return the pokemon of the current node
	 */
	public Pokemon getPokemon() {
		return this.data;
	}
	
	/*
	 * Sets the left child of the current node
	 * 
	 * @param left: the node that will be set as the left child of the current node
	 */
	public void setLeftChild(PokemonNode left) {
		this.leftChild = left;
	}
	
	/*
	 * Sets the right child of the current node
	 * 
	 * @param right: the node that will be set as the left child of the current node
	 */
	public void setRightChild(PokemonNode right) {
		this.rightChild = right;
	}
	
	
}

