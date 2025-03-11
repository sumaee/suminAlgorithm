import java.lang.*;
import java.util.*;

class Solution {
    static int n, m;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static String[][] map;
    static boolean[][] visited;
    static List<Container> containerCnt;
    
    public int solution(String[] storage, String[] requests) {
        n = storage.length+2;
        m = storage[0].length()+2;
        map = new String[n][m];
        visited = new boolean[n][m];
        containerCnt = new ArrayList<>();
        
        for(int i=0 ; i<n ; i++){
            Arrays.fill(map[i] , "-");
        }
        
        for(int i=1; i<n-1 ;i++){
            for(int j=1; j<m-1 ; j++){
                map[i][j] = storage[i-1].substring(j-1, j);
            }
        }
        
        for(String request : requests){
            String target = request.substring(0, 1);
            
            // 지게차라면 모서리 찾아 없애기
            if(request.length() == 1){
                //모서리 컨테이너 찾기
                visited = new boolean[n][m];
                findContainer(0, 0, target);
                
                //컨테이너 빼기
                for(int i=0; i<n ;i++){
                    for(int j=0; j<m ; j++){
                        if(map[i][j].equals("~")){
                            map[i][j] = "-";
                        }
                    }
                }
            }else{
                for(int i=0; i<n ;i++){
                    for(int j=0; j<m ;j++){
                        if(map[i][j].equals(target)){
                            map[i][j] = "-";
                        }
                    }
                }
            }
        }
        return count();
    }
    
    private static void findContainer(int row, int col, String target){
        Queue<Container> que = new LinkedList<>();
        que.offer(new Container(row, col));
        visited[row][col] = true;
        
        while(!que.isEmpty()){
            Container curr = que.poll();
            
            for(int i=0; i<4 ;i++){
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];
                
                //범위를 벗어나거나 이미 제거된 컨테이너면 패스
                if(drow < 0 || dcol < 0 || drow >=n || dcol >=m || visited[drow][dcol]) continue;
                
                //해당 위치가 뺄 컨테이너가 있다면 ~로 변경
                if(map[drow][dcol].equals(target)){
                    map[drow][dcol] = "~";
                }else if(map[drow][dcol].equals("-")){
                    que.offer(new Container(drow, dcol));
                }
                
                visited[drow][dcol] = true;
            }
        }
    }
    
    private static int count(){
        int cnt = 0;
        for(int i=0; i<n ; i++){
            for(int j=0; j<m ; j++){
                if(!map[i][j].equals("-")) cnt++;
            }
        }
        return cnt;
    }
    
    static class Container{
        int row, col;
        
        public Container(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
}