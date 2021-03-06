package assign9;

/**
 * Represents a generically-typed binary tree node. Each binary node contains
 * data, a left child, a right child and its parent.
 * 
 * @author Tim Ellenberger, ellenber
 * @author John Strasser, strasser
 * @author Erin Parker
 * @version 11/05/2015
 */
public class BinaryNode<Type extends Comparable<? super Type>> {
	private Type data;
	private BinaryNode<Type> leftChild;
	private BinaryNode<Type> rightChild;
	private BinaryNode<Type> parent;

	/**
	 * Constructor for creating a node in a binary search tree.
	 * 
	 * @param _data The information of a specified type, stored in the node.
	 * @param _parent The parent node of this node.
	 * @param _leftChild The left child node of this node.
	 * @param _rightChild The right child node of this node.
	 */
	public BinaryNode(Type _data, BinaryNode<Type> _parent, BinaryNode<Type> _leftChild, BinaryNode<Type> _rightChild) {
		data = _data;
		parent = _parent;
		leftChild = _leftChild;
		rightChild = _rightChild;
	}

	/**
	 * Constructor for creating the root node of a binary search tree.
	 * 
	 * @param _data The information of a specified type, stored in the node.
	 */
	public BinaryNode(Type _data) {
		this(_data, null, null, null);
	}

	/**
	 * Getter method.
	 * 
	 * @return the node data.
	 */
	public Type getData() {
		return data;
	}

	/**
	 * Setter method.
	 * 
	 * @param _data
	 *            - the node data to be set.
	 */
	public void setData(Type _data) {
		data = _data;
	}

	/**
	 * @return the parent
	 */
	public BinaryNode<Type> getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(BinaryNode<Type> _parent) {
		this.parent = _parent;
	}

	/**
	 * Getter method.
	 * 
	 * @return the left child node.
	 */
	public BinaryNode<Type> getLeftChild() {
		return leftChild;
	}

	/**
	 * Setter method.
	 * 
	 * @param _leftChild
	 *            - the left child node to be set.
	 */
	public void setLeftChild(BinaryNode<Type> _leftChild) {
		leftChild = _leftChild;
	}

	/**
	 * Getter method.
	 * 
	 * @return the right child node.
	 */
	public BinaryNode<Type> getRightChild() {
		return rightChild;
	}

	/**
	 * Setter method.
	 * 
	 * @param _rightChild
	 *            - the right child node to be set.
	 */
	public void setRightChild(BinaryNode<Type> _rightChild) {
		rightChild = _rightChild;
	}

	/**
	 * Returns the leftmost node in the binary tree rooted at this node.
	 */
	public BinaryNode<Type> getLeftmostNode() {
		if (getLeftChild() == null)
			return this;
		return getLeftChild().getLeftmostNode();
	}

	/**
	 * Returns the rightmost node in the binary tree rooted at this node.
	 */
	public BinaryNode<Type> getRightmostNode() {
		if (getRightChild() == null)
			return this;
		return getRightChild().getRightmostNode();
	}
	
	/**
	 * Returns true if this node is a leaf node i.e. does not have children
	 * 
	 * @return True if this node is a leaf node i.e. does not have children
	 */
	public boolean isLeafNode() {
		return (getLeftChild() == null && getRightChild() == null);
	}
	
	/**
	 * Returns true if this node is a left child of its parent.
	 * 
	 * @return True if this node is a left child of its parent.
	 */
	public boolean isLeftChild() {
		if(getParent() == null)
			return false;
		BinaryNode<Type> parentsLeftChild = getParent().getLeftChild();
		if(parentsLeftChild == null)
			return false;
		
		return (parentsLeftChild.getData().equals(this.getData()));
	}
	
	/**
	 * Returns true if this node is a right child of its parent.
	 * 
	 * @return True if this node is a right child of its parent.
	 */
	public boolean isRightChild() {
		if(getParent() == null)
			return false;
		BinaryNode<Type> parentsRightChild = getParent().getRightChild();
		if(parentsRightChild == null)
			return false;
		
		return (parentsRightChild.getData().equals(this.getData()));
	}
	
	/**
	 * Returns true if this node has a left child.
	 * 
	 * @return True if this node has a left child.
	 */
	public boolean hasLeftChild() {
		return (getLeftChild() != null);
	}
	
	/**
	 * Returns true if this node has a right child.
	 * 
	 * @return True if this node has a right child.
	 */
	public boolean hasRightChild() {
		return (getRightChild() != null);
	}
	
	/**
	 * Returns true if this node only has a left child.
	 * 
	 * @return True if this node only has a left child.
	 */
	public boolean hasLeftChildOnly() {
		return (getLeftChild() != null && getRightChild() == null);
	}
	
	/**
	 * Returns true if this node only has a right child.
	 * 
	 * @return True if this node only has a right child.
	 */
	public boolean hasRightChildOnly() {
		return (getLeftChild() == null && getRightChild() != null);
	}
	
	/**
	 * Returns true if this node has both a left and a right child.
	 * 
	 * @return True if this node has both a left and a right child.
	 */
	public boolean hasTwoChildren() {
		return (getLeftChild() != null && getRightChild() != null);
	}

	/**
	 * Returns the height of the binary tree rooted at this node. The height of
	 * a tree is the length of the longest path to a leaf node. Consider a tree
	 * with a single node to have a height of zero.
	 */
	public int height() {
		// get left child and right child
		BinaryNode<Type> lc = getLeftChild();
		BinaryNode<Type> rc = getRightChild();

		// leaf node has no edged to add to height
		if (lc == null && rc == null)
			return 0;

		// if single child, just add one + height of subtree
		if (lc == null)
			return 1 + rc.height();
		if (rc == null)
			return 1 + lc.height();

		// if two children, find max of subtrees
		return 1 + Math.max(lc.height(), rc.height());

	}

}
