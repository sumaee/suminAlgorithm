import java.lang.*;
import java.util.*;

class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        int idx = 0;
        for(int[] ball : balls){
            int targetX = ball[0];
            int targetY = ball[1];
            
            int len = Integer.MAX_VALUE;
            
            //윗 쿠션을 맞출 수 있을 때
            if(!(startX == targetX && startY <= targetY)){
                len = Math.min(calDist(startX, startY, targetX, n + (n - targetY)), len);
            }
            
            //아래 쿠션을 맞출 수 있을 때
            if(!(startX == targetX && startY >=targetY)){
                len = Math.min(calDist(startX, startY, targetX, targetY * (-1)), len);
            }
            
            //오른쪽 쿠션을 맞출 수 있을 때
            if(!(startY == targetY && startX <=targetX)){
                len = Math.min(calDist(startX, startY, m + (m - targetX), targetY), len);
            }
            
            //왼쪽 쿠션을 맞출 수 있을 때
            if(!(startY == targetY && startX >=targetX)){
                len = Math.min(calDist(startX, startY, targetX * (-1), targetY), len);
            }
            
            answer[idx++] = len;
        }
        
        return answer;
    }
    
    private static int calDist(int startX, int startY, int targetX, int targetY){
        return (int) (Math.pow(startX - targetX, 2) + Math.pow(startY - targetY, 2));
    }
}