package PracticeDSA;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class HashMapDemo {
	
	public char maxFreq(String s) {
		HashMap<Character, Integer> map = new HashMap<>();
		for(int i=0;i<s.length();i++) {
			char c = s.charAt(i);
			if(map.containsKey(c)) {
				map.put(c, map.get(c)+1);
			}
			else {
				map.put(c, 1);
			}
		}
		Set<Map.Entry<Character, Integer>> entrySet = map.entrySet();
		char ch = s.charAt(0);
		int val = map.get(ch);
		for(Map.Entry<Character,Integer> entry : entrySet) {
			if(entry.getValue()>val) {
				val = entry.getValue();
				ch = entry.getKey();
			}
		}
		return ch;
	}
    
    public int nextSmallestHappy(int n){
        int i = n+1;
        while(!this.isHappy(i)){
            i++;
        }
        return i;
    }
    
    private boolean isHappy(int n){
        HashSet<Integer> set = new HashSet<>();
        int l = (String.valueOf(n).length());
        int k = this.squareSum(n,l-1);
        set.add(k);
        boolean happy = true;
        while(k!=1){
        	l = (String.valueOf(k).length());
            k = this.squareSum(k,l-1);
            if(set.contains(k)){
            	happy = false;
                break;
            }else{
                set.add(k);
            }
        }
        return happy;
    }
    
    private int squareSum(int n, int l){
        if(n/10==0){
            return n*n;
        }
        int ten = (int)Math.pow((double)10, (double)l);
        return (n/ten)*(n/ten) + this.squareSum(n%ten,l-1);
    }

	public static void main(String[] args) {
		HashMapDemo obj = new HashMapDemo();
//		System.out.println(obj.maxFreq("abababagbgbaaaazzzzzzzzz"));
		System.out.println(obj.isHappy(7));;
	}

}
