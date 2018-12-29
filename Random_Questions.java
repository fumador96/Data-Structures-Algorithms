package PracticeDSA;
import java.util.*;
public class dnkjdf {
//   public static void DicePath(int diceface,int curr,int end,String ans){
//		int sum = 0;
//    	for(int z=0;z<ans.size();z++) {
//			sum = sum + ans.get(z);
//		}
//    	if(sum==end) {
//    		for(int z=0;z<ans.size();z++)
//    			System.out.print(ans.get(z)+" ");
//    		System.out.print(" ");
//    		return;
//    	}
//    	else if(sum>end) {
//    		return;
//    	}
//    	for(int i=0;i<diceface;i++) {
//    		if(i!=end){
//    			ans.add(i);
//    			//int[] newarr = newArray(arr,i);
//    			DicePath(diceface, curr+i,end, ans);
//    			ans.remove(ans.size()-1);
//    		}
//    		if(i==end && ans.isEmpty()==true) {
//    			System.out.println(i);
//    		}
//    	}
//    	
//	}
	
	public static int CountDicePath(int data,int curr,int[] arr,ArrayList<Integer> ans){
		int sum = 0;
		int count = 0;
    	for(int z=0;z<ans.size();z++) {
			sum = sum + ans.get(z);
		}
    	if(sum==data) {
    		return count+1;
    	}
    	else if(sum>data) {
    		return count;
    	}
    	for(int i=0;i<arr.length;i++) {
    		if(arr[i]!=data){
    			ans.add(arr[i]);
    			int[] newarr = newArray(arr,i);
    		    count = count + CountDicePath(data, curr+arr[i], newarr, ans);
    			ans.remove(ans.size()-1);
    		}
    		if(arr[i]==data && ans.isEmpty()==true) {
    			count = count+1;
    		}
    	}
    	return count;
	}
//	public static int[] merge(int[] one, int[] two) {
//		int i=0,j=0;
//		int k=0;
//		int[] sorted = new int[one.length+two.length];
//		while(i<one.length && j<two.length) {
//			if(one[i]<two[j]) {
//				sorted[k]=one[i];
//				i++;
//				k++;
//			}
//			else {
//				sorted[k]=two[j];
//				j++;
//				k++;
//			}
//		}
//		if(i<one.length && j>=two.length) {
//			while(i<one.length) {
//				sorted[k] = one[i];
//				k++;
//				i++;
//			}
//		}
//		else if(j<two.length && i>=one.length) {
//			while(j<two.length) {
//				sorted[k] = two[j];
//				k++;
//				j++;
//			}
//		}
//		return sorted;
//	}
//	public static int[] mergeSort(int[] arr,int low, int high) {
//		if(low==high) {
//			int[] baseres = new int[1];
//			baseres[0] = arr[low];
//			return baseres;
//		}
//		int mid = (low+high)/2;
//		int[] one = mergeSort(arr,low,mid);
//		int[] two = mergeSort(arr,mid+1,high);
//		int[] sorted = merge(one,two);
//		return sorted;
//	}
   //Takes log(n) time complexity.
   	public static int power(int x, int n) {
   		int ans = 1;
   		if(n==1) {
   			return x;
   		}
   		if(n%2==0) {
   			ans = ans*power(x,n/2);
   			return ans*ans;
   		}
   		else if(n%2!=0) {
   			ans = ans*power(x,n/2);
   			return ans*ans*x;
   		}
   		return ans;
   	}
   //Takes log(logn) time complexity, for finding total primes between 0-n.
   	public static void SiereOfErotosthenes(int n) {
   		boolean[] primes = new boolean[n+1];
   		for(int i=0;i<primes.length;i++) {
   			primes[i] = true;
   		}
   		for(int j=1;j<primes.length;j++) {
   			if(j%2==0) {
   				primes[j] = false;
   			}
   		}
   		int sqrtN = (int)Math.sqrt(n);
   		for(int i=2;i<=sqrtN;i++) {
   			if(primes[i]!=false) {
   				for(int j=i+1;j<n;j++) {
   					if(primes[j]!=false && j%i==0) {
   						primes[j] = false;
   					}
   				}
   			}
   		}
   		for(int i=1;i<primes.length;i++) {
   			if(primes[i]!=false)
   				System.out.println(i);
   		}
   	}
   	
   	
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //int x = sc.nextInt();
//        ArrayList<Integer> ans = new ArrayList<>();
//       // System.out.println(CountDicePath(data,0,arr,ans));
//        DicePath(diceface,0,n,ans);
////    	int[] arr = {7,2,1,6,8,5,3,4};
////    	int[] third = mergeSort(arr,0,arr.length-1);
////    	for(int i=0;i<third.length;i++) {
////    		System.out.println(third[i]);
////    	}
        //SiereOfErotosthenes(n);    }
        	float gpa = 2.6f;
    	   	if((gpa>=2.3) && (gpa<3.0))
    	   		System.out.println("A");
    	   	if((gpa>=3) && (gpa<=3.7))
    	   		System.out.println("B");
    	   	else
    	   	   	System.out.println("C");
}
