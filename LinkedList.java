package PracticeDSA;
import java.util.*;
public class LinkedList {
	
	private class Node{
		int data;
		Node next;
		Node(int data,Node next){
			this.data = data;
			this.next = next;
		}
	}
	private Node head;
	private Node tail;
	private int size;
	
	public LinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		if(this.size==0)
			return true;
		else
			return false;
	}
	
	public void addFirst(int data) {
		Node node = new Node(data,this.head);
		this.head = node;
		if(this.size==0)
			this.tail = node;
		this.size++;
	}
	
	public void addLast(int data) {
		Node node = new Node(data,null);
		if(this.size==0) {
			this.head = node;
		}else {
			this.tail.next = node;
		}
		this.tail = node;
		this.size++;
	}
	
	private Node getNode(int index) throws Exception{
		if(index<0 || index>this.size-1) {
			throw new Exception("Invalid index");
		}
		Node temp = this.head;
		int count = 0;
		while(count<index) {
			temp = temp.next;
			count++;
		}
		return temp;
	}
	
	public void addAt(int data,int index) throws Exception{
		if(index<0 || index>this.size) {
			throw new Exception("Invalid index");
		}else if(index==0) {
			addFirst(data);
		}else if(index == this.size) {
			addLast(data);
		}else {
			Node node = new Node(data,null);
			node.next = this.getNode(index);
			this.getNode(index-1).next = node;
			this.size++;
		}
	}
	
	public int getFirst() throws Exception {
		if(this.size==0){
			throw new Exception("List is Empty");
		}
		return this.head.data;
	}
	
	public int getLast() throws Exception{
		if(this.size==0){
			throw new Exception("List is Empty");
		}
		return this.tail.data;
	}

	public int getAt(int index) throws Exception{
		if(index<0 || index>=this.size) {
			throw new Exception("Invalid Index");
		}else if(this.size==0) {
			throw new Exception("List is Empty");
		}else if(index==0){
			return this.getFirst();
		}else if(index==this.size-1) {
			return this.getLast();
		}else
		return this.getNode(index).data;
	}
	
	public void removeFirst() throws Exception {
		if(this.size==0) {
			throw new Exception("List is Empty");
		}else if(this.size==1) {
			this.head = null;
			this.tail = null;
			this.size--;
		}else {
			this.head = this.head.next;
			this.size--;
		}
	}
	
	public void removeLast() throws Exception {
		if(this.size==0) {
			throw new Exception("List is Empty");
		}else if(this.size==1) {
			this.removeFirst();
		}else {
			this.getNode(this.size-2).next = null;
			this.tail = this.getNode(this.size-2);
			this.size--;
		}
	}
	
	public void removeAt(int index) throws Exception {
		if(index<0 || index>this.size-1) {
			throw new Exception("Invalid Index");
		}else if(this.size==0) {
			throw new Exception("List is Empty");
		}else if(index==0){
			this.removeFirst();
		}else if(index==this.size-1) {
			this.removeLast();
		}else {
			Node temp = this.getNode(index+1);
			this.getNode(index-1).next = temp;
			this.size--;
		}
	}
	
	public void display() throws Exception {
		if(this.size==0) {
			throw new Exception("List is Empty");
		}
		int i=0;
		while(i<this.size) {
			System.out.print(this.getAt(i)+"=>");
			i++;
		}
		System.out.print("END");
		System.out.println();
	}
	
	public void reverseDataIteratively() throws Exception{
		if(this.size==0) {
			throw new Exception("List is Empty.");
		}else if(this.size==1){
			throw new Exception("Single Node in list, thus already reversed.");
		}
		Node temp = this.head;
		for(int i=this.size-1;i>this.size/2;i--) {
			int data = temp.data;
			temp.data = getAt(i);
			getNode(i).data = data;
			temp = temp.next;
		}
	}
	
	public void reversePointerIteratively(){
		if(this.size()>1) {
			Node curr = this.head;
			Node next = this.head.next;
			Node prev = null;
			for(int i=0;i<this.size;i++) {
				curr.next = prev;
				prev = curr;
				curr = next;
				if(next!=null)
					next = next.next;
			}
			Node temp = this.head;
			this.head = tail;
			this.tail = temp;
		}
	}
	
	public void reversePointerRecursively() throws Exception{
		if(this.size()<=0) {
			throw new Exception("List is Empty.");
		}
		else {
			reversePointerRecursively(this.head);
			Node temp = this.head;
			this.head = tail;
			this.tail = temp;
		}
	}
	
	public void reversePointerRecursively(Node temp) {
		if(temp==tail) {
			return;
		}
		Node prev = temp;
		Node curr = temp.next;
		reversePointerRecursively(curr);
		curr.next = prev;
	}

	public void reverseDataRecursively() {
        if(this.size>1){
            HeapMover obj = new HeapMover();
            obj.node = this.head;
            reverseDataRecursively(obj,this.head,0);
        }
	}
	
	public void reverseDataRecursively(HeapMover obj,Node right, int level){
	    if(right==null){
	        return;
	    }
	    reverseDataRecursively(obj,right.next,level+1);
	    if(level>=this.size()/2){
	        int t = obj.node.data;
	        obj.node.data = right.data;
	        right.data = t;
	        obj.node = obj.node.next;
	    }
	}
	
	public Node mergeResult(Node node1, Node node2){
        Node temp1 = node1;
        Node temp2 = node2;
        Node root = null;
        while(temp1!=null && temp2!=null){
            if(temp1.data<temp2.data){
                Node temp = temp1;
                temp1 = temp1.next;
                temp.next = root;
                root = temp;
            }
            else if(temp1.data>temp2.data){
                Node temp = temp2;
                temp2 = temp2.next;
                temp.next = root;
                root = temp;
            }
            else{
                Node tempa = temp1;
                Node tempb = temp2;
                temp1 = temp1.next;
                temp2 = temp2.next;
                tempa.next = root;
                root = tempa;
                tempb.next = root;
                root = tempb;
            }
        }
        if(temp1==null && temp2!=null){
            while(temp2!=null){
                Node temp = temp2;
                temp2 = temp2.next;
                temp.next = root;
                root = root.next;
            }
        }
        else if(temp2==null && temp1!=null){
            while(temp1!=null){
                Node temp = temp1;
                temp1 = temp1.next;
                temp.next = root;
                root = temp;
            }
        }
        
        return root;
    }
    
    private Node mergeSort(Node node1, Node node2){
        if(node1==null){
            return node2;
        }
        else if(node2==null){
            return node1;
        }
        Node temp1 = node1;
        Node temp2 = node2;
        Node head = null;
        if(temp1.data<=temp2.data) {
        	head = temp1;
        	temp1 = temp1.next;
        }
        else {
        	head = temp2;
        	temp2 = temp2.next;
        }
        
        Node root = head;
        while(temp1!=null && temp2!=null){
            if(temp1.data<temp2.data){
                root.next = temp1;
                temp1 = temp1.next;
            }
            else if(temp1.data>temp2.data){
                root.next = temp2;
                temp2 = temp2.next;
            }
            else{
                root.next = temp1;
                root.next.next = temp2;
                temp1 = temp1.next;
                temp2 = temp2.next;
                root = root.next;
            }
            root = root.next;
        }
        if(temp1==null && temp2!=null){
            root.next = temp2;
        }
        else if(temp2==null && temp1!=null){
            root.next = temp1;
        }
        return head;
    }
    
    private Node reverseList(Node curr, Node next){
        if(next==null){
            return curr;
        }
        Node newnext = next.next;
        curr.next = null;
        Node head = this.reverseList(next,newnext);
        next.next = curr;
        return head;
    }
	
	private class HeapMover{
	    Node node;
	    HeapMover(){
	        this.node = null;
	    }
	}
	
	public Node midElement() {
		Node temp = this.head;
		Node doubl = this.head;
		while(doubl.next!=null && doubl.next.next!=null) {
			temp = temp.next;
			doubl = doubl.next.next;
		}
		return temp;
	}
	
	public int fromLast(int index) throws Exception{
		if(index<=0 || index>this.size) {
			throw new Exception("Invalid Index");
		}
		Node slow = this.head;
		Node fast = this.head;
		for(int i=0;i<index;i++) {
			fast = fast.next;
		}
		while(fast!=null) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow.data;
	}
	
	public void kReverse(int k, Node slow,int count) throws Exception{
        if(slow==null)
            return;
        for(int i=k*count-1;i>k*count-k+1;i--) {
			int data = slow.data;
			slow.data = this.getAt(i);
			this.getNode(i).data = data;
			slow = slow.next;
		}
        for(int i=0;i<=k/2;i++){
            slow = slow.next;
        }
        kReverse(k,slow,count+1);
    }
	
	public static int maxPalindrome(Node head){
        if(head==null){
            return 0;
        }
        if(head.next==null){
            return 1;
        }
        Node curr = head;
        Node prev = null;
        int res = 0;
        while(curr!=null){
            Node next = curr.next;
            curr.next = prev;
            
            res = Math.max(res,2*countCommon(prev,next)+1);
            res = Math.max(res,2*countCommon(curr,next));
            prev = curr;
            curr = next;
        }
        return res;
    }
    
    private static int countCommon(Node head, Node root){
        int res = 0;
        while(head!=null && root!=null){
            if(head.data==root.data){
                res++;
                head = head.next;
                root = root.next;
            }
            else{
                break;
            }
        }
        return res;
    }
    
//    public void kReverse(Node slow,int count,int k) throws Exception{
//		for(int i=count-1;i>count-k+1;i--) {
//			int data = slow.data;
//			slow.data = this.getAt(i);
//			this.getNode(i).data = data;
//			slow = slow.next;
//		}
//    }
//	
	
	public static void main(String[] args) throws Exception{
		LinkedList list = new LinkedList();
		list.addFirst(12);
		list.addLast(4);
		list.addLast(4);
		list.addLast(3);
		list.addLast(14);
//		LinkedList ls = new LinkedList();
//		ls.addFirst(2);
//		ls.addLast(3);
//		ls.addLast(3);
//		ls.addLast(3);
//		ls.addLast(20);
//		ls.addLast(40);
//		ls.addLast(40);
//		ls.addLast(40);
		//list.reverseDataIteratively();
		//list.display();
		//list.reversePointerIteratively();
//		list.reversePointerIteratively();
//		list.display();
//		System.out.println(list.midElement().data);
//		System.out.println(list.fromLast(2));
//		list.kReverse(3,list.head,1);
		System.out.println(maxPalindrome(list.getNode(0)));
//		list.display();
	}

}
