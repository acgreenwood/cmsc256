import java.util.*;

/**
 * A program MyTree.java which uses a linked List structure to represent an
 * n-ary tree in which the keys are integers.
 * 
 * @author Andrew Greenwood
 *
 * @param <Integer> because the elements in this n-ary tree are supposed to be integers
 */
@SuppressWarnings("hiding")
public class MyTree<Integer> extends Node<Integer> {
	//Root node of the tree
	private Node<Integer> root;
	//A list of all the nodes in the tree to make some stuff easier
	//Root is index 0, other than that order does not matter in this list
	private List<Node<Integer>> allNodes;

	/**
	 * A method to add/create a root node for this tree
	 * 
	 * @param r the key value of the tree
	 * @return	this node (the root)
	 */
	public Node<Integer> addRoot(int r) {
		Node<Integer> parent = new Node<Integer>(r, null);
		setRoot(parent);
		//adds this node to first position in allNodes list
		allNodes.add(0, parent);
		return parent;
	} 
	
	/** 
	 * A method to add a child node to the right-most position
	 * 
	 * @param node the parent node which will have a new child node 
	 * @param c the key of the child
	 * @return the new child node 
	 * */ 
	public Node<Integer> addChild(Node<Integer> node, int c) {
		Node<Integer> newNode = new Node<Integer>(c, node);
		//adds this node to the end of the allNodes list
		allNodes.add(newNode);
		return newNode;
	} 
	
	/**
	 * A method to get the root node of this tree 
	 * 
	 * @return the root node of this tree
	 */
	public Node<Integer> getRoot() {
		return root;
	}
	
	/**
	 * A method to get the parent node of a node
	 * 
	 * @param n the node whose parent you wish to identify
	 * @return	the parent node of the input node
	 */
	public Node<Integer> getParentNode(Node<Integer> n) {
		return n.getParent();
	}
	
	/**
	 * A method to get a list of the child nodes of a node
	 * 
	 * @param n the node whose children you wish to identify
	 * @return a list of the children of the input node
	 */
	public List<Node<Integer>> getChildNodes(Node<Integer> n) {
		return n.getChildren();
	}
	
	/**
	 * A method to set or replace the root of this tree
	 * 
	 * @param newRoot the node to be the new root
	 */
	public void setRoot(Node<Integer> newRoot) {
		this.root = newRoot;
		//Removes previous root from allNodes list and adds new one in its place
		allNodes.remove(0);
		allNodes.add(0, newRoot);
	}

	/**
	 * A method to determine whether or not the tree is complete
	 * 
	 * @return whether or not the tree is complete
	 */
	public boolean isComplete(){
		//k represents the n in n-ary tree
		//In other words it is the maximum number of children held by a single node
		int k = 0;
		//A for loop to find k
		for(Node<Integer> n : allNodes) {
			if(n.getNumOfChildren() > k) {
				k = n.getNumOfChildren();
			}
		}
		//Another for loop to check if the tree is complete
		for(Node<Integer> o : allNodes) {
			if(o.hasChildren() && o.getChildAt(0).hasChildren()) {
				//if it is found that the tree is not complete then false is returned
				if(o.getNumOfChildren() != k) {
					return false;
				}
			}
		}
		//else true is returned
		return true;
	}

	/**
	 * A method to determine whether or not the tree is full
	 * 
	 * @return whether or not the tree is full
	 */
	public boolean isFull() {
		//k represents the n in n-ary tree
		//In other words it is the maximum number of children held by a single node
		int k = 0;
		//A for loop to find k
		for(Node<Integer> n : allNodes) {
			if(n.getNumOfChildren() > k) {
				k = n.getNumOfChildren();
			}
		}
		//Another for loop to check if the tree is full
		for(Node<Integer> o : allNodes) {
			//if it is found that a node has any number of children other than 0 or k then false is returned
			if(o.hasChildren() && o.getNumOfChildren() != k) {
				return false;
			}
		}
		//else true is returned
		return true;
	}
	
	/**
	 * A method to print the preorder traversal result of the entire tree
	 */
	public void preOrderTraversal() {
		preOrderTraversal(root);
	}
	
	/** 
	 * A method to print the preorder traversal result of the tree or specified subtree
	 * 
	 * @param r the root node of the subtree to be traversed 
	 */ 
	public void preOrderTraversal(Node<Integer> r) { 
		//if the root node is null then never mind
		if(r == null) {
			return;
		}
		
		System.out.print(r.toString()+", ");
		//recursive for loop to traverse the tree
		for(Node<Integer> child : r.getChildren()) {
			preOrderTraversal(child);
		}
	} 
	
	/**
	 * A method to print the postorder traversal result of the entire tree
	 */
	public void postOrderTraversal() {
		postOrderTraversal(root);
	}
	/** 
	 * A method to print the postorder traversal result of the tree or specified subtree 
	 * 
	 * @param r the root node of the subtree to be traversed 
	 */
	public void postOrderTraversal(Node<Integer> r) {  
		//if the root node is null then never mind
		if(r == null) {
			return;
		}
		//recursive for loop to traverse the tree
		for(Node<Integer> child : r.getChildren()) {
			postOrderTraversal(child);
		}
		
		System.out.print(r.toString()+", ");
	} 

	/**
	 * A method to print the breadth-first traversal result of the entire tree
	 */
	public void breathFirstTraversal() {
		breadthFirstTraversal(root);
	}
	
	/**
	 * A method to print the breadth-first traversal result of the tree or specified subtree
	 *  
	 * @param r the root node of the subtree to be traversed 
	 */ 
	public void breadthFirstTraversal(Node<Integer> r) {  
		//if root node is null then never mind
		if(r == null) {
			return;
		}
		//Make a queue of all the nodes
		Queue<Node<Integer>> queue = new LinkedList<Node<Integer>>();
	    queue.clear();
	    queue.add(r);
	    //while loop to sort through the queue
	    //queue checks for next nodes and then children to get through each
	    //level of the tree
	    while(!queue.isEmpty()) {
	        Node<Integer> node = queue.remove();
	        System.out.print(node.getValue() + ", ");
	        //if the node has a next node then keep going on same level
	        if(node.next != null) {
	        	queue.add(node.next);
	        }
	        //if the node has a child node (but no next node) then go down to the next level
	        if(node.getChildAt(0) != null) {
	        	queue.add(node.getChildAt(0));
	        }
	    }
	}
}
