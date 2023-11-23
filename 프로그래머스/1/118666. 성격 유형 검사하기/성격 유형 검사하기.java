import java.lang.*;
import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        String[] results = {"RT", "CF", "JM", "AN"};

        StringBuilder sb = new StringBuilder();
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < survey.length; i++) {
            char agree = survey[i].charAt(1);
            char disagree = survey[i].charAt(0);
            //점수 판단
            //4보다 작다면 비동의
            if (choices[i] < 4) {
                map.put(disagree, map.getOrDefault(disagree, 0) + Math.abs(4 - choices[i]));
            }
            //4보다 크다면 동의
            else if (choices[i] > 4) {
                map.put(agree, map.getOrDefault(agree, 0) + Math.abs(4 - choices[i]));
            }
        }

        //각 지표의 최고 점수 확인
        for (String result : results) {
            int agreeCnt = map.getOrDefault(result.charAt(1), 0);
            int disagreeCnt = map.getOrDefault(result.charAt(0), 0);
            //두개의 점수가 같다면 사전순
            if (agreeCnt == disagreeCnt) {
                sb.append(result.charAt(1) < result.charAt(0) ? result.charAt(1) : result.charAt(0));
                continue;
            }

            sb.append(agreeCnt > disagreeCnt ? result.charAt(1) : result.charAt(0));
        }

        return sb.toString();
    }
}