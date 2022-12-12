import java.util.*;
import java.lang.*;

class Solution {
    // 고속도로에 먼저 진입한 순서로 정렬 후 진입 차로에 설치
    public int solution(int[][] routes) {
        int answer = 0;
        List<int[]> car = new ArrayList<>();
        for(int[] route: routes){
           car.add(route);
        }
        Collections.sort(car, (a,b) -> b[0] - a[0]);
        while(!car.isEmpty()) {
            int position = car.get(0)[0];
 
            for(int i = 0; i<car.size(); i++ ) {
                if(car.get(i)[1]>=position){
                    car.remove(i);
                    i--;
                } 
            }

            answer++;
        }
        return answer;
}
}