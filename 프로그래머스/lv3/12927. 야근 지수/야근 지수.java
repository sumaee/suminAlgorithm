import java.lang.*;
import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
       long answer=0;
        
        //야근 피로도 최소화 하기 위해서는 큰값부터 빼줘야함
       PriorityQueue<Integer> que = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    });
        
        for(int i=0 ;i<works.length; i++){
            que.offer(works[i]);
        }
        
        while(n!=0){
            int work = que.poll();
            //만약 돌다가 꺼낸게 0이면 끝
            if(work==0)
                break;
            work--;
            que.offer(work);
            n--;
        }
        
        for(int work: que){
            answer+=Math.pow(work, 2);
        }
        return answer;
    }
}