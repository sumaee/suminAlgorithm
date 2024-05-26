import java.lang.*;
import java.util.*;

class Solution {
    static int answer;
    
    public int solution(int[] info, int[][] edges) {
        boolean[] visited = new boolean[info.length];
        int sheepCnt = 0;
        int wolfCnt = 0;
        dfs(0, info, edges, visited, sheepCnt, wolfCnt);

        return answer;
    }
    
    private static void dfs(int idx, int[] info, int[][] edges, boolean[] visited, int sheepCnt, int wolfCnt) {
        visited[idx] = true;

        if (info[idx] == 0) {
            sheepCnt++;
            answer = Math.max(answer, sheepCnt);
        } else {
            wolfCnt++;
        }

        if (wolfCnt >= sheepCnt) {
            return;
        }

        for (int[] edge : edges) {
            if (visited[edge[0]] && !visited[edge[1]]) {
                boolean[] tempVisited = new boolean[info.length];
                for (int i = 0; i < visited.length; i++) {
                    tempVisited[i] = visited[i];
                }

                dfs(edge[1], info, edges, tempVisited, sheepCnt, wolfCnt);
            }
        }
    }
}