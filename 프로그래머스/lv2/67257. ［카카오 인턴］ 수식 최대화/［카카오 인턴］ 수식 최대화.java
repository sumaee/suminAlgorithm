import java.lang.*;
import java.util.*;

class Solution {
    
    static List<Long> num;
    static List<Character> opers;
    static char[] topOper, oper;
    static boolean[] visited;
    static long answer;
    
    public long solution(String expression) {
        num = new ArrayList<>();
        opers = new ArrayList<>();

        //숫자랑 연산자 나눠 넣기
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            //연산자라면 지금까지 연결했던 숫자 집어넣고 연산자도 넣기
            if (ch == '-' || ch == '+' || ch == '*') {
                opers.add(ch);
                num.add(Long.parseLong(sb.toString()));
                sb = new StringBuilder();
            }
            //숫자라면 연결
            else {
                sb.append(ch);
            }
        }
        num.add(Long.parseLong(sb.toString()));

        topOper = new char[3];
        oper = new char[]{'-', '*', '+'};
        visited = new boolean[3];
        answer = 0;
        perm(0, 3);
        return answer;
    }
     
    private static void perm(int idx, int limit) {
        //연산 순서 정했으면 계산 후 비교해서 넣기
        if (idx == limit) {
            long result = calculate(new ArrayList<>(num), new ArrayList<>(opers));
            answer = Math.max(answer, result);
            return;
        }

        for (int i = 0; i < limit; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            topOper[idx] = oper[i];
            perm(idx + 1, limit);
            visited[i] = false;
        }

    }

    private static long calculate(List<Long> num, List<Character> oper) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < oper.size(); j++) {
                if (oper.get(j) == topOper[i]) {
                    if (topOper[i] == '-') {
                        num.add(j, num.remove(j) - num.remove(j));
                    } else if (topOper[i] == '*') {
                        num.add(j, num.remove(j) * num.remove(j));
                    } else {
                        num.add(j, num.remove(j) + num.remove(j));
                    }
                    oper.remove(j--);
                }
            }
        }
        return Math.abs(num.get(0));
    }
}