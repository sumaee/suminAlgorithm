import java.lang.*;
import java.util.*;
/*
dp[k][0] : k번째 역삼각형을 오른쪽 방향 마름모로 채울떄
dp[k][1] : k번째 역삼각형을 윗방향 마름모, 왼쪽방향 마름모, 삼각형 하나로 채울때 
*/
class Solution {
    public int solution(int n, int[] tops) {
        int[][] dp = new int[n][2];
        
        dp[0][0] = 1;
        if(tops[0] == 1) dp[0][1] = 3;
        else dp[0][1] = 2;
        
        for(int i=1; i<n ;i++){
            //꼭대기가 존재한다면
            if(tops[i] == 1){
                dp[i][0] = dp[i-1][0] + dp[i-1][1];
                dp[i][1] = dp[i-1][0] * 2 + dp[i-1][1] * 3;
            }else {
                dp[i][0] = dp[i-1][0] + dp[i-1][1];
                dp[i][1] = dp[i-1][0] + dp[i-1][1] * 2;
            }
            
            dp[i][0] %= 10007;
            dp[i][1] %= 10007;
        }
        
        return (dp[n-1][0] + dp[n-1][1]) % 10007;
        
        
    }
}