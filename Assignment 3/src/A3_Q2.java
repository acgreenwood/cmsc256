import java.util.ArrayList;

/**
 * A class A3_Q2.java that fixes Alice's implementation of a binary min-heap using an array list.
 * 
 * @author Andrew Greenwood
 */
public class A3_Q2 {
    //The ArrayList that represents the min heap
	private ArrayList<Integer> binMinHeap;
    
	/**
	 * Constructor; initializes an empty ArrayList to represent the min heap
	 */
    public A3_Q2() {
        binMinHeap = new ArrayList<Integer>();
    }
    
    /**
     * A method to remove the minimum value from the min heap
     *  
     * @return the value that was removed
     */
    public Integer dequeue() {
    	//Creates an Integer and sets it to the value of the lowest Integer in the min heap
    	//This will be returned at the end
        Integer toReturn = binMinHeap.get(0);
        //Removes the last element in the min heap array list and replaces the lowest value (the one at index 0) with it
        binMinHeap.set(0, binMinHeap.remove(binMinHeap.size()-1));
        //Executes bubbleDown to reorder the min heap so that the smallest element is at index 0 again
        bubbleDown(0);
        //Returns the value that was removed
        return toReturn;
    }
    
    /**
     * A method to add an item to the min heap
     * 
     * @param item the item to be added
     */
    public void enqueue(Integer item) {
    	//Adds the new item to the end of the min heap array list
        binMinHeap.add(item);
        //Executes bubbleUp to reorder the min heap array list so that the new element is in the proper place
        bubbleUp(binMinHeap.size()-1);
    }
    
    /**
     * A method to swap the positions of two elements in the ArrayList
     * Only intended for use in the bubbleDown and bubbleUp methods in this class
     * 
     * @param index1 the index of one of the two elements to be swapped
     * @param index2 the index of the other of the two elements to be swapped
     */
    private void swap(int index1, int index2) {
    	//two temp Integer variables are created to store the values at the two indexes that were input
        Integer temp1 = binMinHeap.get(index1);
        Integer temp2 = binMinHeap.get(index2);
        //Then the temp integers are set at the other's index, effectively swapping them
        binMinHeap.set(index1, temp2);
        binMinHeap.set(index2, temp1);
    }
    
    /**
     * A method to reorder the min heap array list by "bubbling down"
     * 
     * @param index the index to start bubbling down from
     */
    private void bubbleDown(int index) {
    	//int i is created and set equal to the input index
        int i = index;
        //while loop runs as long as i and i+1 are valid indices in the array list, i.e. less than the size of the list
        //(and greater than or equal to zero)
        while(i >= 0 && i < binMinHeap.size()-1) {
        	//if an element is greater than the element after it, they are swapped
            if(binMinHeap.get(i) > binMinHeap.get(i+1)) {
                swap(i, i+1);
                //after that, i is reset to index so the whole thing runs again -- this ensures that
                //the smallest Integer will always be at index 0
                i = index;
            }
            //else, if the elements are in proper order, move on to the next pair (i++)
            else {
                i++;
            }
        }
    }
    
    /**
     * A method to reorder the min heap array list by "bubbling up"
     * 
     * @param index the index to start bubbling up from
     */
    private void bubbleUp(int index) {
    	//if the input index is too big, abort 
        if(index >= binMinHeap.size()) {
            return;
        }
        //if not, create int i and set it equal to the input index
        int i = index;
        //while loop runs as long as i and i-1 are valid indices in the array list, i.e. greater than zero
        while(i > 0) {
        	//if an element is less than the element before it, they are swapped
            if(binMinHeap.get(i) < binMinHeap.get(i-1)) {
                swap(i, i-1);
                //after that, i is reset to index so the whole thing runs again -- this ensures that the
                //largest Integer will always be at the last index in the list
                i = index;
            }
            //else, if the elements are in proper order, move down to the next pair (i--)
            else {
                i--;
            }
        }
    }
    
    /**
     * A method to return out the string representation of the arraylist
     * 
     * @return the string representation of the arraylist
     */
    public String toString() {
        return binMinHeap.toString();
    }
    
    /**
     * testing
     */
    public static void main(String[] args) {
        A3_Q2 h = new A3_Q2();
        h.enqueue(6);
        h.enqueue(7);
        h.enqueue(12);
        h.enqueue(23);
        h.enqueue(2);
        h.enqueue(1);
        h.enqueue(9);
        h.enqueue(14);
        h.enqueue(3);
        h.enqueue(22);
        h.enqueue(16);
        System.out.println(h.toString());
        System.out.println(h.dequeue());
        System.out.println(h.toString());
        System.out.println(h.dequeue());
        System.out.println(h.toString());
        System.out.println(h.dequeue());
        System.out.println(h.toString());
        System.out.println(h.dequeue());
        System.out.println(h.toString());
        System.out.println(h.dequeue());
        System.out.println(h.toString());
        System.out.println(h.dequeue());
        System.out.println(h.toString());
    }
}