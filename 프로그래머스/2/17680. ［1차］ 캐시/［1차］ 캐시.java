import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        Queue<String> que = new LinkedList<>();
        int answer = 0;
        for(String city : cities){
            city = city.toLowerCase();
            
            if(que.contains(city)){
                que.remove(city);
                que.offer(city);
                answer++;
            }else{
                if(cacheSize>0){
                    if(que.size() >= cacheSize){
                        que.poll();
                    }
                    que.offer(city);
                }
                answer+=5;
            }
        }
        return answer;
    }
}