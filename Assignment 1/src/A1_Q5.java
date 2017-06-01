import java.io.*;
import java.util.*;
/**
 * This is my absolutely hilarious attempt at doing A1_Q5 in what is probably the least efficient way possible.
 * I am sorry but I do not have the patience to comment all this spaghetti code.
 * It literally only works with the example input that you gave and I have no idea why.
 * 
 * @author Andrew Greenwood
 */

public class A1_Q5 {

	private static String filepath;

	private static int numOfBoxes;
	
	private static ArrayList<int[]> fileOpen() throws IOException {
		//A FileReader is created to read the input file
		FileReader reader = new FileReader(filepath);
		//A BufferedReader is created to read the FileReader
		BufferedReader reader2 = new BufferedReader(reader);

		String input = reader2.readLine();

		//The BufferedReader is closed once it is no longer needed
		reader2.close();

		numOfBoxes = Integer.parseInt(input.substring(0,1));

		ArrayList<int[]> output = new ArrayList<int[]>(numOfBoxes);

		String boxDimensions = input.substring(1);
		
		
		String boxDimensions2 = "";
		
		for (int t = 1; t < (boxDimensions.length()/2)+1; t++) {
			boxDimensions2 = boxDimensions2 + boxDimensions.substring(2*t-1, 2*t);
		}

		for(int i = 0; i < numOfBoxes; i++)
		{
			int a = Integer.parseInt(boxDimensions2.substring(0, 1));
			int b = Integer.parseInt(boxDimensions2.substring(1, 2));
			int c = Integer.parseInt(boxDimensions2.substring(2, 3));
			output.add(new int[]{a, b, c});

			boxDimensions2 = boxDimensions2.substring(3);
		}

		//The ArrayList is returned
		return output;
	}

	private static int getMinValueIndex(int[] in) throws IndexOutOfBoundsException {
		if(in.length == 0) {
			throw new IndexOutOfBoundsException("Array size is zero");
		}
		else {
			int min = 0; 

			for(int i = 1; i < in.length; i++)
			{
				if(in[i] < min) {
					min = i;
				}
			}

			return min;
		}
	}
	
	private static void removeFromArray(int index, int[] in) {
		int[] out = new int[in.length-1];
		for (int i = 0; i < index; i++) {
			out[i] = in[i];
		}
		for (int j = index; j < out.length; j++) {
			out[j] = in[j+1];
		}
	}

	private static int boxCount() throws IOException {

		ArrayList<int[]> list = fileOpen();

		int[] smallest = new int [3];
		int[] largest = new int [3];

		int smallIndex = 0;
		int largeIndex = 0;

		int x = 0;
		int y = Integer.MAX_VALUE;

		for(int i = 0; i < list.size(); i++) {

			Arrays.sort(list.get(i));

			int vol = list.get(i)[0] * list.get(i)[1] * list.get(i)[2]; 

			if (vol > x) {
				largest = list.get(i);
				largeIndex = i;
				x = vol;
			}

			if (vol < y) {
				smallest = list.get(i);
				smallIndex = i;
				y = vol;
			}
		}

		int fits;
		if(smallest[0] >= largest[0] || smallest[1] >= largest[1] || smallest[2] >= largest[2]) {
			fits = 1;
			return fits;
		}
		else {
			ArrayList<int[]> list2 = new ArrayList<int[]>(numOfBoxes-2);

			fits = 2;

			for(int j = 0; j < list.size(); j++) {
				if(j == smallIndex || j == largeIndex) {
				}
				else {
					list2.add(list.get(j));
				}
			}

			int[] areaArray = new int[list.size()];

			for(int k = 0; k < list2.size(); k++) {
				areaArray[k] = list2.get(k)[0] * list2.get(k)[1] * list2.get(k)[2];
			}

			int v = areaArray.length;
			for(int l = 0; l < v; l++) {
				int[] temp = {0, 0, 0};
				if (list2.get(getMinValueIndex(areaArray))[0] < largest[0] && list2.get(getMinValueIndex(areaArray))[1] < largest[1] && list2.get(getMinValueIndex(areaArray))[2] < largest[2] && list2.get(getMinValueIndex(areaArray))[0] > smallest[0] && list2.get(getMinValueIndex(areaArray))[1] > smallest[1] && list2.get(getMinValueIndex(areaArray))[2] > smallest[2] && list2.get(getMinValueIndex(areaArray))[0] > temp[0] && list2.get(getMinValueIndex(areaArray))[1] > temp[1] && list2.get(getMinValueIndex(areaArray))[2] > temp[2]) {
					temp = list2.get(getMinValueIndex(areaArray));
					list2.remove(getMinValueIndex(areaArray));
					removeFromArray(getMinValueIndex(areaArray), areaArray);
					fits++;
				}
				else {
					break;
				}
			}

			return fits;
		}
	}

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
			int result = boxCount();
			
			System.out.println(result);

		}
		//catch block is used to catch IOException as a result of
		//a problem with the text file or the filepath being invalid
		catch (IOException e) {
			//The error message is printed
			System.out.println(e.getMessage());
		}
	}
}
