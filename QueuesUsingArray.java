package PracticeDSA;

public class QueuesUsingArray {
	protected int[] arr;
	protected int front;
	protected int size;
	public static final int DefaultCapacity = 100;
	QueuesUsingArray() throws Exception{
		this(DefaultCapacity);
	}
	QueuesUsingArray(int capacity) throws Exception{
		if(capacity<=0) {
			throw new Exception("Invalid capacity of Queue");
		}
		else {
			this.arr = new int[capacity];
			this.front = 0;
			this.size = 0;
		}
	}
	
	public void enQueue(int data) throws Exception {
		if(this.size==this.arr.length) {
			throw new Exception("Queue is Full");
		}
		else {
			this.arr[(this.size+this.front)%this.arr.length]=data;
			this.size++;
		}
	}
	public int deQueue() throws Exception {
		if(this.size==0) {
			throw new Exception("Queue is empty");
		}
		else {
			int temp = this.arr[this.front];
			this.arr[this.front] = 0;
			this.front = (this.front+1)%this.arr.length;
			this.size--;
			return temp;
		}
	}
	
	public int size() {
		return this.size;
	}
	
	public int front() {
		return this.arr[front];
	}
	
	public boolean isEmpty() {
		if(this.size==0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void display() {
		int i=0;
		while(i<this.size) {
			System.out.print(this.arr[(this.front+i)%this.arr.length]+", ");
			i++;
		}
		System.out.println("END");
	}
	
	public static void main(String[] args) throws Exception{
		QueuesUsingArray queue = new QueuesUsingArray(5);
		for(int i=1;i<=5;i++) {
			queue.enQueue(i*10);
		}
		queue.display();
		System.out.println();
		System.out.println("Size => "+queue.size());
		System.out.println("front => "+queue.front());
		queue.deQueue();
		System.out.println("***************");
		queue.display();
		System.out.println();
		System.out.println("Size => "+queue.size());
		System.out.println("front => "+queue.front());
		queue.deQueue();
		System.out.println("***************");
		queue.display();
		System.out.println();
		System.out.println("Size => "+queue.size());
		System.out.println("front => "+queue.front());
		queue.deQueue();
		System.out.println("***************");
		queue.display();
		System.out.println();
		System.out.println("Size => "+queue.size());
		System.out.println("front => "+queue.front());
		queue.deQueue();
		System.out.println("***************");
		queue.display();
		System.out.println();
		System.out.println("Size => "+queue.size());
		System.out.println("front => "+queue.front());
		queue.deQueue();
		System.out.println("***************");
		queue.display();
		System.out.println();
		System.out.println("Size => "+queue.size());
		System.out.println("front => "+queue.front());
		queue.deQueue();
		System.out.println("***************");
		//queue.display();
	}
}
