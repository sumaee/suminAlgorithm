import java.lang.*;
import java.util.*;
class Solution {
    static int answer = Integer.MAX_VALUE;
    public int solution(int[][] beginning, int[][] target) {
        int n = beginning.length; 
        int m = beginning[0].length;
        
        dfs(beginning, target, n, m, 0, 0);
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    private static void dfs(int[][] beg, int[][] tar, int n, int m, int row, int cnt){
        if(row == n){
            int result = cnt;
            for(int c = 0 ; c <m ; c++){
                int diffCnt = check(c, n, beg, tar);
                if(diffCnt == n) result++;
                else if(diffCnt != 0) return;
            }
            
            answer = Math.min(answer, result);
            return;
        }
        
        changeRow(row, m, beg);
        dfs(beg, tar, n, m, row+1, cnt+1);
        changeRow(row, m, beg);
        dfs(beg, tar, n, m, row +1 , cnt);
    }
    
    private static void changeRow(int row, int m, int[][] beg){
        for(int i=0 ;i<m ;i++){
            beg[row][i] = beg[row][i] == 1 ? 0:1;
        }
    }
    
    private static int check(int c, int n, int[][] beg, int[][]tar){
        int cnt = 0;
        for(int i=0; i<n ; i++){
            if(beg[i][c] != tar[i][c]) cnt++;
        }
        return cnt;
    }
}