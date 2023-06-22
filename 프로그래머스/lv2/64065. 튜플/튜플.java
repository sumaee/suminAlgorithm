import java.lang.*;
import java.util.*;

class Solution {
    public int[] solution(String s) {
       HashMap<Integer, Integer> map = new HashMap<>();

        for (String str : s.replace("{", "").replace("}", "").split(",")) {
            int num = Integer.parseInt(str);
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        //우선 순위 큐에 담기
        PriorityQueue<Num> nums = new PriorityQueue<>((o1, o2) -> o2.cnt - o1.cnt);
        for (int key : map.keySet()) {
            nums.add(new Num(key, map.get(key)));
        }

        int[] answer = new int[map.size()];
        int idx = 0;
        while (!nums.isEmpty()) {
            answer[idx++] = nums.poll().num;
        }
        return answer;
    }
}

class Num{
    int num, cnt;
    
    public Num(int num, int cnt){
        this.num = num;
        this.cnt = cnt;
    }
}