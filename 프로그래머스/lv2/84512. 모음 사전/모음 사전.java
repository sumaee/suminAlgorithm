import java.lang.*;
import java.util.*;

class Solution {
    String[] alp = {"A", "E", "I", "O", "U"};
    public int solution(String word) {
        int answer = 1;
        List<String> list = new ArrayList<>();
        
        //모든 경우의 수를 돌며 사전값 list 에 저장
        for(int i=0; i<alp.length; i++)
            dfs(list, alp[i]);
        
        //저장된 리스트를 돌며 word 값이면 break
        for(String str : list){
            if(str.equals(word)) break;
            answer++;
            
        }
        return answer;
    }
    
    void dfs(List<String> list, String str){
        if(str.length() > 5){
            return;
        }
        
        if(!list.contains(str)){
            list.add(str);
        }
        
        for(int i=0; i<alp.length ; i++){
            dfs(list, str+alp[i]);
        }
    }
}