package PracticeDSA;
import java.lang.Math;
public class RecursionDemo {
	public static void pdiskip(int n) {
		if(n<=0)
			return;
		if(n%2!=0)
			System.out.println(n);
		pdiskip(n-1);
			if(n%2==0 && n<=5)
				System.out.println(n);
		
	}
	public static int fact (int n) {
		if(n==1)
			return 1;
		return n*fact(n-1);
	}
	
	public static int fib(int n) {
		if(n==1)
			return 1;
		if(n==0)
			return 0;
		return fib(n-1) + fib(n-2);
	}
	
	public static int power(int x,int n) {
		if(n==0)
			return 1;
		return x*power(x,n-1);
	}
	
	static int i = 0;
	public static boolean isSorted(int[] arr,int i) {
		if(i==arr.length-1)
			return true;
		if(arr[i]>arr[i+1]) {
			return false;
		}
		if(arr[i]<=arr[i+1]) {
			return isSorted(arr,i+1);
		}
		else
			return false;
	}
	
	public static int firstIndex(int[] arr, int data,int j) {
		if(j==arr.length)
			return -1;
		if(arr[j]==data)
			return j;
		if(arr[j]!=data) {
			return firstIndex(arr,data,j+1);
		}
		else
			return -1;
	}
	
	static int index = -1;
	public static int lastIndex(int[] arr, int data,int i) {
		if(i==arr.length)
			return -1;
		index = lastIndex(arr,data,i+1);
		if(index!=-1) {
			return index;
		}
		else {
			if(arr[i]==data) {
				index = i;
				return index;
			}
			else 
				return -1;
		}
	}
	public static int[] allIndex(int[] arr, int data,int i,int count) {
		if(i==arr.length)
			return new int[count];
		int ans[] = null;
		if(arr[i]==data){
			ans = allIndex(arr,data,i+1,count+1);
		}
		else {
			ans = allIndex(arr,data,i+1,count);
		}
		if(arr[i]==data) {
			ans[count] = i;
		}
		return ans;
	}

	public static void pattern(int n,int row, int col){
		if(col==n)
			return;
		if(row<col) {
			System.out.println();
			pattern(n,row+1,0);
			return;
		}
		System.out.print("* ");
		pattern(n,row,col+1);
	}
	
	public static int printReverse(int n){
        int ans = 0;
        if(n/10==0){
            return n;
        }
        int length = String.valueOf(n).length()-1;
        int den = (int)Math.pow(10,length);
        ans = printReverse(n%den);
        ans = ans*10 + n/den;
        return ans;
        
    }
	
	public static void main(String[] args) {
//		pdiskip(5);
//		System.out.println(fact(5));
//		System.out.println(fib(5));
//		System.out.println(power(3,4));
//		int arr[] = {7,3,2,4,5,2,1,2};
//		System.out.println(isSorted(arr,0));
//		System.out.println(firstIndex(arr,2,0));
//		System.out.println(lastIndex(arr,2,0));
//		int b[] = allIndex(arr,2,0,0);
//		for(int i=0;i<b.length;i++)
//		System.out.print(b[i]+" ");
//		pattern(5,0,0);
		System.out.println(printReverse(1000000000));
	}
}
