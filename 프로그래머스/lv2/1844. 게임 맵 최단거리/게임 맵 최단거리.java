import java.lang.*;
import java.util.*;

class Solution {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    public int solution(int[][] maps) {
        //답을 담을 배열
        int[][] result = new int[maps.length][maps[0].length];
        result[0][0]=1;
        bfs(maps, result);
        
        int answer = result[maps.length-1][maps[0].length-1];
        
        if(answer == 0){
            answer = -1;
        }
        
        return answer;
    }
    
    public void bfs(int[][] maps, int[][] result){
        Queue<Locate> que = new LinkedList<>();
        que.offer(new Locate(0, 0));
        
        while(!que.isEmpty()){
            Locate curr = que.poll();
            int row = curr.row;
            int col = curr.col;
            
            for(int i=0 ; i<4 ; i++){
                int r = row + dr[i];
                int c = col + dc[i];
                
                if(r>=0 && r<maps.length && c>=0 && c<maps[0].length){
                    if(maps[r][c] == 1 && result[r][c]==0){
                        result[r][c] = result[row][col]+1;
                        que.offer(new Locate(r, c));
                    }
                }
            }
        }
    }   
}

class Locate{
    int row, col;
    public Locate(int row,int col){
        this.row = row;
        this.col = col;
    }
}