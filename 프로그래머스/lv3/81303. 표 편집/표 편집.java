import java.lang.*;
import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        StringBuilder answer = new StringBuilder();
        
        //되돌리기를 위해 삭제되는 위치를 담을 스택
        Stack<Integer> delete = new Stack<>();
        
        int len = n;
        //cmd의 길이만큼 실행
        for(int i=0; i<cmd.length; i++){
            //cmd
            char direction = cmd[i].charAt(0);
            
            //cmd의 방향이 D인 경우, 가리키는 위치만 바꿔줌
            if(direction=='D'){
                // k+= (cmd[i].charAt(2)-'0');
                k += Integer.parseInt(cmd[i].substring(2));
            }
                
            
            //cmd의 방향이 U인 경우, 가리키는 위치만 바꿔줌
            else if(direction=='U'){
                 // k-= (cmd[i].charAt(2)-'0');
                k -= Integer.parseInt(cmd[i].substring(2));
            }
               
             
            //cmd의 방향이 C인 경우
            else if(direction=='C'){
                //우선 삭제되는 위치를 스택에 추가
                delete.push(k);
                // //위치 삭제
                // answer.deleteCharAt(k);
                len-=1;
                //삭제할 행이 마지막 행이라면 윗행을 가리켜야 하므로 -1진행
                if(k==len)
                    k-=1;
            }
            
            //cmd의 방향이 Z인 경우
            else{
                int rollback = delete.pop();
                // answer.insert(rollback, 'O');
                
                //만약 돌이킨 위치가 현재 가리키는 위치보다 작다면 가르키는 위치는 한칸 뒤로 
                k = rollback<=k ? k+1 : k;
                len+=1;
            }
        }
        
        for(int i=0; i<len ; i++){
            answer.append('O');
        }
        
        //스택에 남아있는 것 x로 빼내기
        while(!delete.isEmpty()){
            int last = delete.pop();
            answer.insert(last, 'X');
        }
        
        
        
        return answer.toString();
    }
}