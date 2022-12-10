import java.lang.*;
import java.util.*;

class Solution {
    
    boolean[] visited;
    
    public int solution(String begin, String target, String[] words) {
        int ans = Integer.MAX_VALUE;
        visited = new boolean[words.length];
        
        ans = dfs(begin, target, words, 0, ans);
        
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
    
    private int dfs(String begin, String target, String[] words, int cnt, int ans){
        //target과 단어가 같아지면 끝내기
        if(begin.equals(target)){
            return cnt;
        }
        
        for(int i=0; i<words.length; i++){
            //방문하지 않았고 한글자만 바뀐거라면 진행
            if(!visited[i] && checkWord(begin, words[i])){
              
                    visited[i]=true;
                    // dfs 진행후 나온 값과 기존의 ans 중 더 작은 값으로 갱신
                    ans= Math.min(ans, dfs(words[i], target, words, cnt+1, ans));
                    // 다음진행을 위해 false로 바꿔주기
                    visited[i]=false;
                }
                
            }
        
        //위의 과정이 다 끝났으므로 ans 반환
        return ans;
    }
    
    private boolean checkWord(String begin, String word){
        int difCnt = 0;
        for(int i=0; i<begin.length(); i++){
            if(begin.charAt(i) != word.charAt(i)){
                 difCnt++;
            }
        }
        // difCnt 가 1이면 한글자만 같은 것이므로 true를 return
        return difCnt == 1;
    }
}

