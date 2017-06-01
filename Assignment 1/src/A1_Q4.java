import java.io.*;
import java.util.Scanner;
/**
 * A program that reads math expressions from a file line by line,
 * then computes the result for each line and outputs them line by line.
 * 
 * @author Andrew Greenwood
 */
public class A1_Q4 {
	//A static String variable is created for storing the filepath of the input file
	private static String filepath;

	/**
	 * A method to count the number of consecutive lines (starting with the first)
	 * containing text in a text file.
	 * 
	 * @return an int representing the number of lines containing text in the text file
	 * @throws IOException in case there is a problem with the text file or the filepath is invalid
	 */
	private static int lineCount() throws IOException {
		//A FileReader is created to read the input file
		FileReader reader3 = new FileReader(filepath);
		//A BufferedReader is created to read the FileReader
		BufferedReader reader4 = new BufferedReader(reader3);

		//int lines is instantiated and set to 0
		int lines = 0;

		//A while loop that adds 1 to the lines variable each time 
		//a new line containing text is reached
		while(reader4.readLine() != null) {
			lines++;
		}

		//The BufferedReader is closed once the loop is complete
		reader4.close();

		//The int lines is returned
		return lines;
	}

	/**
	 * A method to read the file input by the user
	 * 
	 * @return an Array with each line at a different index in the Array
	 * @throws IOException in case there is a problem with the text file or the filepath is invalid
	 */
	private static String[] fileOpen() throws IOException {
		//A FileReader is created to read the input file
		FileReader reader = new FileReader(filepath);
		//A BufferedReader is created to read the FileReader
		BufferedReader reader2 = new BufferedReader(reader);

		//The number of lines is set equal to the result of the lineCount() method
		int lines = lineCount();
		//A String array is created with a size equal to the number of lines
		String[] text = new String[lines];

		//A for loop is used to set each different line found by the BufferedReader 
		//equal to a different value in the Array.
		for(int i = 0; i < lines; i++) {
			text[i] = reader2.readLine();
		}

		//The BufferedReader is closed once the loop is complete
		reader2.close();

		//The Array is returned
		return text;
	}

