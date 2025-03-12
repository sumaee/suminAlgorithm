import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int N, int number) {
        
        if(N == number) return 1;
        
        List<Set<Integer>> dpList = new ArrayList<>();
        for(int i=0 ;i<=8 ;i++){
            dpList.add(new HashSet<>());
        }
        
        dpList.get(1).add(N);
        
        for(int i=2 ; i<=8 ; i++){
            for(int j=1 ;j<=i ; j++){
                Set<Integer> firstSet = dpList.get(j);
                Set<Integer> secondSet = dpList.get(i-j);
                
                for(int first : firstSet){
                    for(int second : secondSet){
                        dpList.get(i).add(first + second);
                        dpList.get(i).add(first - second);
                        dpList.get(i).add(first * second);
                        if(first != 0 && second != 0){
                            dpList.get(i).add(first / second);
                        }
                    }
                }
            }
            
            dpList.get(i).add(Integer.parseInt(Integer.toString(N).repeat(i)));
            
            if(dpList.get(i).contains(number)){
                return i;
            }
        }
        
        return -1;
    }
}