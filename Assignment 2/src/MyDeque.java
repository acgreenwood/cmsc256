import java.util.*;

/**
 * A Java program MyDeque.java that implements java.util.Deque based on array structure
 * In this case, a resizeable array is used to represent the Deque
 * 
 * @author Andrew Greenwood
 *
 * @param <E> the type of elements held in this collection
 */

@SuppressWarnings("unchecked") //Just to get rid of annoying warnings in eclipse
public abstract class MyDeque<E> implements Deque<E> {

	//An array is created to hold the elements of the Deque
	private E[] elements;

	//An int is created to represent the index of the element at the head of the deque
	private int head;

	//Another int is created to represent the index at which a new element would be added to
	//the tail of the deque
	private int tail;

	/**
	 * The Default Constructor
	 * 
	 * Creates an empty array deque with initial capacity of 8 
	 * 
	 */
	public MyDeque() {
		//The array elements is set equal to a new Object array of size 8
		elements  = (E[]) new Object[8];
	}

	/**
	 * A Constructor with custom size
	 * 
	 * @param size initial capacity of the deque
	 */
	public MyDeque(int size) {
		//First, the empty array is allocated to hold a given number of elements
		//Initial capacity is set to 0 since the array technically doesn't exist yet
		int inCap = 0;
		//Checks to see if the desired size is greater than zero (which it should be)
		//Then finds the best exponentiation of two to hold elements.
		if(size >= inCap) {
			inCap = size;
			inCap |= (inCap >>> 1);
			inCap |= (inCap >>> 2);
			inCap |= (inCap >>> 4);
			inCap |= (inCap >>> 8);
			inCap |= (inCap >>> 16);
			inCap++;
		}
		//Then the array elements is set equal to a new Object array of size inCap
		elements = (E[]) new Object[inCap];
	}

	/**
	 * A Constructor that creates a Deque using elements from a specified Collection
	 * 
	 * @param c the Collection whose elements are to be placed into the Deque
	 */
	public MyDeque(Collection<? extends E> c) {
		//First, the empty array is allocated to hold a given number of elements
		//Initial capacity is set to 0 since the array technically doesn't exist yet
		int inCap = 0;
		//Checks to see if the desired size is greater than zero (which it should be)
		//Then finds the best exponentiation of two to hold elements.
		if(c.size() >= inCap) {
			inCap = c.size();
			inCap |= (inCap >>> 1);
			inCap |= (inCap >>> 2);
			inCap |= (inCap >>> 4);
			inCap |= (inCap >>> 8);
			inCap |= (inCap >>> 16);
			inCap++;
		}
		//addAll method from java.util.Collection is used to add the elements from the specified collection
		//to the array
		addAll(c);
	}

	/**
	 * A method to remove all elements from the deque
	 */
	@Override
	public void clear() {
		//int h is created to represent index of head
		int h = head;
		//int t is created to represent index of tail
		int t = tail;
		//A check is run to make sure head and tail do not have same index
		if(h != t) { 
			//All cells are to be cleared
			head = tail = 0;
			//int i is created to act as a counter; initially set equal to h
			int i = h;
			//Do-while loop is used to ensure at least one iteration
			do {
				//Sets the value of each value of the array to null
				elements[i] = null;
				i++;
			} 
			//Loop continues as long as i is not equal to t
			while(i != t);
		}
	}

	/**
	 * A simple method to return whether or not the array Deque is empty
	 * 
	 * @return boolean value indicating whether or not the array Deque is empty
	 */
	@Override
	public boolean isEmpty() {
		//If head and tail are the same, then this method will return true indicating that the array Deque is empty 
		return head == tail;
	}

