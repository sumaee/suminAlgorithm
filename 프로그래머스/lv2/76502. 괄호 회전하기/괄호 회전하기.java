import java.lang.*;
import java.util.*;

class Solution {
    public int solution(String s) {
        
       
        int answer = 0;
        //회전 시키는 횟수는 글자의 길이의 수만큼이므로 길이만큼 돌림
        for(int i=0; i<s.length(); i++){
            //true라면 올바른 괄호
            if(check(s)) answer ++;
            //문자열 회전 시키기
            s = s.substring(1, s.length())+s.charAt(0);
        }
        return answer;
    }
    
    public boolean check(String s){
         Stack<Character> stack = new Stack();
                
        //스택을 사용해서 현재 문자열이 올바른 괄호인지 확인
        for(int j=0; j<s.length(); j++){
            //만약 여는 괄호라면 스택에 추가
            if(s.charAt(j)=='['||s.charAt(j)=='{'||s.charAt(j)=='(')
                stack.push(s.charAt(j));
            //만약 스택이 비어있지 않다면 스택에서 꺼낸 괄호와 지금 괄호가 올바른 것인지 확인
            else if(!stack.isEmpty()){
                char ch = stack.pop();
                char now = s.charAt(j);
                
                //괄호 성립하면 계속 진행
                if((ch == '[' && now == ']') || (ch =='{'&& now =='}')||(ch=='('&& now==')')) continue;
                
                //괄호가 안맞으면 땡!
                else return false;
            }
            //스택에서 빼낼게 없으면 땡!
            else return false;
        }
        
        //for문을 다 돌았을 때 스택이 빈것인지 아닌지 확인
        if(stack.isEmpty()) return true;
        else return false;
    }
}