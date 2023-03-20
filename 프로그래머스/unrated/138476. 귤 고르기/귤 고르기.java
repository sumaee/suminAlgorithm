import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int orange : tangerine)
            map.put(orange, map.getOrDefault(orange, 0)+1);
        
        //서로 다른 종류의 수를 최소화 하고 싶다 했으므로
        //양이 많은 것 부터 집어넣으면 됨
        //map 의 value를 기준으로 내림차순 정리
        List<Map.Entry<Integer, Integer>> check = new LinkedList<>(map.entrySet());
        check.sort(((o1, o2) -> map.get(o2.getKey()) - map.get(o1.getKey())));
        
        //check를 돌면서 k 값에서 빼줌 k가 1보다 작아진 순간이 정답이 됨
        int answer = 0;
        for(Map.Entry<Integer, Integer> entry : check){
            answer++;
            k-=entry.getValue();
            if(k<1)
                break;
        }
        return answer;
    }
}
