import java.util.*;

class Solution {
    public int solution(int[] elements) {
        //중복을 걸러줄 set 함수 생성
        Set<Integer> set = new HashSet<>();
        
        //elements의 길이만큼 연속 부분 수열이 될 수 있으므로 돌리기
        for(int i=0; i<elements.length; i++){
            //연속 부분 수열의 길이만큼 돌리기 위한 인덱스
            for(int j=0; j<elements.length ; j++){
                //부분 수열 합치기
                int sum = 0;
                for (int k = 0; k < i; k++) {
                    if (j + k > elements.length - 1) {
                        sum += elements[j + k - elements.length];
                    } else {
                        sum += elements[j + k];
                    }
                }
                set.add(sum);
            }
            
        }
        
        int answer = set.size();
        
        
        return answer;
    }
}