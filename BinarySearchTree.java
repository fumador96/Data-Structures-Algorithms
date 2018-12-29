package PracticeDSA;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
public class BinarySearchTree {
	private class Node{
		int data;
		Node left;
		Node right;
		Node(int data, Node left, Node right){
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
	private Node root;
	private int size;
	BinarySearchTree(){
		this.root = null;
		this.size = 0;
	}
	
	public int size() {
		return this.size;
	}
	
	public void add(int data) {
		this.add(data,this.root);
	}
	
	private void add(int data, Node node) {
		if(node==null) {
			this.root = new Node(data,null,null);
			this.size++;
			return;
		}
		if(data>node.data && node.right!=null) {
			this.add(data,node.right);
		}
		else if(data>node.data && node.right==null) {
			node.right = new Node(data,null,null);
			this.size++;
			return;
		}
		if(data<node.data && node.left!=null) {
			this.add(data,node.left);
		}
		else if(data<node.data && node.left==null) {
			node.left = new Node(data,null,null);
			this.size++;
			return ;
		}
	}
	
	public int maxTerm() {
		return maxTerm(this.root);
	}
	
	private int maxTerm(Node node) {
		if(node.right==null) {
			return node.data;
		}
		return maxTerm(node.right);
	}
	
	public int minTerm() {
		return minTerm(this.root);
	}
	
	private int minTerm(Node node) {
		if(node.left==null) {
			return node.data;
		}
		return minTerm(node.left);
	}
	
	public void removeNode(int data) {
		this.removeNode(data,this.root,null);
		this.size--;
	}
	
	private void removeNode(int data, Node node,Node last) {
		if(node.data!=data) {
			if(data<node.data) {
				this.removeNode(data,node.left,node);
			}
			else if(data>node.data){
				this.removeNode(data, node.right,node);
			}	
		}
		else {
			if(node.left==null && node.right==null) {
				if(node.data>last.data) {
					last.right = null;
				}
				else {
					last.left = null;
				}
			}
			else if(node.left!=null && node.right==null) {
				if(node.data>last.data) {
					last.right = node.left;
				}
				else {
					last.left = node.left;
				}
			}
			else if(node.right!=null && node.left==null) {
				if(node.data>last.data) {
					last.right = node.right;
				}
				else {
					last.left = node.right;
				}
			}
			else if(node.right!=null && node.left!=null){
				int max = this.maxTerm(node.left);
				node.data = max;
				this.removeNode(max, node.left, node);
			}
		}
	}
	
	public void display() {
		display(this.root);
	}
	
	private void display(Node node) {
		if(node.left!=null) {
			System.out.print(node.left.data+"=>");
		}else {
			System.out.print("END=>");
		}
		System.out.print(node.data);
		if(node.right!=null) {
			System.out.print("<="+node.right.data);
		}else {
			System.out.print("<=END");
		}
		System.out.println();
		if(node.left!=null) {
			this.display(node.left);
		}
		if(node.right!=null) {
			this.display(node.right);
		}
		
	}
	
	public void inOrder(Queue<Node> inq) {
		this.inOrder(inq,this.root);
	}

	private void inOrder(Queue<Node> inq,Node node) {
		if (node == null) {
			return;
		}
		inOrder(inq,node.left);
		inq.add(node);
		inOrder(inq,node.right);
	}
	
	public void BSTSumNodes(){
	    this.BSTSumNodes(this.root);
	}
	
	private void BSTSumNodes(Node node){
	    Queue<Node> inq = new LinkedList<Node>();
	    this.inOrder(inq);
	    int sum = 0;
	    for(Node s:inq) {
	    	  sum = sum + s.data;
	    }
	    while(!inq.isEmpty()) {
	    	Node temp = inq.poll();
	    	int d = temp.data;
	    	temp.data = sum-temp.data;
	    	sum = sum - d;
	    }
	}
	
	public void levelOrder() {
		this.levelOrder(this.root);
		System.out.println();
	}

	private void levelOrder(Node node) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (queue.isEmpty() == false) {
			Node temp = queue.poll();
			System.out.print(temp.data + " ");
			if (temp.left != null && temp.right != null) {
				queue.add(temp.left);
				queue.add(temp.right);
			}
		}
	}


	public static void main(String[] args) {
		BinarySearchTree BST = new BinarySearchTree();
//		Scanner sc = new Scanner(System.in);
//		int k = sc.nextInt();
//		while(k!=-1){
//		    BST.add(k);
//		    k = sc.nextInt();
//		}
		BST.add(4);
		BST.add(2);
		BST.add(5);
		BST.add(1);
		BST.add(3);		
		//BST.levelOrder();
//		BST.BSTSumNodes();
		BST.levelOrder();
	}

}