	/**
	 * A method to return an array representing the elements in the Deque
	 * 
	 * @return an array containing all of the elements in this list in the correct order
	 */
	@Override
	public Object[] toArray() {
		//Object array is created with size equal to size of the Deque
		Object[] a = new Object[this.size()];
		//arraycopy method from System class is used to copy from the original array Deque
		//to the one that is to be returned
		if(head < tail) {
			System.arraycopy(elements, head, a, 0, size());
		} else if(head > tail) {
			int headPortionLength = elements.length - head;
			System.arraycopy(elements, head, a, 0, headPortionLength);
			System.arraycopy(elements, 0, a, headPortionLength, tail);
		}
		//The array is returned
		return a;
	}
	/**
	 * A method to return an array representing the elements in the Deque and with a specified runtime type.
	 * 
	 * @param a the array into which the elements of the Deque are to be stored
	 * @return an array containing the elements of the Deque
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		if(a.length < this.size())
			a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), this.size());
		//arraycopy method from System class is used to copy from the original array Deque
		//to the one that is to be returned
		if(head < tail) {
			System.arraycopy(elements, head, a, 0, size());
		} else if(head > tail) {
			int headPortionLength = elements.length - head;
			System.arraycopy(elements, head, a, 0, headPortionLength);
			System.arraycopy(elements, 0, a, headPortionLength, tail);
		}
		//If the Deque fits in the specified array with room to spare, the remaining elements
		//of the array are set to null
		if(a.length > this.size())
			a[this.size()] = null;
		//The array is returned
		return a;
	}

	@Override
	public boolean add(E e) {
		addLast(e);
		return true;
	}

	/**
	 * A method that inserts the specified element to the front of the Deque
	 * 
	 * @param e the element to be inserted
	 * @throws NullPointerException if e is null
	 */
	@Override
	public void addFirst(E e) {
		//If e is null, NullPointerException is thrown
		if(e == null) {
			throw new NullPointerException();
		}

		//head is set equal to head - 1 and the object at the
		//index of this value in the array elements is set equal to e
		elements[head = (head - 1)] = e;
		//If this causes head and tail to be the same, then the capacity of the array Deque must be increased
		if(head == tail) {
			//int p is set equal to the index of the head
			int p = head;
			//int n is set equal to the length of array elements
			int n = elements.length;
			//int r is set equal to n - p, which represents the
			//number of elements to the right of p
			int r = n - p; 

			//The new capacity is set equal to n shifted left once
			int newCapacity = n << 1;

			//Exception is thrown if this new capacity is less than 0 
			if(newCapacity < 0) {
				throw new IllegalStateException("Deque is too large");
			}    

			//A new Object array is created with the size equal to the new capacity
			Object[] a = new Object[newCapacity];

			//arraycopy method from System class is used to copy the elements from the original array into the new one
			System.arraycopy(elements, p, a, 0, r);
			System.arraycopy(elements, 0, a, r, p);

			//elements array (the array that represents the deque) is set equal to the new, larger array
			elements = (E[]) a;
			//head index is set equal to 0 and tail index is set equal to n
			head = 0;
			tail = n;
		}
	}

	/**
	 * A method that inserts the specified element at the end of the Deque
	 * 
	 * @param e the element to be inserted
	 * @throws NullPointerException if e is null
	 */
	@Override
	public void addLast(E e) {
		//If e is null, NullPointerException is thrown
		if(e == null) {
			throw new NullPointerException();
		}

		//The object at the index of the tail value is set equal to e
		elements[tail] = e;
		//checks to see if tail+1 is equal to head; if so, the capacity of the array Deque
		//must be increased
		if((tail + 1) == head)	{
			//int p is set equal to the index of the head
			int p = head;
			//int n is set equal to the length of array elements
			int n = elements.length;
			//int r is set equal to n - p, which represents the
			//number of elements to the right of p
			int r = n - p; 

			//The new capacity is set equal to n shifted left once
			int newCapacity = n << 1;

			//Exception is thrown if this new capacity is less than 0 
			if(newCapacity < 0) {
				throw new IllegalStateException("Deque is too large");
			}  

			//A new Object array is created with the size equal to the new capacity
			Object[] a = new Object[newCapacity];

			//arraycopy method from System class is used to copy the elements from the original array into the new one
			System.arraycopy(elements, p, a, 0, r);
			System.arraycopy(elements, 0, a, r, p);

			//elements array (the array that represents the deque) is set equal to the new, larger array
			elements = (E[]) a;
			//head index is set equal to 0 and tail index is set equal to n
			head = 0;
			tail = n;
		}
	}

