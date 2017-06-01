/**
 * A class MyStringHash that implements a Hash Set for strings, using an array for storage and Linked List
 * for collision handling.
 * 
 * @author Andrew Greenwood
 */
public class MyStringHash {
	//array containing the LinkedHashEntries
	private static LinkedHashEntry[] strings;

	/**
	 * Constructs a MyStringHash with designated size
	 * 
	 * @param size the desired size of the hash set
	 */
	public MyStringHash(int size) {
		strings = new LinkedHashEntry[size];
	}

	/**
	 * Method to add a string to the hash set
	 * 
	 * @param s the desired string to be added
	 * @return true if the string was added, false otherwise
	 */
	public boolean add(String s) {
		//as long as this string isn't already in the hash set
		if(this.contains(s) == false) {
			//find the hash value of the string
			int hashValue = hash(s);
			//if the array index at hashValue isn't null, then there is already something there
			//so add the new string to the end of the linked list stored at that array entry
			if(strings[hashValue] != null) {
				strings[hashValue].setNext(new LinkedHashEntry(hashValue, s));
			}
			//if the array index at hashValue is null, then there is nothing there
			//so simply add a new LinkedHashEntry containing the input string at that point
			else if(strings[hashValue] == null) {
				strings[hashValue] = new LinkedHashEntry(hashValue, s);
			}
			//then return true because the string was successfully inserted
			return true;
		}
		//if the string was already in the hash set return false
		else {
			return false;
		}
	}

	/**
	 * Method to remove designated string from the hash set
	 * 
	 * @param s the string to be removed
	 * @return whether or not the input string was removed
	 */
	public boolean remove(String s) {
		//for loop to traverse the array 
		for(int i = 0; i < strings.length; i++) {
			//temporary variables are created for the purpose of checking each element of the
			//linked list at each array index
			boolean next = true;
			LinkedHashEntry temp = strings[i];
			//as long as there is a next at the current array index
			while(next) {
				//check to see if the string matches the input string
				String value = temp.getValue();
				if(value.equals(s)) {
					//if so, remove it and return true
					strings[i] = temp.getNext();
					return true;
				}
				//if not, but there is a next, go to the next
				else if(temp.hasNext()) {
					temp = temp.getNext();
				}
				//if there is no next and the match was not found then end the while loop and
				//go to the next array index
				else {
					next = false;
				}
			}
		}
		//if after the whole loop runs, there is no match (and therefore no removed entry), return false
		return false;
	}

	/**
	 * Method to show whether or not the input string is in the hash set
	 * 
	 * @param s the string to be searched for
	 * @return whether or not s is in the hash set
	 */
	public boolean contains(String s) {
		//same loop as the remove() method
		//with same variables etc.
		for(int i = 0; i < strings.length; i++) {
			boolean next = true;
			LinkedHashEntry temp = strings[i];
			while(next) {
				String value = temp.getValue();
				if(value.equals(s)) {
					//only the value is not removed when it is found
					return true;
				}
				else if(temp.hasNext()) {
					temp = temp.getNext();
				}
				else {
					next = false;
				}
			}
		}
		return false;
	}

	/**
	 * Method to find the hash value of the input string
	 * Uses Joshua Bloch's hash code to find the hashing index
	 * 
	 * @param s the string to be hashed
	 * @return the hash code of s
	 */
	private static int hash(String s) {
		//hashValue is set to 17
		int hashValue = 17;
		//then the characters of the string are used to produce a hash code 
		for(int i = 0; i < s.length(); i++) {
			hashValue = 37*hashValue + s.charAt(i);
		}
		//the final hashValue result is modulo the array length so that the returned value is a 
		//valid index in the array
		return hashValue % strings.length;
	}
}
