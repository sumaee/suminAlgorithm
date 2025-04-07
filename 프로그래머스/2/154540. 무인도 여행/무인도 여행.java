import java.lang.*;
import java.util.*;

class Solution {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[][] visited;
    public int[] solution(String[] maps) {
        
        visited = new boolean[maps.length][maps[0].length()];
        List<Integer> answer = new ArrayList<>();
        for(int i=0; i<maps.length; i++){
            for(int j=0 ; j<maps[i].length() ; j++){
                if(maps[i].charAt(j) != 'X' && !visited[i][j]){
                    answer.add(dfs(i, j, maps));
                }
            }
        }
        
        if(answer.isEmpty()) return new int[]{-1};
        
        answer.sort((o1, o2) -> o1 - o2);
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private static int dfs(int row, int col, String[] maps){
        Queue<Locate> que = new LinkedList<>();
        visited[row][col] = true;
        
        que.offer(new Locate(row, col));
        int sum = (maps[row].charAt(col) - '0');
        while(!que.isEmpty()){
            Locate curr = que.poll();
            
            for(int i=0; i<4 ;i++){
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];
                
                if(drow <0 || dcol < 0 || drow >=maps.length || dcol >=maps[0].length() 
                   || visited[drow][dcol] || maps[drow].charAt(dcol) == 'X') continue;
                
                que.offer(new Locate(drow, dcol));
                visited[drow][dcol] = true;
                sum += (maps[drow].charAt(dcol) - '0');
            }
        }
        
        return sum;
    }
    
    static class Locate{
        int row, col;
        
        public Locate(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
}