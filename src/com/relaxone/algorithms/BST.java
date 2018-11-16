package com.relaxone.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现基于二叉查找树的符号表
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
	 * 获取树的大小
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
	 * 获取指定 key 的 value
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
	 * 将一个键值对插入树中
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
	 * 获取当前树最小的键
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
	 * 返回当前树最大的键
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
	 * 获取小于等于指定 key 的键
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
			// 只有当根节点右子树中存在小于等于 key 的节点时，小于等于 key 的最大键才会出现在有字数汇总，否则根节点就是小于等于 key 的最大键
			return floor(root.right, key) == null ? root : floor(root.right, key);
		else if (cmp < 0)
			return floor(root.left, key);
		else
			return root;
	}

	/**
	 * 获取大于等于指定 key 的键
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
	 * 查询第 k 个元素的 key
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
	 * 查找指定 key 的顺序
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
	 * 删除二叉树中键最小的节点
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
	 * 删除键为 key 的在节点元素
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
			// 以下代码有待弄清楚????
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
	 * 获取当前二叉查找树中所有的 key
	 * 
	 * @return
	 */

	public Iterable<Key> keys() {
		return keys(min(), max());
	}

	/**
	 * 二叉查找树的范围查找操作
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
	 * 利用中序遍历的方法打印二叉查找树中的所有 key 和 value
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
