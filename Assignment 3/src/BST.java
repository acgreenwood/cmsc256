/**
 * A class BST.java that contains methods for the creation and modification
 * of a binary search tree (BST)
 * 
 * @author Andrew Greenwood
 *
 * @param <Key> the key of each Node in a binary tree
 */

@SuppressWarnings("all")
public class BST<Key extends Comparable<Key>> {
	
	/**
	 * A class Node that contains instance variables and constructors for the Nodes of the BST
	 * 
	 * @author Andrew Greenwood
	 */
	private class Node {
		//The left child Node of a Node
		Node left;
		//The right child Node of a Node
		Node right;
		//The data contained within the Node
		int value;
		//The key by which the tree is sorted
		Key key;
		
		/**
		 * A constructor to create a Node without a designated key
		 * 
		 * @param v the desired data to be contained within the Node
		 */
		private Node(int v) {
			value = v;
			left = null;
			right = null;
			key = null;
			
		}
		
		/**
		 * A constructor to create a Node with a designated key
		 * 
		 * @param k the key
		 * @param v	the desired data to be contained within the Node
		 */
		private Node(Key k, int v) {
			value = v;
			key = k;
			left = null;
			right = null;
		}
	}

	//the root Node of the BST
	private Node root;
	
	/**
	 * A constructor to create a new BST
	 */
	public BST() {
		this.root = null;
	}
	
	/**
	 * A method to determine whether the input BST is a valid BST or not
	 * 
	 * @param b the input BST
	 * @return	whether the input is a valid BST or not
	 */
	boolean isBST(BST b) {
		return isBST(b.root, null, null);
	}
	
	/**
	 * A recursive method to determine whether the subtree is a valid BST or not
	 * 
	 * @param x the root Node of the subtree
	 * @param min	the minimum key of the search 
	 * @param max	the maximum key of the search
	 * @return	whether the subtree is a valid BST or not
	 */
	public boolean isBST(Node x, Key min, Key max) {
		//if the root Node is null return true
		if (x == null) return true;
		//if a key has a value less than min return false
		if (min != null && x.key.compareTo(min) <= 0) return false;
		//if a key has a value greater than max return false
		if (max != null && x.key.compareTo(max) >= 0) return false;
		//else continue with the recursion in both left and right subtrees
		return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
	} 
	
	/**
	 * A method to find an element in the BST
	 * 
	 * @param id the value of the element to be found
	 * @return	whether or not the element is in the tree
	 */
	public boolean find(int id) {
		//Starts the search at the root
		Node c = root;
		//As long as the Node is not null
		while(c != null) {
			//return true if the value of the Node being searched equals what we're looking for
			if(c.value == id) {
				return true;
			}
			//if the value of the current Node is larger than what we're looking for, search the left subtree
			else if(c.value > id) {
				c = c.left;
			}
			//else search the right subtree
			else {
				c = c.right;
			}
		}
		//return false if the matching element was never found
		return false;
	}

