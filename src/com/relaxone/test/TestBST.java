package com.relaxone.test;

import com.relaxone.algorithms.BST;

public class TestBST {

	public static void main(String[] args) {
		BST<Integer,String> bst = new BST<>();
		
		bst.put(1, "yi");
		bst.put(2, "er");
		bst.put(4, "si");
		bst.put(3, "san");
		
		bst.printBST();
		
		bst.delete(3);
		
		bst.printBST();
	}
	
}
