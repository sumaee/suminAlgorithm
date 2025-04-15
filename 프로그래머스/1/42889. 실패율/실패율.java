import java.lang.*;
import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        PriorityQueue<Stage> que = 
            new PriorityQueue<>((o1, o2) -> Double.compare(o1.failPer, o2.failPer) == 0 ? 
                                o1.idx - o2.idx : o2.failPer.compareTo(o1.failPer));
        Arrays.sort(stages);
        
        int playerCnt = stages.length;
        int stage = 1;
        for(int i=0; i<stages.length ; i++){
            if(stage > N) continue;
            
            int successCnt = 0;
            while(i<stages.length && stages[i] == stage){
                successCnt++;
                i++;
            }
            
            i--;
            
            if(successCnt == 0){
                que.offer(new Stage(0, stage));
                stage++;
                continue;
            }
            
            que.offer(new Stage((double) successCnt/(double) playerCnt, stage));
            playerCnt -= successCnt;
            stage++;
        }
        
        while(stage <= N){
            que.offer(new Stage(0, stage));
            stage++;
        }
    
    
        int[] answer = new int[N];
        int i = 0;
        System.out.println(que.size());
        while(!que.isEmpty()){
            Stage curr = que.poll();
            answer[i++] = curr.idx;
        }
        
        return answer;
    }
    
    static class Stage{
        Double failPer;
        int idx;
        
        public Stage(double failPer, int idx){
            this.failPer = failPer;
            this.idx = idx;
        }
    }
}