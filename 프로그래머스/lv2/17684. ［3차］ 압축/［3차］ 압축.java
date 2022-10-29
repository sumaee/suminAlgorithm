import java.lang.*;
import java.util.*;

class Solution {
    public List<Integer> solution(String msg) {
        Map<String, Integer> dic = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        //알파벳 우선 넣기
        int idx =1;
       for(char i= 'A' ; i<='Z'; i++){
           dic.put(i+"", idx++);
       }
        
        for(int i=0; i<msg.length(); i++){
            int j = 1;
            //msg 길이를 벗어나지 않고 dic에 없을 때까지 j++해주기
            while(i+j<=msg.length() && dic.containsKey(msg.substring(i, i+j))){
                j++;
            }
            
            //만약 범위가 벗어난다면 msg의 끝이므로 끝내기
            if(i+j>msg.length()){
                result.add(dic.get(msg.substring(i)));
                break;
            }
            
            result.add(dic.get(msg.substring(i, i+j-1)));
            dic.put(msg.substring(i, i+j), idx++);
            if(j>1)
                i+=j-2;
        }
        
        return result;
    }
}