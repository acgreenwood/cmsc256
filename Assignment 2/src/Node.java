import java.util.LinkedList;
import java.util.List;


/**
 * A Node class to create, modify, and access the nodes in the MyTree class
 * 
 * @author Andrew Greenwood
 *
 * @param <Integer> because the elements in the n-ary tree are supposed to be integers
 */
@SuppressWarnings("hiding")
public class Node<Integer> {       
	//Each node has an integer assigned to it
	int number;     
	//Each node has a LinkedList of its children
	List<Node<Integer>> children;
	//Each node has a single parent
	Node<Integer> parent;
	//Each node has one or zero nodes that come "after" it and share the same parent
	Node<Integer> next;
	
	/**
	 * Default Constructor
	 * 
	 * Creates a node with a key of zero and no parent
	 */
	public  Node() {        
		this(0, null);    
	}   
	
	/**
	 * Constructor to create a node from a preexisting node
	 * 
	 * @param node the preexisting node to be used
	 */
	public Node(Node<Integer> node) {
		//The key is set equal to the value of the node
		this.number = (int) node.getValue();
		//A new LinkedList is created to represent this node's children
		children = new LinkedList<Node<Integer>>();
	}
	
	/**
	 * Constructor to create a node with no specified parent
	 * 
	 * @param number integer value of key
	 */
	public Node(int number) {
		//The key is set equal to the input
		this.number = number;
		//A new LinkedList is created to represent this node's children
		this.children = new LinkedList<Node<Integer>>();
	}
	
	/**
	 * Main Constructor to create a new node with specified key and parent
	 * 
	 * @param n integer value of key
	 * @param parentNode parent node
	 */
	public  Node(int n,  Node<Integer> parentNode) {
		//Key and parent node set equal to inputs
		this.number  =  n;       
		this.parent =  parentNode;
		//A new LinkedList is created to represent this node's children
		children = new LinkedList<Node<Integer>>();
		//If this node's parent has other children, set the right-most child's next node to this one
		if(parentNode.getNumOfChildren() > 0) {
			parentNode.getChildAt(parentNode.getNumOfChildren()-1).next = this;
		}
	}    
	
	/**
	 * A method to get the key of a node
	 * 
	 * @return the key of the node
	 */
	public  Object getValue() {        
		return  this.number;    
	}    

	/**
	 * A method to set the key of a node
	 * 
	 * @param newNum the desired key of the node
	 */
	public void  setValue(int newNum)  {          
		this.number  =  newNum;    
	}    
	
	/**
	 * A method to add a child to a node
	 */
	public void addChild(Node<Integer> child) {
		//Sets the child's parent to this node
		child.setParent(this);
		//Adds the child to this node's LinkedList of children
		children.add(child);
		//If this node has other children, set the right-most node's next node to the child
		if(this.getNumOfChildren() > 0) {
			this.getChildAt(this.getNumOfChildren()-1).next = child;
		}
	}

	public void addChildAt(int index, Node<Integer> child) {
		//Sets the child's parent to this node
		child.setParent(this);
		//Adds the child to this node's LinkedList of children
		this.children.add(index, child);
		//If this node has other children, set the node at index's next node to the child
		if(this.getNumOfChildren() > 0) {
			this.getChildAt(index).next = child;
		}
	}
	
	/**
	 * A method to input a LinkedList of children to add to a node
	 * 
	 * @param cl the linkedlist to be added
	 */
	public void setChildren(List<Node<Integer>> cl) {
		//A for loop is used to set the parent of each element in the list to this node
		for (Node<Integer> child : cl) {
			child.setParent(this);
		}
		//This node's children list is set equal to the one input
		this.children = cl;
	}
	
	/**
	 * A method to remove all children from a node 
	 */
	public void removeChildren() {
		this.children.clear();
	}
	
	/**
	 * A method to remove a specific child from a node
	 * 
	 * @param index The index of the child to be removed
	 * @return	The node that was removed
	 */
	public Node<Integer> removeChildAt(int index) {
		return children.remove(index);
	}
	
	/**
	 * A method to remove a specific element from the list of children of
	 * a node if that element is a child of the node
	 * 
	 * @param childToBeDeleted The element to be removed
	 */
	public void removeThisIfItsAChild(Node<Integer> childToBeDeleted)
	{
		List <Node<Integer>> list = getChildren();
		list.remove(childToBeDeleted);
	}

	/**
	 * A method to get the parent of a node
	 * 
	 * @return the parent of this node
	 */
	public Node<Integer> getParent() {
		return this.parent;
	}
	
	/**
	 * A method to set the parent of a node
	 * 
	 * @param parent the desired parent of this node
	 */
	public void setParent(Node<Integer> parent) {
		this.parent = parent;
	}
	
	/**
	 * A method to get the list of children of a node
	 * 
	 * @return the list of children of this node
	 */
	public List<Node<Integer>> getChildren() {
		return this.children;
	}
	
	/**
	 * A method to get the number of children a node has
	 * 
	 * @return The size of the list of children of this node
	 */
	public int getNumOfChildren() {
		return this.children.size();
	}
	
	/**
	 * A method to get the child at a specified index
	 * 
	 * @param index The index of the child
	 * @return The child at the specified index
	 */
	public Node<Integer> getChildAt(int index) {
		return children.get(index);
	}
	
	/**
	 * A method to see if a node has children
	 * 
	 * @return whether or not this node has children
	 */
	public boolean hasChildren() {
		return this.getNumOfChildren() != 0;
	}
	
	/**
	 * A method to see if two nodes are equal
	 * 
	 * @return whether or not the input node is equal to this node
	 */
	@Override
	public boolean equals(Object obj) {
		//If the input node is null then return false
		if (null == obj) {
			return false;
		}
		//else check if they are equal and if so return true
		if (obj instanceof Node) {
			if (((Node<?>) obj).getValue().equals(this.number))
				return true;
		}
		//else return false
		return false;
	}
	
	/**
	 * A method to return a string containing the key of a node
	 * 
	 * @return a string containing the key of this node
	 */
	@Override
	public String toString() {
		return ""+this.number;
	}
}
