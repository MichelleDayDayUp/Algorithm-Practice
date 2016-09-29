import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.Stack;


public class TheLastWord {
	
	public static String maxString(String s){
		Stack<Integer> stk = new Stack<Integer>();
		for(int i=s.length()-1; i>0; i--){
			char ch = s.charAt(i);
			if(ch < s.charAt(0)){
				continue;
			}
			while((!stk.isEmpty())&&(ch > s.charAt(stk.peek()))){
				stk.pop();
			}
			stk.push(i);
		}
		
		String ret = "";
		HashSet<Integer> hs = new HashSet<Integer>();
		while(!stk.isEmpty()){
			int index = stk.pop();
			ret = String.valueOf(s.charAt(index)) + ret;
			hs.add(index);
		}
		
		for(int i=0; i<s.length(); i++){
			if(!hs.contains(i)){
				ret += String.valueOf(s.charAt(i));
			}
		}
		return ret;
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("A-large-practice.in"));
		int T = Integer.parseInt(br.readLine());
		for(int i=1; i<=T; i++){
			String s = br.readLine();
			String maxS = TheLastWord.maxString(s);
			System.out.println("Case #" + i + ": " + maxS);
		}
	}

}
