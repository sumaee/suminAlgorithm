import java.lang.*;
import java.util.*;

class Solution {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[][] map;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        map = new int[101][101];
        
        for(int i=0 ; i< rectangle.length; i++){
            checkSide(rectangle[i][0]*2, rectangle[i][1]*2, rectangle[i][2]*2, rectangle[i][3]*2);
        }
        
        return bfs(characterX*2, characterY*2, itemX*2, itemY*2);
    }
    
    private static int bfs(int startX, int startY, int itemX, int itemY){
        Queue<Locate> que = new LinkedList<>();
        boolean[][] visited = new boolean[101][101];
        
        que.offer(new Locate(startX, startY, 0));
        visited[startY][startX] = true;
        
        while(!que.isEmpty()){
            Locate curr = que.poll();
            
            if(curr.x == itemX && curr.y ==itemY){
                return curr.cnt/=2;
            }
            
            for(int i=0; i<4 ;i++){
                int dxx = curr.x + dx[i];
                int dyy = curr.y + dy[i];
                
                if(dxx < 0 || dyy < 0 || dxx >=101 || dyy>= 101 || visited[dyy][dxx] || map[dyy][dxx] != 2) continue;
                
                que.offer(new Locate(dxx, dyy, curr.cnt+1));
                visited[dyy][dxx] = true;
            }
        }
        
        return 0;
    }
    
    private static void checkSide(int x1, int y1, int x2, int y2){
        for(int i= y1 ; i<=y2 ;i++){
            for(int j=x1 ; j<=x2 ; j++){
                if(map[i][j] == 1) continue;
                
                map[i][j] = 1;
                if(i == y1 || i == y2 || j == x1 || j == x2){
                    map[i][j] = 2;
                }
            }
        }
    }
    
    static class Locate{
        int x, y, cnt;
        public Locate(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}