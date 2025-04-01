import java.lang.*;
import java.util.*;

class Solution {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0 ,-1};
    public int solution(String[] board) {
        
        Locate start = null;
        Locate end = null;
        for(int i=0; i<board.length ; i++){
            for(int j=0 ; j<board[i].length() ; j++){
                if(board[i].charAt(j) == 'R') start = new Locate(i, j, 0);
                else if(board[i].charAt(j) == 'G') end = new Locate(i, j, 0);
            }
        }
        
        return bfs(start, end, board);
    }
    
    private static int bfs(Locate start, Locate end, String[] board){
        Queue<Locate> que = new LinkedList<>();
        boolean[][] visited = new boolean[board.length][board[0].length()];
        
        que.offer(start);
        visited[start.row][start.col] = true;
        
        while(!que.isEmpty()){
            Locate curr = que.poll();
            
            if(curr.row == end.row && curr.col ==end.col) return curr.move;
            
            for(int i=0; i<4 ;i++){
                int drow = curr.row;
                int dcol = curr.col;
                
                while(drow+dr[i] >=0 && dcol + dc[i] >=0 && drow + dr[i] < board.length && dcol+dc[i]<board[0].length()
                     && board[drow+dr[i]].charAt(dcol + dc[i]) != 'D'){
                    drow += dr[i];
                    dcol += dc[i];
                }
                
                if(visited[drow][dcol]) continue;
                visited[drow][dcol] = true;
                que.offer(new Locate(drow, dcol, curr.move+1));
            }
        }
        
        return -1;
    }
    
    static class Locate{
        int row, col, move;
        
        public Locate(int row, int col, int move){
            this.row = row;
            this.col = col;
            this.move = move;
        }
    }
}