	/**
	 * A method that returns true if the Deque contains the specified element
	 * 
	 * @param o Object to be searched for in the Deque
	 * @return true if the specified element is in the Deque
	 */
	@Override
	public boolean contains(Object o) {
		//If the specified Object is null, returns false automatically
		if(o == null) {
			return false;
		}
		//int i is set to the index of the head; to be used for counting purposes 
		int i = head;
		//Temporary E x is created
		E x;
		//while loop is used to test if any of the elements in the Deque are the same as the
		//Object o input
		while((x = elements[i]) != null) {
			//If there is a match, the method returns true
			if(o.equals(x)) {
				return true;
			}
			i++;
		}
		//If there is no match, the method returns false
		return false;
	}

	@Override
	public Iterator<E> descendingIterator() {
		//Class DescendingDequeIterator is created with methods for the Iterator
		/**
		 * This class is nearly identical to DequeIterator, using
		 * tail instead of head for initial c, and head instead of
		 * tail for f.
		 */
		class DescendingDequeIterator implements Iterator<E> {
			//int c is created to represent index of element to be returned by subsequent call to next. 
			private int c = head;
			//int f is created to represent index of tail to know when to stop iterator
			private int f = tail;
			//int last is created to represent index of the most recent call to next
			//this will be reset to -1 if the element is deleted
			private int last = -1;
			
			/**
			 * A method to check if there is another element
			 * 
			 * @return whether or not there is another element
			 */
			public boolean hasNext() {
				//Checks to see if c is equal to f to determine if there is another element
				return c != f;
			}
			
			/**
			 * A method to get the next element
			 * 
			 * @return the next element
			 * @throws NoSuchElementException if the next element does not exist
			 * @throws ConcurrentModificationException if inpermissible concurrent modification of an object
			 * 		   is detected 
			 */
			public E next() {
				//If the head index is equal to the tail index, there is no next element
				//and an exception is thrown
				if(c == f) {
					throw new NoSuchElementException();
				}
				//c is set equal to itself minus 1
				c--;
				//E result is created and set equal to the object at index c in the array elements 
				E result = elements[c];
				
				//A check is run to catch concurrent modifications
				if(head != f || result == null) {
					throw new ConcurrentModificationException();
				}
				
				//int last is set equal to c
				last = c;
				//E result is returned
				return result;
			}
			
			/**
			 * A method to remove an element
			 * 
			 * @throws IllegalStateException if int last is negative 
			 */
			public void remove() {
				//boolean delete is initialized to true
				boolean delete = true;
				//If any of the three conditions are met then the array deque is copied over itself
				//with the head missing, the element at head is set equal to null, and the boolean is set to false
				if((head < tail || tail == 0) || last >= head) {
					System.arraycopy(elements, head, elements, head + 1, last - head);
					elements[head] = null;
					head = (head + 1) & (elements.length - 1);
					delete = false;
				}
				
				//if last is negative then an exception is thrown
				if(last < 0) {
					throw new IllegalStateException();
				}
				
				//if boolean delete is false then c is increased by 1 and
				//int f is set equal to the index of the head
				if(!delete) {
					c++;
					f = head;
				}
				
				//int last is then set equal to negative 1
				last = -1;
			}
		}
		//The DescendingDequeIterator is returned
		return new DescendingDequeIterator();
	}

	/**
	 * A method to retrieve the first element of the Deque
	 * This method is essentially the same as the getFirst() method
	 * 
	 * @return the first element of the Deque
	 */
	@Override
	public E element() {
		return this.getFirst();
	}

