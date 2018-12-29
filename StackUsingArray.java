package PracticeDSA;

public class StackUsingArray {
		protected int top;
		protected int arr[];
		public static final int DefaultCapacity = 100;
		StackUsingArray() throws Exception{
			this(DefaultCapacity);
		}
		StackUsingArray(int capacity) throws Exception{
			if(capacity<=0) {
				throw new Exception("Invalid Capacity");
			}
			else {
				arr = new int[capacity];
				this.top = -1;
			}
		}
	
	public void push(int data) throws Exception{
		if(this.size()==this.arr.length) {
			throw new Exception("Stack is Full");
		}
		this.top++;
		this.arr[this.top] = data;
	}
	
	public int pop() throws Exception{
		if(this.top!=-1){
		    int temp = this.arr[this.top];
		    this.top--;
		    return temp;
		}
		else {
		    throw new Exception("Stack is Empty");
		}
	}
	
	public boolean isEmpty(){
		if(this.top==-1)
			return true;
		else	
			return false;
	}
	
	public int size() {
		return this.top+1;
	}
	
	public int peek() throws Exception{
		if(this.top==-1) {
			throw new Exception("Stack is Empty");
		}
		else {
			return this.arr[this.top];
		}
	}
	
	public void display() {
		for(int i=this.top;i>=0;i--){
			System.out.print(this.arr[i]+", ");
		}
		System.out.println("END");
	}
	
	public static void main(String[] args) throws Exception{
		StackUsingArray stack  = new StackUsingArray(5);
		for(int i=1;i<=5;i++) {
			stack.push(i*10);
		}
		stack.display();
		System.out.println("Size is: "+stack.size());
		System.out.println("Top=> "+stack.peek());
	}

}
