import java.lang.*;
import java.util.*;

class Solution{
    public int solution(String s){
        int end = s.length();
        int max = s.length();
        int answer = 0;
        for(int i=0; i<end; i++){
            int left = i; // 시작점
            int right = end -1; //맨 끝에서 시작
            int result = 0;
            int cnt = 1; // 반복 횟수
            while(left<right){
                if(s.charAt(left)==s.charAt(right)){
                    left++;
                    right--;
                    result+=2;
                }else{
                    result = 0;
                    left = i;
                    right = end-1-cnt;
                    cnt++;
                }
            }
            
            if(left == right) result ++;
            
            answer = Math.max(result, answer);
        }
        return answer;
    }
}