	/**
	 * A method to retrieve (but not remove) the first element of the deque
	 * 
	 * @return the first element of the Deque
	 * @throws NoSuchElementException if the deque is empty
	 */
	@Override
	public E getFirst() {
		//Creates new E x and sets it equal to the element in the array deque
		//at the index equal to head
		E x = elements[head];

		//If x is null, then an exception is thrown
		if(x == null) {
			throw new NoSuchElementException();
		}

		//If not, x is simply returned
		return x;
	}

	/**
	 * A method to retrieve (but not remove) the last element of the deque
	 * 
	 * @return the last element of the Deque
	 * @throws NoSuchElementException if the deque is empty
	 */
	@Override
	public E getLast() {
		//Creates new E x and sets it equal to the element in the array deque
		//at the index equal to tail-1
		E x = elements[(tail - 1)];

		//If x is null, then an exception is thrown
		if(x == null) {
			throw new NoSuchElementException();
		}

		//If not, x is simply returned
		return x;
	}

	@Override
	public Iterator<E> iterator() {
		//Class DequeIterator is created with methods for the Iterator
		class DequeIterator implements Iterator<E> {
			//int c is created to represent index of element to be returned by subsequent call to next. 
			private int c = head;
			//int f is created to represent index of tail to know when to stop iterator
			private int f = tail;
			//int last is created to represent index of the most recent call to next
			//this will be reset to -1 if the element is deleted
			private int last = -1;
			
			/**
			 * A method to check if there is another element
			 * 
			 * @return whether or not there is another element
			 */
			public boolean hasNext() {
				//Checks to see if c is equal to f to determine if there is another element
				return c != f;
			}
			
			/**
			 * A method to get the next element
			 * 
			 * @return the next element
			 * @throws NoSuchElementException if the next element does not exist
			 * @throws ConcurrentModificationException if inpermissible concurrent modification of an object
			 * 		   is detected 
			 */
			public E next() {
				//E result is created
				E result;
				//If the head index is equal to the tail index, there is no next element
				//and an exception is thrown
				if(c == f) {
					throw new NoSuchElementException();
				}
				
				//A check is run to catch concurrent modifications
				if(tail != f || (result = elements[c]) == null) {
					throw new ConcurrentModificationException();
				}
				
				//int last is set equal to c because c was the last element accessed
				last = c;
				//c is increased by one as long as there is room in the array elements
				c = (c + 1) & (elements.length - 1);
				//E result is returned
				return result;
			}
			
			/**
			 * A method to remove an element
			 * 
			 * @throws IllegalStateException if int last is negative 
			 */
			public void remove() {
				//boolean delete is initialized to true
				boolean delete = true;
				//If any of the three conditions are met then the array deque is copied over itself
				//with the head missing, the element at head is set equal to null, and the boolean is set to false
				if((head < tail || tail == 0) || last >= head) {
					System.arraycopy(elements, head, elements, head + 1, last - head);
					elements[head] = null;
					head = (head + 1) & (elements.length - 1);
					delete = false;
				}
				
				//if last is negative then an exception is thrown
				if(last < 0) {
					throw new IllegalStateException();
				}
				
				//if the boolean delete is true, then c is reduced by 1
				if(delete) {
					c--;
				}
				
				//last is reset to negative 1
				last = -1;
				//int f is set equal to the index of the tail
				f = tail;
			}
		}
		//The DequeIterator is returned
		return new DequeIterator();
	}

	/**
	 * A method to insert the specified element to the end of the Deque
	 * Essentially the same as addLast(E e) and offerLast(E e)
	 * 
	 * @param e the element to be inserted
	 * @return true
	 * @throws NullPointerException if e is null
	 */
	@Override
	public boolean offer(E e) {
		return offerLast(e);
	}

	/**
	 * A method to insert the specified element to the start of the Deque
	 * Essentially the same as addFirst(E e)
	 * 
	 * @param e the element to be inserted
	 * @return true
	 * @throws NullPointerException if e is null
	 */
	@Override
	public boolean offerFirst(E e) {
		addFirst(e);
		return true;
	}

