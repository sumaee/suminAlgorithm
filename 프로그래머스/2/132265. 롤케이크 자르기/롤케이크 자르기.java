import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int[] topping) {
        Set<Integer> bro = new HashSet<>();
        Map<Integer, Integer> you = new HashMap<>();
        
        for(int top : topping){
            you.put(top, you.getOrDefault(top, 0)+1);
        }
        
        int answer = 0;
        for(int i=0 ; i<topping.length ;i++){
            int top = topping[i];
            
            bro.add(top);
            you.put(top, you.get(top)-1);
            
            if(you.get(top) == 0){
                you.remove(top);
            }
            
            if(you.size() == bro.size()) answer++;
        }
        return answer;
    }
}