	/**
	 * This method does all the math.
	 * 
	 * @param str	A string intended to be a mathematical equation in text form
	 * @return		The result of the mathematical equation input
	 */
	private static double interpreter(String str) {
		/**
		 * A temporary class interpret is created inside the method for keeping
		 * everything neat and tidy.
		 * 
		 * @author Andrew Greenwood
		 */
		class interpret {
			//An int variable is created to represent position in the String being read
			int pos = -1;
			//An int variable is created to interpret the character at each position in the String
			int c;

			/**
			 * A short method to remove a character from the String
			 */
			void removeChar() {
				c = (++pos < str.length()) ? str.charAt(pos) : -1;
			}

			/**
			 * A short method to remove whitespace from the String
			 */
			void removeSpace() {
				while (Character.isWhitespace(c)) removeChar();
			}

			/**
			 * One of the methods that does the actual math.
			 * Uses methods parseEquation(), parseTerm(), and parseOperator(), all
			 * of which are shown further below.
			 * 
			 * @return The result of the calculation
			 */
			double parse() throws RuntimeException {
				//First character is removed
				removeChar();
				//A double variable is instantiated and set equal to the result of the parseEquation() method
				double a = parseEquation();
				//An if statement is used to make sure c does not equal -1
				//If it does, a RuntimeException is thrown
				if (c != -1) throw new RuntimeException("Unexpected: " + (char)c, null);
				//The result/solution of the equation is returned
				return a;
			}

			/**
			 * Modifies the result of the parseTerm() method to complete
			 * solving the equation.
			 * 
			 * @return The solution to the equation
			 */
			double parseEquation() {
				//A double variable is created and set equal to the result
				//of the parseTerm() method
				double b = parseTerm();
				//A for loop is used to check for addition or subtraction and 
				//apply them if necessary
				for (;;) {
					//Whitespace is removed
					removeSpace();
					//Check is run to see if there is a +
					//If so, then addition is necessary
					if (c == '+') {
						//If there is a +, it is removed 
						removeChar();
						//The result of parseTerm() before the + is then added to 
						//the result of parseTerm() after the +
						b += parseTerm();
					} 
					//Check is run to see if there is a -
					//If so, then subtraction is necessary
					else if (c == '-') {
						//If there is a -, it is removed
						removeChar();
						//The result of parseTerm() after the - is then subtracted from
						//the result of parseTerm() before the -
						b -= parseTerm();
					} 
					//If no more + or - are present then addition and subtraction 
					//are no longer necessary
					else {
						//The double b, now representing the final solution to
						//the equation, is returned
						return b;
					}
				}
			}

			/**
			 * Modifies the result of the parseOperator() method to continue
			 * solving the equation.
			 * 
			 * @return  The result of the equation after all multiplication, division, 
			 * 			parentheses, and exponentiation have been taken into account
			 */
			double parseTerm() {
				//A double variable is created and set equal to the result
				//of the parseOperator() method
				double d = parseOperator();
				//A for loop is used to check for multiplication, division, or 
				//exponentiation and apply them if necessary
				for (;;) {
					//Whitespace is removed
					removeSpace();
					//Check is run to see if there is a /
					//If so, then division is necessary
					if (c == '/') {
						//If there is a /, it is removed
						removeChar();
						//The result of parseOperator() before the / is then 
						//divided by the result of parseOperator() after the /
						d /= parseOperator();
					}
					//Check is run to see if there is a *
					//If so, then multiplication or exponentiation is necessary
					else if (c == '*') {
						//If there is a *, it is removed
						removeChar();
						//Check is run to see if there is another * after the first one
						//If so, then exponentiation is necessary
						if (c == '*') {
							//If there is a second *, it is also removed
							removeChar();
							//The result of parseOperator() before the ** is raised to
							//the power of the result of parseOperator() after the **
							d = Math.pow(d, parseOperator());
						}
						//If there is not a second *, then multiplication is necessary
						else {
							//The result of parseOperator() before the * is multiplied by
							//the result of parseOperator after the *
							d *= parseOperator();
						}
					} 
					//If no more * or / are present then multiplication, division,
					//and exponentiation are no longer necessary
					else {
						//The double d is returned
						return d;
					}
				}
			}
			
			/**
			 * Begins the interpretation of the input equation by checking for extraneous + symbols,
			 * - symbols intended for negation instead of subtraction, parentheses, and numbers.
			 * 
			 * @return  The result of the equation after parentheses and negatives 
			 * 			have been taken into account
			 */
			double parseOperator() {
				//A double f is created
				double f;
				//A boolean variable indicating if a value should be negated (multiplied by negative 1) is
				//created and is set to false by default
				boolean negate = false;
				//Whitespace is removed
				removeSpace();
				//Check is run for a - symbol with no number preceding it or any extraneous + symbols
				if (c == '+' || c == '-') {
					//If there is a - symbol, the boolean negate is set to true
					negate = c == '-';
					//The + or - symbol is removed
					removeChar();
					//Whitespace is removed
					removeSpace();
				}
				//Check is run for any parentheses
				if (c == '(') {
					//If one is found, it is removed
					removeChar();
					//The equation within the parentheses is then parsed as if it were a standalone equation
					f = parseEquation();
					//Check is then run of the closing parenthese
					//If one is found, it is removed
					if (c == ')') removeChar();
				} 
				//Check is run for numbers
				else {
					//A temporary StringBuilder is created
					StringBuilder temp = new StringBuilder();
					//While loop is used to check for numbers or decimal points
					while ((c >= '0' && c <= '9') || c == '.') {
						//If a number or decimal point is found it is added to the StringBuilder
						temp.append((char)c);
						//The character is then removed
						removeChar();
					}
					//If no numbers were added then a RuntimeException is thrown 
					//(because that means there are no numbers in the equation)
					if (temp.length() == 0) throw new RuntimeException("Unexpected: " + (char)c, null);
					
					//The StringBuilder is converted to a String and parsed as a double to get
					//a double that is essentially a list of all the numbers in the equation
					f = Double.parseDouble(temp.toString());
				}
				//Whitespace is removed
				removeSpace();
				//If there was a negative (or any odd number of negatives) the double is multiplied by -1
				if (negate) f = -f;
				//The double is returned
				return f;
			}
		}
		//The equation that was input is parsed and returned
		return new interpret().parse();
	}

	//IOException is thrown if there is a problem with the text file or the filepath is invalid
	public static void main(String[] args) throws IOException { 
		//Scanner is created to take used input for text file location
		Scanner sc = new Scanner(System.in);
		//User is asked for filepath of text file for input
		System.out.println("Enter the filepath of the input file:");
		//String is created and set equal to the first line entered
		String str = sc.nextLine();
		//Scanner is then closed
		sc.close();
		//static String filepath from way earlier is set equal to string that was input
		filepath = str;
		
		//try block is used to attempt to open the text file, read its contents, and output the
		//results of the equations inside of it
		try {
			//String Array is created and set equal to the result of the fileOpen() method
			//The equations are now stored in the String Array
			String[] lineArray = fileOpen();
			//double Array is created and set to be the same size as the String Array
			double[] answerArray = new double[lineArray.length];

			//for loop is used to get the solution to each equation in the String array
			//The solutions are each set equal to a value in the double Array answerArray
			for(int y = 0; y < answerArray.length; y++) {
				answerArray[y] = interpreter(lineArray[y]);
			}
			
			//Another for loop is used to print the solutions
			for(int z = 0; z < answerArray.length; z++) {
				System.out.println(answerArray[z]) ;
			}
		}
		//catch block is used to catch IOException as a result of
		//a problem with the text file or the filepath being invalid
		catch (IOException e) {
			//The error message is printed
			System.out.println(e.getMessage());
		}
	}
}