package PracticeDSA;
public class DynamicStack extends StackUsingArray{
	DynamicStack() throws Exception {
		this(DefaultCapacity);
	}
	DynamicStack(int number) throws Exception{
		super(number);
	}
	
	public void push(int data) throws Exception{
		int[] newarr;
		if(this.size()==this.arr.length) {
			newarr = new int[2*this.arr.length];
			for(int i=0;i<arr.length;i++) {
				newarr[i] = arr[i];
			}
			this.arr = newarr;
		}
		super.push(data);
	}
	
	public int pop() throws Exception{
		return super.pop();
	}
	
	public static void main(String[] args) throws Exception{
		StackUsingArray obj = new DynamicStack(5);
		for(int i=1;i<=5;i++) {
			obj.push(i*10);
		}
		obj.display();
		System.out.println("Size is: "+obj.size());
		System.out.println("Top=> "+obj.peek());
		System.out.println("****************"); 
		obj.push(60);
		obj.display();
		System.out.println("Size is: "+obj.size());
		System.out.println("Top=> "+obj.peek());
		
		
	}
}
 