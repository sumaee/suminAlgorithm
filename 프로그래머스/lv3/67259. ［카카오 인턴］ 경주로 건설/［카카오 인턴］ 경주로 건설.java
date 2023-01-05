import java.lang.*;
import java.util.*;

class Solution {
    static int[][] cost;
    // static boolean[][] visited;
    static int value = Integer.MAX_VALUE;
    //방향 조절-> 상, 우, 하, 좌 순서
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    public int solution(int[][] board) {
        // 가격을 담을 배열
        cost = new int[board.length][board.length];
        for(int[] a : cost){
            Arrays.fill(a, Integer.MAX_VALUE);
        }
        
        // 방문처리를 할 것
        // visited = new boolean[board.length][board.length];
        
        //출발점 (0, 0)
        bfs(0, 0, -1, 0, board.length, board);
        int answer = value;
        return answer;
    }
    
    private static void bfs(int row, int col, int dir, int price, int n, int[][]board){
        Queue<Road> que = new LinkedList<>();
        que.offer(new Road(row, col, dir, price));
        // visited[row][col]=true;
        
        while(!que.isEmpty()){
            Road road = que.poll();
            
            //현재 있는 위치에서 정보들
            int crow = road.row;
            int ccol = road.col;
            int cdir = road.dir;
            int ccost = road.cost;
            
            //도착지에 도착한다면 최솟값 찾기
            if(crow == n-1 && ccol == n-1){
                value = Math.min(value, ccost);
            }
            
            for(int i=0; i<4; i++){
                int drow = crow + dr[i];
                int dcol = ccol + dc[i];
                int ddir = i;
                int dcost = ccost;
                if(drow<0 || dcol<0 || drow>=n || dcol>=n || board[drow][dcol]==1) continue;
                //이동한 값이 범위에 벗어나지 않고 벽이 아니라면 진행
                // if(drow>=0 && dcol>=0 && drow<n && dcol<n && cost[drow][dcol]!=1){
                    // 직선 방향일 때
                    if (cdir == -1 || cdir == ddir) {
                        dcost += 100;
                    }
                    //방향이 다를 때
                    else {
                        dcost += 600;
                    }
                      
                    // 방문하지 않았고 이전 값이 더 크다면 작은 값을 넣어준다
                    if (cost[drow][dcol] >= dcost-500) {
                        // visited[drow][dcol] = true;
                        cost[drow][dcol] = Math.min(cost[drow][dcol], dcost);
                        que.add(new Road(drow, dcol, ddir, dcost));
                    }
                  
                
                
            }
        }

    }
    
    private static class Road {
        int row, col, dir, cost;

         Road(int row, int col, int dir, int cost) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.cost = cost;
         }
    }

}