	/**
	 * A method to insert the specified element to the end of the Deque
	 * Essentially the same as addLast(E e)
	 * 
	 * @param e the element to be inserted
	 * @return true
	 * @throws NullPointerException if e is null
	 */
	@Override
	public boolean offerLast(E e) {
		addLast(e);
		return true;	
	}

	/**
	 * A method to retrieve (but not remove) the first element of the Deque
	 * Essentially the same as peekFirst()
	 * 
	 * @return the first element of the Deque, or null if Deque is empty
	 */
	@Override
	public E peek() {
		return this.peekFirst();
	}

	/**
	 * A method to retrieve (but not remove) the first element of the Deque
	 * 
	 * @return the first element of the Deque, or null if Deque is empty
	 */
	@Override
	public E peekFirst() {
		return elements[head];
	}

	/**
	 * A method to retrieve (but not remove) the last element of the Deque
	 * 
	 * @return the last element of the Deque, or null if Deque is empty
	 */
	@Override
	public E peekLast() {
		return elements[tail];
	}

	/**
	 * A method to retrieve and remove the first element of the Deque
	 * Essentially the same as pollFirst()
	 * 
	 * @return the first element of the Deque, or null if Deque is empty
	 */
	@Override
	public E poll() {
		return this.pollFirst();
	}

	/**
	 * A method to retrieve and remove the first element of the Deque
	 * 
	 * @return the first element of the Deque, or null if Deque is empty
	 */
	@Override
	public E pollFirst() {
		//int h is set equal to the index of the head
		int h = head;
		//E to be output is set equal to the Object at index h
		E result = elements[h]; 

		//Element is null if deque is empty
		//If statement to check for this and return null
		if(result == null) {
			return null;
		}

		//To remove the element, slot must be set to null
		elements[h] = null; 
		//Index of head is increased by one
		head++;
		//Result is returned
		return result;
	}

	/**
	 * A method to retrieve and remove the last element of the Deque
	 * 
	 * @return the last element of the Deque, or null if Deque is empty
	 */
	@Override
	public E pollLast() {
		//int t is set equal to index of tail - 1
		int t = (tail - 1);
		//E to be output is set equal to the Object at index t
		E result = elements[t];

		//Element is null if deque is empty
		//If statement to check for this and return null
		if(result == null) {
			return null;
		}

		//To remove the element, slot must be set to null
		elements[t] = null;
		//Index of tail is decreased by one by setting it equal to t
		tail = t;
		//Result is returned
		return result;
	}

	/**
	 * A method to pop an element from the stack represented by the deque
	 * Essentially the same as removeFirst()
	 * 
	 * @return the element at the front of this deque
	 * @throws NoSuchElementException if deque is empty
	 */
	@Override
	public E pop() {
		return this.removeFirst();
	}

	/**
	 * A method to push an element onto the stack represented by the deque
	 * Essentially the same as addFirst()
	 * 
	 * @param e the element to be pushed
	 * @throws NullPointerException if e is null
	 */
	@Override
	public void push(E e) {
		this.addFirst(e);
	}

	/**
	 * A method to retrieve and remove the head of the deque
	 * Essentially the same as removeFirst()
	 * 
	 * @return the head of the deque
	 * @throws NoSuchElementException if the deque is empty
	 */
	@Override
	public E remove() {
		return this.removeFirst();
	}

	/**
	 * A method to remove a single instance of the specified element from the deque
	 * Essentially the same as removeFirstOccurrence()
	 * 
	 * @param e element to be removed
	 * @return true if the element was present and then removed
	 */
	@Override
	public boolean remove(Object o) {
		return removeFirstOccurrence(o);
	}

