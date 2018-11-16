package com.relaxone.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * ʵ�ֻ��ڶ���������ķ��ű�
 * 
 * @author zhoucw
 *
 * @param <Key>
 * @param <Value>
 */
public class BST<Key extends Comparable<Key>, Value> {

	private Node root;

	public class Node {
		private Key key;
		private Value value;
		private Node left, right;
		private int size;

		public Node(Key key, Value value, int size) {
			this.key = key;
			this.value = value;
			this.size = size;
		}
	}

	/**
	 * ��ȡ���Ĵ�С
	 * 
	 * @return
	 */
	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		return x.size;
	}

	/**
	 * ��ȡָ�� key �� value
	 * 
	 * @param key
	 * @return
	 */
	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node root, Key key) {
		if (root == null)
			return null;
		int cmp = key.compareTo(root.key);
		if (cmp > 0)
			return get(root.left, key);
		else if (cmp > 0)
			return get(root.right, key);
		else
			return root.value;
	}

	/**
	 * ��һ����ֵ�Բ�������
	 * 
	 * @param key
	 * @param val
	 */
	public void put(Key key, Value val) {
		root = put(root, key, val);
	}

	private Node put(Node root, Key key, Value val) {
		if (root == null)
			return new Node(key, val, 1);
		int cmp = key.compareTo(root.key);
		if (cmp < 0)
			root.left = put(root.left, key, val);
		else if (cmp > 0)
			root.right = put(root.right, key, val);
		else
			root.value = val;
		root.size = size(root.left) + size(root.right) + 1;
		return root;
	}

	/**
	 * ��ȡ��ǰ����С�ļ�
	 * 
	 * @return
	 */
	public Key min() {
		return min(root).key;
	}

	private Node min(Node root) {
		if (root.left == null)
			return root;
		return min(root.left);
	}

	/**
	 * ���ص�ǰ�����ļ�
	 * 
	 * @return
	 */
	public Key max() {
		return max(root).key;
	}

	private Node max(Node root) {
		if (root.right == null)
			return root;
		return max(root.right);
	}

	/**
	 * ��ȡС�ڵ���ָ�� key �ļ�
	 * 
	 * @param key
	 * @return
	 */
	public Key floor(Key key) {
		Node temp = floor(root, key);
		if (temp == null)
			return null;
		return temp.key;
	}

	private Node floor(Node root, Key key) {
		if (root == null)
			return null;
		int cmp = key.compareTo(root.key);
		if (cmp > 0)
			// ֻ�е����ڵ��������д���С�ڵ��� key �Ľڵ�ʱ��С�ڵ��� key �������Ż���������������ܣ�������ڵ����С�ڵ��� key ������
			return floor(root.right, key) == null ? root : floor(root.right, key);
		else if (cmp < 0)
			return floor(root.left, key);
		else
			return root;
	}

	/**
	 * ��ȡ���ڵ���ָ�� key �ļ�
	 * 
	 * @param key
	 * @return
	 */
	public Key ceil(Key key) {
		Node temp = ceil(root, key);
		if (temp == null)
			return null;
		return temp.key;
	}

	private Node ceil(Node root, Key key) {
		if (root == null)
			return null;
		int cmp = key.compareTo(root.key);
		if (cmp < 0)
			return ceil(root.left, key) == null ? root : ceil(root.left, key);
		else if (cmp > 0)
			return ceil(root.right, key);
		return root;
	}

	/**
	 * ��ѯ�� k ��Ԫ�ص� key
	 * 
	 * @param k
	 * @return
	 */
	public Key select(int k) {
		return select(root, k).key;
	}

	private Node select(Node root, int k) {
		if (root == null)
			return null;
		int t = size(root.left);
		if (t > k)
			return select(root.left, k);
		else if (t < k)
			return select(root.right, k - t - 1);
		else
			return root;
	}

	/**
	 * ����ָ�� key ��˳��
	 * 
	 * @param key
	 * @return
	 */
	public int rank(Key key) {
		return rank(root, key);
	}

	private int rank(Node root, Key key) {
		if (root == null)
			return 0;
		int cmp = key.compareTo(root.key);
		if (cmp < 0)
			return rank(root.left, key);
		else if (cmp > 0)
			return rank(root.right, key) + size(root.left) + 1;
		else
			return size(root.left);
	}

	/**
	 * ɾ���������м���С�Ľڵ�
	 */
	public void deleteMin() {
		root = deleteMin(root);
	}

	private Node deleteMin(Node root) {
		if (root.left == null)
			return root.right;
		root.left = deleteMin(root.left);
		root.size = size(root.left) + size(root.right) + 1;
		return root;
	}

	/**
	 * ɾ����Ϊ key ���ڽڵ�Ԫ��
	 * 
	 * @param key
	 */
	public void delete(Key key) {
		root = delete(root, key);
	}

	private Node delete(Node root, Key key) {
		if (root == null)
			return null;
		int cmp = key.compareTo(root.key);
		if (cmp < 0)
			root.left = delete(root.left, key);
		else if (cmp > 0)
			root.right = delete(root.right, key);
		else {
			// ���´����д�Ū���????
			if (root.left == null)
				return root.right;
			else if (root.right == null)
				return root.left;
			Node t = root;
			root = min(t.right);
			root.right = deleteMin(t.right);
			root.left = t.left;
		}
		root.size = size(root.left) + size(root.right) + 1;
		return root;
	}

	/**
	 * ��ȡ��ǰ��������������е� key
	 * 
	 * @return
	 */

	public Iterable<Key> keys() {
		return keys(min(), max());
	}

	/**
	 * ����������ķ�Χ���Ҳ���
	 * 
	 * @param lo
	 * @param hi
	 * @return
	 */
	public Iterable<Key> keys(Key lo, Key hi) {
		List<Key> list = new ArrayList<Key>();
		keys(root, list, lo, hi);
		return list;
	}

	private void keys(Node root, List<Key> list, Key lo, Key hi) {
		if (root == null)
			return;
		int cmplo = lo.compareTo(root.key);
		int cmphi = lo.compareTo(root.key);

		if (cmplo < 0)
			keys(root.left, list, lo, hi);
		if (cmplo <= 0 && cmphi >= 0)
			list.add(root.key);
		if (cmphi > 0)
			keys(root.right, list, lo, hi);
	}

	/**
	 * ������������ķ�����ӡ����������е����� key �� value
	 * 
	 * @param root
	 */
	public void printBST() {
		printBST(root);
	}

	private void printBST(Node root) {
		if (root == null)
			return;
		printBST(root.left);
		System.out.println(root.key + " = " + root.value);
		printBST(root.right);
	}
}
