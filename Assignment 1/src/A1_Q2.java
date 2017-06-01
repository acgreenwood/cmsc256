import java.util.*;

/**
 * A Java program A1_Q2.java that takes all the lines input to standard 
 * input and writes them to standard output in reverse order.
 * 
 * @author Andrew Greenwood
 */

public class A1_Q2 {

	@SuppressWarnings({ "resource" }) //this is just to get rid of the annoying warnings
	public static void main(String[] args) {
		//Scanner is used to take user input
		Scanner sc1 = new Scanner(System.in);
		//User is first asked how many lines they would like to input
		System.out.print("How many lines of text would you like to enter? ");
		//Their answer is saved to a variable
		int lines = sc1.nextInt();
		//An array is created to store the lines they enter
		String[] input = new String[lines];
		
		sc1.nextLine(); 
		//The user is then prompted to enter their lines of text
		System.out.println("Enter your "+lines+" lines of text: ");
		
		//A for loop is used to set each line equal to one of the values in the Array
		for (int i = 0; i < input.length; i++) {
			input[i] = sc1.nextLine();
		}
		
		//A for loop is used to reverse each String in the array one by one
		for(int j = 0; j < input.length; j++) {
			//A temporary string is created and set equal to the corresponding string from the Array
			String forwards = input[j];
			//StringBuilder is used to easily reverse the String
			String backwards = new StringBuilder(forwards).reverse().toString();
			//The new, reversed String is set equal to the corresponding value in the Array
			input[j] = backwards;
		}
		
		//A for loop is used to print the Strings in the array (which are now reversed) in reverse order
		for(int k = input.length-1; k >= 0; k--) {
			System.out.println(input[k]);
		}
	}
}
