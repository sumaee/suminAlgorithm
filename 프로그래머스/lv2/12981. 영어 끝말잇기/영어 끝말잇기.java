import java.lang.*;
import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        //answer[0] : 틀린 사람
        //answer[1] : 틀린 차례
        int[] answer = new int[2];
        Arrays.fill(answer, 0);
        
        Set<String> set = new HashSet<>();
        //첫단어는 그냥 넣기
        set.add(words[0]);
        String check = words[0];
        int cnt = 0;
        for(int i=1; i<words.length; i++){
            //만약 이어 말한 단어가 전 사람이 말한 단어랑 이어지지 않거나
            //중복된 단어를 얘기 했다면
            if(words[i].charAt(0)!=check.charAt(check.length()-1) || set.contains(words[i])){
                answer[0] = i%n+1;
                answer[1] = i/n+1;
                break;
            }
            
            //이어말하기를 성공했다면 set에 집어넣고 check할 단어를 이어말한 단어로 바꾸기
            set.add(words[i]);            
            check = words[i];
        }
         return answer;
    }
   
}