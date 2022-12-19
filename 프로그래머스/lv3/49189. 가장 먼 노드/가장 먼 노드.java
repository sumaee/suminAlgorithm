import java.util.*;
import java.lang.*;

class Solution {
    int[] dist;
    List<Integer>[] graph;
    boolean[] visited;
    int max;
    public int solution(int n, int[][] edge) {
        //1번부터 얼마나 떨어져있는지 거리를 담을 배열
        dist = new int[n+1];
        //연결관계를 담을 리스트배열
        graph = new ArrayList[n+1];
        //방문처리를 할 배열
        visited = new boolean[n+1];
        
        //초기 설정
        for(int i=1; i<=n ; i++){
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }
        
        //연결관계 지정
        for(int i=0 ; i<edge.length*2; i+=2){
            graph[edge[i/2][i%2]].add(edge[i/2][i%2+1]);
            graph[edge[i/2][i%2+1]].add(edge[i/2][i%2]);
        }
        
       //다잌스트라를 진행하며 제일 긴 간선의 길이를 저장하기 위한 변수
        max = -1;
         //1번부터 시작
        dijkstra(1);
        int answer = 0;
        for(int i=1; i<=n; i++){  
            if(dist[i]==max) answer++;
        }
        
        return answer;
    }
    
    private void dijkstra(int st){
        Queue<Integer> que = new LinkedList<>();
        
        que.offer(st);
        dist[st] = 0;
        while(!que.isEmpty()){
            int idx = que.poll();
            
            if(visited[idx]){
               continue; 
            }
            
            visited[idx] = true;
            
            for(int i : graph[idx]){
                if(!visited[i]){
                    if(dist[i]>dist[idx]+1){
                        dist[i] = dist[idx]+1;
                        max = Math.max(max, dist[i]);
                        que.add(i);
                    }
                }
            }
        }
    }
}