import java.lang.*;
import java.util.*;

class Solution {
    boolean[] visited;
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        int ans = Integer.MAX_VALUE;
        visited = new boolean[words.length];
        int cnt =0;
        answer = dfs(begin, target, words, cnt, ans);
        
        return answer == ans ? 0 : answer;
    }
    
    private int dfs(String begin, String target, String[] words, int cnt, int ans){
        if(begin.equals(target)){
            return cnt;
        }
        
        for(int i=0; i<words.length; i++){
            //방문하지 않았다면 한글자만 다른지 확인
            if(!visited[i]){
                int difCnt = 0;
                for(int j=0; j<begin.length(); j++){
                    if(begin.charAt(j)!=words[i].charAt(j)){
                        difCnt++;
                    }
                }
                
                //만약 한글자만 같다면
                if(difCnt==1){
                    visited[i]=true;
                    ans= Math.min(ans, dfs(words[i], target, words, cnt+1, ans));
                    visited[i]=false;
                }
                
            }
        }
        
        //위의 과정이 다 끝났으므로 ans 반환
        return ans;
    }
}

