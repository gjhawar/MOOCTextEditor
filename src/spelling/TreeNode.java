package spelling;

public class TreeNode<E extends Comparable<? super E>>{
	public TreeNode<E> left;
	public TreeNode<E> right;
	public TreeNode<E> parent;
	public E data;

	public TreeNode(E data){
		this.data = data;
		left = null;
		right = null;
		parent = null;
	}

	public TreeNode(E data, TreeNode<E> left, TreeNode<E> right, TreeNode<E> parent){
		this.data = data;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
}
