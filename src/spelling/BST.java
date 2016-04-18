package spelling;

import java.util.LinkedList;
import java.util.Queue;

public class BST<E> {

	public TreeNode<Integer> root;

	/*
	* Non recursive insert
	*/
	public void insert(Integer data){
		if(data==null)
			throw new NullPointerException("Data is null");
		boolean leftflag = false;
		boolean rightflag = false;

		if(root == null){
			root = new TreeNode<Integer>(data);
		} else {
			TreeNode<Integer>currNode = root;
			while(currNode!=null){
				leftflag = false;
				rightflag = false;
				if(data.compareTo(currNode.data)>0)
				{
					if(currNode.right==null){
						rightflag = true;
						break;
					}
					else
						currNode = currNode.right;
				}
				else if(data.compareTo(currNode.data)<0){
					if(currNode.left == null){
						leftflag = true;
						break;
					}
					else
						currNode = currNode.left;
				}
				else
					return;
			}

			if(leftflag == true){
				currNode.left = new TreeNode<Integer>(data);
			}
			if(rightflag == true){
				currNode.right = new TreeNode<Integer>(data);
			}
		}
	}

	/*
	 * Recursive insert
	 */
	public TreeNode<Integer> insert(TreeNode<Integer> currNode, Integer data){
		if(data==null)
			throw new NullPointerException("Data is null");

		if(currNode!=null){
			if(data.compareTo((Integer)currNode.data)<0){
				currNode.left = insert(currNode.left, data);
				currNode.left.parent = currNode;
			}
			else if(data.compareTo((Integer) currNode.data)>0){
				currNode.right = insert(currNode.right, data);
				currNode.right.parent = currNode;
			}
		} else {
			currNode = new TreeNode<>(data);
		}
		return currNode;
	}

	/*
	 * Remove Helper function for helping delete
	 *  Takes in the current node and checks for cases where that node has :-
	 *  1. 0 child 2. 1 child 2. 2 children
	 *  and accordingly reassigns the pointers in the parent element.
	 */
	public boolean remove(TreeNode<Integer> currNode){
		if(currNode.left == null && currNode.right == null){
			if(currNode.parent.left == currNode)
				currNode.parent.left = null;
			else
				currNode.parent.right = null;
			return true;
		}
		if(currNode.left==null && currNode.right!=null){
			if(currNode.parent.left == currNode)
				currNode.parent.left = currNode.right;
			else
				currNode.parent.right = currNode.right;
			return true;
		}
		if(currNode.left!=null && currNode.right==null){
			if(currNode.parent.left == currNode)
				currNode.parent.left = currNode.left;
			else
				currNode.parent.right = currNode.left;
			return true;
		}
		if(currNode.left!=null && currNode.right!=null){
			// Find minumum element in right subtree
			TreeNode<Integer> minNode = currNode.right;
			while(minNode.left!=null){
				minNode = minNode.left;
			}
			currNode.data = minNode.data;
			// Delete the duplicate node
			delete(currNode.right, currNode.data);
			return true;
		}

		return false;

	}

	/*
	 *  Recursive delete : Reference : http://www.algolist.net/Data_structures/Binary_search_tree/Removal
	 *  
	 */
	public boolean delete(TreeNode<Integer> currNode, Integer data){
		if(data==null)
			throw new NullPointerException("Data is null");

		if(currNode!=null){
			if(data.compareTo((Integer)currNode.data)<0){
				return delete(currNode.left, data);
			} else if(data.compareTo((Integer) currNode.data)>0){
				return delete(currNode.right, data);
			} else{
				return remove(currNode);
			}
		}
		return false;
	}

	/*
	 * Non recursive find
	 */
	public boolean find(Integer data){
		if(data==null)
			throw new NullPointerException("Data is null");

		TreeNode<Integer> start = root;
		while(start!=null){
			if(data.compareTo(start.data)>0)
				find(start.left.data);
			else if(data.compareTo(start.data)<0)
				start = start.left;
			else
				return true;
		}
		return false;
	}

	/*
	 * Recursive find
	 */
	public boolean find(TreeNode<Integer> start, Integer data){
		if(data==null)
			throw new NullPointerException("Data is null");
		if(start!=null){
			if(data.compareTo(start.data)<0)
				 return find(start.left, data);
			else if(data.compareTo(start.data)>0)
				 return find(start.right, data);
			else
				return true;
		}
		return false;
	}

	/*
	 * InOrder traversal
	 */
	public void inOrder(TreeNode<Integer> start){
		if(start!=null){
			System.out.println(start.data);
			inOrder(start.left);
			inOrder(start.right);
		}
	}

	/*
	 * Preorder traversal
	 */
	public void preOrder(TreeNode<Integer> start){
		if(start!=null){
			preOrder(start.left);
			System.out.println(start.data);
			preOrder(start.right);
		}
	}

	/*
	 * Post order traversal
	 */
	public void postOrder(TreeNode<Integer> start){
		if(start!=null){
			postOrder(start.left);
			postOrder(start.right);
			System.out.println(start.data);
		}
	}

	/*
	 * Level order traversal
	 */
	public void levelOrder(TreeNode<Integer> start){
		Queue<TreeNode<Integer>> q = new LinkedList<>();
		q.add(start);
		while(!q.isEmpty()){
			TreeNode<Integer> currNode = q.remove();
			System.out.println(currNode.data);
			if(currNode.left!=null)
				q.add(currNode.left);
			if(currNode.right!=null)
				q.add(currNode.right);
		}
	}


	public static void main(String args[]){
		BST<Integer> bst = new BST<Integer>();
		bst.root = new TreeNode<Integer>(20);
		bst.insert(bst.root, 10);
		bst.insert(bst.root, 30);
		bst.insert(bst.root, 5);
		bst.insert(bst.root, 4);
		bst.insert(bst.root, 3);
		bst.insert(bst.root, 25);
		bst.insert(bst.root, 26);
		bst.insert(bst.root, 7);
		bst.insert(bst.root, 8);
		bst.insert(bst.root, 6);
		System.out.println("\n Inorder :- \n");
		bst.inOrder(bst.root);
		System.out.println("\n PreOrder :- \n");
		bst.preOrder(bst.root);
		System.out.println("\n PostOrder :- \n");
		bst.postOrder(bst.root);
		System.out.println("\n LevelOrder :- \n");
		bst.levelOrder(bst.root);
		System.out.println("\n Trying to find 2 :- \n");
		System.out.println(bst.find(bst.root, 2));
		System.out.println("\n Trying to find 10 :- \n");
		System.out.println(bst.find(bst.root, 10));
		System.out.println("\n Deleting : Case 1 : Node with no children : 4 :- \n");
		System.out.println(bst.delete(bst.root, 4));
		System.out.println("\n Deleting : Case 2 : Node with 1 children : 4 :- \n");
		System.out.println(bst.delete(bst.root, 25));
		System.out.println("\n Deleting : Case 3 : Node with >1 children : 4 :- \n");
		System.out.println(bst.delete(bst.root, 5));
		System.out.println("\n Levelorder after deletes :- \n");
		bst.levelOrder(bst.root);


	}


}
