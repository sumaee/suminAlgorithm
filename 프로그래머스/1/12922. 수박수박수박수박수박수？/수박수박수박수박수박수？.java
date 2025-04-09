import java.lang.*;
import java.util.*;
class Solution {
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        while(i<=n){
            if(i % 2 == 1) sb.append("수");
            else sb.append("박");
            
            i++;
        }
        
        return sb.toString();
    }
}