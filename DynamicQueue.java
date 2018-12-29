package PracticeDSA;

public class DynamicQueue extends QueuesUsingArray{

	DynamicQueue() throws Exception {
		this(DefaultCapacity);
	}
	DynamicQueue(int capacity) throws Exception{
		super(capacity);
	}
	
	public void enQueue(int data) throws Exception{
		int newarr[];
		if(this.size()==this.arr.length) {
			newarr = new int[2*this.arr.length];
			for(int i=0;i<arr.length;i++) {
				newarr[i] = arr[(this.front+i)%this.arr.length];
			}
			this.arr = newarr;
			this.front = 0;
		}
		super.enQueue(data);
	}
	
	public int deQueue() throws Exception {
		return super.deQueue();
	}
	public static void main(String[] args) throws Exception{
		QueuesUsingArray obj = new DynamicQueue(5);
		for(int i=1;i<=5;i++) {
			obj.enQueue(i*10);
		}
		obj.display();
		System.out.println();
		System.out.println("Size => "+obj.size());
		System.out.println("front => "+obj.front());
		obj.deQueue();
		System.out.println("***************");
		obj.display();
		System.out.println();
		System.out.println("Size => "+obj.size());
		System.out.println("front => "+obj.front());
		obj.deQueue();
		System.out.println("***************");
		obj.display();
		System.out.println();
		System.out.println("Size => "+obj.size());
		System.out.println("front => "+obj.front());
		obj.enQueue(60);
		obj.enQueue(70);
		obj.display();
		System.out.println();
		System.out.println("Size => "+obj.size());
		System.out.println("front => "+obj.front());
//		obj.deQueue();
//		System.out.println("***************");
//		obj.display();
//		System.out.println();
//		System.out.println("Size => "+obj.size());
//		System.out.println("front => "+obj.front());
//		obj.deQueue();
//		System.out.println("***************");
//		obj.display();
//		System.out.println();
//		System.out.println("Size => "+obj.size());
//		System.out.println("front => "+obj.front());
//		//obj.deQueue();
//		obj.enQueue(60);
//		System.out.println("***************");
//		obj.display();
//		System.out.println();
//		System.out.println("Size => "+obj.size());
//		System.out.println("front => "+obj.front());
//		obj.deQueue();
//		System.out.println("***************");
//		obj.display();
//		System.out.println();
//		System.out.println("Size => "+obj.size());
//		System.out.println("front => "+obj.front());
//		//obj.deQueue();
	}

}