	/**
	 * A method to remove an element from the BST
	 * 
	 * @param id the element to be removed
	 * @return	whether or not the element was found and removed
	 */
	public boolean delete(int id) {
		//Two temporary Nodes are created and set equal to the root Node of the BST
		Node parent = root;
		Node c = root;
		//a boolean variable isLeftChild is initialized to false
		boolean isLeftChild = false;
		//as long as the value of the Node being checked is not the same as the one being searched for
		while(c.value != id) {
			//set Node parent equal to Node c
			parent = c;
			//if c's value is greater than what we're looking for
			if(c.value > id) {
				//set the boolean to true
				isLeftChild = true;
				//and set c equal to its left child
				c = c.left;
			}
			//if not (i.e. c.value < id)
			else {
				//set the boolean to false
				isLeftChild = false;
				//and set c equal to its right child
				c = c.right;
			}
			//if we reach the point where c is null (i.e. Node doesn't exist then we did not find what we were looking
			//to delete and so false is returned and the method ends
			if(c == null) {
				return false;
			}
		}
		
		//If we reach this point then the Node to be deleted has been found
		//First case: Node to be deleted has no children
		if(c.left == null && c.right == null) {
			//if this is the case AND the Node that is to be deleted is the root
			//set root equal to null -- the tree now essentially is empty
			if(c == root) {
				root = null;
			}
			//if the Node that is to be deleted is a left child
			if(isLeftChild) {
				//then its left parent is set to null
				parent.left = null;
			}
			//if not then it is a right child
			else {
				//and so its right parent is set to null
				parent.right = null;
			}
		}
		
		//Second Case: Node to be deleted has a single child
		//This means that one of its children will be null -- however if both of its children are null
		//then it would have been caught by the previous if statement so this can be split into two parts
		//Second Case Part One: c has no right child
		else if(c.right == null) {
			//if c is the root, then the root is set equal to c's left child
			if(c == root) {
				root = c.left;
			}
			//if c is a left child, then set c's parent's left child to c's left child
			else if(isLeftChild) {
				parent.left = c.left;
			}
			//if c is a right child, set c's parent's right child to c's left child
			else
			{
				parent.right = c.left;
			}
		}
		//Second Case Part Two: c has no left child
		else if(c.left==null) {
			//if c is the root, then the root is set equal to c's right child
			if(c == root){
				root = c.right;
			}
			//if c is a left child, then set c's parent's left child to c's right child
			else if(isLeftChild)
			{
				parent.left = c.right;
			}
			//if c is a right child, then set c's parent's right child to c's right child
			else
			{
				parent.right = c.right;
			}
		}
		
		//Third (and final) Case: Node to be deleted has two children
		else if(c.left != null && c.right != null) {
			//Node s is found using the getSuccessor method (see below)
			//s is essentially replacing c
			Node s = getSuccessor(c);
			//if c is the root, then s is the new root
			if(c == root) {
				root = s;
			}
			//if c is a left child, then set s equal to c's parent's left child
			else if(isLeftChild) {
				parent.left = s;
			}
			//if c is a right child, then set s equal to c's parent's right child
			else {
				parent.right = s;
			}
			//finally, set s's left child equal to c's left child 
			s.left = c.left;
		}
		//if this statement is reached it means that the Node must have been found and therefore deleted
		//and so true is returned because the Node was successfully deleted
		return true;		
	}

	/**
	 * A method to find the "successor" of a Node, i.e. the Node that would replace it if it was deleted
	 * 
	 * @param Node the Node whose successor is to be found
	 * @return the successor Node
	 */
	public Node getSuccessor(Node Node) {
		//First we are trying to find the farthest left Node in the tree
		//Nodes s (for successor) and parent are created and set to null
		Node s = null;
		Node parent = null;
		//while Node child is created and set equal to the right child of the input Node 
		Node child = Node.right;
		//As long as this Node child is not null
		while(child != null){
			//set Node parent equal to s
			parent = s;
			//and set Node s equal to child
			s = child;
			//then set child equal to its left child
			child = child.left;
		}
		//Now check if s is the input Node's right child because we know it is not the left child 
		//If it is the right child, add it to the left of parent
		if(s != Node.right) {
			parent.left = s.right;
			s.right = Node.right;
		}
		//then return the successor (s)
		return s;
	}

	/**
	 * A method to insert a new Node with the given value
	 * 
	 * @param id the value of the new Node
	 */
	public void insert(int id) {
		//A new Node is created with the input value as its value
		Node newNode = new Node(id);
		//if there is no root, then this is the only Node in the tree and therefore the root
		if(root == null) {
			//so the root is set equal to the new Node and the insertion is done
			root = newNode;
			return;
		}
		
		//if not, a temporary Node c is created and set equal to the root
		Node c = root;
		//and a temporary Node parent is created and set to null
		Node parent = null;
		//infitite while loop to run until the method is ended with a return statement 
		while(true) {
			//set the Node parent equal to Node c
			parent = c;
			//If the input value for the new Node is less than c's value
			if(id < c.value) {				
				//set c equal to its left subchild and try again there
				c = c.left;
				//but if c is now null (because "previous c"'s left subchild did not exist)
				if(c == null) {
					//then set the left subchild of parent (which is previous c) to the new Node
					parent.left = newNode;
					//and return to end the loop
					return;
				}
			}
			//else, if the input value for the new Node is greater than c's value
			else {
				//set c equal to its right subchild and try again there
				c = c.right;
				//but if c is now null (because "previous c"'s right subchild did not exist)
				if(c == null) {
					//then set the right subchild of parent (which is previous c) to the new Node
					parent.right = newNode;
					//and return to end the loop
					return;
				}
			}
		}
	}

	/**
	 * A recursive method to display the values of the given subtree using inorder traversal
	 *
	 * @param root the root of the subtree to display
	 */
	public void display(Node root) {
		//as long as the input Node is not null
		if(root != null) {
			//display the left subtree
			display(root.left);
			//print the value
			System.out.print(" " + root.value);
			//then display the right subtree
			display(root.right);
		}
	}
}