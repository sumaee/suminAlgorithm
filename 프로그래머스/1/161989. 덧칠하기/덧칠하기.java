import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int start = section[0];
        answer++;
        
        for(int sec : section){
            if(start + m > sec) continue;
            
            start = sec;
            answer++;
        }
        
        return answer;
    }
}