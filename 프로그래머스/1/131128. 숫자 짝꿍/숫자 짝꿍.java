import java.lang.*;
import java.util.*;

class Solution {
    public String solution(String X, String Y) {
        Map<String, Integer> x = new HashMap<>();
        Map<String, Integer> y = new HashMap<>();
        
        for(String str : X.split("")){
            x.put(str, x.getOrDefault(str, 0)+1);
        }
        
        for(String str : Y.split("")){
            y.put(str, y.getOrDefault(str, 0)+1);
        }
        
        List<String> sameThing = new ArrayList<>();
        for(String str : x.keySet()){
            if(!y.containsKey(str)) continue;
            
            for(int i=0; i<Math.min(x.get(str), y.get(str)) ; i++){
                sameThing.add(str);
            }
        }
        
        sameThing.sort((o1, o2) -> o2.compareTo(o1));
        if(sameThing.isEmpty()) return "-1";
        if(sameThing.get(0).equals("0")) return "0";
        
        StringBuilder sb = new StringBuilder();
        for(String str : sameThing){
            sb.append(str);
        }

        return sb.toString();
    }
}