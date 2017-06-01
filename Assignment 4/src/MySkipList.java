import java.util.*;

/**
 * A class MySkipList.java that implements the skip list data structure using linked list
 * 
 * @author Andrew Greenwood
 *
 */
public class MySkipList {
	//head and tail nodes (positive and negative infinity) for each layer
	public SkipListNode head;
	public SkipListNode tail;
	
	//layers to represent the top and bottom layer
	//top layer only contains positive and negative infinity
	//bottom layer contains all nodes
	public SkipListLayer base;
	public SkipListLayer empty;
	
	//A linked list is created to store the layers
	private LinkedList<SkipListLayer> layerList;
	
	public MySkipList() {
		//head and tail are set equal to smallest and largest integer values 
		//to representnegative and positive infinity
		head = new SkipListNode("-infinity", Integer.MIN_VALUE);
		tail = new SkipListNode("+infinity", Integer.MAX_VALUE);
		//new nodes head and tail nodes are also created for the base layer
		SkipListNode h2 = new SkipListNode("-infinity", Integer.MIN_VALUE);
		SkipListNode t2 = new SkipListNode("+infinity", Integer.MAX_VALUE);
		
		//the nodes are added to their respective layers
		empty = new SkipListLayer();
		empty.setLayer(0);
		empty.addNode(head);
		empty.addNode(tail);
		empty.nodeSort();
		
		base = new SkipListLayer();
		base.setLayer(9);
		base.addNode(h2);
		base.addNode(t2);
		base.nodeSort();
		
		//the layerList containing all the SkipListLayers is initiated
		layerList = new LinkedList<SkipListLayer>();
		//and the top layer is added
		layerList.add(empty);
		
		//then 8 new layers are added
		for(int i = 1; i < 9; i++) {
			layerList.add(i, new SkipListLayer());
			layerList.get(i).addNode(new SkipListNode("-infinity", Integer.MIN_VALUE));
			layerList.get(i).addNode(new SkipListNode("+infinity", Integer.MAX_VALUE));
		}
		
		//and last the base layer is added for a total of 10
		layerList.add(base);
		
		//then the up and down pointers are set for the head and tail nodes
		for(int j = 1; j < 9; j++) {
			layerList.get(j).head.up = layerList.get(j-1).head;
			layerList.get(j).head.down = layerList.get(j+1).head;
			layerList.get(j).tail.up = layerList.get(j-1).tail;
			layerList.get(j).tail.down = layerList.get(j+1).tail;
		}
		
		empty.head.down = layerList.get(1).head;
		empty.tail.down = layerList.get(1).tail;
		base.head.up = layerList.get(8).head;
		base.tail.up = layerList.get(8).tail;
	}
	
	/**
	 * A method to insert a node with set value into the Skip List
	 * 
	 * @param i the value of the node to be inserted
	 */
	public void skipInsert(int i) {
		//give up if a node with that value is already there
		if(inSkipList(i)) {
			return;
		}
		//if not, create a new node and run the skipInsert method that requires a node input on it
		SkipListNode n = new SkipListNode(i+"", i);
		skipInsert(n);
	}
	
	/**
	 * The "main" skipInsert method, takes a node input instead of a value input
	 * 
	 * @param n the node to be inserted
	 */
	public void skipInsert(SkipListNode n) {
		//if there is already a node with this node's value, give up
		if(inSkipList(n.getValue())) {
			return;
		}
		//else choose a random layer to insert the node at
		int l = (int) (9 * Math.random() + 1);
		
		//and of course insert it in the base layer
		base.addNode(n);
		base.nodeSort();

		//for loop to add the node to layers
		//because the layers count up as you go down (empty = layer 0, base = layer 9) we start at
		//l and go up in indices in the layerList
		for(int j = l; j < 9; j++) {
			layerList.get(j).addNode(n);
		}
		
		//another for loop to set the up and down pointers for the new node
		//because nodes are inserted at the end, it simply uses the last node in each layer
		for(int k = l; k < 9; k++) {
			layerList.get(k).getLastNode().down = layerList.get(k+1).getLastNode();
			layerList.get(k+1).getLastNode().up = layerList.get(k).getLastNode();
		}
		
		//then all the layers are sorted
		for(int m = l; m < 10; m++) {
			layerList.get(m).nodeSort();
		}
	}
	
	/**
	 * A method to remove all nodes with given value from the SkipList
	 * 
	 * @param i the value of the nodes to be removed
	 * @return whether or not any nodes were removed
	 */
	public boolean skipRemove(int i) {
		//if a node with value i is in the list, then find and remove it
		if(inSkipList(i)) {
			//for loop to remove the node(s) with value i
			for(int k = 0; k < 10; k++) {
				//removes the node first
				layerList.get(k).removeValue(i);
				//then sorts the layer
				layerList.get(k).nodeSort();
			}
			return true;
		}
		//if there is no node with value i then nothing was removed and false is returned
		else {
			return false;
		}
	}
	
	/**
	 * Simple method to return the index (in the base layer)
	 * 
	 * @param i value of the node to be searched for
	 * @return the nodes index in the base layer 
	 */
	public Integer skipSearch(int i) {
		//uses contains() method from SkipListLayer to search base layer
		if(base.contains(i)) {
			return base.indexOf(i);
		}
		//if it's not in the base layer then it's not in any layer so return null
		else {
			return null;
		}
	}
	
	/**
	 * Simple method to return whether or not a node with value i is in the SkipList
	 * 
	 * @param i the value to be searched for
	 * @return whether or not a node with value i is in the SkipList
	 */
	public boolean inSkipList(int i) {
		//also uses contains() method from SkipListLayer to search base layer
		if(base.contains(i)) {
			return true;
		}
		//if it's not in the base layer then it's not in any layer so return false
		else {
			return false;
		}
	}
}