import java.util.*;
class Solution {
    public int solution(String[] wants, int[] number, String[] discount) {
        Queue<String> que = new LinkedList<>();
        Map<String, Integer> cnt = new HashMap<>();
        
        int result=0;
        for(int i=0; i<10; i++){
            que.offer(discount[i]);
            cnt.put(discount[i], cnt.getOrDefault(discount[i], 0)+1);
        }
        
        if(check(cnt, wants, number)){
            result++;
        }
        
        
        for(int i=10 ; i<discount.length ; i++){
            String out = que.poll();
            que.offer(discount[i]);
            cnt.put(out, cnt.get(out)-1);
            cnt.put(discount[i], cnt.getOrDefault(discount[i], 0)+1);
            if(check(cnt, wants, number)){
               result++; 
            }
            
        }
        
        return result;
    }
    
    public boolean check(Map<String, Integer> cnt, String[] wants, int[] number){
        
        for(int i=0; i<wants.length; i++){
            if(!cnt.containsKey(wants[i])){
               return false; 
            }
            
            if(cnt.get(wants[i]) < number[i]){
                return false;
            }
        }
        
        return true;
        
    }
}