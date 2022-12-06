import java.lang.*;
import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int cnt =(int)Math.sqrt(yellow); // 될 수 있는 경우의 수
        int row = 1;
        
        for(; row<=cnt; row++){
            
            //만약 yellow가 row로 안나눠 떨어진다면 직사각형을 만들수 없으므로 패스
            if(yellow%row != 0)
                continue;
            
            // col은 yellow의 개수에서 row를 나눈 몫
            int col = yellow/row;
            
            //갈색 격자의 수는 노란색격자의 (가로+세로)*2+4 이므로 같다면 그 수가 정답
            // (가로+세로)*2 : 노란색 격자로 만든 사각형의 테두리
            // +4 : 카펫의 꼭짓점
            if((row+col)*2+4 == brown){
                answer[0] = col+2;
                answer[1] = row+2;
                break;
            }
        }
        return answer;
    }
}