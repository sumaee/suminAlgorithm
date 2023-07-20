import java.lang.*;
import java.util.*;

class Solution {
    static int[] parents;
    public int solution(int[] cards) {
        parents = new int[cards.length + 1];
        for (int i = 1; i < parents.length; i++) {
            parents[i] = i;
        }
        for (int i = 1; i <= cards.length; i++) {
            //합치기
            union(i, cards[i - 1]);
        }
        
        //부모가 같은 애들끼리 개수 찾기
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < parents.length; i++) {
            int parent = find(i);
            map.put(parent, map.getOrDefault(parent, 0) + 1);
        }
        
        //부모가 전부 같다면 0
        if (map.size() == 1) {
            return 0;
        }
        
        List<Integer> answer = new ArrayList<>();
        for (int key : map.keySet()) {
            answer.add(map.get(key));
        }

        //개수를 담은 리스트값 정렬
        answer.sort((o1, o2) -> o2 - o1);
        //제일 큰 숫자 2개를 곱한 것이 정답
        return answer.get(0) * answer.get(1);
    }
    
    private static void union(int a, int b) {
        parents[find(b)] = find(a);
    }

    private static int find(int a) {
        if (a == parents[a]) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }
}