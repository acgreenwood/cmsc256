/**
 * A class LinkedHashEntry.java that stores the data for each entry in the hash set for strings
 * 
 * @author Andrew Greenwood
 */

public class LinkedHashEntry {
	//each entry has a key, a value, and a next pointer
	//key will represent the entry's hash value, the value will represent the String stored in the entry
	private int key;
	private String value;
	private LinkedHashEntry next;

	/**
	 * Creates a new LinkedHashEntry with specified key and value
	 * 
	 * @param key the desired key
	 * @param value the desired value
	 */
	LinkedHashEntry(int key, String value) {
		this.key = key;
		this.value = value;
		//next pointer defaults to null
		this.next = null;
	}

	/**
	 * Simple method to return the value of a LinkedHashEntry
	 * 
	 * @return the value of this LinkedHashEntry
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Simple method to set the value of a LinkedHashEntry
	 * 
	 * @param value the desired value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Simple method to return the key of a LinkedHashEntry
	 * 
	 * @return the key of this LinkedHashEntry
	 */
	public int getKey() {
		return key;
	}

	/**
	 * Simple method to return the next LinkedHashEntry
	 * 
	 * @return the LinkedHashEntry to which this LinkedHashEntry's next pointer points
	 */
	public LinkedHashEntry getNext() {
		return next;
	}

	/**
	 * Simple method to see if this LinkedHashEntry has a next LinkedHashEntry
	 * 
	 * @return whether or not this LinkedHashEntry has a next LinkedHashEntry
	 */
	public boolean hasNext() {
		if (this.next == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Simple method to set the next pointer of a LinkedHashEntry
	 * 
	 * @param next the desired next LinkedHashEntry
	 */
	public void setNext(LinkedHashEntry next) {
		this.next = next;
	}
}