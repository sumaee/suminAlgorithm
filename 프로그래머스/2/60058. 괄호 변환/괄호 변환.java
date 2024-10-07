import java.lang.*;
import java.util.*;

class Solution {
    public String solution(String p) {
        String answer = dfs(p);
        return answer;
    }
    
    private static String dfs(String str){
        
        if(str.isEmpty()) return "";
        
        int idx = balanceStr(str);
        String u = str.substring(0, idx);
        String v = str.substring(idx, str.length());
        
        if(isCorrectStr(u)) return u + dfs(v);
        else return "(" + dfs(v) + ")"+ changeStr(u);
    }
    
    private static int balanceStr(String str){
        int open = 0;
        int close = 0;
        
        for(int i=0; i<str.length() ;i++){
            char ch = str.charAt(i);
            
            if(ch == '(') open++;
            else close++;
            
            if(open == close){
                return open + close;
            }
        }
        
        return 0;
    }
    
    private static boolean isCorrectStr(String str){
        Stack<Character> stack = new Stack();
        
        for(int i=0 ; i<str.length() ; i++){
            char ch = str.charAt(i);
            
            if(ch == '('){
                stack.push(ch);
            }else{
                if(stack.isEmpty()) return false;
                
                stack.pop();
            }
        }
        
        if(!stack.isEmpty()) return false;
        return true;
    }
    
    private static String changeStr(String str){
        StringBuilder sb = new StringBuilder();
        
        for(int i=1 ;i<str.length() -1 ; i++){
            char ch = str.charAt(i);
            
            if(ch == '(') sb.append(')');
            else sb.append('(');
        }
        
        return sb.toString();
    }
}