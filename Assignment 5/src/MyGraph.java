import java.util.*;

/**
 * A class MyGraph.java that implements Dijkstra's and Prim's Algorithms, as well as all
 * other methods necessary for their implementation.
 * 
 * @author Andrew Greenwood
 *
 */
public class MyGraph{
	
	//A LinkedList is created to store all the nodes of the graph
	private LinkedList<Integer> nodeList;
	
	//An int is created to store the number of nodes in the graph
	private int nodeCount;
	
	//A LinkedList of LinkedLists is created to store the edges of the graph
	private LinkedList<LinkedList<Integer>> adjList;
	
	/**
	 * Constructor to create a new graph with nothing in it.
	 */
	public MyGraph() {
		//instantiates the LinkedLists and sets nodeCount to 0
		nodeList = new LinkedList<Integer>();
		adjList = new LinkedList<LinkedList<Integer>>();
		nodeCount = 0;
	}
	
	/** 
	 * A method to add a new vertex into the graph. 
	 * If the new vertex already exists in the graph then return false.
	 * 
	 * @param newNode the vertex number of the new node. 
	 * @return true if the vertex number >=0 and is not used; otherwise false.
	 */
	public boolean addVertex(int newNode) {
		//returns false if there is already a node with this value in the graph
		if(nodeList.contains(newNode)) {
			return false;
		}
		//returns false if the desired node has a negative value
		else if(newNode < 0) {
			return false;
		}
		//else, adds the new node and increases nodecount
		//and returns true
		else {
			nodeList.add(newNode);
			adjList.add(newNode, new LinkedList<Integer>());
			nodeCount++;
			return true;
		}
		
	}
	
	/** 
	 * A method to add a new edge into the graph.
	 * 
	 * @param source the vertex number of the source node.
	 * @param destination the vertex number of the destination node.
	 * @param weight the weight of the edge.
	 * @return true if the edge satisfies the following criteria: 
	 * 1. valid source node. 2. valid destination node. 3. weight > 0.
	 * 4. No existing edge sharing the same source and destination;
	 * otherwise false.
	 */
	public boolean addEdge(int source, int destination, int weight) {
		//if either the source or destination are not present in the nodeList, return false as the edge cannot be created
		if(!nodeList.contains(source) || !nodeList.contains(destination)) {
			return false;
		}
		//if the weight is not a positive nonzero integer, return false
		else if(weight <= 0) {
			return false;
		}
		//if there is already an edge from source to destination, return false
		else if(adjList.get(source).get(destination) != null) {
			return false;
		}
		//if all criteria are satisfied, add the edge and return true
		else {
			adjList.get(source).add(destination, weight);
			adjList.get(destination).add(source, weight);
			return true;
		}
	}
	
