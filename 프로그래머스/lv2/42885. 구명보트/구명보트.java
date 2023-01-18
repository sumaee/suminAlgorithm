import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        //가벼운 사람과 무거운 사람을 태워 무거운 사람 먼저 빼내기 위한 정렬
        Arrays.sort(people);
        
        int light=0;
        int heavy=people.length-1;
        
        while(light<=heavy) {
            int sum = people[light]+people[heavy];
            // 보트에 두명씩 밖에 못탄다고 했으므로 
		     // 양 끝부터 탐색을 시작해 가장 무거운 사람과 가장 가벼운 사람의 합이
		    // limit를 넘지 않으면 보트를 태우고, 인덱스를 한칸씩 안쪽으로 이동
		    // limit를 넘으면 무거운 사람을 혼자 보내고, max_index만 이동
		    // 모든 원소를 탐색할 때 까지 반복
            if(light!=heavy-- && sum<=limit) light++;
            answer++;
        }
        return answer;
    }
}