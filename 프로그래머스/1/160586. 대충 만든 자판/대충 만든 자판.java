import java.lang.*;
import java.util.*;
class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        Map<Character, Integer> keys = new HashMap<>();
        
        for(String key : keymap){
            for(int i=0; i<key.length() ;i++){
                char ch = key.charAt(i);
                
                if(!keys.containsKey(ch)){
                    keys.put(ch, i+1);
                    continue;
                }
                
                keys.put(ch, Math.min(keys.get(ch), i+1));
            }
        }
        
        int[] answer = new int[targets.length];
        for(int i=0; i<targets.length; i++){
            int cnt = 0;
            
            for(int j=0 ; j<targets[i].length(); j++){
                char ch = targets[i].charAt(j);
                
                if(!keys.containsKey(ch)) {
                    cnt = -1;
                    break;
                }
                cnt += keys.get(ch);

            }
            System.out.println();
            answer[i] = cnt;
        }
        return answer;
    }
}