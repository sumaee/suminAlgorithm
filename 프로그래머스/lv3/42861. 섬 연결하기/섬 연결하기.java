import java.lang.*;
import java.util.*;

class Solution {
    int[] parent;
    
    public int solution(int n, int[][] costs) {
        
        //건설비용을 기준으로 오름차순 정렬해서 이어야함
        Arrays.sort(costs, (int[] o1, int[]o2)-> o1[2]-o2[2]);
        parent = new int[n];
        
        //초기 설정
        for(int i=0; i<n; i++){
            parent[i]=i;
        }
        
        int answer = 0;
        for(int[] location : costs){
            int stP = find(location[0]);
            int edP = find(location[1]);
            
            // 부모 노드가 다르다면 연결시고 비용 추가
            if(stP != edP){
                answer += location[2];
                parent[edP] = stP; // 두 노드가 같다는 연결되었다는 것을 표시
            }
        }
        
        
        return answer;
    }
    
    private int find(int idx){
        if(parent[idx]==idx) return idx;
        return parent[idx]=find(parent[idx]);
    }
    
    
}