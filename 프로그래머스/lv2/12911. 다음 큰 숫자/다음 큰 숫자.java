import java.util.*;
import java.lang.*;

class Solution {
    public int solution(int n) {
        int num = n+1;
        int one = cntOne(n);
        
        while(true){
            if(cntOne(num++)==one){
                break;
            }
        }
        
        
        return num-1;
    }
    
    public int cntOne(int n){
        
        int cnt = 0;
        while(n>0){
            if(n%2==1){
                cnt++;
            }
            n/=2;
        }
        return cnt;
    }
}