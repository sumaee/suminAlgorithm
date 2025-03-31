import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int k, int n, int[][] reqs) {
        int[] counsel = new int[k+1];
        Arrays.fill(counsel, 1);
        
        int remain = n-k;
        while(remain>0){
            int minWaitTime = Integer.MAX_VALUE;
            int counselIdx = -1;
            for(int i=1; i<=k ; i++){
                counsel[i]++;
                
                int time = 0;
                for(int j=1 ;j<=k ;j++){
                    time+=sumTime(reqs, counsel, j);
                }
                
                if(minWaitTime > time){
                    minWaitTime = time;
                    counselIdx = i;
                }
                
                counsel[i]--;
            }
            
            counsel[counselIdx]++;
            remain--;
        }
        
        int answer = 0;
        for(int j=1 ;j<=k ;j++){
            answer+=sumTime(reqs, counsel, j);
        }
        
        return answer;
    }
    
    private static int sumTime(int[][]reqs, int[] counsel, int counselType){
        PriorityQueue<Integer> que = new PriorityQueue<>();
        
        int time = 0;
        for(int[] req : reqs){
            if(req[2] != counselType){
                continue;
            }
            
            if(que.size() < counsel[counselType]){
                que.offer(req[0] + req[1]);
                continue;
            }
            
            int finishTime = que.poll();
            time += Math.max(0, finishTime - req[0]);
            que.offer(Math.max(finishTime, req[0]) + req[1]);
        }
        return time;
    }
}