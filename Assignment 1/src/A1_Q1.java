import java.util.*;

/**
 * A Java program A1_Q1.java which inputs a list of words,  
 * separated by whitespace, and outputs how many times each
 * word appears in the list.
 * 
 * @author Andrew Greenwood
 */

public class A1_Q1 {
	@SuppressWarnings({ "unchecked", "resource", "rawtypes", "unused" }) //this is just to get rid of the annoying warnings
	public static void main(String[] args) {
		//A LinkedList is created for storing each word input by the user
		List<String> words = new LinkedList<String>(); 

		//The user is prompted for input
		System.out.println("Input a list of words, on a single line, separated by whitespace:"); 

		//A scanner is created that takes the user input
		//The user input is then converted to a string and the scanner is closed
		Scanner sc1 = new Scanner(System.in);
		String s = sc1.nextLine();
		sc1.close();

		//Another scanner is created using the String created by the previous scanner
		//A nested loop is used to take each individual word from the user input and add it to the List
		Scanner sc2 = new Scanner(s);
		while (sc2.hasNext()) {
			Scanner sc3 = new Scanner(sc2.next());
			while (sc3.hasNext()) {
				String q = sc3.next();
				words.add(q);
			}
		}

		//A HashSet is created for the purpose of identifying the number of UNIQUE words in the List
		HashSet noDuplicates = new HashSet();
		
		//A for loop is used to add the data from the List to the HashSet
		for (int a = 0; a < words.size(); a++) {
			noDuplicates.add(words.get(a));
		}

		//The number of UNIQUE words in the List is stored in this variable
		int unique = noDuplicates.size();

		//The HashSet is converted to an Array
		Object[] uniqueOrdered = noDuplicates.toArray();
		
		//The Array is then sorted to create an Array containing one of each word in alphabetical order
		Arrays.sort(uniqueOrdered);

		//An int array is created such that it corresponds with the Array containing one of each word in alphabetical order
		int[] counter = new int[uniqueOrdered.length];
		//for (int b = 0; b < counter.length; b++) {
		//	counter[b] = 0;
		//}

		/* 
		 * A set of for loops is used to compare the uniqueOrdered Array to the original List
		 * For each match with a value in the uniqueOrdered Array in the List, 1 is added to 
		 * the corresponding value of the counter Array
		 */
		for (int j = 0; j < uniqueOrdered.length; j++) {
			for (int k = 0; k < words.size(); k++) {
				if(uniqueOrdered[j].toString().compareTo(words.get(k)) == 0) {
					//Each time a duplicate is found, the corresponding value in the counter increases by one
					counter[j]++;
				}
			}
		}

		//Finally, a for loop is used to print each word and the number of times it occurred
		for (int z = 0; z < counter.length; z++) {
			System.out.println(uniqueOrdered[z]+" "+counter[z]);
		}

	}
}