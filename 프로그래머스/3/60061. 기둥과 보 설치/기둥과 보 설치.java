import java.lang.*;
import java.util.*;

class Solution {
    static int[][][] map = new int[101][101][2];
    static int possibleInstall;
    
    public int[][] solution(int n, int[][] build_frame) {
        possibleInstall = 0;
        for(int i=0; i<build_frame.length; i++){
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            int a = build_frame[i][2];
            int b = build_frame[i][3];
            
            //삭제
            if(b == 0){
                map[x][y][a] = 0;
                // 삭제 가능한지 확인
                if(isPossibleDelete(n, x, y, a)){
                    possibleInstall--;
                }else{
                    map[x][y][a] = 1;
                }
            }
            //설치
            else if(b == 1){
                // 설치 조건 만족하는지 확인
                if(isPossibleInstall(n, x, y, a)){
                    map[x][y][a] = 1;
                    possibleInstall++;
                }
            }
        }
        
        int[][] answer = new int[possibleInstall][3];
        int idx = 0;
        for(int i=0; i<=n ; i++){
            for(int j=0 ; j<=n ; j++){
                for(int k = 0 ; k<=1 ; k++){
                    if(map[i][j][k] == 1){
                        answer[idx][0] = i;
                        answer[idx][1] = j;
                        answer[idx][2] = k;
                        idx++;
                    }
                }
            }
        }
        
        return answer;
    }
    
    private static boolean isPossibleDelete(int n, int x, int y, int a){
        //기둥이라면
        if(a == 0){
            // 삭제될 기둥 위의 왼쪽에 설치된 보
            if(x-1 >= 0 && map[x-1][y+1][1] == 1 && !isPossibleInstall(n, x-1, y+1, 1)) return false;
            // 삭제될 기둥 위의 오른쪽에 설치된 보
            if(x+1 <= n && map[x][y+1][1] == 1 && !isPossibleInstall(n, x, y+1, 1)) return false;
            // 삭제될 기둥 위에 설치된 기둥
            if(y+2 <= n && map[x][y+1][0] == 1 && !isPossibleInstall(n, x, y+1, 0)) return false;
        }
        // 보라면
        else if(a == 1){
            // 삭제될 보의 왼쪽 위로 설치된 기둥
            if(y+1 <= n && map[x][y][0] == 1 && !isPossibleInstall(n, x, y, 0)) return false;
            // 삭제될 보의 오른쪽 위로 설치된 기둥
            if(y+1 <= n && map[x+1][y][0] == 1 && !isPossibleInstall(n, x+1, y, 0)) return false;
            // 삭제될 보의 왼쪽에 설치된 보
            if(x-1 >= 0 && map[x-1][y][1] == 1 && !isPossibleInstall(n, x-1, y, 1)) return false;
            // 삭제될 보의 오른쪽에 설치된 보
            if(x+2 <= n && map[x+1][y][1] == 1 && !isPossibleInstall(n, x+1, y, 1)) return false;
        }
        
        return true;
    }
    private static boolean isPossibleInstall(int n, int x, int y, int a){
        //기둥이라면
        if(a == 0){
            // 바닥에 설치하는 거라면
            if(y == 0) return true;
            // 보의 왼쪽에 설치
            if(x+1 <= n && map[x][y][1] == 1) return true;
            // 보의 오른쪽에 설치
            if(x-1 >= 0 && map[x-1][y][1] == 1) return true;
            // 다른 기둥 위에 설치
            if(y-1 >= 0 && map[x][y-1][0] == 1) return true;
        }
        //보라면
        else if(a == 1){
            // 보의 왼쪽에 기둥 존재
            if(y-1 >= 0 && map[x][y-1][0] == 1) return true;
            // 보의 오른쪽에 기둥 존재
            if(y-1 >=0 && map[x+1][y-1][0] == 1) return true;
            // 양쪽 끝이 다른 보 존재
            if(x-1 >=0 && map[x-1][y][1] == 1 && x+2 <= n && map[x+1][y][1] == 1) return true;
        }
        
        return false;
    }
}