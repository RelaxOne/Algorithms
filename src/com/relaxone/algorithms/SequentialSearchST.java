package com.relaxone.algorithms;

import java.util.ArrayList;
import java.util.List;

public class SequentialSearchST<Key, Value> {
	private class Node {
		private Key key;
		private Value value;
		private Node next;

		public Node(Key key, Value val, Node next) {
			this.key = key;
			this.value = val;
			this.next = next;
		}
	}

	private Node first;

	public Value get(Key key) {
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key))
				return x.value;
		}
		return null;
	}

	public void put(Key key, Value val) {
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				x.value = val;
				return;
			}
		}
		first = new Node(key, val, first);
	}

	public int size() {
		int size = 0;
		for (Node x = first; x != null; x = x.next) {
			size++;
		}
		return size;
	}
	
	public List<Key> keys(){
		List<Key> list = new ArrayList<>();
		for(Node x = first; x!= null;x = x.next) {
			list.add(x.key);
		}
		return list;
	}

	public boolean delete(Key key) {
		Node head = null;
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				head.next = x.next;
				return true;
			}
			head = x;
		}
		return false;
	}
	
	public void print() {
		Node x = first;
		while(x!= null) {
			System.out.println(x.key + " = " + x.value);
			x = x.next;
		}
	}
}
