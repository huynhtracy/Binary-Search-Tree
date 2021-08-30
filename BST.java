package recursion;

public class BST {
	
	public BST left; 
	public BST right; 
	public Integer value; 
	private boolean empty; 
	
	//constructors
	public BST() {
		empty = true;
	}
	
	public BST(int value) {
		empty = false; 
		this.value = value;
	}

	private boolean isLeaf() {
		return left == null && right == null;
	}
	
	private boolean hasLeftChild() {
		return left != null; 
	}
	
	
	private boolean hasRightChild() {
		return right != null;
	}
	
	public boolean isEmpty() {
		return empty; 
	} 
	
	
	public void insert(int value) {
		if (isLeaf()) {
			if (value <= this.value) {
				left = new BST(value);
			}
			else {
				right = new BST(value);
			}
		}
		else if (!hasLeftChild()) {
			//if it doesn't have a left child
			if (value <= this.value) {
				//if it belongs on left, create a left and store value
				left = new BST(value);
			}
			// if it belongs on right
			else {
				right.insert(value);
			}
		}
		else if(!hasRightChild()) {	
		//if it doesn't have a right child
			if(value <= this.value) {
				left.insert(value);
			}
			else {
				right = new BST(value);
			}
		}		
		else {
			//if it has both left and right child
				if (value <= this.value) {
					left.insert(value);
				}
				else {
					right.insert(value);
				}
		}
	}

	public int height() {
		if(isEmpty()) {
			return 0;
		}
		if(isLeaf()) {
			return 1;
		}
		if(hasLeftChild()) {
			return 1 + left.height();
		}
		if(hasRightChild()) {
			return 1 + right.height();
		}
		return 1 + Math.max(left.height(), right.height());
	}
	
	public String toString() {
		if(isEmpty()) {
			return "";
		}
		if(left == null && right == null) {
			return value.toString();
		}
		if(left == null) {
			return value.toString() +   "->{" + right.toString()  + "}";
		}
		if (right == null) {
			return "{" + left.toString() + "}<-" + value.toString();
		}
		return "{" + left.toString() + "}<-" + value.toString() + "->{" + right.toString() + "}";	
	}
	
	public boolean contains(int value) {	
		if(isLeaf()) {
			return this.value == value; 
		}
		else if (!hasLeftChild()) {
			//If doesn't have left child (and has a right child)
			if(value <= this.value) {
				//If value belongs on left
				return this.value == value; 
			}
			//If value belongs on right, check right child
			return right.contains(value);	
		}
		else if (!hasRightChild()) {
			//if it doesn't have a right child (and does have a left child)
			if(value <= this.value) {
				//If value belongs on left
				//Note: If it is equal to value, we inserted it on the left
				return this.value == value || left.contains(value);
			}
			//if value belongs on right 
			return false; 
		}
		else {
			//it has left and right child
			if (value <= this.value) {
			//if it belongs on left
				return this.value == value || left.contains(value);
			}
			//if it belongs on right
			return right.contains(value);
		}		
	}
	
	public void delete(int value) {

		BST current = this;
		BST parent = this;
		boolean isLeftChild = false;
		
		
		if (current == null) {
			return;
		}
		
		while (current != null && current.value != value) {
			//loop terminates when we find the matching node
			parent = current;
			
			if(value <= current.value) {
				current = current.left;
				isLeftChild = true;
			}
			else {
				current = current.right;
				isLeftChild = false;
			}	
		}
		
		//now we have the current and parent nodes we want
		
		if(!(current.hasLeftChild()) && !(current.hasRightChild())) {
			//its a leaf
			
			if (current == parent) {
				//delete the root
				current.value = null;
				current.empty = true; 
			}
			else {
				
				if(isLeftChild) {
					parent.left = null;
				}
				else {//is right child
					parent.right = null;
				}
			}	
		}
		
		else if(!current.hasRightChild()) {
			//it has only left child
			if (current == parent) {
				//delete the root
				current.value = null;
				current.empty = true; 
			}
			else if (isLeftChild) {
				parent.left = current.left;
			}
			else {
				parent.right = current.left;
			}

		}
		
		else if(!current.hasLeftChild()) {
			//it has only right child
			if (current == parent) {
				//delete the root
				current.value = null;
				current.empty = true; 
			}
			else if (isLeftChild) {
				parent.left = current.right;
			}
			else {
				parent.right = current.right;
			}
		}
		
		else if(current.hasLeftChild() && current.hasRightChild()) {
			//has both left and right child
			//get the leftmost child of current.right (the successor)
			//this will replace current
			//if the successor has a right child, the right child becomes 
			//the left child of the parent of the successor

			BST successor = getSuccessor(current);
			if (current == parent) {
				current.value = successor.value;
				//current.left stays the same
				current.right = successor.right;	
			}
			else if (isLeftChild) {
				parent.left = successor;
			} else {
				parent.right = successor;
			}
			successor.left = current.left;
		}		
	}
	
	private BST getSuccessor(BST node) {
		BST parentOfSuccessor = node;
		BST successor = node;
		BST current = node.right;
		while (current != null) {
			parentOfSuccessor = successor;
			successor = current;
			current = current.left;
		}
		//loops through and retrieves the leftmost child of the right subtree
		
		if (successor != node.right) {
			parentOfSuccessor.left = successor.right; //if it exists. if not, its null. 
			successor.right =node.right;
		}
		return successor;
	}

	
	public int getMax() {
		//rightmost value
		BST current;
		current = this; 
		
		if(isEmpty()) {
			return 0;
		}
		
		while (current.right != null) {
			current = current.right;
		}
		return current.value;
	}
	
	public int getMin() {
		BST current;
		current = this; 
		
		if(isEmpty()) {
			return 0;
		}
		
		while (current.left != null) {
			current = current.left;
		}
		return current.value;
		
	}
	
}
	
	



