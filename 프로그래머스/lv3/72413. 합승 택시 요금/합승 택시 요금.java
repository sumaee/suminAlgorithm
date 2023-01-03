import java.util.*;
import java.lang.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] fare = new int[n+1][n+1];
        
        //우선 간선 정리
        for(int i=0; i<fares.length;i++){
            int st = fares[i][0];
            int ed = fares[i][1];
            
            fare[st][ed] = fares[i][2];
            fare[ed][st] = fares[i][2];
        }
        
        //출발지에서 시작해서 각 노드들까지 같이가는 거리들 구하기
        int[] couple = prim(s, fare, n);
        
        int answer = Integer.MAX_VALUE;
        //각 위치에서 따로 가는 거리 구해서 같이 가는 거리 더해서 최솟값 찾기
        for(int i=1; i<=n; i++){
            int[] solo = prim(i, fare, n);
            int result = couple[i] + solo[a] + solo[b];
            
            answer = Math.min(result, answer);
        }
        return answer;
    }
    
    private int[] prim(int st, int[][] fare, int n){
        PriorityQueue<Node> que = new PriorityQueue<>((o1, o2)-> o1.cost - o2.cost);
        boolean[] visited = new boolean[n+1];
        int[] dist = new int[n+1];
        //초기 값 최대로 셋팅
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        //시작점 0으로 셋팅
        dist[st] = 0;
        que.offer(new Node(0, st));
        
        while(!que.isEmpty()){
            Node curr = que.poll();
            
            //방문했다면 패스
            if(visited[curr.idx]) continue;
            
            //방문안했다면 방문처리 하고 거리 갱신
            visited[curr.idx] = true;
            for(int i=0; i<=n; i++){
                //현재 위치(=시작점)라면 패스
                if(fare[curr.idx][i]==0) continue;
                
                if(dist[i]>dist[curr.idx]+fare[curr.idx][i]){
                    dist[i]=dist[curr.idx]+fare[curr.idx][i];
                    que.offer(new Node(dist[i], i));
                }
            }
        }
        
        return dist;
    }
    
    private static class Node{
        int cost, idx;
        
        public Node(int cost, int idx){
            this.cost = cost;
            this.idx = idx;
        }
    }
}