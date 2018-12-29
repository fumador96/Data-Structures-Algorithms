package PracticeDSA;

//public class QuickSort {
//	
//	public static void quickSort(int[] arr, int start, int end) {
//		if(start>=end)
//			return;
//		int pIndex = partition(arr,start,end); 
//		quickSort(arr, start, pIndex-1);
//		quickSort(arr, pIndex+1, end);
//	}
//	
//	public static int partition(int[] arr, int start, int end) {
//		int pivot = arr[end];
//		//pIndex will be the actual index of pivot element after complete sorting.
//		int pIndex = start;
//		for(int i=start;i<=end-1;i++) {
//			if(arr[i]<=pivot) {
//				int temp = arr[i];
//				arr[i] = arr[pIndex];
//				arr[pIndex] = temp;
//				pIndex++;
//			}
//		}
//		int temp = arr[pIndex];
//		arr[pIndex] = arr[end];
//		arr[end] = temp;
//		return pIndex;
//	}
//
//	public static void main(String[] args) {
//		int[] arr = {7,2,1,6,8,5,3,4};
//		quickSort(arr,0,arr.length-1);
//		for(int i=0;i<arr.length;i++) {
//			System.out.println(arr[i]);
//		}
//	}
//
//}
import java.util.*;
import java.util.Arrays;
public class QuickSort {
    
    public int[] stockSpan(int[] arr){
        int[] span = new int[arr.length];
        Stack<Integer> s = new Stack<>();
        s.push(0);
        span[0] = 1;
        for(int i=1;i<arr.length;i++){
            while(!s.isEmpty() && arr[i]>=arr[s.peek()]){
                s.pop();
            }
            if(s.isEmpty()){
                span[i] = i+1;
            }else{
                span[i] = i-s.peek();
            }
            s.push(i);
        }
        return span;
    }
    public static void main(String args[]) {
        QuickSort obj = new QuickSort();
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        for(int t=0;t<test;t++){
            int n =  sc.nextInt();
            int[] arr = new int[n];
            for(int i=0;i<n;i++)
                arr[i] = sc.nextInt();
            int[] span = obj.stockSpan(arr);
            for(int i=0;i<n;i++)
                System.out.print(span[i]+" ");
        }
    }
}



