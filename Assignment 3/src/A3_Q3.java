/**
 * A class containing a method to determine, in array A of size N where A[0] < A[1] < ... < A[N-1],
 * whether or not there exists at least one index k < N such that A[k] = k.
 * 
 * The "test class" is included in this class.
 * 
 * @author Andrew Greenwood
 *
 */
public class A3_Q3 {
	
	/**
	 * @param args the integers to be put into the array to be tested
	 */
	public static void main(String[] args) {
		//the method is then run on the array
		//u better hope all the strings are parseable integers
		doesIndexEqualValue(args);
	}
	/**
	 * A method to determine, in array A of size N where A[0] < A[1] < ... < A[N-1],
	 * whether or not there exists at least one index k < N such that A[k] = k.
	 * 
	 * @param array the array to be analyzed
	 */
	//See the method below for alrogithm time complexity analysis
	public static void doesIndexEqualValue(String[] array) {
		//with just an array input, automatically searches whole array 
		doesIndexEqualValue(array, 0, array.length-1);
	}

	/**
	 * A recursive method to determine, in array A of size N where A[0] < A[1] < ... < A[N-1],
	 * whether or not there exists at least one index k < N such that A[k] = k.
	 * 
	 * @param array the array to be analyzed
	 * @param startIndex the smallest index in the array to be searched -- i.e. where to start the search
	 * @param endIndex the largest index in the array to be searched -- i.e. where to end the search
	 */
	//This algorithm runs with worst-case time complexity O(log(N)), where log represents log base 2 and 
	//N represents the number of elements in the array 
	public static void doesIndexEqualValue(String[] array, int startIndex, int endIndex) {
		//if for whatever reason the end index is >= the size of the array, it
		//is assumed that it was supposed to be the largest index in the array and is set
		//to array.length - 1
		if(endIndex >= array.length) { 		//cost: c1
			endIndex = array.length - 1;	//times run: log(N) + 1 at most
		}

		//if the start index is equal to the end index then that means an match was never found so false is returned
		//also if the start index is greater than the end index for whatever reason then it's time to end the recursion
		if(startIndex >= endIndex) { 		//cost: c2
			System.out.println("False");	//times run: log(N) + 1 at most
			return;
		}

		//int i represents the average of the start and end index -- i.e. the value that will be checked if array[i] = i
		int i = (endIndex + startIndex)/2; //cost c3; times run: log(N) at most

		//if the two are equal print True
		if(Integer.parseInt(array[i]) - i == 0) {				//cost: c4; times run: log(N) at most
			//best case: the very first i that is checked is equal to array[i]: this is O(1)
			System.out.println("True");
			return;
		}

		//if the value in the array is greater than the index value
		else if(Integer.parseInt(array[i]) - i > 0) {			//cost: c5; times run: log(N) at most
			//search the first half of the array or sub-array that you just searched
			doesIndexEqualValue(array, startIndex, i);
			//each time this happens, the size of the array being searched is cut in half
			//this means that the maximum number of times that this can happen is log base 2 (N) -- worst-case
		}

		//if the value in the array is less than the index value
		else if(Integer.parseInt(array[i]) - i < 0) {			//cost: c6; times run: log(N) at most
			//search the second half of the array or sub-array that you just searched
			doesIndexEqualValue(array, i, endIndex);
			//each time this happens, the size of the array being searched is cut in half
			//this means that the maximum number of times that this can happen is log base 2 (N) -- worst-case
		}

		//if none of these conditions are met
		else {								//cost: c7; times run: 1
			//print False
			System.out.println("False");
			return;
		}
		
		/*
		 * Full worst-case analysis:
		 * Call the the array being tested A, and say A has 16 elements, with A[0] = 0 and A[1] = 2 
		 * (and therefore A[k] > k for all remaining k in A)
		 * This means that the only index that satisfies A[k] = k is 0 -- if this is the case (or if the last
		 * index is the only one to satisfy A[k] = k) then it will take the maximum amount of time for the algorithm
		 * to run.
		 * 
		 * The first time it runs it checks from 0 to 15; i = (15+0)/2 = 7. As we stated earlier A[k] is greater than k
		 * for all k > 0 in A so the method will reach the statement:
		 * 		else if(array[i] - i > 0) { doesIndexEqualValue(array, startIndex, i); }
		 * when this happens it will reiterate, checking from 0 to 7; i = (7+0)/2 = 3. A[3] is greater than 3, so it
		 * reiterates, checking from 0 to 3; i = (0+3)/2 = 1. A[1] is greater than 1, so it reiterates,
		 * checking from 0 to 1; i = (1+0)/2 = 0; A[0] = 0, so the method returns true.
		 * 
		 * All in all it has run 4 times: checking 0 to 15, 0 to 7, 0 to to 3, and 0 to 1.
		 * Coincidentally, log(16) = 4.
		 */
	}
}
