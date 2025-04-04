import java.lang.*;
import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        //삭제된 행을 담기 위한 스택
        Stack<Integer> stack = new Stack<>();
        
        for(String command : cmd){
            String[] str = command.split(" ");
            
            if(str[0].equals("U")){
                k -= Integer.parseInt(str[1]);
            }else if(str[0].equals("D")){
                k += Integer.parseInt(str[1]);
            }else if(str[0].equals("C")){
                stack.push(k);
                n-=1;
                if(k == n){
                    k-=1;
                }
            }else{
                int back = stack.pop();
                k = back <= k ? k+1 : k;
                n+=1;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n ; i++){
            sb.append("O");
        }
        
        while(!stack.isEmpty()){
            sb.insert(stack.pop(), "X");
        }
        
        return sb.toString();
    }
}