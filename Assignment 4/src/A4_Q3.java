import java.util.Arrays;
/**
 * A class A4_Q3.java that contains an algorithm for Quicksorting an array in decreasing order.
 * 
 * @author Andrew Greenwood
 */

public class A4_Q3 {
	//The algorithm uses recursion to quicksort the array
	//However the recursive method is split into two methods for the sake of simplicity

	/**
	 * A method to sort the array from highest to lowest and find the pivot for the quicksort
	 * 
	 * @param array The array to be quicksorted
	 * @param start The index of the start of the search
	 * @param end	The index of the end of the search
	 * @return		The index of the pivot
	 */
	private static int partition(int array[], int start, int end)
	{
		//ints are created to store the start and end values
		int a = start;
		int b = end;
		//temp int is created for switching two values in the array
		int temp;
		//the pivot point is set at the midpoint between start and end
		int pivot = array[(start + end)/2];
		
		//as long as a (the int that represents start) is less than b (the int that represents end)
		//run the loop
		while(a <= b) {
			//if the value at a (in the first half of the array) is greater than the value at the pivot point
			//add 1 to a, as that value is in the right place
			while (array[a] > pivot) {
				a++;
			}
			//likewise, if the value at b is less than the value at the pivot point
			//subtract one from b, as that value is in the right place
			while(array[b] < pivot) {
				b--;
			}
			//if a is less than or equal to b
			//switch their values
			if(a <= b) {
				//this is where the temp int comes into play
				temp = array[a];
				array[a] = array[b];
				array[b] = temp;
				//after switching, add 1 to a and subtract 1 from b as both those values are in
				//the right place
				a++;
				b--;
			}
		}
		//last, return a, which should be at the point of the pivot by the time the method is done running
		return a;
	}

	/**
	 * A method to quicksort the array -- this one doesn't require you to input the array start/end values --
	 * only the array itself
	 * 
	 * @param array The array to be quicksorted
	 */
	public static void quickSort(int[] array) {
		quickSort(array, 0, array.length-1);
	}

	/**
	 * The other recursive method for quicksorting the array
	 * 
	 * @param array The array to be quicksorted
	 * @param start The first index of the array to be quicksorted
	 * @param end	The last index of the array to be quicksorted
	 */
	private static void quickSort(int array[], int start, int end) {
		//The partition method is run once to get a pivot point
		int index = partition(array, start, end);
		
		//then, if the start index is less than the pivot index - 1
		//quicksort that part of the array
		if(start < index - 1) {
			quickSort(array, start, index - 1);
		}
		
		//then, if the end index is greater than the pivot index
		//quicksort that part of the array
		if(index < end) {
			quickSort(array, index, end);
		}
		
		//this method recurses until the array is fully sorted
	}

	/**
	 * Test class
	 */
	public static void main(String[] args) {
		int[] intArray = new int[]{7, 23, 4, 56, 19, 12, 33, 49, 86, 88, 92, 31, 8, 5, 11, 1, 47};
		System.out.println("Array before sorting: "+Arrays.toString(intArray));
		quickSort(intArray);
		System.out.println("Array after sorting: "+Arrays.toString(intArray));
		
		int[] intArray2 = new int[]{1, 3, 4, 6, 8, 9, 10, 14, 16, 17, 23, 25, 34, 46, 55, 57, 61, 99, 100};
		System.out.println("Array before sorting: "+Arrays.toString(intArray2));
		quickSort(intArray2);
		System.out.println("Array after sorting: "+Arrays.toString(intArray2));
	}
}
