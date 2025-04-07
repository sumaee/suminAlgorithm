import java.lang.*;
import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();
        for(String par : participant){
            map.put(par, map.getOrDefault(par, 0)+1);
        }
        
        
        for(String com : completion){
            map.put(com, map.get(com)-1);
        }
        
        String answer = null;
        for(String key : map.keySet()){
            if(map.get(key) != 0) {
                answer = key;
                break;
            }
        }
        
        return answer;
    }
}