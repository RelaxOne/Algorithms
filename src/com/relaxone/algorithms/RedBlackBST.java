package com.relaxone.algorithms;

/**
 * ��ڶ�����
 * 
 * @author zhoucw
 *
 */
public class RedBlackBST<Key, Value> {

	// ��ʾָ��ýڵ�����ӵ���ɫ
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private Node root;

	public class Node {
		Key key; // ��
		Value value; // ֵ
		Node left, right; // ��������
		int nodeCount; // �ڵ����ӽڵ������
		boolean color; // ���丸�ڵ�ָ���������ӵ���ɫ

		Node(Key key, Value val, int nodeCount, boolean color) {
			this.key = key;
			this.value = val;
			this.nodeCount = nodeCount;
			this.color = color;
		}
	}

	/**
	 * ��ȡ��ǰ�����ӽڵ����Ŀ
	 * 
	 * @return
	 */
	public int size() {
		return size(root);
	}

	/**
	 * ��ȡָ���ڵ���ӽڵ���Ŀ
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
	 * �ж�һ���ڵ�����ĸ��ڵ�֮������ӵ���ɫ
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
	 * ����ɫ��������ת��Ϊ������(����ת)
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
	 * ����ɫ��������ת��Ϊ������(����ת)
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