	/** 
	 * A method to print the shortest distances and shortest paths of the graph.
	 * 
	 * @param source the vertex number of the source node.
	 * @return true if the source node is valid; otherwise false.
	 */
	public boolean dijkstra(int source){
		//if the source node is not in the nodeList, return false
		if(!nodeList.contains(source)) {
			return false;
		}
		//saves the index of the source node for future reference
		int sourceIndex = nodeList.indexOf(source);
		//A LinkedList is created to store the unvisited nodes, and set equal to the list of nodes
		LinkedList<Integer> unvisited = nodeList;
		//the source node is removed from the unvisited list
		unvisited.removeFirstOccurrence(source);
		//Another LinkedList is created to store distances from source node
		LinkedList<Integer> distance = new LinkedList<Integer>();
		//of course, the distance from source node to itself is 0
		distance.add(sourceIndex, 0);
		
		//A for loop is used to set the distance from source node to all other nodes to MAX_VALUE initially
		for(int i = 0; i < nodeCount; i++) {
			if(i != sourceIndex) {
				distance.add(i, Integer.MAX_VALUE);
			}
		}
		
		//start at the source, so currentNode is set to the index of source
		int currentNode = sourceIndex;
		
		//run loop as long as there are unvisited nodes
		while(!unvisited.isEmpty()) {
			//Iterator is used to iterate the adjacency list that stores distances to other nodes
			Iterator<Integer> itr = adjList.get(currentNode).listIterator();
			//int closest defaults to max value
			int closest = Integer.MAX_VALUE;
			int output;
			//as long as there is a next in the Iterator
			while(itr.hasNext()) {
				//if the next value is less than closest AND it is an unvisited node
				if(itr.next() < closest && unvisited.contains(itr.next())) {
					//set this node's value to closest and continue
					closest = itr.next();
				}
			}
			//at the end you should have the closest unvisited node
			//if no node was reached set closest back to sourceIndex, essentially starts over
			//of course if you run out of unvisited nodes then the whole loop will just end
			if(closest == Integer.MAX_VALUE) {
				closest = sourceIndex;
			}
			//output is equal to the node closest to current node
			output = adjList.get(currentNode).indexOf(closest);
			//create int evaluationNode and set it equal to this
			int evaluationNode = output;
			//remove evaluationNode from unvisited
			unvisited.removeFirstOccurrence(evaluationNode);
			//find its distance and add it to distance list
			distance.add(nodeList.indexOf(evaluationNode), adjList.get(currentNode).get(evaluationNode));
			//currentNode is now evaluationNode
			currentNode = evaluationNode;
			//the loop continues unless we have run out of unvisited nodes
		}
		
		//once the loop is all done, print all the shortest distances
		for(int j = 0; j < nodeCount; j++) {
			if(j != sourceIndex) {
				System.out.println("Shortest distance from source node to "+j+": "+distance.get(j));
			}
		}
		
		//and return true to end the method
		return true;
	}
	
	/**
	 * A method to find the minimum spanning tree of a graph using the Prim-Jarnik algorithm.
	 * 
	 * @param source the vertex number of the source node
	 * @return true if the source node is valid; otherwise false
	 */
	public boolean prim(int source) {
		//if the desired source node isn't in the graph, return false
		if(!nodeList.contains(source)) {
			return false;
		}
		
		//doing similar stuff as the Dijkstra method
		//save the index of source node
		int sourceIndex = nodeList.indexOf(source);
		//create a list of both visited and unvisited nodes
		LinkedList<Integer> unvisited = nodeList;
		LinkedList<Integer> visited = new LinkedList<Integer>();
		//remove source from unvisited and add it to visited
		unvisited.removeFirstOccurrence(source);
		visited.add(nodeList.indexOf(source), source);
		
		//loop runs as long as there is at least one unvisited node
		while(!unvisited.isEmpty()) {
			//for loop to iterate through all the visited nodes
			for(int s : visited) {
				//doing basically the same thing as Dijkstra's algorithm 
				//Iterating through the adjacency list finding shortest path
				//however this time we are looking for the shortest "unexplored" edge in the whole tree
				//not just ones adjacent to our current node
				//Iterator is used to iterate the adjacency list that stores distances to other nodes
				Iterator<Integer> itr = adjList.get(s).listIterator();
				//int closest defaults to max value
				int closest = Integer.MAX_VALUE;
				int output;
				//as long as there is a next in the Iterator
				while(itr.hasNext()) {
					//if the next value is less than closest AND it is an unvisited node
					if(itr.next() < closest && unvisited.contains(itr.next())) {
						//set this node's value to closest and continue
						closest = itr.next();
					}
				}
				//at the end you should have the closest unvisited node
				//if no node was reached set closest back to sourceIndex, essentially starts over
				//of course if you run out of unvisited nodes then the whole loop will just end
				if(closest == Integer.MAX_VALUE) {
					closest = sourceIndex;
				}
				//output is equal to the node closest to current node
				output = adjList.get(s).indexOf(closest);
				//create int evaluationNode and set it equal to this
				int evaluationNode = output;
				//remove evaluationNode from unvisited
				unvisited.removeFirstOccurrence(evaluationNode);
				//now add this node to the visited list
				visited.add(nodeList.indexOf(evaluationNode), evaluationNode);
				//and print the distance to sort of build the minimum spanning tree bit by bit
				System.out.println("Edge from "+s+" to "+evaluationNode+" with distance "+adjList.get(s).get(evaluationNode));
			}
		}
		//then return true to end the method
		return true;
	}
}