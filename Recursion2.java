package PracticeDSA;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.*;
public class Recursion2 {

	static int i =0;
	public static ArrayList<String> getSubSeq(String s){
		ArrayList<String> list = null;
		if(s.length()==0) {
			list = new ArrayList<String>();
			list.add("");
			return list;
		}
		list = getSubSeq(s.substring(1,s.length()));
		String str = s.substring(0,1);
		ArrayList<String> l = new ArrayList<>();
		for(int k=0;k<list.size();k++) {
			l.add(list.get(k));
			l.add(str + list.get(k));
		}
		return l;
	}
	
	public static ArrayList<String> getSubSeqAscii(String s) {
		ArrayList<String> list = null;
		if(s.length()==0) {
			list = new ArrayList<String>();
			list.add("");
			return list;
		}
		list = getSubSeqAscii(s.substring(1));
		String str = s.substring(0,1);
		ArrayList<String> l = new ArrayList<>();
		for(int k=0;k<list.size();k++) {
			l.add(list.get(k));
			l.add(str + list.get(k));
			char c = str.charAt(0);
			l.add((int)c + list.get(k));
		}
		return l;	
	}
	
	public static ArrayList<String> getPermutations(String s){
		ArrayList<String> list = null;
		if(s.length()==1) {
			list = new ArrayList<>();
			//list.add("");
			list.add(s);
			return list;
		}
		list = getPermutations(s.substring(1));
		ArrayList<String> l = new ArrayList<>();
		char cc = s.charAt(0);
		for(int k=0;k<list.size();k++){
			String str = list.get(k);
			for(int j=0;j<=str.length();j++)
				l.add(str.substring(0,j)+cc+str.substring(j));
		}
		return l;
	}
	public static boolean palindrome(String s) {
		String str = "";
		if(s.length()==0)
			return true;
		palindrome(s.substring(1));
		str = str + s.charAt(0);
		if(str==s)
			return true;
		else return false;
	}
	
	public static void isPalindrome(ArrayList<String> list) {
		for(int i=0;i<list.size();i++) {
			if(palindrome(list.get(i))==true) {
				System.out.println("Possible palindrome");
				break;
			}
		}
	}
	
	public static void printSubSeq(String s,String res){
		if(s.length()==0) {
			System.out.println(res);
			return;
		}
		String str = s.substring(0,1);
		printSubSeq(s.substring(1), res);
		printSubSeq(s.substring(1),res+str);
	}
	
	public static void printSubSeqAscii(String s,String res){
		if(s.length()==0) {
			System.out.print(res+" ");
			return;
		}
		String str = s.substring(0,1);
		int ascii = (int)(s.charAt(0));
		printSubSeqAscii(s.substring(1), res);
		printSubSeqAscii(s.substring(1),res+str);
		printSubSeqAscii(s.substring(1),res+ascii);
	}
	
	public static void printPermutation(String s,String res){
		if(s.length()==0) {
			System.out.println(res);
			return;
		}
		if(res.length()==0) {
			printPermutation(s.substring(1),s.substring(0,1));
			printPermutation(s.substring(1),res);
		}
		if(res.length()!=0) {
			String str = s.substring(0, 1);
			for(i=0;i<=res.length();i++) {
				String resn = res.substring(0,i) + str + res.substring(i);
				printPermutation(s.substring(1),resn);
			}
		}
	}
	
	public static ArrayList<String> DicePath(int end,int curr){
		ArrayList<String> list = null;
		if(curr==end) {
			list = new ArrayList<>();
			list.add("");
			return list;
		}
		if(curr>end) {
			list = new ArrayList<>();
			return list;
		}
		ArrayList<String> l = new ArrayList<>();
		for(int dice=1;dice<=6;dice++) {
			list = DicePath(end,curr+dice);
			for(int i=0;i<list.size();i++) {
				l.add(dice+list.get(i));
			}
		}
		return l;
	}
	
	public int towerHanoisteps(int n){
        return (int)Math.pow((double)2,(double)n)-1;
    }
    
    public void towerHanoiprintSteps(char from, char to, char aux, int n){
        if(n==1){
            System.out.println("move disk 1 from rod "+from+" to rod "+to);
            return;
        }
        this.towerHanoiprintSteps(from,aux,to,n-1);
        System.out.println("move disk "+n+" from rod "+from+" to rod "+to);
        this.towerHanoiprintSteps(aux,to,from,n-1);
    }
    
    private class Numb{
        int num;
        int weig;
        Numb(int num, int weig){
            this.num = num;
            this.weig = weig;
        }
    }
    
    public String factDigitSum(int n){
        ArrayList<Numb> list = new ArrayList<Numb>();
        this.factDigit(n,9,list);
        String ans = "";
        for(int i=list.size()-1;i>=0;i--){
            Numb numb = list.get(i);
            while(numb.weig!=0){
                ans += numb.num;
                numb.weig--;
            }
        }
        return ans;
    }
    
    private void factDigit(int n, int k, ArrayList<Numb> list){
        if(n==0){
            return;
        }
        int facto = this.fact(k);
        int q = n/facto;
        if(q>0){
            Numb obj = new Numb(k,q);
            list.add(obj);
        }
        this.factDigit(n-(q*facto),k-1,list);
    }
    
    private int fact(int n){
        if(n<=2)
            return n;
        return n*this.fact(n-1);    
    }
    
    public void printOneZero(int n){
        if(n==1){
            System.out.print("1");
            return;   
        }
        String temp="1";
        this.getAns(n,temp,1,0,1);
    }
    
    private void getAns(int n, String temp, int i, int zero, int one){
        if(i==n){
        	if(one>=zero)
        		System.out.print(temp+" ");
            return;
        }
        else{
            this.getAns(n,temp+"1",i+1,zero,one+1);
            if(zero!=one || zero<one)
            	this.getAns(n,temp+"0",i+1,zero+1,one);
        }
    }
    
