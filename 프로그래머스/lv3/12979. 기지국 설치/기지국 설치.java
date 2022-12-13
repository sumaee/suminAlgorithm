import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        int first = stations[0]-w-1; // 첫번째 기지국이 1번 아파트까지 도달하는지 확인
        
        //만약 도달하지 못했다면
        if(first>0){
            if(first % (1+2*w)>0) answer+= first/(1+2*w)+1;
            else answer+= first/(1+2*w);
        }
        
        //사이의 아파트
        for(int i=0; i<stations.length-1; i++){
            int second = stations[i+1] - stations[i]-2*w-1;
            if(second% (1+2*w)>0) answer+=second/(1+2*w)+1;
            else answer+= second/(1+2*w);
        }
        
        //마지막 아파트
        int last = n - stations[stations.length-1]-w;
        if(last>0){
             if(last % (1+2*w)>0) answer+= last/(1+2*w)+1;
            else answer+= last/(1+2*w);
        }
 
        return answer;
    }
}