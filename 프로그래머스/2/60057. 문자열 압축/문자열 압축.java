import java.lang.*;
import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = s.length();
        StringBuilder str;
        for(int i = 1; i < s.length()/2 +1; i++){
            int repeatCnt = 1;
            str = new StringBuilder();
            String originStr = s.substring(0, i);
            
            for(int j=i ; j<s.length()+1 ; j+=i){
                String compareStr = s.substring(j, Math.min(s.length(), j+i));
                
                if(compareStr.equals(originStr)){
                    repeatCnt++;
                }else{
                    if(repeatCnt != 1){
                        str.append(repeatCnt);
                        repeatCnt = 1;
                    }
                    
                    str.append(originStr);
                    originStr = compareStr;
                }
            }
            
            str.append(originStr);
            answer = Math.min(answer, str.toString().length());
            
        }
        
        return answer;
    }
}