package PracticeDSA;
public class GenericLinkedList<T> {
	
	protected class Node{
		T data;
		Node next;
		Node(T data,Node next){
			this.data = data;
			this.next = next;
		}
	}
	private Node head;
	private Node tail;
	private int size;
	
	public GenericLinkedList() {
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
	
	public void addFirst(T data) {
		Node node = new Node(data,this.head);
		this.head = node;
		if(this.size==0)
			this.tail = node;
		this.size++;
	}
	
	public void addLast(T data) {
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
	
	public void addAt(T data,int index) throws Exception{
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
	
	public T getFirst() throws Exception {
		if(this.size==0){
			throw new Exception("List is Empty");
		}
		return this.head.data;
	}
	
	public T getLast() throws Exception{
		if(this.size==0){
			throw new Exception("List is Empty");
		}
		return this.tail.data;
	}

	public T getAt(int index) throws Exception{
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
	
	public T removeFirst() throws Exception {
		if(this.size==0) {
			throw new Exception("List is Empty");
		}
		Node rv = this.head;	
		if(this.size==1) {
			this.head = null;
			this.tail = null;
			this.size--;
		}else {
			this.head = this.head.next;
			this.size--;
		}
		return rv.data;
	}
	
	public T removeLast() throws Exception {
		if(this.size==0) {
			throw new Exception("List is Empty");
		}
		Node rv = this.tail;	
		if(this.size==1) {
			this.removeFirst();
		}else {
			this.getNode(this.size-2).next = null;
			this.tail = this.getNode(this.size-2);
			this.size--;
		}
		return rv.data;
	}
	
	public T removeAt(int index) throws Exception {
		if(index<0 || index>this.size-1) {
			throw new Exception("Invalid Index");
		}else if(this.size==0) {
			throw new Exception("List is Empty");
		}
		if(index==0){
			return this.removeFirst();
		}else if(index==this.size-1) {
			return this.removeLast();
		}else {
			Node rv = this.getNode(index);
			Node temp = this.getNode(index+1);
			this.getNode(index-1).next = temp;
			this.size--;
			return rv.data;
		}
	}
	
	public int findIndexWithSameData(T data) {
		int index = 0;
		for(Node temp=this.head;temp!=null;temp = temp.next) {
			if(temp.data.equals(data))
				return index;
			index++;
		}
		return -1;
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
	
	public static void main(String[] args) throws Exception{
		LinkedList list = new LinkedList();
		list.addFirst(20);
		list.addFirst(10);
		list.addLast(30);
		list.addAt(50, 3);
		list.addAt(40, 3);
		list.addLast(60);
		list.display();
	}

}
