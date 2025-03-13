import java.lang.*;
import java.util.*;

class Solution {
    static int[] dr = {1, 0, 0, -1};
    static int[] dc = {0,-1, 1, 0};
    static char[] dir = {'d', 'l', 'r', 'u'};
    static String answer = null;
    static StringBuilder sb;
    static Locate endPoint;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        sb = new StringBuilder();
        endPoint = new Locate(r, c);
        if(!isPossible(x, y, r, c, k)){
            return "impossible";
        }
        dfs(k, 0, x, y, n, m);
        return answer == null ? "impossible" : answer;
    }
    
    private static boolean isPossible(int x, int y, int r, int c, int k){
        int moveCnt = Math.abs(x-r) + Math.abs(y-c);
        
        if((k-moveCnt) % 2 == 1 || k < moveCnt) return false;
        return true;
    }
    private static void dfs(int k, int depth, int row, int col, int n, int m){
        if(answer != null) return;
        if(k-depth < Math.abs(row - endPoint.row) + Math.abs(col - endPoint.col)) return;
        if(k == depth){
            answer = sb.toString();
            System.out.println("정답: " + answer);
            return;
        }
        
        for(int i=0; i<4 ;i++){
            int drow = row + dr[i];
            int dcol = col + dc[i];
            if(drow <= 0 || drow > n || dcol <= 0 || dcol > m) continue;
            sb.append(dir[i]);
            dfs(k, depth + 1, drow, dcol, n, m);
            sb.delete(depth, depth+1);
        }
    }
    
    static class Locate{
        int row, col;
        
        public Locate(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
}