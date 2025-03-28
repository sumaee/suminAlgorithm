import java.lang.*;
import java.util.*;
class Solution {
    static int totalNode = 0;
    static List<Integer>[] graph;
    static int[] inLine;
    static boolean[] visited;
    public int[] solution(int[][] edges) {
        for(int[] edge : edges){
            totalNode = Math.max(Math.max(edge[0], edge[1]), totalNode);
        }
        
        visited = new boolean[totalNode+1];
        graph = new ArrayList[totalNode+1];
        inLine = new int[totalNode+1];
        for(int i=0; i<=totalNode ; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int[] edge : edges){
            int start = edge[0];
            int end = edge[1];
            
            graph[start].add(end);
            inLine[end]++;
        }
        
        int center = findCenter();
        for(int end : graph[center]){
            inLine[end]--;
        }
        
        int[] answer = new int[4];
        answer[0] = center;
        for(int edge : graph[center]){
            if(!visited[edge]){
                answer[findGraph(edge)]++;
            }
        }
        
        return answer;
    }
    
    private static int findGraph(int start){
        Queue<Integer> que = new LinkedList<>();
        que.offer(start);
        visited[start] = true;
        
        while(!que.isEmpty()){
            int edge = que.poll();
            if(graph[edge].size() == 2 && inLine[edge]== 2){
                return 3;
            }else if(graph[edge].isEmpty()){
                return 2;
            }else{
                for(int end : graph[edge]){
                    if(!visited[end]){
                        que.offer(end);
                        visited[end] = true;
                    }
                }
            }
        }
        
        return 1;
    }
    
    private static int findCenter(){
        for(int i=1 ; i<=totalNode ; i++){
            if(graph[i].size() >=2 && inLine[i] == 0){
                return i;
            }
        }
        return -1;
    }
  
    
}