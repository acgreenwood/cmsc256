/**
 * A class SkipListNode.java to store the data for the individual nodes in the Skip List
 * 
 * @author Andrew Greenwood
 */

public class SkipListNode {
	
	//There is a key and a value for each node
	//They are essentially the same thing in this case though
	private String key;
	private Integer value;

	//Each node also has a pointer to the nodes above, below, and to both sides of it
	public SkipListNode up;
	public SkipListNode down;
	public SkipListNode left;
	public SkipListNode right;

	/**
	 * A constructor that creates a SkipListNode with specified key and value
	 * 
	 * @param k desired key
	 * @param v desired value
	 */
	public SkipListNode(String k, Integer v) {
		//assigns variables as necessary
		key = k;
		value = v;
		up = null;
		down = null;
		left = null;
		right = null;
	}
	
	/**
	 * Simple method to return the value of a node
	 * 
	 * @return the value of the node
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * Simple method to set the value of a node
	 * 
	 * @param newVal the new value of the node
	 */
	public void setValue(int newVal) {
		value = newVal;
	}

	/**
	 * Simple method to set the key of a node
	 * 
	 * @return the key of the node
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * A method to see if another node is the same as this one
	 * 
	 * @param n the node to be compared
	 * @return whether or not they are the same
	 */
	public boolean equals(SkipListNode n) {
		return (key.compareTo(n.getKey()) == 0 && value == n.getValue());
	}
	
	/**
	 * A simple method to return the string representation of the node
	 * 
	 * @return the string representation of the node
	 */
	public String toString() {
		return "Key: "+key+", Value: "+value;
	}
}