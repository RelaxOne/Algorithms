package com.relaxone.test;

import java.util.List;

import com.relaxone.algorithms.SequentialSearchST;

public class TestSequentilSearchST {

	public static void main(String[] args) {
		SequentialSearchST<Integer, String> test = new SequentialSearchST<>();
		
		test.put(1, "yi");
		test.put(2, "er");
		test.put(3, "san");
		
		test.print();
		
		int size = test.size();
		System.out.println("the size is:" + size);
		
		test.delete(2);
		
		test.print();
		List<Integer> keys = test.keys();
		
		for(int i =0 ;i<keys.size();i++) {
			System.out.print(keys.get(i)+ " ");
		}
		
	}
	
}
