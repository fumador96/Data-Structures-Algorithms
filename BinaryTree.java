package PracticeDSA;
import java.util.Scanner;import java.util.*;
public class BinaryTree {
	private class Node {
		int data;
		Node left;
		Node right;

		Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	private Node root;
	private int size;

	BinaryTree() {
		Scanner sc = new Scanner(System.in);
		this.root = takeInput(sc, null, false);
	}

	private Node takeInput(Scanner sc, Node parent, boolean isLeftorRight) {
		if (parent == null) {
			System.out.println("Enter the value of root node");
		} else {
			if (isLeftorRight) {
				System.out.println("Enter the value of left node of " + parent.data);
			} else {
				System.out.println("Enter the value of right node of " + parent.data);
			}
		}
		int value = sc.nextInt();
		Node node = new Node(value, null, null);
		this.size++;
		System.out.println("Do you have a left child for " + node.data);
		boolean child = sc.nextBoolean();
		if (child) {
			node.left = takeInput(sc, node, true);
		}

		System.out.println("Do you have a right child for " + node.data);
		child = sc.nextBoolean();
		if (child) {
			node.right = takeInput(sc, node, false);
		}
		return node;
	}

	public void display() {
		display(this.root);
	}

	private void display(Node node) {
		if (node.left != null) {
			System.out.print(node.left.data + "=>");
		} else {
			System.out.print("END=>");
		}
		System.out.print(node.data);
		if (node.right != null) {
			System.out.print("<=" + node.right.data);
		} else {
			System.out.print("<=END");
		}
		System.out.println();
		if (node.left != null) {
			this.display(node.left);
		}
		if (node.right != null) {
			this.display(node.right);
		}

	}

	public int size2() {
		return this.size2(this.root);
	}

	private int size2(Node node) {
		int count = 0;
		if (node == null) {
			return count;
		} else {
			count = count + 1;
		}
		if (node.left != null) {
			count = count + this.size2(node.left);
		}
		if (node.right != null) {
			count = count + this.size2(node.right);
		}
		return count;
	}

	public int height() {
		return this.height(this.root);
	}

	private int height(Node node) {
		if (node == null) {
			return -1;
		} else {
			int heightL = 1 + height(node.left);
			int heightR = 1 + height(node.right);
			if (heightL > heightR)
				return heightL;
			return heightR;
		}
	}

	public int maxOfTree() {
		return this.maxOfTree(this.root);
	}

	private int maxOfTree(Node node) {
		if (node == null) {
			return -1;
		} else {
			int maxL = maxOfTree(node.left);
			int maxR = maxOfTree(node.right);
			if (maxL >= maxR) {
				if (maxL > node.data) {
					return maxL;
				}
				return node.data;
			} else {
				if (maxR > node.data) {
					return maxR;
				}
				return node.data;
			}
		}
	}

	public int minOfTree() {
		return this.minOfTree(this.root);
	}

	private int minOfTree(Node node) {
		if (node == null) {
			return Integer.MAX_VALUE;
		} else {
			int minL = minOfTree(node.left);
			int minR = minOfTree(node.right);
			if (minL <= minR) {
				if (minL < node.data) {
					return minL;
				}
				return node.data;
			} else {
				if (minR < node.data) {
					return minR;
				}
				return node.data;
			}
		}
	}

	public boolean find(int data) {
		return this.find(data, this.root);
	}

	private boolean find(int data, Node node) {
		if (node == null) {
			return false;
		}
		if (node.data == data) {
			return true;
		}
		boolean left = find(data, node.left);
		boolean right = find(data, node.right);
		return left || right;
	}

	public void preOrder() {
		this.preOrder(this.root);
	}

	private void preOrder(Node node) {
		if (node == null) {
			return;
		}
		System.out.print(node.data + " ");
		preOrder(node.left);
		preOrder(node.right);
	}
	
	public void preorderUsingStack() throws Exception{
		if(this.root==null) {
			return;
		}
		else {
			this.preorderUsingStack(this.root);
		}
	}

