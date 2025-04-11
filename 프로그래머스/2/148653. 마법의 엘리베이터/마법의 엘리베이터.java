import java.lang.*;
import java.util.*;
class Solution {
    public int solution(int storey) {
        int answer =0;
        while(storey >0){
            System.out.println("현재 층: "+ storey);
            int mod = storey % 10;
            storey /= 10;
            
            if(mod < 5){
                answer += mod;
            }else if(mod >5){
                answer += 10 - mod;
                storey++;
                System.out.println("2 : "+ storey);
            }else if(storey % 10 >=5){
                answer += 5;
                storey++;
                System.out.println("3 : "+ storey);
            }else{
                answer+=5;
            }
        }
        return answer;
    }
}