    public void shuffleArray(int[] arr){
        int n = arr.length;
        int i = 0;
        int j = n/2;
        this.getAns(arr,i+1,j,0);
        for(i=0;i<n;i++){
            System.out.print(arr[i]+" ");
        }
    }
    
    private void getAns(int[] arr, int i, int j, int count){
        int n = arr.length;
        if(n-(2*count)==2 || i>=n || j>=n){
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        count++;
        int a = arr[j];
        int b = arr[j+1];
        if(i==j)
        	return;	
        this.shift(arr,i,j-1);
        arr[i+1] = a;
        arr[i+2] = b;
        count++;
        this.getAns(arr,2*count+1,(n/2)+count,count);
    }
    
    private void shift(int[] arr, int i, int j){
        if(j==i || j==-1)
            return;
        arr[j+2] = arr[j];
        this.shift(arr,i,j-1);
    }
    
    //IMPORTANT
    public void strictIncreasing(int n, int k, String ans){
        if(ans.length()==n){
            System.out.print(ans+" ");
            return;
        }
        for(int i=k;i<=9;i++){
            String newans = ans + Integer.toString(i);
            this.strictIncreasing(n,i+1,newans);
        }
    }
    
    public void permutation(String s){
        if(s.length()==0){
            return;
        }
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        s = String.valueOf(arr);
        this.printPermutation(s.length(),s,"");
    }
    
    private void printPermutation(int n, String s, String ans){
        int ansl = ans.length();
        if(ansl==n){
            System.out.print(ans+" ");
            return;
        }
        for(int i=0;i<n-ansl;i++){
            String news = s.substring(0,i)+s.substring(i+1);
            this.printPermutation(n,news,ans+s.charAt(i));
        }
    }
    
    public void Pattern(int n){
        this.printPattern(n,n,0);
    }
    
    private void printPattern(int n, int curr, int count){
        if(curr==n){
            if(count==2)
                return;
            if(count==0){
                System.out.print(curr+" ");
                count++;
            }
        }
        if(curr>0 && count!=2){
            System.out.print(curr-5+" ");
            this.printPattern(n,curr-5,count);
        }
        else if(curr<=0){
        	if(count==1) {
        		count++;
        	}
            System.out.print(curr+5+" ");
            this.printPattern(n,curr+5,count);
        }
    }
    
    public void subsetSum(int[] arr, int n){
        if(n==0){
            return;
        }
        if(n==1){
            System.out.print(arr[0]);
            return;
        }
        //Arrays.sort(arr);
        ArrayList<Integer> list = this.findSum(arr,n,0);
        Collections.sort(list); 
        System.out.print(list);
    }
    int count = 0;
    private ArrayList<Integer> findSum(int[] arr, int n, int k){
        ArrayList<Integer> list;
        if(k>n){
            list = new ArrayList<Integer>();
            return list;
        }
        if(k==n && count==0){
        	count++;
            list = new ArrayList<Integer>();
            list.add(0);
            return list;
        }
        ArrayList<Integer> l = new ArrayList<Integer>();
        for(int i=k;i<n;i++){
            list = this.findSum(arr,n,i+1);
            for(int j=0;j<list.size();j++){
                l.add(list.get(j));
                //l.add(arr[i]);
                l.add(list.get(j) + arr[i]);
            }
        }
        return l;
    }
	
    public int Josephus(int n, int k){
        if (n == 1) 
            return 1; 
        else
            return (Josephus(n - 1, k) + k-1) % n + 1; 
    }
    
    public void helpOldMan(char a, char b, char c, int plates, int n){
        if(plates==0){
            return;
        }
        int pass = (int)Math.pow((double)2,(double)(plates-1));
        this.helpOldMan(a,c,b,plates-1,n);
        if(n==1){
            System.out.println(a+" "+c);
        }
        n--;
        this.helpOldMan(b,a,c,plates-1,n);
    }
    
    int max = Integer.MIN_VALUE;
    public void specialKeyboard(int n){
        if(n<=4){
            System.out.print(n);
            return;
        }
        this.totalStrokes(n,0,false,0);
        System.out.println(max);
        max = Integer.MIN_VALUE;
    }
    
    private void totalStrokes(int n, int curr,boolean copy, int copied){
        if(n==0){
            if(max<curr){
                max = curr;
            }
            return;
        }
        if(n<0){
            return;
        }
        this.totalStrokes(n-1,curr+1,copy,copied);
        if(max!=Integer.MIN_VALUE) {
            if(!copy) {
            	n = n - 2;
            	copy = !copy;
            	copied = curr;
            }
            this.totalStrokes(n-1, curr+copied, copy,copied);
        }
    }
    
//  GFG obj = new GFG();
//	Scanner sc = new Scanner(System.in);
//	int t =  sc.nextInt();
//	while(t>0){
//	    int n = sc.nextInt();
//	}
	public static void main(String[] args) {
		//Scanner sc = new Scanner(System.in);
		//int end = sc.nextInt();
//        isPalindrome(getPermutations(s));
		//System.out.println(getSubSeq("abc"));
//		System.out.println(DicePath(5, 1));
		Recursion2 obj = new Recursion2();
//		obj.printOneZero(4);
//		int[] arr = {1, 5, 2};
//		obj.shuffleArray(arr);
//		obj.strictIncreasing(5,1,"");
//		obj.permutation("ACB");
//		obj.Pattern(6);
//		obj.subsetSum(arr, arr.length);
//		System.out.println(obj.Josephus(7, 3));
//		obj.helpOldMan('1','2','3',2,2);
		obj.specialKeyboard(7);
	}
}
