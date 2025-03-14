import java.lang.*;
import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> map = new HashMap<>();
        for(int i=0 ; i<players.length ; i++){
            map.put(players[i], i);
        }
        
        for(String calling : callings){
            int callPlayerIdx = map.get(calling);
            String frontPlayer = players[callPlayerIdx-1];
            String backPlayer = players[callPlayerIdx];
            
            players[callPlayerIdx] = frontPlayer;
            players[callPlayerIdx-1] = backPlayer;
            
            map.put(frontPlayer, callPlayerIdx);
            map.put(backPlayer, callPlayerIdx-1);
        }
        
        return players;
    }
}