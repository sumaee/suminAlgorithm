import java.util.*;
import java.lang.*;

class Solution {
    // 고속도로를 먼저 진출한 순서로 정렬 후 진출 차로에 설치
    public int solution(int[][] routes) {
        int answer = 0;
     
        Arrays.sort(routes, (a,b) -> a[1] - b[1]);
           
        int camera = Integer.MIN_VALUE;
        for(int[] route : routes){            
            if(route[0]>camera){
                answer++;
                camera = route[1];
            }
        }
        return answer;
    }
}