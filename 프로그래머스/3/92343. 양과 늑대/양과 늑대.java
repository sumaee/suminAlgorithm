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
        //해당 위치가 양이라면 answer 갱신
        if (info[idx] == 0) {
            sheepCnt++;
            answer = Math.max(answer, sheepCnt);
        } else {
            wolfCnt++;
        }

        //지금까지 모은 양의 수보다 늑대의 수가 많다면 끝냄
        if (wolfCnt >= sheepCnt) {
            return;
        }

        //연결된 곳 돌기
        for (int[] edge : edges) {
            
            //부모 노드는 방문했고 자식노드를 방문 안한경우 돌기
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