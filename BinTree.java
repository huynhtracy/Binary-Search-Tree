package recursion;
import java.lang.Math;
public class BinTree <T>{

	
	public T value; 
	public BinTree <T> left;
	public BinTree <T> right;
	
	public BinTree(T value) {
		this.value = value; 
	}
	
	public int getHeight() {				
		if(left == null && right == null) {
			return 1;
		}
		if(left == null) {
			return 1 + right.getHeight();
		}
		if(right == null) {
			return 1 + left.getHeight();
		}
		return 1 + Math.max(left.getHeight(),right.getHeight());
		
	}
	
	
	public int getNumElements() {				
		if(left == null && right == null) {
			return 1;
		}
		if(left == null) {
			return 1 + right.getNumElements();
		}
		if(right == null) {
			return 1 + left.getNumElements();
		}
		return 1 + left.getNumElements() + right.getNumElements();				
	}
	
	public String toString() {
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
	
	public boolean contains (T value) {	
		if(this.value == value) {
			return true;
		}
		if(left == null && right == null) {
			return false; 
		}
		if(left == null) {
			return right.contains(value);
		}
		if(right == null) {
			return left.contains(value);
		}
		return left.contains(value) || right.contains(value);
	}
	
	

	

	
	
}
