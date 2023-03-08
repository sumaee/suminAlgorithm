import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        //결과의 승패를 담을 2차원 배열
        int[][] graph = new int[n][n];
        
        //이겼다면 1
        for(int i=0; i<results.length; i++){
            int win = results[i][0]-1;
            int lose = results[i][1]-1;
            
            graph[win][lose]=1;
            graph[lose][win]=-1;
        }
        
        //결과를 알 수 있는 경기를 최대한 채우기 위한 과정
        //1>2 이고 2>5 라면 1>5이다
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    if(graph[j][i] == 1 && graph[i][k] == 1){
                        graph[j][k] = 1;
                        graph[k][j] = -1;
                    }
                    if(graph[j][i] == -1 && graph[i][k] == -1){
                        graph[j][k] = -1;
                        graph[k][j] = 1;
                    }
                }
            }
        }
        
        // 게임 수가 (인원수-1) 만큼 채워진 사람은 확실하게 등수를 알 수 있음
        int answer = 0;
         for(int i = 0; i < n; i++){
            int gameCnt = 0; 
            for(int j = 0; j < n; j++){
                if(graph[i][j] != 0) gameCnt++;
            }
            if(gameCnt == n-1) answer++;
        }
        
        return answer;
    }
}