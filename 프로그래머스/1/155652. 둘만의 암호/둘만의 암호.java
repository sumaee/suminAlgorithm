import java.lang.*;
import java.util.*;

class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<s.length() ; i++){
            char ch = s.charAt(i);
            int skipCnt = 0;
            
            while(skipCnt < index){
                ch = (char)((ch-97+1)%26 + 97);
                if(!skip.contains(String.valueOf(ch))){
                    skipCnt++;
                }
            }
            
            sb.append(ch);
        }
        return sb.toString();
    }
}