	private void preorderUsingStack(Node node) throws Exception{
		Stack<Node> stack = new Stack<>();
		stack.push(this.root);
		while(!stack.isEmpty()) {
			System.out.print(stack.peek().data+" ");
			Node temp = stack.pop();
			if(temp.right!=null) {
				stack.push(temp.right);
			}
			if(temp.left!=null) {
				stack.push(temp.left);
			}
		}
	}
	// Analyse the algorithm properly.
	public void preorderMorrisTraversal() {
		if(this.root==null) {
			return;
		}
		else {
			this.preorderMorrisTraversal(this.root);
		}
	}
	// 1 true 2 true 4 false false true 5 true 6 false false true 7 true 10 false false false true 3 true 9 false false true 8 true 11 false false false
	private void preorderMorrisTraversal(Node node) {
		Node curr = this.root;
		while(curr!=null) {
			if(curr.left==null) {
				System.out.print(curr.data+" ");
				curr = curr.right;
			}
			else {
				Node temp = curr.left;
				while(temp.right!=null && temp.right!=curr)
					temp = temp.right;
				if(temp.right==curr) {
					temp.right = null;
					curr = curr.right;
				}
				else {
					System.out.print(curr.data+" ");
					temp.right = curr;
					curr = curr.left;
				}
			}
		}
	}
	
	public void inOrder() {
		this.inOrder(this.root);
	}

	private void inOrder(Node node) {
		if (node == null) {
			return;
		}
		inOrder(node.left);
		System.out.print(node.data + " ");
		inOrder(node.right);
	}
	
	private class Sum{
        int sum;
        Sum(int sum){
            this.sum = sum;
        }
    }
	
	public void modify(Node root){
	    Sum s = new Sum(0);
	    this.inorder(root,s);
	    this.inOrder();
    }
	
	private void inorder(Node node, Sum s) {
		if (node == null) {
			return;
		}
		inorder(node.right,s);
		node.data += s.sum;
		s.sum = node.data;
		inorder(node.left,s);
	}
	
	public void inorderUsingStcak() throws Exception{
		this.inorderUsingStcak(this.root);
	}
	
	private void inorderUsingStcak(Node node) throws Exception{
		Stack<Node> stack = new Stack<>();
		stack.push(node);
		Node temp = node.left;
		while(temp!=null) {
			stack.push(temp);
			temp = temp.left;
		}
		while(!stack.isEmpty()) {
			Node pop = stack.pop();
			System.out.print(pop.data+" ");
			Node tem = pop.right;
			while(tem!=null) {
				stack.push(tem);
				tem = tem.left;
			}
		}
		
	}
	//Time complexity is O(n) and Space complexity is O(1).
	public void inorderMorrisTraversal() {
		this.inorderMorrisTraversal(this.root);
	}
	
	private void inorderMorrisTraversal(Node node) {
		if(node==null) {
			return;
		}
		Node curr = this.root;
		while(curr!=null) {
			if(curr.left==null) {
				System.out.print(curr.data+" ");
				curr = curr.right;
			}
			else {
				Node pre = curr.left;
				while(pre.right!=null && pre.right!=curr)
					pre = pre.right;
				if(pre.right==null) {
					pre.right = curr;
					curr = curr.left;
				}
				else {
					pre.right = null;
					System.out.print(curr.data+" ");
					curr = curr.right;
				}
				
			}
		}
	}

	public void postOrder() {
		this.postOrder(this.root);
	}

	private void postOrder(Node node) {
		if (node == null) {
			return;
		}
		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.data + " ");
	}

	public void levelOrder() {
		this.levelOrder(this.root);
	}

