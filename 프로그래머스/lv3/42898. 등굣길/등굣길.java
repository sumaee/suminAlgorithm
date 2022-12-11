class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n][m];
       
        // 물웅덩이인 부분 dp=-1로 변경
        for(int[] puddle: puddles){
            dp[puddle[1]-1][puddle[0]-1] = -1;
        }
        
        //시작점은 1, 갈수 있는 길 모두 1로 만들고 최종 지점에서 합치기
        dp[0][0] = 1;
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                //해당 위치가 물웅덩이가 아니라면
                if(dp[i][j] != -1){
                    // 전의 위치에서 아래로 이동하고 전위치가 웅덩이가 아닐 경우
                    if(i>=1 && dp[i-1][j]!=-1){
                        //전의 숫자에서 지금 숫자 더해주기
                        dp[i][j] += dp[i-1][j];
                        
                    }
                    
                    //전의 위치에서 오른쪽으로 이동하고 전위치가 웅덩이가 아닐경우
                    if(j>=1 && dp[i][j-1]!=-1){
                        //전의 숫자에서 지금 숫자 더해주기
                        dp[i][j] += dp[i][j-1];
                    }
                    
                    //구하다가 숫자가 커지는 경우에 바로바로 나눠주기
                    if(dp[i][j]>1000000007){
                        dp[i][j]%=1000000007;
                    }
                }
            }
        }
        return dp[n-1][m-1];
    }
}