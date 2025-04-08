import java.lang.*;
import java.util.*;
class Solution {
    public int[] solution(long begin, long end) {
        List<Integer> answer = new ArrayList<>();
        while(begin <= end){
            answer.add(getPrime(begin));
            begin++;
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private static int getPrime(long n){
        if(n == 1){
            return 0;
        }
        
        if(n == 2 || n == 3){
            return 1;
        }
        
        int value = 0;
        for(int i=2 ; i<=Math.min(Math.sqrt(n), 10000000) ; i++){
            if(n % i == 0){
                value = (int) Math.max(value, n/i <= 10000000 ? n/i : i);
            }
        }
        
        return value == 0 ? 1 : value;
    }
}