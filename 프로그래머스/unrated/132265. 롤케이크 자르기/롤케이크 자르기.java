import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int[] topping) {
        HashSet<Integer> my = new HashSet<>();
        HashMap<Integer, Integer> brother = new HashMap<>();

        for (int j : topping) {
            brother.put(j, brother.getOrDefault(j, 0) + 1);
        }

        int answer = 0;
        for (int i = 0; i < topping.length; i++) {
            int top = topping[i];
            //동생 토핑을 빼서 내걸로 넣기
            //동생토핑을 뺏을 때 0이라면 map에서 지우기
            my.add(top);
            brother.put(top, brother.get(top) - 1);
            if (brother.get(top) == 0) {
                brother.remove(top);
            }
            //만약 나와 동생의 토핑의 개수가 같다면 answer++
            if (my.size() == brother.size()) {
                answer++;
            }
        }

        return answer;
    }
}