	private void levelOrder(Node node) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		// int count = 0;
		// int size = 1;
		while (queue.isEmpty() == false) {
			Node temp = queue.poll();
			size--;
			System.out.print(temp.data + " ");
			if (temp.left != null && temp.right != null) {
				queue.add(temp.left);
				queue.add(temp.right);
			}
		}
	}
	
	public void levelOrderDirectionChange() {
		if(this.root==null) {
			return;
		}
		this.levelOrderDirectionChange(this.root);
	}
	
	private  void levelOrderDirectionChange(Node root) {
		Queue<Node> q = new LinkedList<>();
	    Stack<Node> s = new Stack<>();
	    if(root.left==null && root.right==null){
	        System.out.println(root.data);
	    }
	    else{
	        q.add(root);
	        boolean dir = false;
	        int count = 0;
	        int qSize = 0;
	        
	        while(!q.isEmpty()){
	            count++;
	            qSize = q.size();
	            for(int i=0;i<qSize;i++){
	                Node temp = q.poll();
	                if(dir==false){
	                    System.out.print(temp.data+" ");
	                }
	                else{
	                    s.push(temp);
	                }
	                if(temp.left!=null){
	                    q.add(temp.left);
	                }
	                if(temp.right!=null){
	                    q.add(temp.right);
	                }
	            }
	            
	            if(dir==true){
	                while(!s.isEmpty()){
	                    System.out.print(s.pop().data+" ");
	                }
	            }
	            if(count==2){
	                dir = !dir;
	                count=0;
	            }
	            System.out.println();
	        }
	    }
	}
	
	public void reverseLevelOrder() throws Exception{
		if(this.root==null) {
			return;
		}else {
			this.reverseLevelOrder(this.root);
		}
	}
	
	private void reverseLevelOrder(Node node) throws Exception{
		Queue<Node> queue = new LinkedList<>();
		DynamicStack stack = new DynamicStack();
		queue.add(node);
		stack.push(node.data);
		while(!queue.isEmpty()) {
			Node temp = queue.poll();
			if(temp.right!=null) {
				queue.add(temp.right);
				stack.push(temp.right.data);
			}
			if(temp.left!=null) {
				queue.add(temp.left);
				stack.push(temp.left.data);
			}
		}
		while(!stack.isEmpty()) {
			System.out.print(stack.pop()+" ");
		}
	}
	
	public void verticalTraversal() {
		if(this.root==null) {
			return;
		}
		this.verticalTraversal(this.root);
	}
	
	private void verticalTraversal(Node node) {
		values data = new values();
		findMinMax(node, data, 0);
		for(int line=data.min;line<=data.max;line++) {
			printEachLine(node,line,0);
			System.out.println();
		}
	}
	
	private void printEachLine(Node node,int line, int hor_dis) {
		if(node==null) {
			return;
		}
		if(hor_dis==line) {
			System.out.print(node.data+" ");
		}
		
		printEachLine(node.left,line,hor_dis-1);
		printEachLine(node.right,line,hor_dis+1);
	}
	
	public void topView() {
		if(this.root==null) {
			return;
		}
		this.topView(this.root);
	}
	
	private void topView(Node node) {
		values value = new values();
		findMinMax(node, value, 0);
		for(int line=value.min;line<=value.max;line++) {
			printEachLineTop(node,line,0);
			System.out.println();
		}
	}
	
	private void printEachLineTop(Node node, int line, int hor_dis) {
		if(node==null) {
			return;
		}
		if(hor_dis==line) {
			System.out.print(node.data+" ");
			return;
		}
		
		printEachLineTop(node.left,line,hor_dis-1);
		printEachLineTop(node.right,line,hor_dis+1);
	}
	
	public void diagonalTraversalUsingQueue() {
		if(this.root==null){
	        return;
	    }
		this.diagonalTraversalUsingQueue(this.root);
	}
	
	private void diagonalTraversalUsingQueue(Node node){
	    Queue<Node> queue = new LinkedList<>();	    
	    queue.add(node);
	    queue.add(null);
	    while(!queue.isEmpty()){
	        Node temp = queue.poll();
	        while(queue.peek()!=null || (temp!=null && temp.right!=null)){
	        	System.out.print(temp.data+" ");
	            if(temp.left!=null){
	                queue.add(temp.left);
	            }
	            if(temp.right!=null){
	                temp = temp.right;
	            }
	            else if(queue.peek()!=null){
	                temp = queue.poll();
	            }
	        }
	        if(temp!=null){
	            System.out.print(temp.data);
	        }
	        queue.add(null);
	        queue.poll();
	        System.out.println();
	    }
	}

	
	private void findMinMax(Node node, values data, int hor_dis) {
		if(node==null) {
			return;
		}
		if(hor_dis<data.min) {
			data.min = hor_dis;
		}
		else if(hor_dis>data.max) {
			data.max = hor_dis;
		}
		
		findMinMax(node.left, data, hor_dis-1);
		findMinMax(node.right, data, hor_dis+1);
	}
	
	private class values{
		int min;
		int max;
	}
	
	public int leastCommonAncestor(int a, int b) {
		if (a == b) {
			return a;
		}
		return leastCommonAncestor(a, b, this.root).data;
	}

	private Node leastCommonAncestor(int a, int b, Node node) {
		if (node == null) {
			return null;
		}
		Node oldAnc = node;
		Node ansL = null;
		Node ansR = null;
		if (this.find(a, node) && this.find(b, node)) {
			ansL = leastCommonAncestor(a, b, node.left);
			if (ansL == null)
				ansR = leastCommonAncestor(a, b, node.right);
			if (ansL == null && ansR == null) {
				return oldAnc;
			} else if (ansL != null)
				return ansL;
			else
				return ansR;
		} else {
			return null;
		}
	}
	
	//Another method of finding LCA, space complexity is O(n).
	public int lca(Node A, int B, int C) {
        ArrayList<Integer> l = new ArrayList<Integer>();
        ArrayList<Integer> r = new ArrayList<Integer>();
        int lef = this.store(A,l,B);
        int rit = this.store(A,r,C);
        // System.out.println(l);
        // System.out.println(r);
        if(lef==0 || rit==0){
            return -1;
        }
        int i = 0;
        for(i=0;i<l.size() && i<r.size();i++){
            int a = l.get(i);
            int b = r.get(i);
            if(a!=b){
                return l.get(i-1);
            }
        }
        return l.get(i-1);
    }
    
    private int store(Node root, ArrayList<Integer> list, int n){
        if(root==null){
            return 0;
        }
        list.add(root.data);
        if(root.data==n){
            return 1;
        }
        int ans = this.store(root.left,list,n) + 
        this.store(root.right,list,n);
        if(ans!=1)
            list.remove(list.size()-1);
        return ans;    
    }
	
	private class Ans{
        int lef;
        // int rit;
        boolean flag;
        Ans(int lef, boolean flag){
            this.lef = lef;
            // this.rit = rit;
            this.flag = flag;
        }
    }
	
	public int countDist(int a, Node node) {
		Ans ans = new Ans(0,false);
        this.count(a,node,ans);
		System.out.println(ans.lef);
		return 0;
	}
	
	private void count(int a, Node node, Ans ans){
        if(node==null){
            return;
        }
        if(node.data==a){
            ans.flag = true;
            ans.lef++;
            return;
        }
        this.count(a,node.left,ans);
        if(ans.flag){
            ans.lef++;
            return;
        }
        this.count(a,node.right,ans);
        if(ans.flag){
            ans.lef++;
            return;
        }
    }

	public boolean isBST() {
		return this.isBST(this.root,Integer.MIN_VALUE,Integer.MAX_VALUE);
	}

	private boolean isBST(Node node, int min, int max) {
		if(node==null) {
			return true;
		}
		if(node.data<min || node.data>max)
			return false;
		else if(!isBST(node.left, min, node.data))
			return false;
		else if(!isBST(node.right,node.data,max))
				return false;
		else
			return true;
	}
	
	public void boundaryNodes() {
		Node left = null;
		Node right = null;
		Node node = this.root;
		while(node.left!=null){
		    node = node.left;
		}
		left = node;
		node  = root;
		while(node.right!=null){
		    node = node.right;
		}
		right = node;
		this.boundaryNodes(this.root,left,right);
	}
	
	
	private void boundaryNodes(Node root,Node left, Node right) {
		
		this.leftBoundary(root.left);
		System.out.print(root.data+" ");
		this.rightBoundary(root.right);
		this.leaves(root,left,right);
	}

	private void leftBoundary(Node node){
	    if(node==null){
	        return;
	    }
	    this.leftBoundary(node.left);
	    System.out.print(node.data+" ");
	}

	private void rightBoundary(Node node){
	    if(node==null){
	        return;
	    }
	    System.out.print(node.data+" ");
	    this.rightBoundary(node.right);
	    }

	private void leaves(Node node,Node left, Node right){
	    if(node.left==null && node.right==null && node!=left && node!=right){
	        System.out.print(node.data+" ");
	        return;
	    }
	    if(node.right!=null){
	        this.leaves(node.right,left,right);
	    }
	    if(node.left!=null){
	        this.leaves(node.left,left,right);
	    }
	}
	
	public void mirrorImageTree() {
		if(this.root==null) {
			return;
		}
		this.mirrorImageTree(this.root);
	}
	
	private void mirrorImageTree(Node node) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(node);
		while(!queue.isEmpty()) {
			Node left = queue.peek().left;
			if(left!=null) {
				queue.add(left);
			}
			Node right  = queue.peek().right;
			if(right!=null) {
				queue.add(right);
			}
			Node front = queue.poll();
			front.left = right;
			front.right = left;
		}
	}
	
	public int havingChildx(int x) {
		return this.havingChildx(this.root,x);
	}
	
	private int havingChildx(Node node, int x) {
		int sum = 0;
		if(node==null) {
			return 0;
		}
		if((node.left!=null && node.left.data==x) || (node.right!=null && node.right.data==x)) {
			sum = sum + node.data + this.havingChildx(node.left, x) + this.havingChildx(node.right, x);
		}
		else {
			sum = sum + this.havingChildx(node.left, x) + this.havingChildx(node.right, x);
		}
		return sum;
	}
	
	public int leftLeafSum() {
		return this.leftLeafSum(this.root);
	}
	private int leftLeafSum(Node node){
		int sum = 0;
		if(node==null){
          return 0;
		}
	    if(this.isLeaf(node)) {
	    	return sum + node.data;
	    }
	    sum = sum + this.leftLeafSum(node.left);
	    if(!this.isLeaf(node.right)) {
	    	sum = sum + this.leftLeafSum(node.right);
	    }
	    return sum;
    }
	
	public int sumOfLeaf() {
		return this.sumOfLeaf(this.root);
	}
	
	private int sumOfLeaf(Node node) {
		int sum = 0;
		if(node==null){
			return 0;
		}
		if(this.isLeaf(node)) {
			return node.data;
		}
		sum = sum + this.sumOfLeaf(node.left) + this.sumOfLeaf(node.right);
		return sum;
	}
	
	private boolean isLeaf(Node node) {
		if(node==null) {
			return false;
		}
		if(node.left==null && node.right==null) {
			return true;
		}
		return false;
	}
	
	public void printIfSingleChild() {
		if(this.root==null) {
			return;
		}
		this.printIfSingleChild(this.root);
	}
	
	private void printIfSingleChild(Node node) {
		if(node==null) {
			return;
		}
		if(this.onlyChild(node)) {
			System.out.println(this.child(node));
		}
		this.printIfSingleChild(node.left);
		this.printIfSingleChild(node.right);
	}
	
	private boolean onlyChild(Node node) {
		if(node==null) {
			return false;
		}
		if((node.left==null && node.right!=null) || (node.right==null && node.left!=null)) {
			return true;
		}
		return false;
	}
	
	private int child(Node node) {
		if(node.left==null && node.right!=null) {
			return node.right.data;
		}
		else {
			return node.left.data;
		}
	}
	
	public void printDiagonalSum(){
	    this.printDiagonalSum(this.root);
	}
	
	private void printDiagonalSum(Node node){
	    Queue<Node> q = new LinkedList<>();
	    if(this.root==null){
	        System.out.println(0);
	        return;
	    }
	    q.add(this.root);
	    q.add(null);
	    int sum = 0;
	    while(!q.isEmpty()){
	        Node temp = q.poll();
	        while(q.peek()!=null || (temp!=null && temp.right!=null)){
	            sum = sum + temp.data;
	            if(temp.left!=null){
	                q.add(temp.left);
	            }
	            if(temp.right!=null){
	                temp = temp.right;
	            }
	            else if(q.peek()!=null){
	                temp = q.poll();
	            }
	        }
	        if(temp!=null){
	            sum = sum + temp.data;
	        }
	        System.out.print(sum+" ");
	        sum = 0;
	        q.poll();
	        if(!q.isEmpty())
	        	q.add(null);
	    }
	}
	
	public int sumOfLongRootToLeafPath() {
		if(this.root==null) {
			return 0;
		}
		return this.sumOfLongRootToLeafPath(this.root,0);
	}
	
	int maxsum = 0;
	int maxcount = 0;
	private int sumOfLongRootToLeafPath(Node node,int count) {
		if(node==null) {
			return 0;
		}
		int sum = 0;
		sum = sum + node.data;
		if(node.left!=null) {
			this.sumOfLongRootToLeafPath(node.left, count+1);
		}
		if(node.right!=null) {
			this.sumOfLongRootToLeafPath(node.right, count+1);
		}
		if(count>maxcount) {
			maxcount = count;
			maxsum = sum;
		}
		return maxsum;
	}
	
	//Print all the Root to Leaf Paths With Sum = K.
	public ArrayList<ArrayList<Integer>> pathSum(Node A, int B) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(A==null){
            return res;
        }
        ArrayList<Integer> ans = new ArrayList<Integer>();
        this.pathSum(A,B,ans,res);
        return res;
    }
    
    private void pathSum(Node root, int k, ArrayList<Integer> ans, ArrayList<ArrayList<Integer>> res){
        if(root==null){
            return;
        }
        ans.add(root.data);
        if(this.isLeaf(root)){
            if(k==root.data){
                ArrayList<Integer> answer = new ArrayList<Integer>();
                int n = ans.size();
                for(int i=0;i<n;i++){
                    answer.add(ans.get(i));
                }
                res.add(answer);
            }
            return;
        }
        if(root.left!=null){    
            this.pathSum(root.left,k-root.data,ans,res);
            ans.remove(ans.size()-1);
        }
        if(root.right!=null){    
            this.pathSum(root.right,k-root.data,ans,res);
            ans.remove(ans.size()-1);
        }
    }
	
	//Deleting paths whose sum from root to leaf is less than key.
	public void rootLeafPathDeletion(int key){
        if(this.root==null){
            return;
        }
        this.rootLeafPathDeletion(this.root,key,null,false);
     }

    private void rootLeafPathDeletion(Node node, int sum,Node par,boolean leftRight){
        if(node==null){
            return;
        }
        
        this.rootLeafPathDeletion(node.left,sum-node.data,node,false);
        this.rootLeafPathDeletion(node.right,sum-node.data,node,true);
        
        if(this.isLeaf(node)){
            if(sum-node.data>0){
                if(leftRight==false){
                    par.left=null;
                }
                else{
                    par.right=null;
                }
            }
            return;
        }
    }
    
    //Maximum sum between two leaves of a tree.
    public int maxSumBetweenLeaves(){
        if(this.root==null){
            return 0;
        }
        if((this.root.left!=null && this.root.right==null) || (this.root.left==null && this.root.right!=null)){
            return Integer.MIN_VALUE;
        }
        
        this.maxSumBetweenLeaves(this.root);
        return max;
    }
    int max = Integer.MIN_VALUE;
    private int maxSumBetweenLeaves(Node node){
        if(node==null){
            return 0;
        }
        if(this.isLeaf(node)){
            return node.data;
        }
        int left = this.maxSumBetweenLeaves(node.left);
        int right = this.maxSumBetweenLeaves(node.right);


        if(left+right+node.data>max){
            max = left+right+node.data;
        }
       return Math.max(left,right) + node.data;
   }
	
    //Print maximum sum path from leaf to root.
    int mx = Integer.MIN_VALUE;
    Stack<Node> s = new Stack<>();
    Queue<Node> q = new LinkedList<>();
    public void maxSumLeafToRoot(){
        this.maxSumLeafToRoot(this.root,0);
        for(int i=0;i<s.size();i++){
            System.out.print(s.pop().data+" ");
        }
        System.out.print(this.root.data);
        System.out.println();
        System.out.println("Maximum sum is "+mx);
    }
    
    private void maxSumLeafToRoot(Node node, int sum){
        if(node==null){
            int si = q.size();
            if(sum>mx){
                mx = sum;
                for(int i=0;i<s.size();i++){
                    s.pop();
                }
                for(int j=0;j<si;j++){
                    s.push(q.poll());
                }
            }
            else{
                for(int j=0;j<si;j++){
                    q.poll();
                }
            }
            return;
        }
            q.add(node);
            this.maxSumLeafToRoot(node.left,sum+node.data);
            q.add(node);
            this.maxSumLeafToRoot(node.right,sum+node.data);
    }
    
    //Maximum sum among all the subtrees of a tree.
    int maax = Integer.MIN_VALUE;
    public void maxSumofSubtree(){
        if(this.root!=null)
            this.maxSumofSubtree(this.root);
        System.out.println(maax);    
    }
    
    private int maxSumofSubtree(Node node){
        if(node==null){
            return 0;
        }
        if(this.isLeaf(node)){
            return node.data;
        }
        int left = this.maxSumofSubtree(node.left);
        int right = this.maxSumofSubtree(node.right);
        if(maax<left || maax<right || maax<left+right+node.data)
            maax = Math.max(Math.max(left,right),left+right+node.data);
        return left+right+node.data;
    }
    
    private class Index{
        int i;
        Index(int i){
            this.i = i;
        }
    }
    
    //Check if a given array can represent Preorder Traversal of Binary Search Tree.
    public int preorderbst(int n, int[] pre){
        if(n<3){
            return 1;
        }
        Index index = new Index(0);
        boolean ans = this.preorderBST(n,pre,0,n,index);
        return ans ? 1 : 0;
    }
    
    private boolean preorderBST(int n, int[] pre, int low, int high, Index index){
        if(index.i>=n || low>=high){
            return true;
        }
        int root = pre[index.i];
        if(low==high-1){
            return true;
        }
        index.i += 1;
        boolean flag = false;
        int j=0;
        for(j=index.i;j<high;j++){
            if(flag&&pre[j]<root){
                return false;
            }
            if(pre[j]>root){
                flag=true;
            }
        }
        return this.preorderBST(n,pre,index.i,j,index) && this.preorderBST(n,pre,j,high,index);
    }
    
    //Maximum sum from a tree with adjacent levels not allowed.
    public void maxSumAdjNotAllowed(){
    	if(this.root==null) {
    		System.out.println(0);
    		return;
    	}
    	System.out.println(this.maxSumAdjNotAllowed(this.root));
    }
    
    private int maxSumAdjNotAllowed(Node node) {
    	Queue<Node> queue = new LinkedList<>();
    	queue.add(this.root);
    	queue.add(null);
    	int odd = 0;
    	int even = 0;
    	int count = 1;
    	while(!queue.isEmpty()) {
    		Node temp = queue.poll();
    		if(temp!=null && temp.left!=null) {
    			queue.add(temp.left);
    		}
    		if(temp!=null && temp.right!=null) {
    			queue.add(temp.right);
    		}
    		if(temp!=null && count%2!=0) {
    			odd = odd + temp.data;
    		}
    		if(temp!=null && count%2==0){
    			even = even + temp.data;
    		}
    		if(temp==null && !queue.isEmpty()) {
    			queue.add(null);
    			count = count + 1;
    		}
    	}
    	System.out.println(odd);
    	System.out.println(even);
    	return Math.max(even, odd);
    }
    
    private class Answer{
        int data = Integer.MAX_VALUE;
        int diff = Integer.MAX_VALUE;
    }
    
    public int maxDiff(Node root, int k){
        if(root==null){
            return -1;
        }
        Answer ans = new Answer();
        ans.data = root.data;
        if(k>root.data){
            ans.diff = Math.abs(k-root.data);
            this.inorder(root.right,k,ans);
        }else if(k==root.data){
            ans.data = root.data;
            ans.diff = 0;
        }
        else{
            ans.diff = Math.abs(k-root.data);
            this.inorder(root.left,k,ans);
        }    
        return ans.data;
    }
    
    private void inorder(Node root, int k, Answer ans){
        if(root==null){
            return;
        }
        if(Math.abs(k-root.data)<ans.diff){
            ans.data = root.data;
            ans.diff = Math.abs(k-root.data);
        }
        this.inorder(root.left,k,ans);
        this.inorder(root.right,k,ans);
    }
    
    public Node construcTree(int arr[], int n){
        if(arr.length==0){
            return null;
        }
        Node root = new Node(0,null,null);
        Node[] tree = new Node[n];
        tree[0] = root;
        this.createTree(arr,n,1,tree);
        return root;
    }
    
    private void createTree(int[] arr, int n, int i, Node[] tree){
        if(i==n){
            return;
        }
        if(i<n){
            Node node = new Node(i,null,null);
            tree[i] = node;
            if(tree[arr[i]]==null){
                tree[arr[i]] = new Node(arr[i],null,null);
            }
            if(tree[arr[i]].left==null)
            	tree[arr[i]].left = node;
            else
            	tree[arr[i]].right = node;
        }
        this.createTree(arr,n,i+1,tree);
    }
    
    public void printCorner(Node node){
        if(node==null){
            return;
        }
        Queue<Node> q = new LinkedList<Node>();
        q.add(node);
        q.add(null);
        boolean first = true;
        while(!q.isEmpty()){
            Node temp = q.poll();
            if(temp!=null){
                if(first || q.peek()==null){
                    System.out.print(temp.data+" ");
                    first = !first;   
                }
                if(temp.left!=null){
                    q.add(temp.left);
                }
                if(temp.right!=null){
                    q.add(temp.right);
                }
                // if(q.peek()==null){
                //     System.out.print(q.peek().data+" ");
                // }
            }
            else{
                first = !first;
                if(!q.isEmpty()){
                    q.add(null);
                }
            }
        }
    }
    
    public  void printExtremeNode(Node node){
        Stack<Node> first = new Stack<Node>();
        Stack<Node> second = new Stack<Node>();
        first.push(null);
        first.push(node);
        boolean f = true;
        boolean s = false;
        while(!first.isEmpty() || !second.isEmpty()){
            while(!first.isEmpty()){
                Node temp = first.pop();
                if(temp!=null){
                    if(second.isEmpty()){
                        second.push(null);
                    }
                    if(f){
                        System.out.print(temp.data+" ");
                        f = !f; 
                    }
                    if(temp.right!=null)    
                        second.push(temp.right);
                    if(temp.left!=null)
                        second.push(temp.left);
                }else{
                    s = !s;
                }
            }
            while(!second.isEmpty()){
                Node temp = second.pop();
                if(temp!=null){
                    if(first.isEmpty()){
                        first.push(null);
                    }
                    if(s){
                        System.out.print(temp.data+" ");
                        s = !s; 
                    }
                    if(temp.left!=null)
                        first.push(temp.left);
                    if(temp.right!=null)    
                        first.push(temp.right);
                }else{
                    f = !f;
                }
            }
        }
    }
	// 50 true 30 true 20 false false true 40 false false true 70 true 60 false false true 80 false false
	//
	public static void main(String[] args) throws Exception{
		BinaryTree tree = new BinaryTree();
		tree.display();
		// System.out.println(tree.size2());
		// System.out.println(tree.height());
		// System.out.println(tree.maxOfTree());
		// System.out.println(tree.minOfTree());
		// System.out.println(tree.find(2));
		// System.out.println("Preorder Traversal=>");
//		tree.preOrder();
		// System.out.println();
		//System.out.println("inorder Traversal=>");
		//tree.inOrder();
		System.out.println("****************");
		// System.out.println("Postorder Traversal=>");
		// tree.postOrder();
		// System.out.println();
		// System.out.println("Levelorder Traversal=>");
		//tree.levelOrder();
		//System.out.println();
		// System.out.println(tree.leastCommonAncestor(12, 50));
//		System.out.println(tree.isBST());
		//tree.reverseLevelOrder();
//		tree.inorderUsingStcak();
//		tree.preorderMorrisTraversal();
//		tree.mirrorImageTree();
//		tree.verticalTraversal();
//		tree.boundaryNodes();
//		tree.diagonalTraversalUsingQueue();
//		tree.levelOrderDirectionChange();
//		System.out.println(tree.havingChildx(2));
//		System.out.println(tree.leftLeafSum());
//		System.out.println(tree.sumOfLeaf());
//		tree.printDiagonalSum();
//		System.out.println(tree.sumOfLongRootToLeafPath());
//		tree.rootLeafPathDeletion(125);
//		tree.display();
//		System.out.println(tree.maxSumBetweenLeaves());
//		tree.maxSumLeafToRoot();
//		tree.maxSumofSubtree();
//		tree.maxSumAdjNotAllowed();
//		tree.topView();
//		tree.countDist(37,tree.root);
//		tree.modify(tree.root);
//		System.out.println(tree.maxDiff(tree.root, 19));
//		int[] arr = {-1, 0, 0, 1, 1, 3, 5};
//		tree.display(tree.construcTree(arr, 7));
		tree.printExtremeNode(tree.root);
	}

}
