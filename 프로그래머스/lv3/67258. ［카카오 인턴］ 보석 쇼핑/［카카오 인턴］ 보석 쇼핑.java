import java.lang.*;
import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        //중복되는 보석없이 담을 것 하나 생성
        Set<String> set = new HashSet<>();
        for(int i=0; i<gems.length; i++){
            set.add(gems[i]);
        }
        
        //담아야 하는 보석 개수
        int limit = set.size();
        
        //진열장 길이
        int len = Integer.MAX_VALUE;
        
        //담긴 보석의 개수를 판단하기 위한 map 생성
        Map<String, Integer> map = new HashMap<>();
        int start = 0;
        for(int i=0 ; i<gems.length; i++){
            //여기가 시작점
            map.put(gems[i], map.getOrDefault(gems[i], 0)+1);
            
            //넣다가 넣은 것이 1개 초과가 되면 1개가 될때까지 시작점 끌어당기기
            while(map.get(gems[start])>1){
                map.put(gems[start], map.get(gems[start])-1);
                start++;
            }
            
            //보석이 모두 담겼다면 확인
            if(map.size()==limit){
                //현재 진열장 길이보다 짧다면 갱신
                if(len>(i-start)){
                    len = i-start;
                    answer[0] = start +1;
                    answer[1] = i +1;
                }
            }
            
        }
        
        
        return answer;
    }
}