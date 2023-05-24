import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> save = new PriorityQueue<>((o1, o2)-> o2-o1);
        int answer = enemy.length;
        for(int i=0; i<enemy.length ; i++){
            n -= enemy[i];
            save.offer(enemy[i]);
            
            if(n<0){
                if(k>0 && !save.isEmpty()){
                    n+=save.poll();
                    k--;
                }else{
                    answer = i;
                    break;
                }
            }
        }
        return answer;
    }
}