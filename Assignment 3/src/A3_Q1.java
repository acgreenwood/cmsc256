/**
 * A class A3_Q1.java containing a method isValidBST that determines
 * whether or not a given binary tree is organized to support the BST property
 * 
 * @author Andrew Greenwood
 */

@SuppressWarnings("all")
public class A3_Q1 extends BST {
	
	/**
	 * A method to determine whether or not a binary tree is a valid BST
	 * 
	 * @param tree the binary tree that the method will examine to determine if it is a valid BST or not
	 * @return	whether or not the input binary tree is a valid BST
	 */
	public boolean isValidBST(BST tree) {
		return isBST(tree);
	}
}
