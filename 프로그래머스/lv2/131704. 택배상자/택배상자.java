import java.util.*;
import java.lang.*;

class Solution {
    public int solution(int[] orders) {
        //택배를 보관할 스택
        Stack<Integer> save = new Stack<>();        
        int answer = 0;
        
        //택배 시작점
        int idx = 1;
        for(int i=0; i<orders.length ; i++){
            int order = orders[i];
            //order가 idx보다 크다면 값이 나올 때까지 담기
            if(order > idx){
                while(order>idx){
                    save.push(idx++);
                }
                idx++;
            }
            
            //order가 idx보다 작 save 스택의 위의 값 확인
            else if(order<idx){
                 //스택의 맨 윗값이 order와 같다면 꺼내기
                if(save.peek() == order){
                    save.pop();
                }
                //스택의 맨 윗값이 order와 다르다면 끝
                else return answer;
            }
            
            // idx와 order 가 같다면 그냥 idx 증가
            else{
                idx++;
            }
            answer ++;
        }

        return answer;
    }
}