import java.util.Iterator;
import java.util.LinkedList;

/**
 * A class A5_Q1 containing a method to take an adjacency matrix as input and print the breadth-first
 * traversal results of the graph starting from the input node.
 * 
 * @author Andrew Greenwood
 *
 */
public class A5_Q1 {

	/**
	 * A method to return the Breadth-First Traversal of the input adjacency matrix, starting
	 * at the input node.
	 * 
	 * @param aMatrix the Adjacency Matrix of the graph to be traversed
	 * @param source the starting node of the traversal
	 */
	public static void BFT(int[][] aMatrix, int source) {
		//A LinkedList array is created to be the adjacency list for each node of the graph
		LinkedList<Integer> adjList[] = new LinkedList[aMatrix.length];

		//A for loop is used to fill out the adjacency lists
		for (int i = 0; i < aMatrix.length; i++) {
			//creates a LinkedList to store the adjacent nodes for all nodes in the graph
			adjList[i] = new LinkedList<Integer>();
			for(int j = 0; j < aMatrix.length; j++) {
				//then, if the [i][j] value in the adjacency matrix is not 0, there must be an edge from i to j
				//so j is added to i's adjacency list
				if(aMatrix[i][j] > 0) {
					adjList[i].add(j);
				}
			}
		}
		
		//A boolean array is created to store whether or not each node has been visited in the traversal
		//all values default to false which makes things easier
		boolean visited[] = new boolean[aMatrix.length];

		//Another LinkedList is created to act as a queue
		LinkedList<Integer> queue = new LinkedList<Integer>();

		//First, mark the input (source) node as visited
		visited[source] = true;
		//And add it to the queue
		queue.add(source);
		
		//Loop runs as long as there is something in the queue
		//i.e. it will end once every node has been visited
		while(queue.size() > 0)
		{
			//First, poll (retrieve + remove) the first element in the queue
			source = queue.poll();
			//And print it
			System.out.print(source + ", ");

			//Then create a  listIterator to iterate through the adjacency lists
			Iterator<Integer> i = adjList[source].listIterator();
			//As long as there is another value in the list
			while(i.hasNext()) {
				//Set an int n to the next value
				int n = i.next();
				//Check if n is visited -- if not, then add it to the queue and set it as visited
				if (visited[n] == false)
				{
					visited[n] = true;
					queue.add(n);
				}
			}
		}
	}

	/**
	 * 
	 * Testing
	 * 
	 */
	public static void main(String[] args) {
		int[][] graph = new int[6][6];
		graph[0][1] = 1;
		graph[1][0] = 1;
		graph[2][3] = 1;
		graph[3][2] = 1;
		graph[1][3] = 1;
		graph[3][1] = 1;
		graph[0][2] = 1;
		graph[2][0] = 1;
		graph[0][3] = 1;
		graph[3][0] = 1;
		graph[5][2] = 1;
		graph[2][5] = 1;
		graph[4][3] = 1;
		graph[3][4] = 1;

		BFT(graph, 2);
	}
}
