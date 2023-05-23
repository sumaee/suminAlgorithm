import java.util.*;
import java.lang.*;

class Solution {
    public int solution(int x, int y, int n) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> nxt;

        set.add(x);
        int answer = 0;

        while (!set.isEmpty()) {
            nxt = new HashSet<>();
            if (set.contains(y)) {
               return answer;
            }

            for (int num : set) {
                if (num + n <= y) {
                    nxt.add(num + n);
                }
                if (num * 2 <= y) {
                    nxt.add(num * 2);
                }
                if (num * 3 <= y) {
                    nxt.add(num * 3);
                }
            }

            set = nxt;
            answer++;


        }
        return -1;
    }
}