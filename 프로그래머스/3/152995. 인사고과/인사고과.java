import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int[] wanho = scores[0];

        //점수표 근무태도 점수 기준 내림차순 정리, 같다면 동료평가 점수 오름차순 정리
        Arrays.sort(scores, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);

        int maxScore = 0;
        int wanhoSum = wanho[0] + wanho[1];
        int rank = 1;
        for (int[] score : scores) {
            //동료평가 점수가 높은 사람이 있다면 탈락 대상
            if (score[1] < maxScore) {
                //그게 완호라면 -1
                if (score[0] == wanho[0] && score[1] == wanho[1]) {
                    return -1;
                }
            }
            //크다면 갱신
            else {
                maxScore = Math.max(maxScore, score[1]);
                //완호점수보다 크다면 완호의 등수 밀림
                if (score[0] + score[1] > wanhoSum) {
                    rank++;
                }
            }
        }

        return rank;
    }
}