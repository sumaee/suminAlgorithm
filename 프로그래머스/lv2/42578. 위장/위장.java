import java.lang.*;
import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> kind = new HashMap<>();
        
        //의상의 종류에 따른 개수 세기
        for(String[] cloth : clothes)
            kind.put(cloth[1], kind.getOrDefault(cloth[1], 0)+1);

        //의상의 조합은 각 (의상의 개수 + 1)들을 곱하면 됨
        //+1을 하는 이유는 해당 의상의 종류를 안입을 경우
        int answer = 1;
        for(String key : kind.keySet())
            answer *= (kind.get(key)+1);
        
        //최소 한 개 이상의 의상은 입으므로 아예 안입는 경우의 수를 빼주기
        answer-=1;
        return answer;
    }
}