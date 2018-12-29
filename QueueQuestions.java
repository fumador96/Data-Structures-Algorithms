package PracticeDSA;
import java.util.ArrayList;
import java.util.Scanner;

public class QueueQuestions {
	public static void reverseQueue(DynamicQueue queue) throws Exception{
		if(queue.size()==0)
			return;
		int pop = queue.deQueue();
		reverseQueue(queue);
		queue.enQueue(pop);
	}

	public static void main(String[] args) throws Exception {
		DynamicQueue queue = new DynamicQueue(5);
		for(int i=1;i<=5;i++) {
			queue.enQueue(i*10);
		}
		queue.display();
		reverseQueue(queue);
		queue.display();
	}
	
//	 public static ArrayList<String> CodeOfTheString(String s){
//	        ArrayList<String> list = null;
//	        if(s.length()==0){
//	            list = new ArrayList<>();
//	            return list;
//	        }
//	        else if(s.length()==1){
//	            list = new ArrayList<>();
//	            list.add(Alphabet(s.substring(0)));
//	            return list;
//	        }
//	        String lastOne = Alphabet(s.substring(0,1));
//	        String lastTwo = Alphabet(s.substring(0,2));
//	        list = CodeOfTheString(s.substring(1));
//	        ArrayList<String> l = new ArrayList<>();
//	        for(int i=0;i<list.size();i++){
//	            l.add(lastOne+list.get(i));
//	            l.add(lastTwo);
//	        }
//	        return l;
//	    }
//	    
//	    public static String Alphabet(String s){
//	        int n = Integer.parseInt(s);
//	        char c = (char)(n+'A'-1);
//	        return String.valueOf(c);
//	    }
//	    public static void main(String args[]) {
//	        Scanner sc = new Scanner(System.in);
//	        String str = sc.next();
//	        System.out.println(CodeOfTheString(str));
//	    }
}
