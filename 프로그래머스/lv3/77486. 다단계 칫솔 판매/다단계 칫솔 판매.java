import java.lang.*;
import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Info> cost = new HashMap<>(); // 추천인과 이익을 담기 위한 map
        
        //우선 각 직원들에 대해 추천인과 이익: 0원을 집어넣음
        for(int i=0; i<enroll.length ; i++)
            cost.put(enroll[i], new Info(referral[i], 0));
                    
        
        // seller 랑 amount를 돌며 계산
        for(int i=0; i<seller.length ; i++){  
            //현재 사람
            String now = seller[i];
            //현재 사람의 이익
            int profit = amount[i]*100;
            
            //최상위 노드가 아닐 때까지 진행
            while(!now.equals("-")){
                //추천인에게 넘겨줄 금액
                int money = profit / 10;
                
                // 내가 가져갈 금액
                int myMoney = profit - money;
                
                cost.get(now).sale += myMoney;
                
                // 사람 이어가기
                now = cost.get(now).person;
                profit /=10;
                
                //10% 계산 후가 1원 미만일 때 그만
                if(profit < 1)
                    break;
            }
        }
        
        int[] answer = new int[enroll.length];
        for(int i=0; i<enroll.length ; i++)
            answer[i] = cost.get(enroll[i]).sale;
        
        return answer;
    }
    
}

class Info{
    String person; // 추천인
    int sale; // 판매량
    
    public Info(String person, int sale){
        this.person = person;
        this.sale = sale;
    }
}