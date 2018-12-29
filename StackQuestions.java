import java.util.*;
public class StackQuestions {
//	public static void reverseStack(DynamicStack stack, DynamicStack helper, int index) throws Exception{
//		if(stack.size()==0) {
//			return;
//		}
//		int pop = stack.pop();
//		reverseStack(stack, helper,index+1);
//			helper.push(pop);
//		if(index==0){
//			int size = helper.size();
//			for(int k=0;k<size;k++) {
//				stack.push(helper.pop());
//			}
//		}
//	}
	
	public String infixToPostfix(String st, HashMap<Character,Integer> list){
        Stack<Character> s = new Stack<Character>();
        int n = st.length();
        String ans = "";
        for(int i=0;i<n;i++){
            char c = st.charAt(i);
            if(c=='('){
                s.push(c);
                continue;
            }
            else if(c==')'){
                while(!s.isEmpty() && s.peek()!='('){
                    ans += s.pop();
                }
                s.pop();
            }
            else if(list.containsKey(c)){
            	if(s.isEmpty()) {
            		s.push(c);
            		continue;
            	}
            	else if(!s.isEmpty() && list.get(c) > list.get(s.peek())){
                    s.push(c);
                }
                else if(!s.isEmpty() && list.get(c)<=list.get(s.peek())){
                    while(!s.isEmpty() && list.get(c)<=list.get(s.peek())){
                        ans += s.pop();
                    }
                    s.push(c);
                }
            }
            else{
                ans += c;
            }
        }
        while(!s.isEmpty()){
            ans += s.pop();
        }
        return ans;
    }

	public static void main(String[] args) throws Exception {
		StackQuestions stack = new StackQuestions();
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t>0){
    	    String s = sc.next();
    	    HashMap<Character,Integer> map = new HashMap<Character,Integer>();
    	    map.put('(',0);
    	    map.put('-',1);
    	    map.put('+',1);
    	    map.put('*',3);
    	    map.put('/',3);
    	    map.put('^',5);
    	    System.out.println(stack.infixToPostfix(s,map));
    	    t--;
	    }
	}

}