	/**
	 * A method to retrieve and remove the first element of the deque
	 * 
	 * @return the first element in the deque
	 * @throws NoSuchElementException if the deque is empty
	 */
	@Override
	public E removeFirst() {
		//Uses the pollFirst method to get the first element of the deque and remove it
		E x = pollFirst();

		//Checks to see if element is null; if so, exception is thrown
		if(x == null) {
			throw new NoSuchElementException();
		}
		//polled element is returned
		return x;
	}

	/**
	 * A method to remove the first occurrence of the specified element in the deque 
	 * 
	 * @param e element to be removed if found
	 */
	@Override
	public boolean removeFirstOccurrence(Object o) {
		//If the object to be removed is null then there is no need to search for the first occurrence
		//and false is returned
		if(o == null) {
			return false;
		}

		//Otherwise we must search through the array deque for the first occurrence
		//int i is set equal to the index of the head
		int i = head;
		//Temporary E x is created
		E x;
		//As long as the element at elements[i] is not null the loop runs
		while((x = elements[i]) != null) {
			//If the element that is currently being checked is the same as the one being searched for,
			//then that element is removed and the loop ends
			if(o.equals(x)) {
				//If any of the three conditions are met then the array deque is copied over itself
				//with the head missing, the element at head is set equal to null, and false is returned
				if(head < tail || tail == 0 || i >= head) {
					System.arraycopy(elements, head, elements, head + 1, i - head);
					elements[head] = null;
					head++;
					return false;
				}
				//If none of the three conditions are met, then the index of the tail is reduced, the array deque
				//is copied over itself with the tail missing, and the element at tail is set equal to null, and
				//true is returned
				tail--;
				System.arraycopy(elements, i + 1, elements, i, tail - i);
				elements[tail] = null;
				return true;
			}
			//after the loop runs once, 1 is added to i to reach the next element
			i++;
		}
		//If no match was found (and therefore nothing was removed) then false is returned
		return false;
	}

	/**
	 * A method to retrieve and remove the last element in the deque
	 * 
	 * @return the last element of the deque
	 * @throws NoSuchElementException if the deque is empty
	 */
	@Override
	public E removeLast() {
		//Uses the pollLast() method to get the last element of the deque and remove it
		E x = pollLast();

		//Checks to see if element is null; if so, exception is thrown
		if(x == null) {
			throw new NoSuchElementException();
		}
		//polled element is returned
		return x;
	}

	/**
	 * A method to remove the last occurrence of the specified element in the deque 
	 * 
	 * @param e element to be removed if found
	 */
	@Override
	public boolean removeLastOccurrence(Object o) {
		//If the object to be removed is null then there is no need to search for the first occurrence
		//and false is returned
		if(o == null) {
			return false;
		}
		
		//Otherwise we must search through the array deque for the first occurrence
		//int i is set equal to the index of the tail - 1
		int i = tail - 1;
		//Temporary E x is created 
		E x;
		//As long as the element at elements[i] is not null the loop runs
		while((x = elements[i]) != null) {
			//If the element that is currently being checked is the same as the one being searched for,
			//then that element is removed and the loop ends
			if(o.equals(x)) {
				//If the element that is currently being checked is the same as the one being searched for,
				//then that element is removed and the loop ends
				if(head < tail || tail == 0 || i >= head) {
					System.arraycopy(elements, head, elements, head + 1, i - head);
					elements[head] = null;
					head = (head + 1);
					return false;
				}
				//If none of the three conditions are met, then the index of the tail is reduced, the array deque
				//is copied over itself with the tail missing, and the element at tail is set equal to null, and
				//true is returned
				tail--;
				System.arraycopy(elements, i + 1, elements, i, tail - i);
				elements[tail] = null;
				return true;
			}
			//after the loop runs once, 1 is subtracted from i to reach the next (technically previous) element
			i--;
		}
		//If no match was found (and therefore nothing was removed) then false is returned
		return false;
	}

	/**
	 * A method to return the number of elements in the deque
	 * 
	 * @return the number of elements in the deque
	 */
	@Override
	public int size() {
		//index of head is subtracted from index of tail to get the size
		return tail - head;
	}
}