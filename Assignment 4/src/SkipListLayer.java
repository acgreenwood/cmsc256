import java.util.LinkedList;

/**
 * A class SkipListLayer.java to store the data for each layer of a SkipList within a linked list
 * 
 * @author Andrew Greenwood
 */
public class SkipListLayer {

	//an int layer is created to store the value of the layer within the skip list of the SkipListLayer
	public int layer;

	//Two nodes head and tail are created, these should basically always be +/- infinity
	public SkipListNode head;
	public SkipListNode tail;

	//This is the actual LinkedList that stores the nodes
	private LinkedList<SkipListNode> layerList;

	/**
	 * The constructor for the SkipListLayer
	 * 
	 * Creates an empty linked list with head and tail defaulting to null and layer defaulting to -1
	 */
	public SkipListLayer() {
		layerList = new LinkedList<SkipListNode>();

		head = null;
		tail = null;

		layer = -1;
	}

	/**
	 * Simple method to set the layer (within the skiplist itself) of the SkipListLayer
	 * 
	 * @param l the new layer of the SkipListLayer
	 */
	public void setLayer(int l) {
		layer = l;
	}
	
	/**
	 * Simple method to return the layer (within the skiplist itself) of the SkipListLayer
	 * 
	 * @return the layer
	 */
	public int getLayer() {
		return layer;
	}

	/**
	 * Simple method to return the last node in the LinkedList representing the SkipListLayer
	 * 
	 * @return the last node in the LinkedList layerList
	 */
	public SkipListNode getLastNode() {
		return layerList.get(this.size()-1);
	}

	/**
	 * Simple method to add a new SkipListNode to this layer
	 * 
	 * @param n The SkipListNode
	 */
	public void addNode(SkipListNode n) {
		layerList.add(n);
		//sorts the LinkedList after inserting a new node
		this.nodeSort();
	}

	/**
	 * A method to determine whether or not the layer contains a node with value i
	 * 
	 * @param i the value to be searched for
	 * @return whether or not a SkipListNode with value i is in the layer
	 */
	public boolean contains(int i) {
		//for loop to check each node's value to see if it matches i
		for(int k = 0; k < this.size(); k++) {
			if(layerList.get(k).getValue() == i) {
				//stops when a match is found
				return true;
			}
		}
		return false;
	}

	/**
	 * A method to determine the index of a node with value i
	 * 
	 * @param i the value to be searched for
	 * @return the index of the node with value i; or if there is no node with value i, -1
	 */
	public int indexOf(int i) {
		int index = -1;
		//for loop to check each node's value to see if it matches i
		for(int k = 0; k < this.size(); k++) {
			if(layerList.get(k).getValue() == i) {
				index = k;
				return index;
			}
		}
		return index;
	}
	
	/**
	 * A method to remove a node with value i from the layer
	 * 
	 * @param i the value of the node to be removed
	 */
	public void removeValue(int i) {
		int removeIndex;
		//for loop to check each node's value to see if it matches i
		for(int k = 0; k < this.size(); k++) {
			if(layerList.get(k).getValue() == i) {
				//when a match is found, remove the node and return
				removeIndex = k;
				layerList.remove(removeIndex);
				return;
			}
		}
	}
	
	/**
	 * A method to sort the nodes in the layer from lowest to highest
	 */
	public void nodeSort() {
		//if the list is of length 1, then it is already sorted (but it shouldn't be)
		if (layerList.size() <= 1) {
			head = layerList.getFirst();
			tail = layerList.getFirst();
			return;
		}	
		
		//otherwise, sort from lowest to highest by comparing each pair of nodes
		for(int i = 0; i < layerList.size(); i++) {
			if(layerList.get(i).getValue() > layerList.get(i+1).getValue()) {
				SkipListNode temp = layerList.get(i);
				layerList.set(i, layerList.get(i+1));
				layerList.set(i+1, temp);
				i = 0;
			}
		}

		//then set the left and right variables for all nodes to match the new order of the list
		layerList.get(0).left = null;
		layerList.get(0).right = layerList.get(1);

		layerList.getLast().right = null;
		layerList.getLast().left = layerList.get(layerList.size()-2);

		for(int j = 1; j < layerList.size()-1; j++) {
			layerList.get(j).left = layerList.get(j-1);
			layerList.get(j).right = layerList.get(j+1);
		}
		
		//and set the head and tail to the first and last nodes
		head = layerList.getFirst();
		tail = layerList.getLast();
	}
	
	/**
	 * Simple method to return the size of the LinkedList representing the layer
	 * 
	 * @return the number of nodes in the linked list
	 */
	public int size() {
		return layerList.size();
	}
}