class Solution {
    boolean[] visited;
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        int answer = 0;
        
        for(int i=0; i<n; i++){
            //방문하지 않은 곳이라면 dfs 실행
            if(!visited[i]){
                bfs(i, computers);
                answer++;                
            }
        }
      
        return answer;
    }
    
    public void bfs(int i, int[][] computers){
        visited[i] =true;
        
        for(int j=0; j<computers.length;j++){
            if(i!=j && computers[i][j]==1 && !visited[j]){
                bfs(j, computers);
            }
        }
    }
    
}