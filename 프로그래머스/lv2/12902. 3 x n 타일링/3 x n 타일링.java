import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int n) {
        
        long[] dp = new long[n+1];
        dp[2] = 3;
        long total = 0;
        
        for(int i=4 ; i<=n ; i+=2){
            dp[i] = (dp[i-2]*3 + (total*2+2)) % 1000000007;
            
            total += dp[i-2]%1000000007;
        }
        
        return (int) dp[n];
    }
}