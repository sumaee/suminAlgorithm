import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        //타겟 끝을 기준으로 오름차순 정렬 같다면 끝지점으로 오름차순 정렬
        Arrays.sort(targets, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);

        //끝지점에 요격지 설치
        int answer = 1;
        int target = targets[0][1] - 1;
        for (int i = 1; i < targets.length; i++) {
            if (targets[i][0] > target) {
                target = targets[i][1] - 1;
                answer++;
            }
        }
        return answer;
    }
}