import java.lang.*;
import java.util.*;

class Solution {
    //{a, b} {b, a}는 같은 것이므로 방지하기 위해 set안에 set 넣어줌
    Set<Set<String>> answer;
    public int solution(String[] user_id, String[] banned_id) {    
        answer = new HashSet<>();
        checkId(new LinkedHashSet<>(), user_id, banned_id); 
        return answer.size();
    }
    
    private void checkId(Set<String> set, String[] user_id, String[] banned_id){
        //set에 담길 길이는 제재 당한 아이디배열의 길이만큼만 가능하므로
        if(set.size()==banned_id.length){
            //제재 아이디와 같은지 확인과정 후 answer에 넣음
            if(check(set, banned_id))
                answer.add(new HashSet<>(set));
        
            return;
        }
        
        //id 조합 만들기
        for(String id : user_id){
            if(!set.contains(id)){
                set.add(id);
                checkId(set, user_id, banned_id);
                set.remove(id);
            }
        }
    }
    
    private boolean check(Set<String> set, String[] banned_id){
        int idx=0;

        for(String id : set){
            String ban = banned_id[idx];
            //만약 id의 길이가 제재 아이디의 길이와 다르면 틀린것이므로 패스
            if(id.length() != banned_id[idx].length())
                return false;
            
            //id를 돌면서 문자가 같은지 확인
            for(int i=0; i<banned_id[idx].length(); i++){
                if(ban.charAt(i)=='*')
                    continue;
                
                if(banned_id[idx].charAt(i) != id.charAt(i))
                    return false;
            }
            
            idx++;
        }
        return true;
    }
}

