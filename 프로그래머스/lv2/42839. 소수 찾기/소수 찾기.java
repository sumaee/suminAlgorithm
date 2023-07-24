import java.lang.*;
import java.util.*;

class Solution {
    
    static boolean[] visited;
    static char[] newNum;
    static Set<Integer> answer;
    
    public int solution(String numbers) {
        answer = new HashSet<>();

        visited = new boolean[numbers.length()];
        for (int i = 1; i <= numbers.length(); i++) {
            newNum = new char[i];
            permutation(0, i, numbers);
        }

        return answer.size();
    }

    private static void permutation(int n, int pick, String numbers) {
        if (n == pick) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pick; i++) {
                sb.append(newNum[i]);
            }

            if (answer.contains(Integer.parseInt(sb.toString()))) return;

            //소수 체크해서 소수라면 answer에 값 넣어주기
            if (checkPrime(sb.toString())) {
                answer.add(Integer.parseInt(sb.toString()));
            }

            return;
        }

        for (int i = 0; i < numbers.length(); i++) {
            if (visited[i]) continue;

            visited[i] = true;
            newNum[n] = numbers.charAt(i);
            permutation(n + 1, pick, numbers);
            visited[i] = false;
        }
    }

    private static boolean checkPrime(String newNum) {
        int checkNum = Integer.parseInt(newNum);

        if (checkNum == 0 || checkNum == 1) return false;

        for (int i = 2; i <= Math.sqrt(checkNum); i++) {
            if (checkNum % i == 0) return false;
        }
        return true;
    }
}