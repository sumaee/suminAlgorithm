import java.lang.*;
import java.util.*;

class Solution {
    boolean[][] visited; // 방문처리할 배열
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    int sum;
    public List<Integer> solution(String[] maps) {
        List<Integer>answer = new ArrayList<>();
        visited = new boolean[maps.length][maps[0].length()];
        
        char[][] map = new char[maps.length][maps[0].length()];
        //이차원 배열로 maps 나누기
        int idx = 0;
        for(String str : maps){
            map[idx++] = str.toCharArray();
        }
        
        //map을 돌면서 x가 아닌 부분에서 dfs로 연결된 땅 확인
        for(int i=0; i<map.length ; i++){
            for(int j=0; j<map[0].length ; j++){
                //해당 지역이 x가 아니고 방문하지도 않았다면
                if(map[i][j] != 'X' && !visited[i][j]){
                    //sum 초기화
                    sum = 0;
                    dfs(map, i, j);
                    answer.add(sum);
                }
            }
        }
        //만약 정답이 비어있으면 -1 넣기
        if(answer.isEmpty()) answer.add(-1);
        //answer 오름차순 정리
        answer.sort((o1, o2)-> o1-o2);
        return answer;
    }
    
    private void dfs(char[][] map, int row, int col){
        //현재 방문한 지역 방문처리
        visited[row][col] = true;
        
        //값 더하기
        sum += map[row][col]-'0';
        
        //사방 확인
        for(int i=0; i<4 ; i++){
            int drow = row + dr[i];
            int dcol = col + dc[i];
            
            //해당 좌표가 map의 크기에 벗어난다면 다음으로 패스
            if(drow<0 || drow >= map.length || dcol <0 || dcol >=map[0].length) continue;
            //다음 넘어갈 부분이 x가 아니고 방문하지 않았다면 넘어가기
            if(map[drow][dcol] != 'X' && !visited[drow][dcol]){
                dfs(map, drow, dcol);
            }
        }
    }
}