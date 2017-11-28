
class Node {

	//value represent node
	public String value;

	//value represent node left
	public Node left;

	//value represent node right
	public Node right;

	public Node(String value) {
		this.value = value;
	}
}

public class BinarySearchTree{

	public Node root;
	// Insert value in the Tree.
	public BinarySearchTree add(String value) {
		Node node = new Node(value);
		if (root == null) {
			root = node;
			return this;
		}
		if(!search(value)){
			insertValue(root, node);
		}
		return this;
	}
	private void insertValue(Node tempNode, Node node) {
		if (tempNode.value.compareTo(node.value) > 0) {
			if (tempNode.left == null) {
				tempNode.left = node;
				return;
			} else {
				insertValue(tempNode.left, node);
			}
		} else {
			if (tempNode.right == null) {
				tempNode.right = node;
				return;
			} else {
				insertValue(tempNode.right, node);
			}
		}
	}
	// find minimum value in the tree
	// Returns minimum node.
	public String findMinimum() {
		if (root == null) return null;
		Node current = root;
		while (current.left != null) {
			current = current.left;
		}
		return current.value;
	}
	// find maximum value in the tree
	// Returns maximum node
	public String findMaximum() {
		if (root == null) return null;
		Node current = root;
		while (current.right != null) {
			current = current.right;
		}
		return current.value;
	}
	// search for node
	// Returns true if found else false
	public boolean search(String value) {
		Node current = root;
		while(current.value.compareTo(value) != 0){
			if(current.value.compareTo(value) > 0){
				current = current.left;
			} else {
				current = current.right;
			}
			if(current == null){
				return false;
			}
		}
		return true;
	}
	// Delete node
	// Returns true if found and delete else false
	public boolean delete(String value) {
		Node parent = root;
		Node current = root;
		// First serching for the element
		boolean isLeftChild = false;
		while(current.value.compareTo(value) != 0){
			parent = current;
			if(current.value.compareTo(value) > 0){
				isLeftChild = true;
				current = current.left;
			} else {
				isLeftChild = false;
				current = current.right;
			}
			if(current == null){
				return false;
			}
		}
		//If found check for child:
		//If node has no children
		if(current.left == null && current.right == null){
			if(current == root){
				root = null;
			}
			if(isLeftChild == true){
				parent.left = null;
			}else{
				parent.right = null;
			}
		}
		//If node has one child
		else if(current.right == null){
			if(current == root){
				root = current.left;
			} else if(isLeftChild){
				parent.left = current.left;
			} else {
				parent.right = current.left;
			}
		}
		else if(current.left == null){
			if(current == root){
				root = current.right;
			}else if(isLeftChild){
				parent.left = current.right;
			}else{
				parent.right = current.right;
			}
			//If node has two child
		}else if(current.left != null && current.right != null){
			//Get a replacement Node
			Node ReplacementNode = getReplacementNode(current);
			if(current == root){
				root = ReplacementNode;
			} else if (isLeftChild){
				parent.left = ReplacementNode;
			} else {
				parent.right = ReplacementNode;
			}			
			ReplacementNode.left = current.left;
		}		
		return true;
	}
	//Find a Replacement Node for Parent
	//returns node
	public Node getReplacementNode(Node temp){
		Node node = null;
		Node Parent = null;
		Node current = temp.right;
		while(current != null){
			Parent = node;
			node = current;
			current = current.left;
		}
		if(node != temp.right){
			Parent.left = node.right;
			node.right = temp.right;
		}
		return node;
	}
	//Find the depth Of Tree
	//returns depthOfTree(int)
	public int depthOfTree() {
		return depthOfTree(root);
	}
	private int depthOfTree(Node node) {
		if (node == null) return -1;
		// Returns height of a node, height of a null node is defined to be -1
		return 1 + Math.max(depthOfTree(node.left), depthOfTree(node.right));
	}

	//Find the depth Of each node in Tree
	//returns depths(int[])
	public int[] DepthEachNode (int[] depth){
		return DepthEachNode(root, depth, 0);
	}
	private int[] DepthEachNode(Node n, int[] depths, int depth) {
		if (n != null) {
			DepthEachNode(n.left, depths, depth+1);
			depths[depth]++;
			DepthEachNode(n.right, depths, depth+1);
		}
		return depths;
	}

	//Find the average depth Of Tree
	//returns average(float)
	public float averageDepth(int[] depths) {
		int a=0, b=0;
		for (int i =0; i < depths.length; i++) {
			a += depths[i];
			b += depths[i]*i;
		}
		return (float)b/a;
	}
}
