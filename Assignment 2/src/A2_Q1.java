import java.util.Scanner;

/**
 * A Java program A2_Q1.java which contains a method that prints out the hex
 * representation of a decimal number.
 * 
 * @author Andrew Greenwood
 */

public class A2_Q1 
{
	/**
	 * 
	 * @param num 	Integer input: decimal value to be converted to hexadecimal
	 * @return		The hexadecimal (base 16) value of the decimal (base 10) input
	 */
	public static String decToHex(int num) {
		//A String is created to store the hexadecimal number as the method runs
		String output = ""; 

		//An int r (for remainder) is set equal to the remainder when dividing the input by 16
		int r = num % 16;	

		if (num == 0) { 
			//If the number input is 0, returns (blank)
			return "";
		} else {
			//A switch statement is used for all cases where num does not equal zero
			switch (r) {
			//For cases where r is between 10 and 15 (inclusive), the number is replaced with the corresponding
			//hexadecimal letters (A through F)
			//In each case, the string output is set equal to this hexadecimal letter and the switch is broken
			case 10:
				output = "A";
				break;
			case 11:
				output = "B";
				break;
			case 12:
				output = "C";
				break;
			case 13:
				output = "D";
				break;
			case 14:
				output = "E";
				break;
			case 15:
				output = "F";
				break;
				//The default case is when r is a single digit number (0-9)
			default:
				//r is added to the beginning of output instead
				output = r + output;
				break;
			}
			//The method recurses until num is less than or equal to 16
			//At this point it runs one last time where num is equal to 0
			//Each recursion adds the result to the beginning of the output
			return decToHex(num / 16) + output;
		}
	}

	public static void main(String[] args) {

		//The user is prompted for input
		System.out.println("Input an integer to be converted to hexadecimal: "); 

		//A scanner is created that takes the user input
		//The user input is then saved to an int variable and the scanner is closed
		Scanner sc1 = new Scanner(System.in);
		int n = sc1.nextInt();
		sc1.close();
		
		//The hexadecimal result is printed
		System.out.println("0x"+decToHex(n));
	}
}
