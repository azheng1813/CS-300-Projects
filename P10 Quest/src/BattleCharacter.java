import java.util.Random;

/**
 * Class to represent characters that are able to battle
 * 
 * @author Michelle & <YOUR NAME HERE>
 *
 */
public class BattleCharacter implements Comparable<BattleCharacter>{

	protected static final Random RAND = new Random(100); // generator of random numbers
	private static int idGenerator = 1; // generator of the id of the next BattleCharacter to be
                                      // created.
	private int[] stats; // size 5 array stats are as follows [Health, Attack, Defense, Magic, Speed]
	private final int ID; // Unique identifier of this battle character
	private String name; // name of this BattleCharacter


	/**
	 * Creates a new BattleCharacter
	 * 
	 * @param name  name for this BattleCharacter
	 * @param stats stats for this BattleCharacter
	 */
	public BattleCharacter(String name, int[] stats) {
		if (name == null || stats.length != 5)
			throw new IllegalArgumentException("Name cannot be null or stats array is not of the proper size.");
		ID = idGenerator++;
		this.name = name;
		this.stats = stats;
	}

	/**
	 * Returns the name of this BattleCharacter
	 * @return the name of this BattleCharacter
	 */
	public String getName() {
		return name;
	}
  
	/**
	 * Returns the ID of this BattleCharacter
	 * @return the ID of this BattleCharacter
	 */
	public int getID() {
		return this.ID;
	}


	/**
	 * Gets the HP (Health Points) of this BattleCharacter
	 * @return the HP of this BattleCharacter
	 */
	public int getHP() {
		return stats[0];
	}

	/**
	 * Returns the Attack value of this BattleCharacter
	 * @return the Attack value of this BattleCharacter
	 */
	public int getAttack() {
		return stats[1];
	}

	/**
	 * Returns the Magic value of this BattleCharacter
	 * @return the Magic value of this BattleCharacter
	 */
	public int getMagic() {
		return stats[3];
	}	
	
	/**
	 * Returns the speed of this BattleCharacter
	 * @return the speed of this BattleCharacter
	 */
	public int getSpeed() {
		return stats[4];
	}
 
	/**
	 * Decreases this character's speed by 25%, rounded down to nearest int
	 */
	public void slow() {
		stats[4] = (int) Math.floor(stats[4] * .75);
	}


	/**
	 * Increases this character's speed by 25%, rounded down to nearest int
	 */
	public void haste() {
		stats[4] = (int) Math.floor(stats[4] * 1.25);
	}

 
	/**
	 * Changes HP based on base damage and half of their defense
	 * @param damageAmount
	 */
	public void takeDamage(int damageAmount) {
		stats[0] -= (damageAmount - (int) Math.floor(stats[2] / 2));
	}


	/**
	 * Returns whether or not the current character is still alive. If Health hits or drops below 0,
	 * they are considered dead.
	 * 
	 * @return returns whether or not the current character is still alive
	 */
	public boolean isAlive() {
		return stats[0] > 0;
	}

	/**
	 * Resets the BattleCharacter.idGenerator to 1. This method can be used at the beginning of your
	 * test methods.
	 */
	public static void resetIDGenerator() {
		idGenerator = 1;
	}

	/**
	 * Returns a String representation of this BattleCharacter in the following format: name(ID, speed)
	 * @return a string representation of this BattleCharacter
	 */
	@Override
	public String toString() {
		return this.name + "(" + this.ID + ", " + this.getSpeed() + ")";
	}
	
	/*
	 * Checks if the current BattleCharacter ID is the same as the obj ID
	 * 
	 * @param obj: The obj that is being checked 
	 * @return true if the current ID and the obj ID is the same, false if not
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof BattleCharacter) {
			if(((BattleCharacter)obj).getID() == this.ID) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Compares BattleCharacters based on speed, if the speed is the same, then IDs are compared
	 * 
	 * @param compObj: the BattleCharacter that is being compared to
	 * @return 1 if the current speed is faster than the compObj or current ID is lower than the compObj ID when the speed is the same
	 * 		-1 if the current speed if slower than the compObj or current ID is higher than the compObj ID when the speed is the same
	 * 		0 if the ID are the same
	 */
	@Override
	public int compareTo(BattleCharacter compObj) {
		if(compObj.getSpeed() > this.getSpeed()) {
			return -1;
		}
		else if(compObj.getSpeed() < this.getSpeed()) {
			return 1;
		}
		else {
			if(compObj.getID() == this.ID) {
				return 0;
			}
			if(compObj.getID() > this.ID) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}
}