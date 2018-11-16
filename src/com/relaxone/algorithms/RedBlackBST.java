package com.relaxone.algorithms;

/**
 * 红黑二叉树
 * 
 * @author zhoucw
 *
 */
public class RedBlackBST<Key, Value> {

	// 表示指向该节点的连接的颜色
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private Node root;

	public class Node {
		Key key; // 键
		Value value; // 值
		Node left, right; // 左右子树
		int nodeCount; // 节点中子节点的总数
		boolean color; // 由其父节点指向它的链接的颜色

		Node(Key key, Value val, int nodeCount, boolean color) {
			this.key = key;
			this.value = val;
			this.nodeCount = nodeCount;
			this.color = color;
		}
	}

	/**
	 * 获取当前树的子节点的数目
	 * 
	 * @return
	 */
	public int size() {
		return size(root);
	}

	/**
	 * 获取指定节点的子节点数目
	 * 
	 * @param x
	 * @return
	 */
	private int size(Node x) {
		if (x == null)
			return 0;
		return x.nodeCount;
	}

	/**
	 * 判断一个节点和它的父节点之间的链接的颜色
	 * 
	 * @param root
	 * @return
	 */
	public boolean isRed(Node root) {
		if (root == null)
			return false;
		return root.color == RED;
	}

	/**
	 * 将红色的右链接转化为左链接(左旋转)
	 * 
	 * @param root
	 * @return
	 */
	private Node rotateLeft(Node root) {
		Node temp = root.right;
		root.right = temp.left;
		temp.left = root;
		temp.color = root.color;
		root.color = RED;
		temp.nodeCount = root.nodeCount;
		root.nodeCount = 1 + size(root.left) + size(root.right);
		return temp;
	}

	/**
	 * 将红色的左链接转化为右链接(右旋转)
	 * 
	 * @param root
	 * @return
	 */
	private Node rotateRight(Node root) {
		Node temp = root.left;
		root.left = temp.right;
		temp.right = root;
		temp.color = root.color;
		root.color = RED;
		temp.nodeCount = root.nodeCount;
		root.nodeCount = 1 + size(root.left) + size(root.right);
		return temp;
	}
}
