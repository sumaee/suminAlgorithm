import java.util.*;

class Solution {
    int answer;
    boolean [] check;
    public int solution(int k, int[][] dungeons) {
        check = new boolean[dungeons.length]; 
        dfs(k, 0, dungeons);
        return answer;
    }
    
    public void dfs(int k, int cnt, int[][] dungeons){
        
        for(int i=0 ; i<dungeons.length ; i++){
            //방문하지 않았고 현재 피로도가 필요 피로도보다 크다면 진행
            if(!check[i] && k>=dungeons[i][0]){
                check[i]=true;
                dfs(k-dungeons[i][1], cnt+1, dungeons);
                check[i]=false;
            }
        }
        
        answer = Math.max(answer, cnt);
    }
}