import java.lang.*;
import java.util.*;

class Solution{
    public int solution(String s){
        Stack<Character> check = new Stack<>();
        
        int idx = 0;
        while(idx<s.length()){
            //스택이 비었다면 넣어주기
            if(check.isEmpty())
                check.push(s.charAt(idx));
                
            //지금 스택의 최상단 값과 다음 결과값이 같다면 최상단 값 pop 해주기
            else if(s.charAt(idx) == check.peek())
                check.pop();
            
            //다르다면 집어넣어주기
            else
                check.push(s.charAt(idx));
            
            idx++;
        }
        
        int answer = check.isEmpty()? 1: 0;
        return answer;
    }
}