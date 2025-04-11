import java.lang.*;
import java.util.*;

class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        StringBuilder sb;
        
        for(int i=0; i<s.length ;i++){
            sb = new StringBuilder();
            Stack<Character> stack = new Stack<>();
            int lloCnt= 0;
            
            for(int j=0 ; j<s[i].length() ;j++){
                char ch = s[i].charAt(j);
                
                if(stack.size()>=2){
                    char b = stack.pop();
                    char a = stack.pop();
                    
                    if(a == '1' && b == '1' && ch == '0'){
                        lloCnt++;
                    }else{
                        stack.add(a);
                        stack.add(b);
                        stack.add(ch);
                    }
                }else{
                    stack.add(ch);
                }
            }
            
            int zeroIdx = stack.size();
            boolean checkIdx = false;
            
            while(!stack.isEmpty()){
                if(!checkIdx){
                    if(stack.peek() == '1') zeroIdx--;
                    else checkIdx = true;
                }
                
                sb.insert(0, stack.pop());
            }
            
            while(lloCnt>0){
                sb.insert(zeroIdx, "110");
                zeroIdx+=3;
                lloCnt--;
            }
            
            answer[i] = sb.toString();
        }
        
        return answer;
    }
}