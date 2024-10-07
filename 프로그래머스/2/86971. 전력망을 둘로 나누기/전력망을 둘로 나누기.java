import java.lang.*;
import java.util.*;

class Solution {
    static boolean[] visited;
    static int cnt;
    static List<Integer>[] line;
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        for(int i=0; i<wires.length ; i++){
            
            line = new List[n+1];
            
            for(int j=0 ; j<=n ; j++){
                line[j] = new ArrayList<>();
            }
            
            int idx = 0;
            for(int j=0; j<wires.length ; j++){
                if(i == j) {
                    idx = wires[j][1];
                    continue;
                }
                
                line[wires[j][0]].add(wires[j][1]);
                line[wires[j][1]].add(wires[j][0]);
            }
            
            cnt = 1;
            visited = new boolean[n+1];
            dfs(idx);
            answer = Math.min(answer, Math.abs(n-2*cnt));
        }
        return answer;
    }
    
    private static void dfs(int idx){
        visited[idx] = true;
        
        for(int nxt : line[idx]){
            if(visited[nxt]) continue;
            cnt++;
            dfs(nxt);
        }
    }
}