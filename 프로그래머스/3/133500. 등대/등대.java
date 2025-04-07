import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        int[] cntArr;
        int[][] lighthouseTmp;
        Set<Integer> edges = new HashSet<>();
        Set<Integer> on = new HashSet<>();
        
        for(int i=0; i<n; i++){
            cntArr = new int[n+1];
            lighthouseTmp = new int[lighthouse.length][2];
             // 0, 4, 2, 1, 1, 2, 3, 1, 1, 2, 1
            for(int j=0 ;j<lighthouse.length ; j++){
                cntArr[lighthouse[j][0]]++;
                cntArr[lighthouse[j][1]]++;
            }
            
           // edge : 3, 4, 7, 8, 10
            for(int j=0 ;j<cntArr.length ;j++){
                //연결된 등대가 하나라면 모서리 등대, 이 등대랑 연결된 등대는 꼭 껴야함
                if(cntArr[j] == 1) edges.add(j);
            }
            
            // on : 1, 6, 9
            // 모서리 등대랑 연결된 등대는 꼭 켜야함
            for(int j=0; j<lighthouse.length ;j++){
                if(edges.contains(lighthouse[j][0])){
                    on.add(lighthouse[j][1]);
                }else if(edges.contains(lighthouse[j][1])){
                    on.add(lighthouse[j][0]);
                }
            }
            
            // 켜진 등대의 영향을 받지 않은 등대 찾기
            int cnt = 0;
            for(int j=0; j<lighthouse.length ;j++){
                if(!on.contains(lighthouse[j][0]) && !on.contains(lighthouse[j][1])){
                    lighthouseTmp[cnt] = lighthouse[j];
                    cnt++;
                }
            }
            
            if(cnt == 0){
                break;
            }
            
            if(cnt == 1){
                answer++;
                break;
            }
            
            lighthouse = new int[cnt][2];
            for(int j=0 ; j<cnt ;j++){
                lighthouse[j] = lighthouseTmp[j];
            }
            
        }
        return on.size() + answer;
    }
}